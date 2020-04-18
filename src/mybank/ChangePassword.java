package mybank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword implements ActionListener{
    public JTextField oldPassword,newPassword,checkPassword;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3,ip4;
    public JButton confirm,cancel,exit;
    public ChangePassword() {
        iframe=new JFrame("更改密码");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ip2=new JPanel();
        ip2.add(new JLabel("原密码:"));
        oldPassword=new JTextField(20);
        ip2.add(new JLabel("<html><br/><html>"));//换行
        ip2.add(oldPassword);

        ip0=new JPanel();
        ip0.add(new JLabel("新密码:"));
        newPassword=new JTextField(20);
        ip0.add(new JLabel("<html><br/><html>"));//换行
        ip0.add(newPassword);

        ip4=new JPanel();
        ip4.add(new JLabel("再次输入新密码:"));
        checkPassword=new JTextField(20);
        ip4.add(new JLabel("<html><br/><html>"));//换行
        ip4.add(checkPassword);

        ip3=new JPanel();
        confirm=new JButton("确定");
        ip3.add(confirm);
        cancel=new JButton("返回");
        ip3.add(cancel);

        iframe.add(ip2);
        iframe.add(ip0);
        iframe.add(ip4);
        iframe.add(ip3);
        iframe.add(confirm);
        iframe.add(cancel);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);

        iframe.setBounds(500,500,350,300);
        confirm.addActionListener(this);

        cancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("确定")) {
            if (Test.currentAccount.password.equals(oldPassword.getText())) {
                try {
                    if(newPassword.getText().equals(checkPassword.getText())) {

                        Test.currentAccount.ChangePassword(newPassword.getText());
                        JOptionPane.showMessageDialog(null, "更改密码成功");
                        iframe.setVisible(false);
                        Test.menu.mframe.setVisible(false);//关闭菜单界面
                        new LoginGui();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
                    }
                }
             catch (Exception e1) {//捕获账户类中更改密码函数的异常并弹窗显示
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            } else {

                JOptionPane.showMessageDialog(null, "原密码错误");
            }
        }
        else//如果点击cancel
        {
            iframe.setVisible(false);
        }
    }
}
