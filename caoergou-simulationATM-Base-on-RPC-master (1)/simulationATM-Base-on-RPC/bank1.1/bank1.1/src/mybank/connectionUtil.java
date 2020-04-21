package mybank;


import java.sql.*;

public class connectionUtil {
    private static final String url = "jdbc:mysql://localhost:3306/bankWithDistributedComputing?serverTimezone=UTC";

    /**
     * 连接数据库的用户名
     */

    private static final String user = "root";
    /**
     * 连接数据库的密码
     */
    private static final String password = "1234";
    /**
     * 数据库驱动
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeAll(Statement st, Connection conn) {
//先产生的后关闭，后产生的先关闭
        if (st != null)
            try {
                st.close();
            } catch (SQLException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
    }
}
