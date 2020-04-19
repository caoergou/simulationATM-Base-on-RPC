package mybank;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RMIClient {
    private static final int REGISTRY_PORT = 1099;
    private static final String REMOTE_HOST = "localhost";
    private static final String SERVICE_URL = "rmi://"+REMOTE_HOST+":"+REGISTRY_PORT;
    public static boolean ClientLogin(String id,String password){
        boolean ifLogin = false;
        try {
            RemoteService handler=(RemoteService) Naming.lookup(SERVICE_URL+"/Account");
            ifLogin = handler.login(id,password);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return ifLogin;
    }
    public static double ClientInquryMoney(String id){
        double money = 0.00;
        try {
            RemoteService handler=(RemoteService) Naming.lookup(SERVICE_URL+"/Account");
            money = handler.InquryMoney(id);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return money;
    }
    public static String[] ClientInquryOperation(String id){
        String[] oper = new String[100];
        try {
            RemoteService handler=(RemoteService) Naming.lookup(SERVICE_URL+"/Account");
            oper = handler.InquryOperation(id);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return oper;
    }

    public static int OutMoney(String id,int money) {
        int result = 1;//1成功，2余额不足，3账户被冻结
        try {
            RemoteService handler=(RemoteService) Naming.lookup(SERVICE_URL+"/Account");
            result = handler.OutMoney(id,money);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static int InMoney(String id, int money) {
        int result = 1;//1成功,3账户被冻结
        try {
            RemoteService handler=(RemoteService) Naming.lookup(SERVICE_URL+"/Account");
            result = handler.InMoney(id,money);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void ClientAddMoney(){

    }
    public static void main(String[] args)throws Exception {

        LoginGui loginGui = new LoginGui();
    }
}

