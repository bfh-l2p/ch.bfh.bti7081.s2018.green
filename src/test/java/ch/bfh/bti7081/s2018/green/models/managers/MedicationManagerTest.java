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

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Person;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

public class MedicationManagerTest {
	private List<Person> insertedPeople = new ArrayList<>();
	private List<Patient> insertedPatients = new ArrayList<>();
	private List<Staff> insertedStaff = new ArrayList<>();
	private List<Medication> insertedMecications = new ArrayList<>();

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
		int testMedicationId = getTestMedicationId();
		MedicationManager medicationManager = new MedicationManager();
		Medication medication = medicationManager.get(testMedicationId);
		Assert.assertEquals(testMedicationId, medication.getId());
	}

	@Test
	public void findAll() throws Exception {
		MedicationManager medicationManager = new MedicationManager();
		List<Medication> medicationList = medicationManager.findAll();
		medicationList.stream().forEach(s -> System.out.println(s.getId()));

		medicationList.forEach(ev1 -> {
			Assert.assertTrue(
					medicationList.stream().anyMatch(ev2 -> ev1.getId() == ev2.getId()));
		});
	}

	@Test
	public void findByPatient() throws Exception {
		Patient patient = insertedPatients.get(insertedPatients.size() - 1);
		List<Medication> medicationList = new MedicationManager().findBy(patient);
		medicationList.forEach(ev1 -> {
			Assert.assertTrue(
					medicationList.stream().anyMatch(ev2 -> ev1.getId() == ev2.getId()));
		});
	}

	@Test
	public void findByStaff() throws Exception {
		Staff staff = insertedStaff.get(0);
		List<Medication> medicationList = new MedicationManager().findBy(staff);
		medicationList.forEach(ev1 -> {
			Assert.assertTrue(
					medicationList.stream().anyMatch(ev2 -> ev1.getId() == ev2.getId()));
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
		LocalDateTime stop = LocalDateTime.of(1991, 10, 18, 14, 00);
		Medication medication = new Medication("somedrug", start, stop, 2, (float) 50.0, staff, patient);
		MedicationManager medicationManager = new MedicationManager();
		medicationManager.add(medication);
		insertedMecications.add(medication);

		Assert.assertEquals(medication.getId(), medicationManager.get(medication.getId()).getId());
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
		LocalDateTime stop = LocalDateTime.of(1991, 10, 18, 14, 00);
		Medication medication = new Medication("somedrug", start, stop, 2, (float) 50.0, staff, patient);
		MedicationManager medicationManager = new MedicationManager();
		medicationManager.add(medication);
		insertedMecications.add(medication);
	}

	/**
	 * Remove all patients and people inserted during the test
	 */
	private void removeTestData() {
		MedicationManager medicationManager = new MedicationManager();
		EntityTransaction updateTransaction = medicationManager.beginTransaction();
		insertedMecications.forEach(ev -> medicationManager.manager
				.remove(medicationManager.manager.contains(ev) ? ev : medicationManager.manager.merge(ev)));
		medicationManager.closeTransaction(updateTransaction);

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
	private int getTestMedicationId() {
		return insertedMecications.get(insertedMecications.size() - 1).getId();
	}
}
