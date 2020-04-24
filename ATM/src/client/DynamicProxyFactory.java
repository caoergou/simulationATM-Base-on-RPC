package client;

import java.lang.reflect.*;
import model.*;

/**
 * Created by lyric on 18/3/27.
 */
// 动态代理类
public class DynamicProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class<T> classType) {

        InvocationHandler handler = (proxy, method, args) -> {
            Connector connector = null;
            RemoteCall call;
            try {
                try {
                    connector = new Connector(ClientConfig.mainHost, ClientConfig.mainPort);
                    System.out.println("使用服务器："+ClientConfig.mainHost+":"+ClientConfig.mainPort);
                } catch (Exception e) {
                    try{
                        connector = new Connector(ClientConfig.backupHost, ClientConfig.backupPort);
                        System.out.println("主服务器不可用，使用备用服务器："+ClientConfig.backupHost+":"+ClientConfig.backupPort);
                    }catch (Exception ee){
                        System.out.println("备用服务器不可用，即将退出...");
                        System.exit(0);
                    }
                    
                }
                call = new RemoteCall(classType.getName(), method.getName(), method.getParameterTypes(), args);
                connector.send(call);
                call = (RemoteCall) connector.receive();
            } finally {
                if (connector != null)
                    connector.close();
            }
            return call.getResult();
        };
        return (T) Proxy.newProxyInstance(classType.getClassLoader(), new Class<?>[] { classType }, handler);
    }

}
