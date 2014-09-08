package uz.javlon.beecell.manager;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author javasparx  [9/4/14]
 */
public class DBManager {

    public static final String dbName = "beecell.db";
    private static Connection connection;

    private static Connection getConnection() {

        System.out.println("Connecting to DB...");

        if (connection != null) {
            return connection;
        }

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }

    public static void createDB() {
        if (getConnection() == null) {
            System.err.println("No db file created");
        }
    }

}
