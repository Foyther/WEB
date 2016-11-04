package Database;

import Entity.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.User;
import Exception.*;

/**
 * Created by Nurislam on 02.11.2016.
 */
public class ListEventsDao {
    private static ListEventsDao led;

    public ListEventsDao getInstance(){
        if(led == null){
            led = new ListEventsDao();
        }
        return led;
    }

    public Event[] getEvents()throws DBException, AlreadyExistException {
        Connection conn = SQLDatabase.getConnection();

        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM events");
            ResultSet resultSet = st.executeQuery();
            Event[] arrayEvent;
            if (resultSet != null) {
                if (resultSet.next()) {
                    if (resultSet.getString("password").equals(/*Encryptor.getHash(password, email)*/"TODO")) {
                        User user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("city"),
                                resultSet.getBoolean("sex"),
                                resultSet.getString("age")
                        );
                        return null; //ToDo
                    }
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }

    }
}
