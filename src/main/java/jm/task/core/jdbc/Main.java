package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Harry", "LastName0", (byte) 51);
        usi.saveUser("Tom", "LastName1", (byte) 20);
        usi.saveUser("Bill", "LastName2", (byte) 25);
        usi.saveUser("Bob", "LastName3", (byte) 31);
        usi.getAllUsers().forEach(System.out::println);
        usi.cleanUsersTable();
        usi.dropUsersTable();
        }
    }
