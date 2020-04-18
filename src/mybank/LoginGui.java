package mybank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;


public class LoginGui implements ActionListener{//实现监听器的接口
    private JFrame frame;
    private JPanel p0,p1,p2,p3,p4;//p4包括确认密码时的输入框；点击register按钮出现

    private JTextField userName;
    private JPasswordField passWord,passwordCheck;
    private JButton login;
    private JButton register;
    private Reader fw;
    Boolean regirsterable=true;//控制是否能注册的变量


    public LoginGui() {
        frame=new JFrame("登录ATM");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口的点击右上角的x的处理方式，这里设置的是退出程序
        p0=new JPanel();

        p0.add(new JLabel("中国农业银行ATM"));
        frame.add(p0);

        p1=new JPanel();
        p1.add(new JLabel("\t用户名:"));
        userName=new JTextField(20);
        p1.add(userName);

        p2=new JPanel();
        p2.add(new JLabel("\t  密码  :"));
        passWord=new JPasswordField(20);
        p2.add(passWord);


        p3=new JPanel();

        login=new JButton("     登录     ");
        register=new JButton("     注册     ");
        p3.add(login);
        p3.add(register);

        p4=new JPanel();
        p4.add(new JLabel("确认密码:"));
        passwordCheck=new JPasswordField(20);
        p4.add(passwordCheck);


        frame.add(p1);
        frame.add(p2);
        frame.add(p4);//确认密码框
        frame.add(p3);


        frame.pack();
        frame.setVisible(true);
        p4.setVisible(false);
        show();
        /*****************************Login****************************/
    }



    public void show(){

        login.addActionListener(this);//添加监视器
        register.addActionListener(this);
        frame.setBounds(500,500,350,250);//设置大小
        frame.setLayout(new FlowLayout());//设置流式布局
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("     注册     ")) {//注册，如果监听器获得的按钮文本是这个，也就是点击的按钮文本是这个的话，执行下面的语句
            if(p4.isVisible()==false)
            {
                p4.setVisible(true);//检查密码输入栏
                login.setText("     取消     ");//将ｌｏｇｉｎ文本改为ｃａｎｃｅｌ，同时也能触发作为Ｃａｎｃｅｌ的监听器
                regirsterable=true;
                return;
            }
            if(regirsterable==true) {
                if (userName.getText().equals(""))//如果userName的文本是空
                {
                    JOptionPane.showMessageDialog(frame, "用户名不能为空");//弹窗
                    return;
                }


                for (int i = 0; i < Test.usersList.size(); i++) {

                    if (Test.usersList.get(i).id.equals(userName.getText())) {
                        JOptionPane.showMessageDialog(frame, "该用户已被注册");
                        userName.setText("");//清空输入框
                        passWord.setText("");
                        passwordCheck.setText("");
                        return;//如果同名，结束方法，不在运行下面的语句
                    }

                }
                //如果执行到这里说明找到用户名
                if (passWord.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "密码不能为空，请重新输入");
                    return;

                } else {
                    if (passwordCheck.getText().equals(passWord.getText())) {
                        Account registeraccount = new Account(userName.getText(), passWord.getText(), "0");//实例化此账号
                        JOptionPane.showMessageDialog(frame, "注册成功，请登录");
                        Test.usersList.add(registeraccount);//加入Test类的静态用户list
                        Test.usersListUpdate();//更新用户文档

                        return;
                    } else {
                        JOptionPane.showMessageDialog(frame, "两次输入的密码不一致，请重新输入");
                        return;
                    }


                }
            }


            }
        if(e.getActionCommand().equals("     登录     ")){
            for (int i = 0; i < Test.usersList.size(); i++) {

                if (Test.usersList.get(i).id.equals(userName.getText())) {

                    if(passWord.getText().equals(Test.usersList.get(i).password))
                    {
                        JOptionPane.showMessageDialog(frame, "登录成功");
                        Test.currentAccount=Test.usersList.get(i);//将list中符合登陆输入的账户密码的类设为当前Test类中的静态的“当前类”，以便后面各种操作;
                        Test.file=new File(Test.currentAccount+".txt");
                        Test.recordString=new StringBuilder();//清空，避免将上一个用户的记录写进新登录的用户中
                        //Test.recordString.append("");//避免recordString空指针
                        Menu menu=new Menu();//实例化菜单窗口

                        Test.menu=menu;
                        frame.setVisible(false);//隐藏登陆窗口

                        /************************创建记录文件**********************/
                        File records = new File(Test.currentAccount.id+".txt");//以账户id命名
                        if(!records.exists())
                        {
                            try {
                                records.createNewFile();
                            }
                            catch (Exception e1)
                            {
                                JOptionPane.showMessageDialog(null, "创建该用户的记录失败");
                            }
                        }
                        Test.file=records;
                        /*****************读取记录文件************/
                        try {
                             fw = new FileReader(Test.file);//字符流
                        }
                        catch (Exception e1)
                        {
                            JOptionPane.showMessageDialog(null, "读取记录失败");
                        }
                        BufferedReader bfr=new BufferedReader(fw);

                        String temp="";
                        try {


                            while ((temp = bfr.readLine()) != null) {//不知为何读取出的字符串中最前面会出现Null，但不影响使用
                                Test.recordString .append(temp);//读取原先该账户的记录的每一行并拼接到Test.recordString中，在inqury类中输出作为查询记录的结果
                            }
                            //将记录读取并合并为一个字符串



                            fw.close();
                        }
                        catch (Exception e1)
                        {
                            System.out.println("读取记录过程中出现错误");
                        }


                        return;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "密码错误");
                        passwordCheck.setText("");
                        return;
                    }

                }
            }
            JOptionPane.showMessageDialog(frame, "该用户不存在");


        }
        if(e.getActionCommand().equals("     取消     "))
        {
            p4.setVisible(false);
            login.setText("     登录     ");
            regirsterable=false;//不可注册
        }


    }
}
