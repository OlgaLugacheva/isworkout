package controllers.users;

import models.dao.exceptions.UserDAOException;
import models.pojo.Person;
import models.pojo.User;
import org.apache.log4j.Logger;
import services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Сервлет для работы механизма "Регистрация".
 */
public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try {
            if(UserService.registration(login, password,email)){
                logger.trace("registration successful");
                resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/login"));
            }else{
                logger.trace("registration failed");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (UserDAOException e) {
            e.printStackTrace();
        }


    }
}

