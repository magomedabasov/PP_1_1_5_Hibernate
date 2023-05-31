package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("user1", "lname1", (byte) 18);
        userService.saveUser("user1", "lname1", (byte) 18);
        userService.saveUser("user1", "lname1", (byte) 18);
        userService.saveUser("user1", "lname1", (byte) 18);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
