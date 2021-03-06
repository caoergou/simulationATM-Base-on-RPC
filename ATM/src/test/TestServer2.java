package test;

import java.io.IOException;


import server.*;


public class TestServer2 {

    private static class ServerThread implements Runnable {
        private String host;
        private int port ;
        private final static String CLASS_PATH="server.";
        
        public ServerThread(String h,int p){
            this.host=h;
            this.port=p;
        }

        @Override
        public void run() {
            try {
                Center server = new ServerCenter(host, port);
                server.register(CLASS_PATH+"RPCService",new RPCServiceImpl());
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread server2 = new Thread(new ServerThread(ServerConfig.backupHost,ServerConfig.backupPort));
        server2.start();
    }
}
