package main;

import dto.GreeterDto;
import entity.AddressEntity;
import entity.GreetResponderEntity;
import entity.GreeterEntity;
import entity.GreetingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import utils.EntityToDto;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class Application {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = getSessionFactory();

        save(sessionFactory);
        printAllGreeters(sessionFactory);
//        printAllGreetingsFromGreeter(sessionFactory, "Ihor");
//        printAllGreetingsFromGreeter(sessionFactory, "Andrew");
        getHql(sessionFactory);
        getSql(sessionFactory);
        getAllGreeters(sessionFactory).forEach(System.out::println);
        getDataViaCriteria(sessionFactory);



    }

    private static void getDataViaCriteria(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<GreeterEntity> criteriaQuery = criteriaBuilder.createQuery(GreeterEntity.class);
        Root<GreeterEntity> greeterRoot = criteriaQuery.from(GreeterEntity.class);
        criteriaQuery.select(greeterRoot);
        session.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
        session.close();


    }

    private static List<GreeterDto> getAllGreeters(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        List<GreeterEntity> greeterEntityList = session.createQuery("from GreeterEntity", GreeterEntity.class).list();

        session.close();
        return greeterEntityList.stream()
                .map(EntityToDto::greeterEntityToDto)
                .toList();
    }

    private static void getSql(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.createNativeQuery("select count(id) as greetings_count from greeter",
                Integer.class).list().forEach(System.out::println);
    }

//        private static void printAllGreetingsFromGreeter(SessionFactory sessionFactory, String greeter) {
//        Session session = sessionFactory.openSession();
//        session.createQuery("from GreetingEntity as p1 " +
//                                " inner join GreeterEntity as p2 " +
//                                "where p2.name = :name",
//                        GreetingEntity.class)
//                        .setParameter("name", greeter)
//                .list().forEach(System.out::println);
//        session.close();
//    }
    private static void printAllGreeters(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.createQuery("from GreeterEntity", GreeterEntity.class)
                .list().forEach(System.out::println);
        session.close();
    }


    private static void getHql(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();

        List<GreeterEntity> result = session.createNamedQuery("selectAllGreets", GreeterEntity.class)
                .setParameter("name", "Ihor")
                .list();

        session.close();
        result.forEach(System.out::println);
    }

    private static void save(SessionFactory sessionFactory) {


        final AddressEntity address1 = new AddressEntity();
        address1.setCity("Kharkiv");
        address1.setCountry("Ukraine");

        final AddressEntity address2 = new AddressEntity();
        address1.setCity("Kiiv");
        address1.setCountry("Ukraine");


        final GreeterEntity greeterIhor = getGreeterEntity("Ihor");
        final GreeterEntity greeterAndrew = getGreeterEntity("Andrew");
        final GreeterEntity greeterSergey = getGreeterEntity("Sergey");

        final GreetResponderEntity greetResponderMarry = new GreetResponderEntity();;
        greetResponderMarry.setName("Marry");
        greetResponderMarry.setAddresses(Set.of(address1, address2));
        final GreetResponderEntity greetResponderJack = new GreetResponderEntity();
        greetResponderJack.setName("Jack");
        greetResponderJack.setAddresses(Set.of(address2, address1));


        final GreetingEntity greetingEntityJpa = getGreetingEntity("Hello JPA", "JPA", greeterIhor);

        final GreetingEntity greetingEntityHibernate = getGreetingEntity("Hello Hibernate!", "Hibernate", greeterAndrew);
        greetingEntityHibernate.setGreetResponder(greetResponderJack);

        final GreetingEntity greetingEntityTest = getGreetingEntity("Hello test!", "Test", greeterSergey);
        greetingEntityTest.setGreetResponder(greetResponderMarry);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(greetResponderMarry);
        session.persist(greetResponderJack);

        session.persist(address1);
        session.persist(address2);


        session.persist(greeterIhor);
        session.persist(greeterAndrew);
        session.persist(greeterSergey);

        session.persist(greetingEntityHibernate);
        session.persist(greetingEntityJpa);
        session.persist(greetingEntityTest);

        session.getTransaction().commit();
        session.close();
    }


//    private static GreetResponderEntity getGreetResponderEntity() {
//        final GreetResponderEntity responder1 = new GreetResponderEntity();
//        responder1.setAddresses(Set.of(new AddressEntity()));
//        responder1.setName("Name1");
//        return responder1;
//    }

    private static GreeterEntity getGreeterEntity(String greeterName) {
        final GreeterEntity greeter = new GreeterEntity();
        greeter.setName(greeterName);
        return greeter;
    }

    private static GreetingEntity getGreetingEntity(String greeting, String target, GreeterEntity greeter) {
        final GreetingEntity greetingEntityHibernate = new GreetingEntity();
        greetingEntityHibernate.setGreeting(greeting);
        greetingEntityHibernate.setTarget(target);
        greetingEntityHibernate.setGreeter(greeter);
        return greetingEntityHibernate;
    }


}
