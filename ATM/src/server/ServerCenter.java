package server;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.reflect.Proxy;

import model.*;

public class ServerCenter implements Center {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final HashMap<String, Object> serviceRegistry = new HashMap<>();

    private static boolean isRunning = false;

    private int port = 8080;

    private String host = "127.0.0.1";

    public ServerCenter() {
    }

    public ServerCenter(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    @Override
    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf("服务器启动完成，监听地址为%s\n", serverSocket.getLocalSocketAddress());
        System.out.printf("center.start\n");
        isRunning = true;
        try {
            while (true) {
                executor.execute(new ServiceTask(serverSocket.accept()));
            }
        } finally {
            serverSocket.close();
        }

    }

    @Override
    public void register(String className, Object impl) {
        serviceRegistry.put(className, impl);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPort() {
        return port;
    }

    private static class ServiceTask implements Runnable {
        Socket client = null;

        public ServiceTask(Socket client) {
            this.client = client;
        }

        public void run() {
            OutputStream out = null;
            ObjectOutputStream oos = null;
            InputStream in = null;
            ObjectInputStream ois = null;

            try {

                out = client.getOutputStream();
                oos = new ObjectOutputStream(out);
                in = client.getInputStream();
                ois = new ObjectInputStream(in);

                // 2.将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
                RemoteCall remotecallobj = (RemoteCall) ois.readObject();

                System.out.println(Thread.currentThread().getName() + " accepted: " + remotecallobj);
                remotecallobj = invoke(remotecallobj);

                oos.writeObject(remotecallobj);
                ois.close();
                oos.close();
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        public RemoteCall invoke(RemoteCall call) throws Throwable {
            Object result = null;
            try {
                String className = call.getClassName();
                String methodName = call.getMethodName();
                Object[] params = call.getParams();
                Class<?> classType = Class.forName(className);
                Class<?>[] paramTypes = call.getParamTypes();
                Method method = classType.getMethod(methodName, paramTypes);
                Object remoteObject = ProxyHandler.getProxy(RPCService.class);
                if (remoteObject == null) {
                    throw new Exception(className + " 的远程对象不存在");
                } else {
                    result = method.invoke(remoteObject, params); 
                }
            } catch (Exception e) {
                System.out.println("服务器执行错误：" + e.getMessage());
            }
            call.setResult(result);
            return call;
        }

    }

}
