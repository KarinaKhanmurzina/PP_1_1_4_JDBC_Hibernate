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

    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        connection.setAutoCommit(false);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.close();
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        connection.setAutoCommit(false);
        try {
            String SQL = "DROP TABLE users";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute(SQL);
            ps.close();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = Util.getConnection();
        connection.setAutoCommit(false);
        try {
            Random random = new Random();
            Long num = random.nextLong(100);
            String SQL = String.format("INSERT INTO users values(%d, '%s', '%s', %d)", num, name, lastName, age);
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute(SQL);
            ps.close();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.close();
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = Util.getConnection();
        connection.setAutoCommit(false);
        try {
            String SQL = "DELETE FROM users WHERE id = id";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute(SQL);
            ps.close();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.close();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = Util.getConnection();
        connection.setAutoCommit(false);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.close();
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        connection.setAutoCommit(false);
        try {
            String SQL = "DELETE FROM users";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute(SQL);
            ps.close();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.close();
    }
}
