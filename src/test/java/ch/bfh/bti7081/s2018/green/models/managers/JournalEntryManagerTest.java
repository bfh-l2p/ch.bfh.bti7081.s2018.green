package ch.bfh.bti7081.s2018.green.models.managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Person;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

public class JournalEntryManagerTest {
	private List<Person> insertedPeople = new ArrayList<>();
	private List<Patient> insertedPatients = new ArrayList<>();
	private List<Staff> insertedStaff = new ArrayList<>();
	private List<JournalEntry> insertedJournalEntries = new ArrayList<>();

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
		int testJournalEntryId = getTestJournalEntryId();
		JournalEntryManager journalEntryManager = new JournalEntryManager();
		JournalEntry journalEntry = journalEntryManager.get(testJournalEntryId);
		Assert.assertEquals(testJournalEntryId, journalEntry.getId());
	}

	@Test
	public void findAll() throws Exception {
		JournalEntryManager journalEntryManager = new JournalEntryManager();
		List<JournalEntry> journalEntryList = journalEntryManager.findAll();
		journalEntryList.stream().forEach(s -> System.out.println(s.getId()));

		journalEntryList.forEach(je1 -> {
			Assert.assertTrue(journalEntryList.stream().anyMatch(je2 -> je1.getId() == je2.getId()));
		});
	}
	
	@Test
	public void findByPatient() throws Exception {
		Patient patient = insertedPatients.get(insertedPatients.size() - 1);
		List<JournalEntry> journalEntryList = new JournalEntryManager().findBy(patient);
		journalEntryList.forEach(je1 -> {
			Assert.assertTrue(journalEntryList.stream().anyMatch(je2 -> je1.getId() == je2.getId()));
		});
	}
	
	@Test
	public void findByStaff() throws Exception {
		Staff staff = insertedStaff.get(0);
		List<JournalEntry> journalEntryList = new JournalEntryManager().findBy(staff);
		journalEntryList.forEach(je1 -> {
			Assert.assertTrue(journalEntryList.stream().anyMatch(je2 -> je1.getId() == je2.getId()));
		});
	}
	
	@Test
	public void findByDate() throws Exception {
		LocalDate today = LocalDate.now();
		List<JournalEntry> journalEntryList = new JournalEntryManager().findBy(today);
		journalEntryList.forEach(je1 -> {
			Assert.assertTrue(journalEntryList.stream().anyMatch(je2 -> je1.getId() == je2.getId()));
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
		
		JournalEntry journalEntry = new JournalEntry("TestEintrag", patient, staff);
		JournalEntryManager journalEntryManager = new JournalEntryManager();
		journalEntryManager.add(journalEntry);
		insertedJournalEntries.add(journalEntry);

		Assert.assertEquals(journalEntry.getId(), journalEntryManager.get(journalEntry.getId()).getId());
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
		
		JournalEntry journalEntry = new JournalEntry("TestEintrag", patient, staff);
		JournalEntryManager journalEntryManager = new JournalEntryManager();
		journalEntryManager.add(journalEntry);
		insertedJournalEntries.add(journalEntry);
	}

	/**
	 * Remove all patients and people inserted during the test
	 */
	private void removeTestData() {
		JournalEntryManager journalEntryManager = new JournalEntryManager();
    	EntityTransaction updateTransaction = journalEntryManager.beginTransaction();
    	insertedJournalEntries.forEach(je -> journalEntryManager.manager.remove(journalEntryManager.manager.contains(je) ? je : journalEntryManager.manager.merge(je)));
        journalEntryManager.closeTransaction(updateTransaction);
        
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
	private int getTestJournalEntryId() {
		return insertedJournalEntries.get(insertedJournalEntries.size() - 1).getId();
	}
}
