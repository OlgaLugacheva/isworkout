package models.dao;

import connector.Connector;
import models.dao.exceptions.UserDAOException;
import models.pojo.Workout;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 03.03.2017.
 */
public class DAOWorkout {
    private static Logger logger = Logger.getLogger(DAOWorkout.class);

    private static String SQL_ALL_workouts = "SELECT * FROM workouts";

    private static String SQL_NEARED_WORKOUT = "SELECT * FROM \"workoutplanner\".\"workouts\" WHERE date >? AND date <?";

    private static String SQL_DELETE_WORKOUT = "DELETE FROM \"workoutplanner\".\"workouts\" WHERE id = ?";

    private static String SQL_UPDATE_WORKOUT = "UPDATE \"workoutplanner\".\"workouts\"\n" +
            "\tSET id=?, workout=?, date=?, duration=?, intensity=?, id_exersise=?" +
            "\tWHERE id=?";

    private static String SQL_INSERT_WORKOUT = "INSERT INTO \"workoutplanner\".\"workouts\"(\n" +
            "\t workout, date, duration, intensity, id_exersise)\n" +
            "\tVALUES (?, ?, ?, ?,?);";

    private static String SQL_FIND_WORKOUT = "SELECT * FROM \"workoutplanner\".\"workouts\" WHERE id =?";



    public static int deleteWorkout(int id) {
        int count = 0;
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_WORKOUT)) {
            preparedStatement.setInt(1, id);

            count = preparedStatement.executeUpdate();
            logger.debug(id+" workout was deleted");
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static int updateWorkout(Workout workout){

        int count = 0;
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_WORKOUT)) {
            preparedStatement.setInt(1, workout.getId());
            preparedStatement.setInt(2, workout.getId_exersise());
            preparedStatement.setString(3, workout.getWorkout());
            preparedStatement.setDate(4, new Date(workout.getDate().getTime()));
            preparedStatement.setString(5, workout.getDuration());
            preparedStatement.setInt(6, workout.getIntensity());


            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static int insertWorkout(Workout workout){

        int count = 0;
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_WORKOUT)) {

            preparedStatement.setInt(1, workout.getId_exersise());
            preparedStatement.setString(2, workout.getWorkout());
            preparedStatement.setDate(3, new Date(workout.getDate().getTime()));
            preparedStatement.setString(4, workout.getDuration());
            preparedStatement.setInt(5, workout.getIntensity());

            count = preparedStatement.executeUpdate();
            logger.debug(workout.getId()+" workout was insert"+workout.getWorkout());
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static Workout getWorkoutById(int id) throws UserDAOException {

        logger.debug(id);
        Workout workout = null;
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_WORKOUT)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find workout"+id);
                workout = new Workout(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_exersise"),
                        resultSet.getString("workout"),
                        resultSet.getDate("date"),
                        resultSet.getString("duration"),
                        resultSet.getInt("intensity"));
            }else{
                logger.debug(id+" workout not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return workout;
    }

    public static List<Workout> getAllWorkouts(){
        List<Workout> workoutList = new ArrayList<Workout>();
        try(Connection connection = Connector.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_ALL_workouts);

            while(resultSet.next()) {
                logger.debug(resultSet.getString("workout"));

                Workout workout = new Workout(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_exersise"),
                        resultSet.getString("workout"),
                        resultSet.getDate("date"),
                        resultSet.getString("duration"),
                        resultSet.getInt("intensity")

                );
                workoutList.add(workout);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return workoutList;
    }


}
