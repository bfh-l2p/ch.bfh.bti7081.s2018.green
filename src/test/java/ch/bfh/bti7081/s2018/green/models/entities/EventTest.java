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
    @Test
    public void setStart_legalArguments_ShouldSucceed() {
        LocalDateTime start = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop = LocalDateTime.of(2018, 5, 21, 14, 30);
        LocalDateTime newStart = LocalDateTime.of(2018, 5, 21, 12, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event = new Event(start, stop, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff);
        event.setStart(newStart);

        Assert.assertEquals(newStart, event.getStart());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStart_stopAsStop_ThrowsException() {
        LocalDateTime start = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop = LocalDateTime.of(2018, 5, 21, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event = new Event(start, stop, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff);
        event.setStart(stop);
    }

    @Test
    public void setStop_legalArguments_ShouldSucceed() {
        LocalDateTime start = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop = LocalDateTime.of(2018, 5, 21, 14, 30);
        LocalDateTime newStop = LocalDateTime.of(2018, 5, 21, 15, 30);


        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event = new Event(start, stop, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff);
        event.setStop(newStop);

        Assert.assertEquals(newStop, event.getStop());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStop_startAsStop_ThrowsException() {
        LocalDateTime start = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop = LocalDateTime.of(2018, 5, 21, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event = new Event(start, stop, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff);
        event.setStop(start);

        Assert.assertEquals(stop, event.getStop());
    }

    @Test
    public void creatingEventInDb_legalArgument_ShouldSucceed() {
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
        em.remove(patient);
        em.remove(staff);
        em.remove(person);
        tx.commit();
        em.close();

        // test if persisted and read are identical
        Assert.assertEquals(event.getId(), event2.getId());
        Assert.assertEquals("2018-05-21T13:45", event2.getStart().toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void creatingEvent_stopBeforeStartParameter_ThrowsException() {
        LocalDateTime start = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop = LocalDateTime.of(2018, 5, 21, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        // test IllegalArgumentException
        new Event(stop, start, "I'm illegal", "start after stop", patient, staff);
    }

    @Test
    public void setNext_legalArgument_ShouldSucceed() {
        LocalDateTime start1 = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop1 = LocalDateTime.of(2018, 5, 21, 14, 30);

        LocalDateTime start2 = LocalDateTime.of(2018, 5, 22, 13, 45);
        LocalDateTime stop2 = LocalDateTime.of(2018, 5, 22, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event1 = new Event(start1, stop1, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff);
        Event event2 = new Event(start2, stop2, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, null);
        event1.setNext(event2);

        Assert.assertEquals(event1.getNext(), event2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNext_IllegalArgument_ThrowsException() {
        LocalDateTime start1 = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop1 = LocalDateTime.of(2018, 5, 21, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event1 = new Event(start1, stop1, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, null);
        event1.setNext(event1);
    }

    @Test
    public void creatingEvent_legalArgument_ShouldSucceed() {
        LocalDateTime start1 = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime start2 = LocalDateTime.of(2018, 5, 22, 13, 45);
        LocalDateTime stop1 = LocalDateTime.of(2018, 5, 21, 14, 30);
        LocalDateTime stop2 = LocalDateTime.of(2018, 5, 22, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event2 = new Event(start2, stop2, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, null);
        Event event1 = new Event(start1, stop1, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, event2);

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
        Event eventT1 = em.find(Event.class, event1.getId());
        tx.commit();

        // test if persisted and read are identical
        Assert.assertEquals(event2, eventT1.getNext());

        // remove test record
        tx.begin();
        em.remove(event1);
        em.remove(event2);
        em.remove(patient);
        em.remove(staff);
        em.remove(person);
        tx.commit();
        em.close();

        // test IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingEvent_IllegalArgument_ThrowsException() {
        LocalDateTime start2 = LocalDateTime.of(2018, 5, 22, 13, 45);
        LocalDateTime stop2 = LocalDateTime.of(2018, 5, 22, 14, 30);

        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);

        Event event2 = new Event(start2, stop2, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, null);

        // test IllegalArgumentException
        new Event(start2, stop2, "illegal", "illegal", patient, staff, event2);
    }
}