package services;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPoolFactory {
    /**
     * Объект для логгирования.
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolFactory.class);
    /**
     * Объект для получения соединения к БД.
     */
    private static DataSource ds = null;

    static {
        try {
            InitialContext initContext = new InitialContext();
            ConnectionPoolFactory.ds = (DataSource) initContext.lookup("java:comp/env/jdbc/is");
        } catch (NamingException e) {
            ConnectionPoolFactory.LOGGER.info(e.getMessage());
        }
    }

    /**
     * Метод возвращает доступное соединение из пула.
     *
     * @return соединение для работы с БД.
     * @throws SQLException
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = ConnectionPoolFactory.ds.getConnection();
        } catch (SQLException e) {
            ConnectionPoolFactory.LOGGER.info(e.getMessage());
        }
        return connection;
    }
}
