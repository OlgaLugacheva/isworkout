package models.dao.users;

import connector.Connector;
import models.dao.exceptions.UserDAOException;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class JdbcUserDAOImpl implements UserDAO{

    /**
     * Объект для логгирования.
     */
    private static final Logger LOGGER = Logger.getLogger(JdbcUserDAOImpl.class);
    private static final String SQL_FIND_USER = "SELECT * FROM users WHERE login = ? AND password = ?";
//    private static String SQL_ALL_USERS = "SELECT * FROM \"workoutplanner\".\"users\"";
    private static final String SQL_CREATE_USER = "INSERT INTO users (login, password, role, email) VALUES (?,?,?,?)";
    private static final String SQL_USER_ID = "SELECT * FROM users WHERE id = ?";

    /**
     * Метод возвращает пользователя по запрашиваемому имени
     */
    @Override
    public User getByName(String name) throws UserDAOException {
        User user = null;
        try (final Connection connection = Connector.getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE name = ?")) {
            statement.setString(1, name);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    user = new User(rs.getInt("id"), rs.getInt("id_personal"), rs.getString("name"), rs.getString("password"), rs.getString("role"), rs.getString("email"));
                    break;
                }
            }
        } catch (SQLException | NullPointerException e) {
            JdbcUserDAOImpl.LOGGER.info(e.getMessage());
            throw new UserDAOException();
        }
        return user;
    }

    /**
     * Метод возвращает пользователя по запрашиваемому идентификатору.

     */
    @Override
    public User getById(int id) throws UserDAOException {
        User user = null;
        try (final Connection connection = Connector.getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    user = new User(rs.getInt("id"), rs.getInt("id_personal"), rs.getString("name"), rs.getString("password"), rs.getString("role"), rs.getString("email"));
                    break;
                }
            }
        } catch (SQLException | NullPointerException e) {
            JdbcUserDAOImpl.LOGGER.info(e.getMessage());
            throw new UserDAOException();
        }
        return user;
    }

    /**
     * Метод добавляет нового пользователя в БД.

     */
    @Override
    public User addNewUser(int id_personal, String login, String password, String role, String email) throws UserDAOException {
        User user = null;
        try (final Connection connection = Connector.getConnection();
             final PreparedStatement statement = connection.prepareStatement("INSERT  INTO users (login, password, role, email) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.setString(4, email);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user = new User(generatedKeys.getInt(1), id_personal, login, password, role, email);
                }
            }
        } catch (SQLException | NullPointerException e) {
            JdbcUserDAOImpl.LOGGER.info(e.getMessage());
            throw new UserDAOException();
        }
        return user;
    }

    /**
     * Метод возвращает пароль пользователя.
     *
     */
    @Override
    public Map<String, String> getPassword(int id) throws UserDAOException {
        Map<String, String> result = new HashMap<>();
        try (final Connection connection = Connector.getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT password, salt FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    result.put("password", rs.getString("password"));
                    result.put("salt", rs.getString("salt"));
                    break;
                }
            }
        } catch (SQLException | NullPointerException e) {
            JdbcUserDAOImpl.LOGGER.info(e.getMessage());
            throw new UserDAOException();
        }
        return result;
    }

    /**
     * Метод обновляет пароль у пользователя.
     */
    @Override
    public User updatePassword(int id, String newPassword, String salt) throws UserDAOException {
        try (final Connection connection = Connector.getConnection();
             final PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ?, salt = ? WHERE id = ?")) {
            statement.setString(1, newPassword);
            statement.setString(2, salt);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            JdbcUserDAOImpl.LOGGER.info(e.getMessage());
            throw new UserDAOException();
        }
        return this.getById(id);
    }

    public static boolean registrationUser(String login, String password, String email) throws UserDAOException {
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, email);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                LOGGER.debug("inserted " + count);
                return true;
            }else{
                LOGGER.debug(login + " " + password + " not inserted");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    public static List<User> getAllUsers()  {
        List<User> usersList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = Connector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while(resultSet.next()) {
                LOGGER.debug(resultSet.getString("login"));

                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_personal"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("email")
                );
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public static User getUserByLoginAndPassword(String login, String password) throws UserDAOException {

        LOGGER.debug(login + " " + password);
        User user = null;
        try(final Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                LOGGER.debug("find");
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_personal"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("email")
                );
            }else{
                LOGGER.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new UserDAOException();
        }
        return user;
    }

    public static User getUserById(int id) throws UserDAOException {

        LOGGER.debug(id);
        User user = null;
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                LOGGER.debug("find"+id);
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_personal"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("email"));
            }else{
                LOGGER.debug(id+" not found");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new UserDAOException();
        }
        return user;
    }


}
