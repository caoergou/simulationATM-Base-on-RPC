package mybank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transfer implements ActionListener{
    public JTextField money,other;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3,ip4;
    public JButton confirm,cancel,exit;
    public JLabel yue;
    public Transfer() {
        iframe=new JFrame("转账");

        ip0=new JPanel();
        ip0.add(new JLabel("账户id:"+Test.currentAccount.id));

        ip1=new JPanel();
        yue=new JLabel("账户余额:"+Test.currentAccount.money);
        ip1.add(yue);

        ip2=new JPanel();
        ip2.add(new JLabel("转账账户id:"));
        other=new JTextField(10);
        ip2.add(other);

        ip4=new JPanel();
        ip4.add(new JLabel("转账金额:"));
        money=new JTextField(10);
        ip4.add(new JLabel("<html><br/><html>"));//换行
        ip4.add(money);

        ip3=new JPanel();
        confirm=new JButton("确定");
        ip3.add(confirm);
        cancel=new JButton("返回");
        ip3.add(cancel);

        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
        iframe.add(ip4);
        iframe.add(ip3);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);

        iframe.setBounds(500,500,350,300);
        confirm.addActionListener(this);

        cancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("确定"))
        {
            try {

                Test.currentAccount.transfer(Integer.parseInt(money.getText()),other.getText());

                yue.setText("账户余额:"+Test.currentAccount.money);//更新面板上的余额
            }

            catch (Exception e1)
            {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        }
        else
        {
            iframe.setVisible(false);

        }
    }
}
