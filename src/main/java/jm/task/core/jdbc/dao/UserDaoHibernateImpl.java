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

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(create_table);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        String drop_table = "DROP TABLE IF EXISTS users";
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(drop_table);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        System.out.printf("User с именем – %s добавлен в базу данных\n", name);
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        System.out.println(users);
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
    }
}
