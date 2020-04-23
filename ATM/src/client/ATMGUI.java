package client;

// import java.lang.reflect.UndeclaredThrowableException;
// import java.io.ConnectException;
// import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import server.RPCService;

public class ATMGUI implements Runnable {
    private static RPCService service;
    // private static Boolean backup = false;

    public ATMGUI() {}

    public void run() {
        service = DynamicProxyFactory.getProxy(RPCService.class);
        LoginGUI loginGui = new LoginGUI();
    }

    public static int ClientLogin(String id, String password) {
        int result = 3;// 0成功，-1 账号被锁定，1 2 错误机会
        try {
            result = service.login(id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static double ClientInquryMoney(String id) {
        double money = 0.00;
        try {
            money = service.InquryMoney(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return money;
    }

    public static List<Object[]> ClientInquryOperation(String id) {
        List<Object[]> oper = new ArrayList<>();
        try {
            oper = service.InquryOperation(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oper;
    }

    public static int OutMoney(String id, int money) {
        int result = 1;// 1成功，2余额不足，3账户被冻结
        try {
            result = service.OutMoney(id, money);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int InMoney(String id, int money) {
        int result = 1;// 1成功，2余额不足，3账户被冻结
        try {
            result = service.InMoney(id, money);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // public static void migrateToBackup() {
    //     System.out.println("main server error, changing to backup server");
    //     backup = true;
    //     service = DynamicProxyFactory.getProxy(RPCService.class, Config.backupHost, Config.backupPort);
    // }

}
