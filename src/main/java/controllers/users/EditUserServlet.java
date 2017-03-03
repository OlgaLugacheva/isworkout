package controllers.users;

import models.dao.exceptions.UserDAOException;
import models.dao.users.JdbcUserDAOImpl;
import models.pojo.User;
import org.apache.log4j.Logger;
import services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


public class EditUserServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        User user = null;

        try {
            user = JdbcUserDAOImpl.getUserById(id);
            logger.debug(user.getId());
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("id", user.getId());
        req.setAttribute("id_personal", user.getId_personal());
        req.setAttribute("login", user.getLogin());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("role", user.getRole());
        req.setAttribute("email", user.getEmail());

        req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on edit");
        String strId = req.getParameter("id");
        int id = (strId.equals(""))?0:Integer.parseInt(req.getParameter("id"));
        User user = new User();
        user.setId(id);
        user.setId_personal(Integer.parseInt(req.getParameter("id_personal")));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("role"));
        user.setEmail(req.getParameter("email"));
        int count = 0;
        if (count != 0) {
            logger.trace("true");
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/list"));
        } else {
            logger.trace("false");
            req.getRequestDispatcher("/users/error.jsp").forward(req, resp);

        }
    }
}
