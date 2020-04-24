package model;

import java.sql.*;
import server.ServerConfig;

public class connectionUtil {



    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(ServerConfig.driver);
        return DriverManager.getConnection(ServerConfig.url, ServerConfig.user, ServerConfig.password);
    }

    public static void closeAll(Statement st, Connection conn) {
//先产生的后关闭，后产生的先关闭
        if (st != null)
            try {
                st.close();
            } catch (SQLException e) {

                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {

                        e.printStackTrace();
                    }
            }
    }
}
