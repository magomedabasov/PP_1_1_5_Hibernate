package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService uService = new UserServiceImpl();
        uService.createUsersTable();
        uService.saveUser("Harry", "LastName0", (byte) 51);
        uService.saveUser("Tom", "LastName1", (byte) 20);
        uService.saveUser("Bill", "LastName2", (byte) 25);
        uService.saveUser("Bob", "LastName3", (byte) 31);
        uService.getAllUsers().forEach(System.out::println);
        uService.cleanUsersTable();
        uService.dropUsersTable();
        }
    }
