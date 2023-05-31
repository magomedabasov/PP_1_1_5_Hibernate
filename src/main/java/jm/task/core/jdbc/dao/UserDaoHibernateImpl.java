package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.buildSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String create_table = """
                CREATE TABLE IF NOT EXISTS users
                (
                    id       BIGINT primary key auto_increment,
                    name     varchar(40) not null,
                    lastName varchar(40) not null,
                    age      TINYINT     null
                );""";

        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                Query query = session.createSQLQuery(create_table);
                query.executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Cant create table " + e.getMessage());
                session.getTransaction().rollback();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        String drop_table = "DROP TABLE IF EXISTS users";
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                Query query = session.createSQLQuery(drop_table);
                query.executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Cant delete table " + e.getMessage());
                session.getTransaction().rollback();
            }
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession();) {
            try {
                session.beginTransaction();
                User user = new User(name, lastName, age);
                session.save(user);
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Cant save User " + e.getMessage());
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.delete(user);
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Cant remove User by id " + e.getMessage());
                session.getTransaction().rollback();
            }
        }


    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.getCurrentSession();) {
            try {
                session.beginTransaction();
                users = session.createQuery("from User").getResultList();
                session.getTransaction().commit();
                System.out.println(users);
            } catch (Exception e) {
                System.out.println("Cant get all Users from table " + e.getMessage());
                session.getTransaction().rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                session.createQuery("delete User").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Cant clean table " + e.getMessage());
                session.getTransaction().rollback();
            }
        }
    }
}
