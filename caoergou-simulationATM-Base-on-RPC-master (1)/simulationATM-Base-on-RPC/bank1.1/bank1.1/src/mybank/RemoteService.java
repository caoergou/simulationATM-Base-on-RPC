package mybank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteService extends Remote {
    boolean login(String id, String password) throws RemoteException;
    double InquryMoney(String id) throws RemoteException;
    List<Object[]> InquryOperation(String id) throws RemoteException;
    int OutMoney(String id, int money) throws RemoteException;
    int InMoney(String id, int money) throws RemoteException;
}
