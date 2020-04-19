package mybank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener{
    public JFrame mframe;
    private JPanel mp0,mp1,mp2,mp3,mp4;//p4是确认密码；点击register按钮石出现


    private JTextField passWord,passwordCheck;
    private JButton inqury;
    private JButton outmoney;
    private JButton transfer;
    private JButton inmoney;
    private JButton changepassword;


    public Menu()
    {
        mframe=new JFrame("欢迎登录中国农业银行");
        mframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton inqury=new JButton("    查询    ");
        JButton outmoney=new JButton("    取款    ");
        JButton transfer=new JButton("    转账    ");
        JButton inmoney=new JButton("    存款    ");
        JButton changepassword=new JButton("    改密    ");
        JButton exit=new JButton("    退卡    ");


        mp0=new JPanel();
        mp0.add(new JLabel("            中国农业银行为您服务            "));
        mp0.add(new JLabel("            ******请选择项目******            "));
        mp0.add(new JLabel(""));
        mp0.add(new JLabel(""));
        mp0.add(new JLabel(""));
        mp0.add(new JLabel(""));
        mp0.add(new JLabel(""));
        mp0.add(new JLabel("           Designed by 重案组           "));

        mp1=new JPanel();
        mp2=new JPanel();

        mp1.add(new JLabel(""));
        mp1.add(inmoney);
        mp1.add(inqury);
        mp1.add(outmoney);
        mp1.add(new JLabel(""));
        mp2.add(new JLabel(""));
        mp2.add(transfer);
        mp2.add(changepassword);
        mp2.add(exit);
        mp2.add(new JLabel(""));

        mp0.setLayout(new GridLayout(8,1,15,15));
        mp1.setLayout(new GridLayout(5,1,25,25));
        mframe.add(mp1);
        mframe.add(mp0);
        mp2.setLayout(new GridLayout(5,1,25,25));
        mframe.add(mp2);
        mframe.pack();
        mframe.setVisible(true);
        mframe.setLayout(new FlowLayout(FlowLayout.CENTER));
        mframe.setBounds(500,500,450,300);
        inqury.addActionListener(this);//绑定监听器
        inmoney.addActionListener(this);
        outmoney.addActionListener(this);
        transfer.addActionListener(this);
        changepassword.addActionListener(this);
        exit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();//cmd赋值为点击的按钮的值
        if(cmd.equals("    查询    "))
        {
        Inqury inquryGui=new Inqury();
        }
        else if(cmd.equals("    取款    "))
        {
        OutMoney outMoneyGui=new OutMoney();
        }
        else if(cmd.equals("    存款    "))
        {
        InMoney inMoney=new InMoney();
        }else if(cmd.equals("    转账    "))
        {
        Transfer transfer=new Transfer();
        }else if(cmd.equals("    改密    "))
        {
        ChangePassword changePassword=new ChangePassword();
        }
        else if(cmd.equals("    退卡    ")){

            mframe.setVisible(false);//隐藏
            LoginGui loginGui=new LoginGui();
            JOptionPane.showMessageDialog(null,"请记得取走你的银行卡");
        }

    }
}
