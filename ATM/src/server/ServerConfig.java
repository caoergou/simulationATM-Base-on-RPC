package server;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class ServerConfig {
    //这里其实可以用json或者xml来存储，但是为了方便直接用类了


    //数据库相关配置
    //以下为 MySQL 8.0以上版本的驱动
    //private static final String driver = "com.mysql.cj.jdbc.Driver";

    //以下为 MySQL 8.0以下版本的驱动
    public static final String driver = "com.mysql.jdbc.Driver";

    public static final String url = "jdbc:mysql://localhost:3306/bank?serverTimezone=UTC";
    public static final String user = "root";
    public static final String password = "Input Your MySQL Password Here";

    //AOP相关配置
    public static final Boolean isEnableCheck = true;
    public static final Boolean isAllowLogging = true;

    //服务器地址相关配置
    public static final String mainHost="localhost";
    public static final int mainPort=8888;
    public static final String backupHost="localhost";
    public static final int backupPort=8889;

    //服务器数据存储
    public static HashMap<String,Integer> ErrorCount = new HashMap<>();
    public static Set<String> lockedAccount = new TreeSet<>();

}
