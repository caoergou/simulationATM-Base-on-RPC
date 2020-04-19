package mybank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

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
        if(e.getActionCommand().equals("查询记录"))
        {
            StringBuilder result = new StringBuilder();
            List<Object> oper=new List<Object>();
            //JOptionPane.showMessageDialog(iframe, oper);

            for (Object[] objects : oper) {
                if (objects != null) {
                    result.append(Arrays.toString(objects)).append("\n");
                    inquryresult.setText(Arrays.toString(objects));//去除掉结果字符串中的null,并将元替换为元\r\n来换行换行
                }
            }

            inquryresult.setText(result.toString());//去除掉结果字符串中的null,并将元替换为元\r\n来换行换行
//            yue.setText("账户余额:"+Test.currentAccount.money);//更新显示余额
        }
    }
}
