package server;

import java.util.List;

/**
 * Created by lyric on 18/3/27.
 */
public class RPCServiceImpl implements  RPCService {
    private Account acc;

    public RPCServiceImpl(){
        this.acc=new Account();
    }

    @Override
    public int login(String id,String password) {
        
        return acc.login(id,password);
    }
    
    @Override
    public double InquryMoney(String id) {
        Account acc=new Account();
        return acc.InquryMoney(id);
    }

    @Override
    public List<Object[]> InquryOperation(String id) {
        Account acc=new Account();
        return acc.InquryOperation(id);
    }

    @Override
    public int OutMoney(String id, int money){
        Account acc=new Account();
        return acc.OutMoney(id,money);
    }

    @Override
    public int InMoney(String id, int money){
        Account acc=new Account();
        return acc.InMoney(id,money);
    }
}