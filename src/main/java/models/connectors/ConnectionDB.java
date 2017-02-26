package models.connectors;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Павел on 25.02.2017.
 */
public class ConnectionDB {
    private static Logger logger = Logger.getLogger(ConnectionDB.class);
    private static final String HOST = "jdbc:mysql://localhost:3306/blog";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static volatile Connection connectionDB;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnectionDB(){
        logger.trace("Get Connection");
        Connection localInstance = connectionDB;
        if (localInstance == null) {
            synchronized (ConnectionDB.class) {
                localInstance = connectionDB;
                if (localInstance == null) {
                    try {
                        connectionDB = localInstance = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
                        System.out.println("Connection is open");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return localInstance;
    }
}
