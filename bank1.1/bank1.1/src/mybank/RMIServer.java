package mybank;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    private static final int REGISTRY_PORT = 1099;
    private static final String REMOTE_HOST = "localhost";
    private static final String SERVICE_URL = "rmi://"+REMOTE_HOST+":"+REGISTRY_PORT;
    public static void main(String[] args) throws RemoteException {
        RemoteService userHandler;
        try {
            LocateRegistry.createRegistry(REGISTRY_PORT);
            userHandler = new RMIService();
            Naming.rebind(SERVICE_URL+"/Account", userHandler);
            System.out.println("rmi server is ready ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}