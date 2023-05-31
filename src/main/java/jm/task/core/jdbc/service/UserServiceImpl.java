package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao uDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        uDao.createUsersTable();
    }

    public void dropUsersTable() {
        uDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        uDao.saveUser(name, lastName, age);
        System.out.printf("User с именем – %s добавлен в базу данных\n", name);
    }

    public void removeUserById(long id) {
        uDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return uDao.getAllUsers();
    }

    public void cleanUsersTable() {
        uDao.cleanUsersTable();
    }
}
