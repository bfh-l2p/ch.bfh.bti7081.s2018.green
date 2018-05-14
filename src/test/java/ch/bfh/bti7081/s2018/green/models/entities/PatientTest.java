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

public class PatientTest {

    @Test
    public void testPatient() throws ClassNotFoundException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dob1 = new Date(format.parse("20.01.1989").getTime());
        Date dob2 = new Date(format.parse("25.05.1992").getTime());

        Person emergencyContact = new Person(
                "testtestEmergency",
                "testestContact",
                dob1,
                "Turnweg 19",
                "3013",
                "Bern",
                "econtact@gmx.ch",
                "0783339297"
        );

        Patient patient = new Patient(
                "testestPatient",
                "testestTestPerson",
                dob2,
                "Turnweg 19",
                "3013",
                "Bern",
                "patient@gmx.ch",
                "0792131312",
                emergencyContact
        );

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // insert test records
        tx.begin();
        em.persist(emergencyContact);
        tx.commit();

        tx.begin();
        em.persist(patient);
        tx.commit();

        // read test record
        tx.begin();
        Person testEmergencyContact = em.find(Person.class, emergencyContact.getId());
        tx.commit();

        tx.begin();
        Patient testPatient = em.find(Patient.class, patient.getId());
        tx.commit();

        // delete test record
        tx.begin();
        em.remove(emergencyContact);
        tx.commit();

        tx.begin();
        em.remove(patient);
        tx.commit();

        em.close();

        // test if persisted and read person are identical
        Assert.assertEquals(testPatient.getId(), patient.getId());
        Assert.assertEquals(testPatient.getFirstName(), patient.getFirstName());
        Assert.assertEquals(emergencyContact.getId(), patient.getEmergencyContact().getId());
        Assert.assertEquals(testEmergencyContact.getFirstName(), patient.getEmergencyContact().getFirstName());

        // test date persistence
        Assert.assertEquals(testPatient.getDob().toString(), "1992-05-25");
    }
}