package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "CREATE TABLE users(\n" +
                    "id bigint,\n" +
                    "name varchar(50),\n" +
                    "lastname varchar(50),\n" +
                    "age tinyint\n" +
                    ")";
            statement.executeUpdate(SQL);
            statement.close();
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DROP TABLE users";
            statement.executeUpdate(SQL);
            statement.close();
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = connection.createStatement();
            Random random = new Random();
            Long num = random.nextLong(100);
            String SQL = String.format("INSERT INTO users values(%d, '%s', '%s', %d)", num, name, lastName, age);
            statement.executeUpdate(SQL);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM users WHERE id = id";
            statement.executeUpdate(SQL);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM users";
            statement.executeUpdate(SQL);
            statement.close();               
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
