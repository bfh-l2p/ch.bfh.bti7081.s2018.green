package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;

public class EventTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEvent() {
        LocalDateTime start = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop = LocalDateTime.of(2018, 5, 21, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event = new Event(start, stop, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // insert test record
        tx.begin();
        em.persist(person);
        em.persist(patient);
        em.persist(staff);

        em.persist(event);
        tx.commit();

        // read test record
        tx.begin();
        Event event2 = em.find(Event.class, event.getId());
        tx.commit();

        // remove test record
        tx.begin();
        em.remove(event);
        tx.commit();
        em.close();

        // test if persisted and read are identical
        Assert.assertEquals(event.getId(), event2.getId());
        Assert.assertEquals("2018-05-21T13:45", event2.getStart().toString());

        // test IllegalArgumentException
        Event illegalEvent = new Event(stop, start, "I'm illegal", "start after stop", patient, staff);
    }
}