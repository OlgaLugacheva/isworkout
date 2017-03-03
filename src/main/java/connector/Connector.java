package connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bot on 23.02.17.
 */
public class Connector {

    private static final String USER = "admin";//Логин пользователя
    private static final String PASSWORD = "ptshka1";//Пароль пользователя
    private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=workoutplanner";//URL адрес
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//Имя драйвера
    private static Logger logger = Logger.getLogger(Connector.class);

    public static Connection getConnection() throws SQLException {
        try {
                Class.forName(driver);
                DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
                logger.trace("Successful connect to base: "+URL);

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
