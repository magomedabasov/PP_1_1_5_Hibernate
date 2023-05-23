package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.*;


public class UserDaoJDBCImpl implements UserDao {

    String create_table = """
            create table if not exists users
            (
                id       BIGINT primary key auto_increment,
                name     varchar(40) not null,
                lastName varchar(40) not null,
                age      TINYINT     null
            );""";
    String drop_table = "drop table if exists users";
    String save_users = "insert into users(name, lastName, age) values(?, ?, ?)";
    String remove_user_by_id = "delete from users where id = ?";
    String clean_users_table = "truncate table users";
    String get_all_users = "select id, name, lastName, age from users";


    // Create table
    public void createUsersTable() {

        try(Statement ps = DriverManager
                .getConnection(URL, USER, PASSWORD)
                .createStatement()) {
            ps.execute(create_table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete table
    public void dropUsersTable() {
        try (Statement ps = DriverManager
                .getConnection(URL, USER, PASSWORD)
                .createStatement()) {
            ps.execute(drop_table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Save user in table
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = DriverManager
                .getConnection(URL, USER, PASSWORD)
                .prepareStatement(save_users)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();
            System.out.println("saveUser OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove user from table
    public void removeUserById(long id) {
        try(PreparedStatement ps  = DriverManager
                .getConnection(URL, USER, PASSWORD).prepareStatement(remove_user_by_id)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all users from table
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(PreparedStatement preparedStatement = DriverManager
                .getConnection(URL, USER, PASSWORD)
                .prepareStatement(get_all_users);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Clean users table
    public void cleanUsersTable() {
        try(Statement st = DriverManager.getConnection(URL, USER, PASSWORD).createStatement()) {
            st.execute(clean_users_table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
