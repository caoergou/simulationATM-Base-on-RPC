package client;

import java.io.*;
import java.net.Socket;

/**
 * Created by lyric on 18/3/27.
 */

public class Connector {

    private String host;
    private int port;
    private Socket skt;
    private InputStream is;
    private ObjectInputStream ois;
    private OutputStream os;
    private ObjectOutputStream oos;






    public Connector(String host, int port) throws Exception {
        this.host = host;
        this.port = port;
        connect(host, port);
    }

    // 发送对象方法
    public void send(Object obj) throws Exception {
        oos.writeObject(obj);
    }

    // 接收对象方法
    public Object receive() throws Exception {
        return ois.readObject();
    }

    // 建立与远程服务器的连接
    public void connect() throws Exception {
        connect(host, port);
    }

    // 建立与远程服务器的连接
    public void connect(String host, int port) throws Exception {

        skt = new Socket(host, port);
        os = skt.getOutputStream();
        oos = new ObjectOutputStream(os);
        is = skt.getInputStream();

        ois = new ObjectInputStream(is);
    }

    // 关闭连接
    public void close() {
        try {
            ois.close();
            oos.close();
            skt.close();
        } catch (Exception e) {
            System.out.println(" Conector.close :" + e);
        }
    }
}
