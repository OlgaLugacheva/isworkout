package services;

import models.dao.exceptions.UserDAOException;
import models.pojo.User;

import java.util.Map;

/**
 * Интерфейс реализует модель обработки данных пользователя.
 */
public interface ServiceOfUsers {

    /**
     * Метод проверяет корректность данных валидации входа.
     *
     */
    User validateLogin(String username, String password, User user) throws UserDAOException;

    /**
     * Метод проверяет, что пароль заполнен.
     *
     * @param password пароль пользователя.
     * @param confirm  подтверждение пароля.
     */
    boolean passwordEmpty(String password, String confirm);

    /**
     * Метод проверяет совпадение паролей.
     */
    boolean checkPasswords(String password, String confirm);

    /**
     * Метод проверяет корректность введенного имени.
     *
     * @param name имя для проверки.
     * @return Если имя корректно возвращается true, иначе возвращается false.
     */
    boolean checkName(String name);

    /**
     * Метод проверяет корректность введенных данных для редактирования профиля пользователя.
     *
     */
    boolean checkDataForEdid(String inputCurrentPassword, Map<String, String> passwordAndSalt, String newPassword, String confirmPassword) throws UserDAOException;

}
