package models.dao;


import models.pojo.Exersise;
import org.apache.log4j.Logger;
import services.ConnectionPoolFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 24.02.2017.
 */
public class DAOExcersise {

    private static Logger logger = Logger.getLogger(DAOExcersise.class);

    private static String SQL_ALL_exersises = "SELECT * FROM exersises";

    public static List<Exersise> getAllExercises(){
        List<Exersise> exersiseList = new ArrayList<Exersise>();
        try(Connection connection = ConnectionPoolFactory.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_ALL_exersises);

            while(resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Exersise exersise = new Exersise(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("sets"),
                        resultSet.getInt("repeats")

                );
                exersiseList.add(exersise);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return exersiseList;
    }


}
