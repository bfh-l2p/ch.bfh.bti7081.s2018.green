package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class RecurringEventTest {
    @Test
    public void testRecurringEvent() {
        LocalDateTime start = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop = LocalDateTime.of(2018, 5, 21, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        RecurringEvent event1 = new RecurringEvent(start, stop, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, null);
        RecurringEvent event2 = new RecurringEvent(start, stop, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, event1);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // insert test record
        tx.begin();
        em.persist(person);
        em.persist(patient);
        em.persist(staff);

        em.persist(event1);
        em.persist(event2);
        tx.commit();

        // read test record
        tx.begin();
        RecurringEvent eventT2 = em.find(RecurringEvent.class, event2.getId());
        tx.commit();

        // test if persisted and read are identical
        Assert.assertEquals(event1, eventT2.getNext());

        // remove test record
        tx.begin();
        em.remove(event1);
        em.remove(event2);
        tx.commit();
        em.close();
    }
}