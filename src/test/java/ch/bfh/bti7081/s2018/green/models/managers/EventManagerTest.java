package ch.bfh.bti7081.s2018.green.models.managers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Person;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

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
		LocalDate today = LocalDate.now();
		List<Event> eventList = new EventManager().findBy(today);
		eventList.forEach(ev1 -> {
			Assert.assertTrue(
					eventList.stream().anyMatch(ev2 -> ev1.getId().intValue() == ev2.getId().intValue()));
		});
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

		LocalDateTime start = LocalDateTime.of(1991, 10, 14, 12, 00);
		LocalDateTime stop = LocalDateTime.of(1991, 10, 14, 14, 00);
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

		LocalDateTime start = LocalDateTime.of(1991, 10, 14, 12, 00);
		LocalDateTime stop = LocalDateTime.of(1991, 10, 14, 14, 00);
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
}
