package mybank;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static List<Account> usersList;
    public static Account currentAccount;//登录的用户
    public static File file;//当前用户的记录文件
    public static StringBuilder recordString=new StringBuilder();//登录后读取文本中的记录，然后和recordString拼接
    public static Menu menu;//静态的菜单界面，用于在更换密码后关闭菜单界面
    public static File usersFile;
    public static StringBuilder usersString=new StringBuilder();


     static Reader fw;

    public static void main(String[] args)throws Exception {

        usersList = new ArrayList<Account>();

        //System.out.println(usersList);
        /**********************用户文本**********************/
        File users = new File("users.txt");

        if (!users.exists()) {
            try {
                users.createNewFile();
                Writer fw = new FileWriter("users.txt");
                fw.write("admin  12345   88888");
                fw.flush();
                fw.close();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "创建用户文档失败");
            }

        }
        usersFile = users;//创建用户文档，存储用户账户，密码，余额信息;
        usersListRead();
        usersListUpdate();
        /*****************************Login****************************/

        LoginGui loginGui = new LoginGui();
    }
    public static void usersListRead()
    {
        /**********************按照用户文档读取用户列表并创建所有用户**********************/
        /**********************并写入list**********************/
        try {
            fw = new FileReader("users.txt");//字符流
        } catch (Exception e) {
            System.out.println("字符流创建失败");
        }

        BufferedReader bfr = new BufferedReader(fw);

        String temp = "";
        try {

            System.out.println("开始写入list");
            while ((temp = bfr.readLine()) != null) {//不知为何读取出的字符串中最前面会出现Null
                String[] tmpstr = new String[5];
                tmpstr = temp.split("\\s+");//分割空格

                System.out.println("余额：" + tmpstr[2]);

                Account a = new Account(tmpstr[0], tmpstr[1], tmpstr[2]);
                usersList.add(a);
                System.out.println("读取到"+a.id+",实例化用户" + a.id);

            }
            bfr.close();
            fw.close();
            System.out.println("用户list:"+usersList);
        } catch (Exception e) {
            System.out.println("读取用户文档失败");
        }
    }





    public static void usersListUpdate()
    {



        /**********************按照list内容写入文本用户信息**********************/
        try {
        Writer fw = new FileWriter("users.txt");

        StringBuilder tmpstr = new StringBuilder();
        for (int i = 0; i < usersList.size(); i++) {
           // System.out.println(Test.currentAccount.id);
            tmpstr.append(usersList.get(i).id + "    " + usersList.get(i).password + "    " + usersList.get(i).money + "\r\n");

            //fw.write(Test.currentAccount.id + "    " + Test.currentAccount.password + "    " + Test.currentAccount.money+"\r\n");
        }
        fw.write(tmpstr.toString());
        fw.flush();
        fw.close();
    }
    catch (Exception e)
    {
        e.printStackTrace();
        System.out.println("更新用户失败");
    }

    }
}
