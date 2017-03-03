package models.dao.users;

import models.dao.exceptions.UserDAOException;
import models.pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga on 03.03.2017.
 */
public interface UserDAO {


    /**
     * Метод возвращает пользователя по  имени.

     */
    User getByName(String name) throws UserDAOException;

    /**
     * Метод возвращает пользователя по запрашиваемому идентификатору.

     */
    User getById(int id) throws UserDAOException;

    /**
     * Метод добавляет нового пользователя .

     */
    User addNewUser(int id_personal, String name, String password, String role, String email) throws UserDAOException;

    /**
     * Метод возвращает пароль пользователя.

     */
    Map<String, String> getPassword(int id) throws UserDAOException;

    /**
     * Метод обновляет пароль у пользователя.
     *

     */
    User updatePassword(int id, String newPassword, String salt) throws UserDAOException;



//     User getUserByLoginAndPassword(String login, String password) throws UserDAOException;
}
