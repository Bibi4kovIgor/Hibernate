package main;

import entity.Greeter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class Application {

    public static void main(String[] args) {
        save();
    }

    private static void save() {
        final Greeter greeterJpa = new Greeter();
        greeterJpa.setGreeting("Bye");
        greeterJpa.setTarget("JPA");

        final Greeter greeterHibernate = new Greeter();
        greeterJpa.setGreeting("Hello");
        greeterJpa.setTarget("Hibernate");

        final SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(greeterJpa);
        session.persist(greeterHibernate);
        session.getTransaction().commit();
        session.close();
    }
}
