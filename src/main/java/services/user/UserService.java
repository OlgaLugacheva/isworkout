package services.user;

import models.dao.users.JdbcUserDAOImpl;
import models.dao.exceptions.UserDAOException;
import models.dao.users.UserDAO;
import models.pojo.User;

import java.sql.SQLException;
import java.util.List;


public class UserService {


    public static User authorise(String login, String password) throws UserDAOException {
        return JdbcUserDAOImpl.getUserByLoginAndPassword(login, password);
    }

    public static List<User> getAllUsers()  {
        return JdbcUserDAOImpl.getAllUsers();
    }

    public static boolean registration(String login, String password, String email) throws UserDAOException {
        return JdbcUserDAOImpl.registrationUser(login, password, email);
    }


}
