package controllers.workouts;

import models.pojo.Workout;
import org.apache.log4j.Logger;
import services.user.WorkoutService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Olga on 03.03.2017.
 */
public class WorkoutServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(WorkoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Workout> workouts= WorkoutService.getAllWorkouts();
        logger.debug(workouts.size());
        req.setAttribute("workouts", workouts);
        req.getRequestDispatcher("/workouts/workouts.jsp").forward(req, resp);;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
