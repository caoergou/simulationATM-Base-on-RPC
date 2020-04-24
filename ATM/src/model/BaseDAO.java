package model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

// import connectionUtil.getConnection;

public class BaseDAO {

    public boolean idQuery(String id) {// 账号是否重复
        Statement st = null;
        Connection conn = null;
        int count = 0;
        try {
            conn = connectionUtil.getConnection();
            String sql = "select count(*) as total from bankuser where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
            while (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        if (count > 0)
            return false;
        else
            return true;
    }

    public boolean register(String id, String password) {// 注册
        Statement st = null;
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            String sql = "insert into bankuser values (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            st = conn.createStatement();
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 0);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return true;
    }

    public static int login(String id, String password) {// 登录
        Statement st = null;
        Connection conn = null;
        String user_password = null;
        try {
            conn = connectionUtil.getConnection();
            String sql = "select user_password from bankuser where username=\'" + id + "\'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
            while (resultSet.next()) {
                user_password = resultSet.getString("user_password");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return password.equals(user_password)?1:0;
    }

    public static double InquryMoney(String id) {// 查余额
        Statement st = null;
        Connection conn = null;
        double money = 0;
        try {
            conn = connectionUtil.getConnection();
            String sql = "select user_money from bankuser where username= \'" + id + "\'";
            // PreparedStatement preparedStatement = conn.prepareStatement(sql);
            // ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                money = resultSet.getDouble("user_money");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return money;
    }

    public static List<Object[]> InquryOperation(String id) {// 查记录
        Statement st = null;
        Connection conn = null;
        List<Object[]> list = new LinkedList<>();
        String[] list1 = new String[100];
        try {
            conn = connectionUtil.getConnection();
            String sql = "select * from user_record where username=\'" + id + "\'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Object[] objects = new Object[] { resultSet.getDate("record_datetime"),
                        resultSet.getString("record_type"), resultSet.getDouble("record_money"), "目标账户",
                        resultSet.getString("goalname") };
                list.add(objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return list;
    }

    public static double InMoney(String id, double money) {// 存钱
        Statement st = null;
        Connection conn = null;
        double money_after = 0;
        try {
            conn = connectionUtil.getConnection();
            // PreparedStatement preparedStatement = c.prepareStatement(sql);
            st = conn.createStatement();
            double money_before = InquryMoney(id);
            money_after = money_before + money;
            String sql = "update  bankuser set user_money =\'" + money_after + "\'" + "where username=" + "\'" + id
                    + "\'";
            String sql1 = "insert into user_record (record_datetime,username,record_type,record_money,goalname)values(NOW(),\'"
                    + id + "\','存款'," + money + ",\'" + id + "\')";
            st.execute(sql);
            st.execute(sql1);
            // preparedStatement.setDouble(1, money_after);
            // preparedStatement.setString(2, id);
            // preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return money_after;
    }

    public static double OutMoney(String id, double money) {// 取钱
        Statement st = null;
        Connection conn = null;
        double money_after = 0;
        try {
            conn = connectionUtil.getConnection();
            // PreparedStatement preparedStatement = c.prepareStatement(sql);
            st = conn.createStatement();
            double money_before = InquryMoney(id);
            money_after = money_before - money;
            if (money_after < 0) {
                // 需要判断

                return 2;
            }
            String sql = "update  bankuser set user_money =\'" + money_after + "\'" + "where username=" + "\'" + id
                    + "\'";
            String sql1 = "insert into user_record (record_datetime,username,record_type,record_money,goalname)values(NOW(),\'"
                    + id + "\','取款'," + money + ",\'" + id + "\')";
            st.execute(sql);
            st.execute(sql1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return money_after;
    }
}
