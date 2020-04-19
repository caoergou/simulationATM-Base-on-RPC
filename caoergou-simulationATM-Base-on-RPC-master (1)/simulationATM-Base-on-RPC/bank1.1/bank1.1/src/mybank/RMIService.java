package mybank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIService extends Account implements RemoteService {
    protected RMIService() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
        UnicastRemoteObject.exportObject(this,1099);
    }
    public boolean login(String id,String password) {
        Account acc=new Account();
        return acc.login(id,password);
    }
    public double InquryMoney(String id) {
        Account acc=new Account();
        return acc.InquryMoney(id);
    }
    public Object[][] InquryOperation(String id) {
        Account acc=new Account();
        return acc.InquryOperation(id);
    }
    public int OutMoney(String id, int money){
        Account acc=new Account();
        return acc.OutMoney(id,money);
    }
    public int InMoney(String id, int money){
        Account acc=new Account();
        return acc.InMoney(id,money);
    }

}
