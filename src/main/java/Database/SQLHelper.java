package Database;

import java.sql.Connection;

import Entity.User;
import Exception.*;
import Service.Encryptor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nurislam on 27.10.2016.
 */
public class SQLHelper {

    public boolean addUser(User user) throws DBException, AlreadyExistException {
        Connection conn = SQLDatabase.getConnection();
        if (!containsUser(user)) {
            try {
                PreparedStatement st = conn.prepareStatement(
                        "INSERT INTO user SET email=?, name=?, password=?, sex=?, city=?, age=?");
                int i = 1;
                st.setString(i++, user.getEmail());
                st.setString(i++, user.getName());
                st.setString(i++, user.getPassword());
                st.setBoolean(i++, user.getSex());
                st.setString(i++, user.getCity());
                st.setInt(i++, user.getAge());
                st.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DBException();
            }
        } else {
            throw new AlreadyExistException();
        }
        return false;
    }

    public User findByEmail(String email, String password) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM user WHERE email=? && password=?");
            int i = 1;
            st.setString(i++, email);
            st.setString(i++, password);
            ResultSet resultSet = st.executeQuery();
            if (resultSet != null) {
                if (resultSet.next()) {
                    if (resultSet.getString("password").equals(/*Encryptor.getHash(password, email)*/password)) {
                        User user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("city"),
                                resultSet.getBoolean("sex"),
                                resultSet.getString("age")
                        );
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return null;
    }

    public List<User> getUsersList() throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = st.executeQuery();
            List<User> userList = new LinkedList<User>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("city"),
                            resultSet.getInt("sex") == 1,
                            resultSet.getString("age")
                    );
                    userList.add(user);
                }
                return userList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return null;
    }

    public boolean containsUser(User user) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM user WHERE email=?");
            int i = 1;
            st.setString(i++, user.getEmail());
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null) {
                return false;
            } else {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public boolean delUser(String email, String password) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM user WHERE email=?");
            int i = 1;
            st.setString(i++, email);
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public boolean updateUser(User user) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        if (containsUser(user)) {
            try {
                PreparedStatement st = conn.prepareStatement("UPDATE user SET name=?, password=?, sex=?, city=?, age=? WHERE email=?");
                int i = 1;
                st.setString(i++, user.getName());
                st.setString(i++, user.getPassword());
                st.setBoolean(i++, user.getSex());
                st.setString(i++, user.getCity());
                st.setInt(i++, user.getAge());
                st.setString(i++, user.getEmail());
                st.execute();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DBException();
            }
        }
        return false;
    }
}
