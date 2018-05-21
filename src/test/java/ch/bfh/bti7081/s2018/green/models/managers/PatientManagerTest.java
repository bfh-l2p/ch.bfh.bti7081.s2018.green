package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PatientManagerTest {

    private List<Patient> insertedPatients = new ArrayList<>();
    private List<Person> insertedPeople = new ArrayList<>();

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
        int testPatientId = getTestPatientId();
        PatientManager patientManager = new PatientManager();
        Patient patient = patientManager.get(testPatientId);
        Assert.assertEquals(testPatientId, patient.getId());
    }

    @Test
    public void findAll() throws Exception {
        PatientManager patientManager = new PatientManager();
        List<Patient> patientList = patientManager.findAll();

        insertedPatients.forEach(p1 -> {
            Assert.assertTrue(
                    patientList.stream().anyMatch(p2 -> p1.getId() == p2.getId())
            );
        });
    }

    @Test
    public void add() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dobPerson = new Date(format.parse("14.10.1991").getTime());
        Person person = new Person(
                "Martin",
                "Scheck",
                dobPerson,
                "Chutzenstrasse 27",
                "3007",
                "Bern",
                "martinscheck91@gmail.com",
                "0798340599"
        );
        PersonManager personManager = new PersonManager();
        personManager.add(person);
        insertedPeople.add(person);

        Date dobPatient = new Date(format.parse("14.10.1991").getTime());
        Patient patient = new Patient(
                "So much fun testing",
                "Last",
                dobPatient,
                "Street 1",
                "4455",
                "City",
                "info@example.com",
                "0777777777",
                person
        );
        PatientManager patientManager = new PatientManager();
        patientManager.add(patient);
        insertedPatients.add(patient);

        Assert.assertEquals(
                patient.getFirstName(),
                patientManager.get(patient.getId()).getFirstName()
        );
    }

    @Test
    public void update() throws Exception {
        int testPatientId = getTestPatientId();
        String s = "Testing is fun";

        PatientManager patientManager = new PatientManager();
        Patient patient = patientManager.get(testPatientId);

        patient.setFirstName(s);
        patientManager.update(patient);

        Patient updatedPatient = patientManager.get(testPatientId);
        Assert.assertEquals(s, updatedPatient.getFirstName());
    }

    @Test
    public void remove() throws Exception {
        PatientManager patientManager = new PatientManager();
        Integer i = insertedPatients.size() - 1;
        Patient patient = insertedPatients.get(i);
        patientManager.remove(patient);
        List<Patient> patientList = patientManager.findAll();
        Assert.assertTrue(patientList.stream().noneMatch(p -> p.getId() == patient.getId()));
        insertedPatients.remove(patient);
    }

    /**
     * Insert some test data
     */
    private void addTestData() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dobPerson = new Date(format.parse("14.10.1991").getTime());
        Person person = new Person(
                "Martin",
                "Scheck",
                dobPerson,
                "Chutzenstrasse 27",
                "3007",
                "Bern",
                "martinscheck91@gmail.com",
                "0798340599"
        );
        PersonManager personManager = new PersonManager();
        personManager.add(person);
        insertedPeople.add(person);

        Date dobPatient = new Date(format.parse("14.10.1991").getTime());
        Patient patient = new Patient(
                "First",
                "Last",
                dobPatient,
                "Street 1",
                "4455",
                "City",
                "info@example.com",
                "0777777777",
                person
        );
        PatientManager patientManager = new PatientManager();
        patientManager.add(patient);
        insertedPatients.add(patient);
    }

    /**
     * Remove all patients and people inserted during the test
     */
    private void removeTestData() {
        PatientManager patientManager = new PatientManager();
        insertedPatients.forEach(patientManager::remove);
        insertedPatients = new ArrayList<>();

        PersonManager personManager = new PersonManager();
        insertedPeople.forEach(personManager::remove);
        insertedPeople = new ArrayList<>();
    }

    /**
     * Get the id of the last patient inserted
     *
     * @return id
     */
    private int getTestPatientId() {
        return insertedPatients.get(insertedPatients.size() - 1).getId();
    }
}