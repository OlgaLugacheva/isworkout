package models.dao;

import connector.Connector;
import models.pojo.Person;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 24.02.2017.
 */
public class DAOPerson {
    private static Logger logger = Logger.getLogger(DAOPerson.class);

    private static String SQL_ALL_persons = "SELECT * FROM person";

    public static List<Person> getAllPersons(){
        List<Person> personList = new ArrayList<Person>();
        try(Connection connection = Connector.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_ALL_persons);

            while(resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Person person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth"),
                        resultSet.getInt("id_phisics")
                );
                personList.add(person);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return personList;
    }


}
