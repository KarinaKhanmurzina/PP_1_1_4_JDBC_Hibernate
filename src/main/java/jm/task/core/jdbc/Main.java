package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

// реализуйте алгоритм здесь
public class Main {
    public static void main(String[] args) {

//В методе main класса Main должны происходить следующие операции:
//1. Создание таблицы User(ов)
        UserService usi = new UserServiceImpl();
        usi.createUsersTable();

//2. Добавление 4 User(ов) в таблицу с данными на свой выбор
//2.1 После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        String userName;
        userName = "Tom";
        usi.saveUser(userName, "White", (byte) 39);
        System.out.println(String.format("User с именем – %s добавлен в базу данных", userName));

        userName = "Lily";
        usi.saveUser(userName, "Red", (byte) 34);
        System.out.println(String.format("User с именем – %s добавлен в базу данных", userName));

        userName = "Mike";
        usi.saveUser(userName, "Black", (byte) 38);
        System.out.println(String.format("User с именем – %s добавлен в базу данных", userName));

        userName = "Kate";
        usi.saveUser(userName, "Yellow", (byte) 41);
        System.out.println(String.format("User с именем – %s добавлен в базу данных", userName));

//3. Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        System.out.println(usi.getAllUsers().toString());

//4. Очистка таблицы User(ов)
        usi.cleanUsersTable();

//5. Удаление таблицы
        usi.dropUsersTable();
    }
}
