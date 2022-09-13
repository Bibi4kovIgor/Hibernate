package main;

import entity.Greeting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class Application {

    public static void main(String[] args) {
        save();
    }

    private static void save() {
        final Greeting greetingJpa = getGreeting("Bye", "JPA");

        final Greeting greetingHibernate = getGreeting("Hello", "Hibernate");

        final Greeting greetingTest = getGreeting("Test", "Test");

        final SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(greetingHibernate);
        session.persist(greetingJpa);
        session.persist(greetingTest);
        session.getTransaction().commit();
        session.close();
    }

    private static Greeting getGreeting(String Bye, String JPA) {
        final Greeting greetingJpa = new Greeting();
        greetingJpa.setGreeting(Bye);
        greetingJpa.setTarget(JPA);
        return greetingJpa;
    }
}
