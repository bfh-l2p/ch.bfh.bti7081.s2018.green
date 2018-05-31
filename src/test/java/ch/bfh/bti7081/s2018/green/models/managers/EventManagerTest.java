package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Person;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventManagerTest {
    private List<Person> insertedPeople = new ArrayList<>();
    private List<Patient> insertedPatients = new ArrayList<>();
    private List<Staff> insertedStaff = new ArrayList<>();
    private List<Event> insertedEvents = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        addTestData();
    }

    @After
    public void tearDown() throws Exception {
        removeTestData();
    }

    @Test
    public void get() throws Exception {
        int testEventId = getTestEventId();
        EventManager eventManager = new EventManager();
        Event event = eventManager.get(testEventId);
        Assert.assertEquals(testEventId, event.getId().intValue());
    }

    @Test
    public void findAll() throws Exception {
        EventManager eventManager = new EventManager();
        List<Event> eventList = eventManager.findAll();
        eventList.stream().forEach(s -> System.out.println(s.getId()));

        eventList.forEach(ev1 -> {
            Assert.assertTrue(
                    eventList.stream().anyMatch(ev2 -> ev1.getId().intValue() == ev2.getId().intValue()));
        });
    }

    @Test
    public void findByPatient() throws Exception {
        Patient patient = insertedPatients.get(insertedPatients.size() - 1);
        List<Event> eventList = new EventManager().findBy(patient);
        eventList.forEach(ev1 -> {
            Assert.assertTrue(
                    eventList.stream().anyMatch(ev2 -> ev1.getId().intValue() == ev2.getId().intValue()));
        });
    }

    @Test
    public void findByStaff() throws Exception {
        Staff staff = insertedStaff.get(0);
        List<Event> eventList = new EventManager().findBy(staff);
        eventList.forEach(ev1 -> {
            Assert.assertTrue(
                    eventList.stream().anyMatch(ev2 -> ev1.getId().intValue() == ev2.getId().intValue()));
        });
    }

    @Test
    public void findByDate() throws Exception {
        EventManager eventManager = new EventManager();

        Patient patient = new PatientManager().findAll().get(0);
        Staff staff = new StaffManager().findAll().get(0);

        LocalDateTime start1 = LocalDateTime.of(2018, 5, 30, 12, 0);
        LocalDateTime stop1 = LocalDateTime.of(2018, 5, 30, 14, 0);
        Event event1 = new Event(start1, stop1, "fully in the given date", "in day", patient, staff);
        eventManager.add(event1);
        insertedEvents.add(event1);

        LocalDateTime start2 = LocalDateTime.of(2018, 5, 30, 12, 0);
        LocalDateTime stop2 = LocalDateTime.of(2018, 5, 31, 14, 0);
        Event event2 = new Event(start2, stop2, "stops the day after", "stop after", patient, staff);
        eventManager.add(event2);
        insertedEvents.add(event2);

        LocalDateTime start3 = LocalDateTime.of(2018, 5, 29, 12, 0);
        LocalDateTime stop3 = LocalDateTime.of(2018, 5, 30, 14, 0);
        Event event3 = new Event(start3, stop3, "starts the day before", "start before", patient, staff);
        eventManager.add(event3);
        insertedEvents.add(event3);

        LocalDateTime start4 = LocalDateTime.of(2018, 5, 29, 12, 0);
        LocalDateTime stop4 = LocalDateTime.of(2018, 5, 31, 14, 0);
        Event event4 = new Event(start4, stop4, "starts before stops after", "start before, stop after", patient, staff);
        eventManager.add(event4);
        insertedEvents.add(event4);

        LocalDateTime start5 = LocalDateTime.of(2018, 5, 5, 12, 0);
        LocalDateTime stop5 = LocalDateTime.of(2018, 5, 5, 14, 0);
        Event event5 = new Event(start5, stop5, "should not be in the list", "no interference", patient, staff);
        eventManager.add(event5);
        insertedEvents.add(event5);

        LocalDate today = LocalDate.parse("2018-05-30");
        List<Event> eventList = new EventManager().findBy(today);

        Assert.assertTrue(eventList.stream().anyMatch(e -> event1.getId().equals(e.getId())));
        Assert.assertTrue(eventList.stream().anyMatch(e -> event2.getId().equals(e.getId())));
        Assert.assertTrue(eventList.stream().anyMatch(e -> event3.getId().equals(e.getId())));
        Assert.assertTrue(eventList.stream().anyMatch(e -> event4.getId().equals(e.getId())));
        Assert.assertFalse(eventList.stream().anyMatch(e -> event5.getId().equals(e.getId())));
    }

    @Test
    public void add() throws Exception {
        LocalDate dobPerson = LocalDate.of(1991, 10, 14);
        Person person = new Person("Martin", "Scheck", dobPerson, "Chutzenstrasse 27", "3007", "Bern",
                "martinscheck91@gmail.com", "0798340599");
        PersonManager personManager = new PersonManager();
        personManager.add(person);
        insertedPeople.add(person);

        LocalDate dobPatient = LocalDate.of(1991, 10, 14);
        Patient patient = new Patient("So much fun testing", "Last", dobPatient, "Street 1", "4455", "City",
                "info@example.com", "0777777777", person);
        PatientManager patientManager = new PatientManager();
        patientManager.add(patient);
        insertedPatients.add(patient);

        LocalDate dobStaff = LocalDate.of(1991, 10, 16);
        Staff staff = new Staff("Martin", "Scheck", dobStaff, "Chutzenstrasse 27", "3007", "Bern",
                "martinscheck91@gmail.com", "0798340599", StaffType.PSYCHOLOGIST);
        StaffManager staffManager = new StaffManager();
        staffManager.add(staff);
        insertedStaff.add(staff);

        LocalDateTime start = LocalDateTime.of(1991, 10, 14, 12, 0);
        LocalDateTime stop = LocalDateTime.of(1991, 10, 14, 14, 0);
        Event event = new Event(start, stop, "etwas besprechen", "besprechung", patient, staff);
        EventManager eventManager = new EventManager();
        eventManager.add(event);
        insertedEvents.add(event);

        Assert.assertEquals(event.getId(), eventManager.get(event.getId()).getId());
    }

    /**
     * Insert some test data
     */
    private void addTestData() throws Exception {
        LocalDate dobPerson = LocalDate.of(1991, 10, 14);
        Person person = new Person("Martin", "Scheck", dobPerson, "Chutzenstrasse 27", "3007", "Bern",
                "martinscheck91@gmail.com", "0798340599");
        PersonManager personManager = new PersonManager();
        personManager.add(person);
        insertedPeople.add(person);

        LocalDate dobPatient = LocalDate.of(1991, 10, 14);
        Patient patient = new Patient("So much fun testing", "Last", dobPatient, "Street 1", "4455", "City",
                "info@example.com", "0777777777", person);
        PatientManager patientManager = new PatientManager();
        patientManager.add(patient);
        insertedPatients.add(patient);

        LocalDate dobStaff = LocalDate.of(1991, 10, 16);
        Staff staff = new Staff("Martin", "Scheck", dobStaff, "Chutzenstrasse 27", "3007", "Bern",
                "martinscheck91@gmail.com", "0798340599", StaffType.PSYCHOLOGIST);
        StaffManager staffManager = new StaffManager();
        staffManager.add(staff);
        insertedStaff.add(staff);

        LocalDateTime start = LocalDateTime.of(2018, 5, 31, 12, 0);
        LocalDateTime stop = LocalDateTime.of(2018, 5, 31, 14, 0);
        Event event = new Event(start, stop, "etwas besprechen", "besprechung", patient, staff);
        EventManager eventManager = new EventManager();
        eventManager.add(event);
        insertedEvents.add(event);
    }

    /**
     * Remove all patients and people inserted during the test
     */
    private void removeTestData() {
        EventManager eventManager = new EventManager();
        EntityTransaction updateTransaction = eventManager.beginTransaction();
        insertedEvents.forEach(ev -> eventManager.manager
                .remove(eventManager.manager.contains(ev) ? ev : eventManager.manager.merge(ev)));
        eventManager.closeTransaction(updateTransaction);

        PatientManager patientManager = new PatientManager();
        insertedPatients.forEach(patientManager::remove);
        insertedPatients = new ArrayList<>();

        PersonManager personManager = new PersonManager();
        insertedPeople.forEach(personManager::remove);
        insertedPeople = new ArrayList<>();

        StaffManager staffManager = new StaffManager();
        insertedStaff.forEach(staffManager::remove);
        insertedStaff = new ArrayList<>();
    }

    /**
     * Get the id of the last patient inserted
     *
     * @return id
     */
    private int getTestEventId() {
        return insertedEvents.get(insertedEvents.size() - 1).getId();
    }

    @Test
    public void findConflicting() {
        EventManager eventManager = new EventManager();

        Patient patient = new PatientManager().findAll().get(0);
        Staff staff = new StaffManager().findAll().get(0);

        LocalDateTime start1 = LocalDateTime.of(2018, 5, 30, 12, 0);
        LocalDateTime stop1 = LocalDateTime.of(2018, 5, 30, 14, 0);
        Event event1 = new Event(start1, stop1, "fully in the given date", "in day", patient, staff);
        eventManager.add(event1);
        insertedEvents.add(event1);

        LocalDateTime start2 = LocalDateTime.of(2018, 5, 30, 12, 0);
        LocalDateTime stop2 = LocalDateTime.of(2018, 5, 31, 14, 0);
        Event event2 = new Event(start2, stop2, "stops the day after", "stop after", patient, staff);
        eventManager.add(event2);
        insertedEvents.add(event2);

        LocalDateTime start3 = LocalDateTime.of(2018, 5, 29, 12, 0);
        LocalDateTime stop3 = LocalDateTime.of(2018, 5, 30, 14, 0);
        Event event3 = new Event(start3, stop3, "starts the day before", "start before", patient, staff);
        eventManager.add(event3);
        insertedEvents.add(event3);

        LocalDateTime start4 = LocalDateTime.of(2018, 5, 29, 12, 0);
        LocalDateTime stop4 = LocalDateTime.of(2018, 5, 31, 14, 0);
        Event event4 = new Event(start4, stop4, "starts before stops after", "start before, stop after", patient, staff);
        eventManager.add(event4);
        insertedEvents.add(event4);

        LocalDateTime start5 = LocalDateTime.of(2018, 5, 5, 12, 0);
        LocalDateTime stop5 = LocalDateTime.of(2018, 5, 5, 14, 0);
        Event event5 = new Event(start5, stop5, "should not be in the list", "no interference", patient, staff);
        eventManager.add(event5);
        insertedEvents.add(event5);

        Patient patient1 = new PatientManager().findAll().get(1);
        Staff staff1 = new StaffManager().findAll().get(1);

        Event event6 = new Event(start1, stop1, "conflict same patient", "same patient", patient1, staff);
        eventManager.add(event6);
        insertedEvents.add(event6);

        Event event7 = new Event(start1, stop1, "conflict same staff", "same staff", patient, staff1);
        eventManager.add(event7);
        insertedEvents.add(event7);

        Event event8 = new Event(start1, stop1, "no conflict", "no conflict", patient1, staff1);
        eventManager.add(event8);
        insertedEvents.add(event8);

        List<Event> eventList = new EventManager().findConflicting(event1);

        Assert.assertTrue(eventList.stream().anyMatch(e -> event1.getId().equals(e.getId())));
        Assert.assertTrue(eventList.stream().anyMatch(e -> event2.getId().equals(e.getId())));
        Assert.assertTrue(eventList.stream().anyMatch(e -> event3.getId().equals(e.getId())));
        Assert.assertTrue(eventList.stream().anyMatch(e -> event4.getId().equals(e.getId())));

        Assert.assertTrue(eventList.stream().anyMatch(e -> event6.getId().equals(e.getId())));
        Assert.assertTrue(eventList.stream().anyMatch(e -> event7.getId().equals(e.getId())));

        Assert.assertFalse(eventList.stream().anyMatch(e -> event5.getId().equals(e.getId())));
        Assert.assertFalse(eventList.stream().anyMatch(e -> event8.getId().equals(e.getId())));
    }
}
