package mybank;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static mybank.connectionUtil.getConnection;

public class BaseDAO {
    public boolean idQuery(String id) {//账号是否重复
        Statement st = null;
        Connection conn = null;
        int count=0;
        try {
            conn = getConnection();
            String sql = "select count(*) as total from bankuser where user_name=?";
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
        if (count>0)
            return false;
        else return true;
    }
    public boolean register(String id, String password) {//注册
        Statement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
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

    public static boolean login(String id, String password) {//登录
        Statement st = null;
        Connection conn = null;
        String user_password = null;
        System.out.println("id="+id);
        System.out.println("password="+password);
        try {
            conn = getConnection();
            String sql = "select user_password from bankuser where user_name=\'" + id+"\'";
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
        return password.equals(user_password);
    }

    public static double InquryMoney(String id) {//查余额
        Statement st = null;
        Connection conn = null;
        double money = 0;
        try {
            conn = getConnection();
            String sql = "select user_money from bankuser where user_name= \'" + id+"\'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
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

    public static Object[][] InqueryOperation(String id) {//查记录
        Statement st = null;
        Connection conn = null;
        List<Object[]> list = new LinkedList();
        try {
            Connection c = getConnection();
            String sql = "select * from user_record where user_name=\'" + id+"\'";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            assert false;
            st = conn.createStatement();
            while (resultSet.next()) {
                Object[] objects = new Object[] { resultSet.getInt("record_datetime"), resultSet.getString("record_type"),
                        resultSet.getDouble("record_money") ,resultSet.getString("goalname")};
                list.add(objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return list.toArray(new Object[0][0]);
    }

    public static double InMoney(String id, double money) {//存钱
        Statement st = null;
        Connection conn = null;
        String sql = "update  bankuser set user_money =? where user_name=?";
        double money_after = 0;
        try {
            Connection c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            st = conn.createStatement();
            double money_before = InquryMoney(id);
            money_after = money_before + money;
            preparedStatement.setDouble(1, money_after);
            preparedStatement.setString(2, id);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return money_after;
    }

    public static double OutMoney(String id, double money) {//取钱
        Statement st = null;
        Connection conn = null;
        String sql = "update bankuser set user_money =? where user_name=?";
        double money_after = 0;
        try {
            Connection c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            st = conn.createStatement();
            double money_before = InquryMoney(id);
            money_after = money_before - money;
            preparedStatement.setDouble(1, money_after);
            preparedStatement.setString(2, id);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connectionUtil.closeAll(st, conn);
        }
        return money_after;
    }
}
