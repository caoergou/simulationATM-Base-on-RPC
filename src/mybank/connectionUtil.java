package bank;

import java.sql.*;

public class connectionUtil {
    private static final String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=bank";

    /**
     * 连接数据库的用户名
     */

    private static final String user = "sa";
    /**
     * 连接数据库的密码
     */
    private static final String password = "123456";
    /**
     * 数据库驱动
     */
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static Connection connection = null;

    static {
        try {
            DriverManager.registerDriver((Driver) Class.forName(driver).newInstance());
        } catch (Exception e) {
            throw new RuntimeException("注册数据库驱动失败", e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                throw new RuntimeException(" 获取数据库连接失败", e);
            }
        }
        return connection;
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
