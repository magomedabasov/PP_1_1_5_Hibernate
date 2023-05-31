package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();


    // Create table
    public void createUsersTable() {
        String create_table = """
                create table if not exists users
                (
                    id       BIGINT primary key auto_increment,
                    name     varchar(40) not null,
                    lastName varchar(40) not null,
                    age      TINYINT     null
                );""";
        try (PreparedStatement ps = connection.prepareStatement(create_table)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete table
    public void dropUsersTable() {
        String drop_table = "drop table if exists users";
        try (PreparedStatement ps = connection.prepareStatement(drop_table)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Save user in table
    public void saveUser(String name, String lastName, byte age) {
        String save_users = "insert into users(name, lastName, age) values(?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(save_users)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove user from table
    public void removeUserById(long id) {
        String remove_user_by_id = "delete from users where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(remove_user_by_id)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all users from table
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String get_all_users = "select id, name, lastName, age from users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(get_all_users);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Clean users table
    public void cleanUsersTable() {
        String clean_users_table = "truncate table users";
        try (PreparedStatement st = connection.prepareStatement(clean_users_table)) {
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
