package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

// реализуйте алгоритм здесь
public class Main {
    public static void main(String[] args) throws SQLException {
        UserService usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Tom", "White", (byte) 39);
        usi.saveUser("Lily", "Red", (byte) 34);
        usi.saveUser("Mike", "Black", (byte) 38);
        usi.saveUser("Kate", "Yellow", (byte) 41);
        System.out.println(usi.getAllUsers().toString());
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
