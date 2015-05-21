package log4j;

import org.apache.log4j.*;
import org.apache.log4j.net.SocketNode;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RootLogger;

import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

/**
 * 日志服务器
 * Created by jiangyukun on 2015/5/13.
 */
public class Log4jServer {

    static String GENERIC = "generic";
    static String CONFIG_FILE_EXT = ".lcf";

    static Logger cat = Logger.getLogger(Log4jServer.class);
    static Log4jServer server;
    static int port;

    // key=inetAddress, value=hierarchy
    Hashtable<InetAddress, Hierarchy> hierarchyMap;
    LoggerRepository genericHierarchy;
    File dir;

    public static void main(String argv[]) {
        if (argv.length == 3)
            init(argv[0], argv[1], argv[2]);
        else
            usage("Wrong number of arguments.");

        try {
            cat.info("Listening on port " + port);
            ServerSocket serverSocket = new ServerSocket(port);
//            serverSocket.setSoTimeout(1000);
            while (true) {
                cat.info("Waiting to accept a new client.");
                Socket socket = serverSocket.accept();
                InetAddress inetAddress = socket.getInetAddress();
                cat.info("Connected to client at " + inetAddress);

                LoggerRepository h = server.hierarchyMap.get(inetAddress);
                if (h == null) {
                    h = server.configureHierarchy(inetAddress);
                }

                cat.info("Starting new socket node.");
                new Thread(new SocketNode(socket, h)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void usage(String msg) {
        System.err.println(msg);
        System.err.println("Usage: java " + Log4jServer.class.getName() + " port configFile directory");
        System.exit(1);
    }

    static void init(String portStr, String configFile, String dirStr) {
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            usage("Could not interpret port number [" + portStr + "].");
        }

        PropertyConfigurator.configure(configFile);

        File dir = new File(dirStr);
        if (!dir.isDirectory()) {
            usage("[" + dirStr + "] is not a directory.");
        }
        server = new Log4jServer(dir);
    }

    public Log4jServer(File directory) {
        this.dir = directory;
        hierarchyMap = new Hashtable<>(11);
    }

    // This method assumes that there is no hiearchy for inetAddress
    // yet. It will configure one and return it.
    LoggerRepository configureHierarchy(InetAddress inetAddress) {
        cat.info("Locating configuration file for " + inetAddress);
        // We assume that the toSting method of InetAddress returns is in
        // the format hostname/d1.d2.d3.d4 e.g. torino/192.168.1.1
        String s = inetAddress.toString();
        int i = s.indexOf("/");
        if (i == -1) {
            cat.warn("Could not parse the inetAddress [" + inetAddress +
                    "]. Using default hierarchy.");
            return genericHierarchy();
        } else {
            String key = s.substring(i + 1);

            File configFile = new File(dir, key + CONFIG_FILE_EXT);
            if (configFile.exists()) {
                Hierarchy h = new Hierarchy(new RootLogger(Level.DEBUG));
                hierarchyMap.put(inetAddress, h);

                new PropertyConfigurator().doConfigure(configFile.getAbsolutePath(), h);

                return h;
            } else {
                cat.warn("Could not find config file [" + configFile + "].");
                return genericHierarchy();
            }
        }
    }

    LoggerRepository genericHierarchy() {
        if (genericHierarchy == null) {
            File f = new File(dir, GENERIC + CONFIG_FILE_EXT);
            if (f.exists()) {
                genericHierarchy = new Hierarchy(new RootLogger(Level.DEBUG));
                new PropertyConfigurator().doConfigure(f.getAbsolutePath(), genericHierarchy);
            } else {
                cat.warn("Could not find config file [" + f +
                        "]. Will use the default hierarchy.");
                genericHierarchy = LogManager.getLoggerRepository();
            }
        }
        return genericHierarchy;
    }

}
