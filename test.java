package bank;

import java.sql.*;

import static bank.connectionUtil.getConnection;

public class test {

    public static void main(String[] args) {
    }


    public boolean login(String id, String password) {
        Statement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            String sql = "select user_password from bankuser where username= " + id;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
            while (resultSet.next()) {
                password = resultSet.getString("user_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        if (password == "222")
            return true;
        else return false;
    }

    public double InquryMoney(String id) {
        Statement st = null;
        Connection conn = null;
        double money = 0;
        try {
            conn = getConnection();
            String sql = "select user_money from bankuser where username= " + id;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
            while (resultSet.next()) {
                money = resultSet.getDouble("user_money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
        return money;
    }

    public void InqueryOperation(String id) {
        Statement st = null;
        Connection conn = null;
        try {
            Connection c = getConnection();
            String sql = "select * from user_record where username=" + id;
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
            while (resultSet.next()) {
                String datetime = resultSet.getString("record_datetime");
                String record_type = resultSet.getString("record_type");
                String record_money = resultSet.getString("record_money");
                String goalname = resultSet.getString("goalname");
                System.out.println(datetime + "  " + record_type + record_money + goalname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionUtil.closeAll(st, conn);
        }
    }

    public double InMoney(String id, double money) {
        Statement st = null;
        Connection conn = null;
        String sql = "update  Cardmessage set money =? where CardNum=?";
        double money_after = 0;
        try {
            Connection c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
            double money_before = InquryMoney(id);
            money_after = money_before + money;
            while (resultSet.next()) {
                preparedStatement.setDouble(1, money_after);
                preparedStatement.setString(2, id);
            }
        } catch (Exception e) {
            throw new RuntimeException(" 执行数据库更新失败", e);
        }
        return money_after;
    }

    public double OutMoney(String id, double money) {
        Statement st = null;
        Connection conn = null;
        String sql = "update  Cardmessage set money =? where CardNum=?";
        double money_after = 0;
        try {
            Connection c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = conn.createStatement();
            double money_before = InquryMoney(id);
            money_after = money_before - money;
            while (resultSet.next()) {
                preparedStatement.setDouble(1, money_after);
                preparedStatement.setString(2, id);
            }
        } catch (Exception e) {
            throw new RuntimeException(" 执行数据库更新失败", e);
        }
        return money_after;
    }
}