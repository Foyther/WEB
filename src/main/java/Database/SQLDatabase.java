package Database;

import Exception.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nurislam on 27.10.2016.
 */
public class SQLDatabase {
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String CONNECTION_URI = "jdbc:mysql://localhost:3306/site";
    private final static String LOGIN = "nurik";
    private final static String PASSWORD = "nurik";
    private static Connection connection;

    public static Connection getConnection() throws DBException {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(CONNECTION_URI, LOGIN, PASSWORD);
            } catch (ClassNotFoundException ex) {
                throw new DBException("Can't find Database driver.");
            } catch (SQLException ex) {
                throw new DBException("Can't connect to Database (" + ex.getErrorCode() + ": " + ex.getMessage() + ").");
            }

        }
        return connection;
    }
}
