package server;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.mysql.jdbc.Connection;

public class ProxyHandler implements InvocationHandler {

	static String beforeMethod = "";
	static String afterMethod = "";

	private RPCService object;

	public ProxyHandler(RPCService object) {
		this.object = object;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(final Class<T> classType) {

		InvocationHandler handler = (proxy, method, args) -> {
			if (ServerConfig.isEnableCheck) {
				if (method.getName().equals("login")) {
					if (ServerConfig.lockedAccount.contains((String) args[0])) {
						return -1;
					}
				}
			}
			RPCServiceImpl service = new RPCServiceImpl();
			Object result = method.invoke(service, args);

			if (ServerConfig.isAllowLogging) {
				System.out.println(args[0] + "执行了" + method.getName() + "操作");
			}
			if (ServerConfig.isEnableCheck) {
				if (method.getName().equals("login") && (int) result == 0) {
					if (!ServerConfig.ErrorCount.containsKey((String) args[0])) {
						ServerConfig.ErrorCount.put((String) args[0], 1);
						result = 2;
					} else if (ServerConfig.ErrorCount.get(args[0]) < 2) {
						ServerConfig.ErrorCount.put((String) args[0], 2);
						result = 1;
					} else {
						result = -1;
					}
				}
			}
			return result;
		};
		return (T) Proxy.newProxyInstance(classType.getClassLoader(), new Class<?>[] { classType }, handler);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 处理目标方法
		Object result = method.invoke(object, args);

		return result;
	}

}
