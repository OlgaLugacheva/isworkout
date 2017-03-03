import connector.Connector;

import java.sql.*;

/**
 * Created by Olga on 03.03.2017.
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        Statement query =   Connector.getConnection().createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM users");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("login"));
        }



    }



}
