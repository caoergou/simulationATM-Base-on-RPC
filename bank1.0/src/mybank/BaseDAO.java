package mybank;

public class BaseDAO {


    public static boolean login(String id, String password) {
        if(id.equals("111") & password.equals("222"))
            return true;
        else
            return false;
    }
    public static double InquryMoney(String id) {
                if(id.equals("111"))
                    return 1000.00;
                else
                    return 200;
    }
    public static String[] InquryOperation(String id)
    {
        String[] oper = new String[100];
        for (int i = 0; i < 10; i++) {
        oper[i] = "aaaaa"+i;
        }
        return oper;
    }
    public static int OutMoney(String id, int money) {
        if (id.equals("111"));
            return 1;//1、成功 2、账户被冻结 3、余额不足
    }

    public static int InMoney(String id, int money) {
        if (id.equals("111"));
        return 1;//1、成功 2、账户被冻结
    }
}
