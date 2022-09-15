package main;

import entity.GreetingEntity;
import entity.MyEntity;
import entity.GreeterEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Set;

import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class Application {

    public static void main(String[] args) {
        save();
    }

    private static void save() {

        final GreeterEntity greeter = new GreeterEntity();
        greeter.setName("Ihor");

        final GreetingEntity greetingEntityJpa = new GreetingEntity();
        greetingEntityJpa.setGreeting("Hello JPA");
        greetingEntityJpa.setTarget("JPA");
        greetingEntityJpa.setGreeter(greeter);

        final GreetingEntity greetingEntityHibernate = new GreetingEntity();
        greetingEntityHibernate.setGreeting("Hello Hibernate!");
        greetingEntityHibernate.setTarget("Hibernate");
        greetingEntityHibernate.setGreeter(greeter);

        final GreetingEntity greetingEntityTest = new GreetingEntity();
        greetingEntityTest.setGreeting("Hello test!");
        greetingEntityTest.setTarget("Test");
        greetingEntityTest.setGreeter(greeter);

        final SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(greeter);
        session.persist(greetingEntityHibernate);
        session.persist(greetingEntityJpa);
        session.persist(greetingEntityTest);

        session.getTransaction().commit();
        session.close();
    }




}
