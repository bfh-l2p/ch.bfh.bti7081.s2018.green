package ch.bfh.bti7081.s2018.green.models.entities;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class PersonTest {

    @Test
    public void testPerson() throws ClassNotFoundException, ParseException {
        LocalDate dob = LocalDate.of(1989, 1, 20);
        Person person = new Person(
                "testestCyrill",
                "testestBolliger",
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

        // delete test record
        tx.begin();
        em.remove(person);
        tx.commit();
        em.close();

        // test if persisted and read person are identical
        Assert.assertEquals(testPerson.getId(), person.getId());
        Assert.assertEquals(testPerson.getFirstName(), person.getFirstName());

        // test date persistence
        Assert.assertEquals(testPerson.getDob().toString(), "1989-01-20");
    }
}