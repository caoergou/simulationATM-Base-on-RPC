package mybank;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        boolean flag = BaseDAO.login(id,password);
        if (flag)
            return true;
        else
            return false;
    }
    public double InquryMoney(String id){
        return BaseDAO.InquryMoney(id);
    }
    public String[] InquryOperation(String id){
        return BaseDAO.InquryOperation(id);

    }
    protected int OutMoney(String id, int money) {
        return BaseDAO.OutMoney(id,money);
    }

    protected int InMoney(String id, int money) {
        return BaseDAO.InMoney(id,money);
    }
}
