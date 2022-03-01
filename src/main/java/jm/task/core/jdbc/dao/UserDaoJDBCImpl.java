package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try {
            Connection connection = Util.getConnection();
            String SQL = "CREATE TABLE users(\n" +
                    "id bigint,\n" +
                    "name varchar(50),\n" +
                    "lastname varchar(50),\n" +
                    "age tinyint\n" +
                    ")";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute(SQL);
            ps.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Connection connection = Util.getConnection();
            String SQL = "DROP TABLE users";
            PreparedStatement ps = connection.prepareStatement(SQL);
            connection.setAutoCommit(false);
            ps.execute(SQL);
            ps.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Connection connection = Util.getConnection();
            Random random = new Random();
            Long num = random.nextLong(100);
            String SQL = String.format("INSERT INTO users values(%d, '%s', '%s', %d)", num, name, lastName, age);
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute(SQL);
            ps.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Connection connection = Util.getConnection();
            String SQL = "DELETE FROM users WHERE id = id";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute(SQL);
            ps.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            Connection connection = Util.getConnection();
            String SQL = "SELECT * FROM users";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            ps.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            Connection connection = Util.getConnection();
            String SQL = "DELETE FROM users";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute(SQL);
            ps.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
