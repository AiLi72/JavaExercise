package config;

import java.util.HashMap;

public class Project {

    //HDFS
    public static final int RESOURCE_MANAGER_PORT = 8000;
    public static final String RESOURCE_MANAGER_HOSTNAME = "dragon";
    public static final int HDFS_FILE_TRANSFERT_PORT = 9000;
    public static final int MAXFILESIZE = 256*1024*1024; //64MB
    public static final String FOLDER_PATH_DATANODE_FILES = "/tmp/datanodefiles/";
    public static final int RMIPORT_RM = 1100;
    public static final int RMIPORT_NM = 1200;
    public static final int REP_FACTOR = 3;
    public static final long PROTOCOL_SYSTOLIC_TIMER = 5000L;

    //HIDOOP
    public static final String PATH = "/tmp/hidoop";
    public static final String REGISTRY_DAEMON_NAME = "daemon";
    public static final String REGISTRY_DAEMONMANAGER_NAME = "daemonManager";
    public static final String RESULT_SUFFIX = "-res";

    //TESTS
    public static final String[] MACHINES_N7 = { "neumann", "dragon", "tesla", "hugo", "muse" };
    public static final String DUMMY_FILES_PATH = "/tmp/dummyfiles/";

}
