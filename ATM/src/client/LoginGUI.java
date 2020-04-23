package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI implements ActionListener {// 实现监听器的接口
    private final JFrame frame;
    private final JPanel p0, p1, p2, p3, p4;// p4包括确认密码时的输入框；点击register按钮出现

    private final JTextField userName;
    private final JPasswordField passWord, passwordCheck;
    private final JButton login;
    private final JButton register;
    Boolean regirsterable = true;// 控制是否能注册的变量

    public static String id; // 全局id

    public LoginGUI() {
        frame = new JFrame("登录ATM");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 设置窗口的点击右上角的x的处理方式，这里设置的是退出程序
        p0 = new JPanel();

        p0.add(new JLabel("中国农业银行ATM"));
        frame.add(p0);

        p1 = new JPanel();
        p1.add(new JLabel("\t用户名:"));
        userName = new JTextField(20);
        p1.add(userName);

        p2 = new JPanel();
        p2.add(new JLabel("\t  密码  :"));
        passWord = new JPasswordField(20);
        p2.add(passWord);

        p3 = new JPanel();

        login = new JButton("     登录     ");
        register = new JButton("     注册     ");
        p3.add(login);
        p3.add(register);

        p4 = new JPanel();
        p4.add(new JLabel("确认密码:"));
        passwordCheck = new JPasswordField(20);
        p4.add(passwordCheck);

        frame.add(p1);
        frame.add(p2);
        frame.add(p4);// 确认密码框
        frame.add(p3);

        frame.pack();
        frame.setVisible(true);
        p4.setVisible(false);
        show();/***************************** Login ****************************/
    }

    public void show() {

        login.addActionListener(this);// 添加监视器
        register.addActionListener(this);
        frame.setBounds(500, 500, 350, 250);// 设置大小
        frame.setLayout(new FlowLayout());// 设置流式布局
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        if (e.getActionCommand().equals("     注册     ")) {// 注册，如果监听器获得的按钮文本是这个，也就是点击的按钮文本是这个的话，执行下面的语句
            JOptionPane.showMessageDialog(frame, "注册新卡请前往中国农业银行控江路营业厅！");
        }
        if (e.getActionCommand().equals("     登录     ")) {
            final int flag = ATMGUI.ClientLogin(userName.getText(), passWord.getText());
            System.out.println(flag);
            switch (flag) {
                case 0:
                    JOptionPane.showMessageDialog(frame, "登录成功");
                    id = userName.getText();
                    final MenuGUI menu = new MenuGUI();// 实例化菜单窗口
                    frame.setVisible(false);// 隐藏登陆窗口
                    break;
                case -1:
                    JOptionPane.showMessageDialog(frame, "多次密码错误，此账号已被锁定！");
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, String.format("密码错误,您还有%d次机会", flag));
            }
            if (e.getActionCommand().equals("     取消     ")) {
                p4.setVisible(false);
                login.setText("     登录     ");
                regirsterable = false;// 不可注册
            }

        }
    }
}
