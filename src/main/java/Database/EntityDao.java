package Database;

import Entity.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nurislam on 31.10.2016.
 */

import Exception.*;

public class EntityDao {
    private static EntityDao entityDao;

    public static EntityDao getInstance() {
        if (entityDao == null) {
            entityDao = new EntityDao();
        }

        return entityDao;
    }

    private EntityDao() {

    }

    public boolean addEvent(Event event) throws DBException, AlreadyExistException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO events SET name=?, date=?, place=?, description=? ");
            int i = 1;
            st.setString(i++, event.getNameEvent());
            st.setString(i++, event.getDate());
            st.setString(i++, event.getPlace());
            st.setString(i++, event.getDescription());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return false;
    }

    public void deleteEvent(Event event) throws DBException, AlreadyExistException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(
                    "DELETE FROM user WHERE name=?, date=?, place=?, desription=?");
            int i = 1;
            st.setString(i++, event.getNameEvent());
            st.setString(i++, event.getDate());
            st.setString(i++, event.getPlace());
            st.setString(i++, event.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }
}
