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
        ip0.add(new JLabel("账户id:"+Test.currentAccount.id));
        ip1=new JPanel();
        yue=new JLabel("账户余额:"+Test.currentAccount.money);
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
            //Test.recordString是从账户文档中度去处的字符串
            //在写入文本时/r/n才是换行,但在java中\r\n则是两个换行，而且Test.recordString是一行一行读取出来的拼接上的，所以并没有换行符，所以这里替换成一个\n
            inquryresult.setText(Test.recordString.toString().replace("元","元\n").replace("null",""));//去除掉结果字符串中的null,并将元替换为元\r\n来换行换行
            yue.setText("账户余额:"+Test.currentAccount.money);//更新显示余额
        }
    }
}
