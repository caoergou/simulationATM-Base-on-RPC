package test;

import client.ATMGUI;

public class TestClient {
    public static void main(String[] args) {
        Thread atm = new Thread(new ATMGUI());
        atm.run();
    }
}
