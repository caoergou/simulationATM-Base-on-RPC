package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class WithdrawGUI implements ActionListener{
    public JTextField money;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3;
    public JButton confirm,cancel,exit;
    public JLabel yue;//余额
    public WithdrawGUI() {
        iframe=new JFrame("取款");

        ip0=new JPanel();
        ip0.add(new JLabel("账户id:"+LoginGUI.id));

        ip1=new JPanel();
        yue=new JLabel("账户余额:"+ ATMGUI.ClientInquryMoney(LoginGUI.id));
        ip1.add(yue);

        ip2=new JPanel();
        ip2.add(new JLabel("取款金额:"));
        money=new JTextField(20);
        ip2.add(money);

        ip3=new JPanel();
        confirm=new JButton("确定");
        ip3.add(confirm);
        cancel=new JButton("返回");
        ip3.add(cancel);

        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
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
        if(e.getActionCommand().equals("确定"))//点击确定按钮
        {
            //取款
                ATMGUI.OutMoney(LoginGUI.id,Integer.parseInt(money.getText()));
                JOptionPane.showMessageDialog(null, "取款成功");//弹窗
                yue.setText("账户余额:"+ ATMGUI.ClientInquryMoney(LoginGUI.id));//更新余额显示
        }
        else
        {
            iframe.setVisible(false);//隐藏

        }
    }
}
