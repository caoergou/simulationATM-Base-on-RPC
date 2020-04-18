package mybank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inqury implements ActionListener{
    public JFrame iframe;
    public JPanel ip0,ip1,ip2;
    public JTextArea inquryresult;
    public JButton confirm,cancel,exit;
    public JLabel yue;
    public Inqury() {
        iframe=new JFrame("查询");

        ip0=new JPanel();
        ip0.add(new JLabel("账户id:"+ LoginGui.id));
        ip1=new JPanel();
        yue=new JLabel("账户余额:"+ RMIClient.ClientInquryMoney(LoginGui.id));
        ip1.add(yue);
        ip2=new JPanel();
        inquryresult=new JTextArea(10,30);
        ip2.add(inquryresult);
        confirm=new JButton("查询记录");
        confirm.addActionListener(this);
        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
        iframe.add(confirm);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);

        iframe.setBounds(500,500,350,300);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("查询记录"));
        {
            String result = "";
            String[] oper = RMIClient.ClientInquryOperation(LoginGui.id);
            //JOptionPane.showMessageDialog(iframe, oper);
            for(int i=0;i<100;i++) {
                if(oper[i]!=null)
                    result += oper[i]+"\n";
            }
            inquryresult.setText(result);//去除掉结果字符串中的null,并将元替换为元\r\n来换行换行
//            yue.setText("账户余额:"+Test.currentAccount.money);//更新显示余额
        }
    }
}
