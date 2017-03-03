package services.user;

import models.dao.DAOWorkout;
import models.pojo.Workout;

import java.util.List;

/**
 * Created by Olga on 03.03.2017.
 */
public class WorkoutService {

    public static List<Workout> getAllWorkouts(){
        return DAOWorkout.getAllWorkouts();
    }

    public static int deleteWorkoutOnId(int id){

        return DAOWorkout.deleteWorkout(id);
    }

    public static int updateWorkoutOnId(Workout workout){

        return DAOWorkout.insertWorkout(workout);
    }

    public static int insertWorkout(Workout workout){

        return DAOWorkout.insertWorkout(workout);
    }

}
