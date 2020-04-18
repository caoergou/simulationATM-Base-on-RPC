package mybank;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Account {
    int money;
    String id;//账号名

    String password;
    Date now=new Date();
    Date currentTime;
    SimpleDateFormat formatter;
    Reader fr;
    ;
    public Account(String id, String password, String money) {//构造方法
        this.id = id;

        this.password = password;
        this.money=Integer.parseInt(money);
    }

    public void outMoney (int money)throws Exception {//抛出异常，由相关的界面类弹窗处理异常,下面几个方法同理
        //如在取钱界面取钱，则会调用此函数，进行try/catch处理，获得这个函数的异常，弹窗说明异常
        if (money > this.money) {
            throw new Exception("余额不足");
        }
        if(money<0)
        {
            throw new Exception("不能取出负数");
        }
        formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");//时间格式
        currentTime = new Date();//当前时间
        String dateString = formatter.format(currentTime);//处理当前时间格式
        Writer fw = new FileWriter(Test.file);
        fw.write(Test.recordString.append(dateString + "\t" + Test.currentAccount.id + "\t取出" + money + "元\r\n").toString());//将这次的取钱行为添加到记录文件中
        fw.flush();//写进文件
        fw.close();
        this.money -= money;
        Test.usersListUpdate();//更新用户文档（信息）
    }

    public void inMoney(int money)throws Exception
    {
        try {
            Writer fw = new FileWriter(Test.file);
           // System.out.println(Test.file);
            formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            currentTime=new Date();
            String dateString=formatter.format(currentTime);
            fw.write(Test.recordString.append(dateString+"\t"+Test.currentAccount.id+"\t存入" + money + "元\r\n").toString());
            fw.flush();//写进文件
            fw.close();

            this.money+=money;

            Test.usersListUpdate();//更新当前用户信息

        }
        catch (Exception e1)
        {
            throw new Exception("写入记录失败");
        }

    }

    public void transfer(int money,String id)throws Exception//转账
    {
        if(id.equals(Test.currentAccount.id))
        {
            throw new Exception("不能转给自己");
        }
        if(money>this.money)
        {
            throw new Exception("余额不足");
        }
        if(money<0) {
            throw new Exception("不能转入负数");
        }


        for(int i=0;i<Test.usersList.size();i++)
        {
            if(Test.usersList.get(i).id.equals(id))//找到要转帐的用户
            {
                Test.usersList.get(i).money+=money;//转入
                this.money-=money;//扣钱

                FileWriter fw=new FileWriter(Test.file);
                formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");//声明时间格式
                currentTime=new Date();//获取当前时间
                String dateString=formatter.format(currentTime);//转换时间格式
                fw.write(Test.recordString.append(dateString+"\t向"+id+"\t转出"+money+"元\r\n").toString());//Test类中的静态字符串拼接上这个字符串覆盖写入当前用户文档
                fw.close();

                /********************向转入目标写入转账信息*************************/
                try {
                fr = new FileReader(id+".txt");//字符流
                 }
                 catch (Exception e)
                 {
                System.out.println("字符流创建失败");
                }

                BufferedReader bfr = new BufferedReader(fr);

                String temp="";
                String temp1;

                while ((temp1 = bfr.readLine()) != null)
                {
                    temp+=temp1;
                }
                temp=temp.replace("元","元\n\r")+dateString+"\t由"+Test.currentAccount.id+"\t转进"+money+"元\r\n";
                System.out.println(temp);
                fw=new FileWriter(id+".txt");
                fw.write(temp);
                fw.close();


                JOptionPane.showMessageDialog(null,"转账成功");
                Test.usersListUpdate();//更新用户文档

                return;
            }
        }
        throw new Exception("目标用户不存在");
    }

    public void ChangePassword(String newPassword)throws Exception
    {
     if(newPassword.equals(this.password))
     {
         throw new Exception("原密码和新密码不能一样");
     }

     else
     {
         if(newPassword.equals(""))
         {
             throw new Exception("密码不能为空");
         }

     }
     password=newPassword;
        Test.usersListUpdate();


    }



}
