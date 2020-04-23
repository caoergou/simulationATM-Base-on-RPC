package server;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class ServerConfig {
    //这里其实可以用json或者xml来存储，但是为了方便直接用类了
    public static final Boolean isEnableCheck = true;
    public static final Boolean isAllowLogging = true;
    public static final String mainHost="localhost";
    public static final int mainPort=8888;
    public static final String backupHost="localhost";
    public static final int backupPort=8889;
    public static HashMap<String,Integer> ErrorCount = new HashMap<>();
    public static Set<String> lockedAccount = new TreeSet<>();

}
