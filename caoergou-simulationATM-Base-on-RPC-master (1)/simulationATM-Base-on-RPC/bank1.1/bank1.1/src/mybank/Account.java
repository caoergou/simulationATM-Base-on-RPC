package mybank;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Account {
    double money;
    String id;//账号名
    String password;

    Date now=new Date();
    Date currentTime;
    SimpleDateFormat formatter;
    Reader fr;
    ;

    public Account(/*String id, String password, String money*/) {//构造方法
        this.id = "0";
        this.password = "0";
        this.money=100000000;
    }


    public boolean login(String id,String password){
        return BaseDAO.login(id,password);
    }
    public double InquryMoney(String id){
        return BaseDAO.InquryMoney(id);
    }
    public List<Object[]> InquryOperation(String id){
        return BaseDAO.InqueryOperation(id);

    }
    protected int OutMoney(String id, int money) {
        return (int) BaseDAO.OutMoney(id,money);
    }

    protected int InMoney(String id, int money) {
        return (int) BaseDAO.InMoney(id,money);
    }
}
