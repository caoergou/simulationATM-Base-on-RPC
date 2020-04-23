package server;

import java.util.List;

/**
 * Created by Ergou on 20/4/23.
 */
public interface RPCService {
     int login(String id, String password);

     double InquryMoney(String id);

     List<Object[]> InquryOperation(String id);

     int OutMoney(String id, int money);

     int InMoney(String id, int money);
}
