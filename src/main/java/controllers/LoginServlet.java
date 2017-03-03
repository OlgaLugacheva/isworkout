package controllers;

import models.dao.exceptions.UserDAOException;
import models.pojo.User;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);
    static {
        DOMConfigurator.configure("log4j.xml");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          req.getRequestDispatcher("/users/login.jsp").forward(req,resp);
          logger.trace("loginGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = UserService.authorise(login,password);
            if (user.getId()!=0) {
                req.getSession().setAttribute("id",user.getId());
                logger.trace("authorized");
                resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/list"));
            } else {
                logger.trace("notauthorized");
                req.getRequestDispatcher("/users/login.jsp").forward(req,resp);
            }
        } catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/users/error.jsp");
        }
    }
}
