package ch.bfh.bti7081.s2018.green.models.entities;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonTest {

    @Test
    public void testPerson() throws ClassNotFoundException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dob = new Date(format.parse("20.01.1989").getTime());
        Person person = new Person(
                "Cyrill",
                "Bolliger",
                dob,
                "Turnweg 19",
                "3013",
                "Bern",
                "bolliger@gmx.ch",
                "0788079297"
        );

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // insert test record
        tx.begin();
        em.persist(person);
        tx.commit();

        // read test record
        tx.begin();
        Person testPerson = em.find(Person.class, person.getId());
        tx.commit();
        em.close();

        // test if persisted and read person are identical
        Assert.assertEquals(testPerson.getId(), person.getId());
        Assert.assertEquals(testPerson.getFirstName(), person.getFirstName());

        // test date persistence
        Assert.assertEquals(testPerson.getDob().toString(), "1989-01-20");
    }
}