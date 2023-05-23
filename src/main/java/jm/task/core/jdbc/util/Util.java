package jm.task.core.jdbc.util;

import com.mysql.cj.log.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE = "mydbtest";
    public static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    public static final String USER = "root";
    public static final String PASSWORD = "root";
}
