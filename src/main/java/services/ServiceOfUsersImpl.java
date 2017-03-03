package services;


import models.dao.exceptions.UserDAOException;
import models.pojo.User;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализует модель обработки данных пользователя.
 *
 */
public class ServiceOfUsersImpl implements ServiceOfUsers {


    /**
     * Объект для логгирования.
     */
    private static final Logger LOGGER = Logger.getLogger(ServiceOfUsersImpl.class);

    /**
     * Метод проверяет корректность данных валидации входа.
     *
     * @param username имя пользователя.
     * @param password пароль пользователя.
     * @param user     пользователь для сверки данных.
     * @return Если валидация пройдена успешно будет возвращено значения пользователя для сессии, иначе возвращается null.
     */
    @Override
    public User validateLogin(String username, String password, User user) throws UserDAOException {
        User result = null;
        if (user != null && user.getPassword() != null  ) {
            result = user;
        }
        return result;
    }

    /**
     * Метод проверяет, что пароль заполнен.
     *
     * @param password пароль пользователя.
     * @param confirm  подтверждение пароля.
     * @return Если пароль заполне возвращается true, иначе false.
     */
    @Override
    public boolean passwordEmpty(String password, String confirm) {
        return password == null || (password != null && password.isEmpty());
    }

    /**
     * Метод проверяет совпадение паролей.
     *
     * @param password пароль пользователя.
     * @param confirm  подтверждение пароля.
     * @return Если пароль и его подтверждение совпадают возвращается true, иначе false.
     */
    @Override
    public boolean checkPasswords(String password, String confirm) {
        boolean result = false;
        if (password != null && confirm != null && !password.isEmpty() && password.equals(confirm)) {
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяет корректность введенного имени.
     *
     * @param name имя для проверки.
     * @return Если имя корректно возвращается true, иначе возвращается false.
     */
    @Override
    public boolean checkName(String name) {
        return name.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM]+\\d*") && name.length() <= 25;
    }

    /**
     * Метод проверяет корректность введенных данных для редактирования профиля пользователя.

     */
    @Override
    public boolean checkDataForEdid(String inputCurrentPassword, Map<String, String> passwordAndSalt, String newPassword, String confirmPassword) throws UserDAOException {
        boolean isValidate = false;
        if (inputCurrentPassword != null && newPassword != null && confirmPassword != null
                && !newPassword.isEmpty() && newPassword.equals(confirmPassword)) {
            isValidate = true;
        }
        return isValidate;
    }


}
