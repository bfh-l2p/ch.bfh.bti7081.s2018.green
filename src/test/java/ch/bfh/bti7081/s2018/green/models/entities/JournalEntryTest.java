package ch.bfh.bti7081.s2018.green.models.entities;

import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

public class JournalEntryTest {

    @Test
    public void testJournalEntry() throws ClassNotFoundException, ParseException {
        Person emergencyContact = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient matthias = new Patient("Patrice", "lastname", null, null, null, null, null, null, emergencyContact);
        Staff exampleDoctor = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);
        JournalEntry entry = new JournalEntry("Halluzinationen", exampleDoctor);
        matthias.addJournalEntry(entry);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // insert test record
        tx.begin();
        em.persist(emergencyContact);
        em.persist(matthias);
        em.persist(exampleDoctor);
        em.persist(entry);
        tx.commit();

        // read test record
        tx.begin();
        JournalEntry testJournalEntry = em.find(JournalEntry.class, entry.getId());
        tx.commit();
        em.close();

        // test if persisted and read person are identical
        Assert.assertEquals(testJournalEntry.getId(), entry.getId());
        Assert.assertEquals(testJournalEntry.getContent(), entry.getContent());
    }
}
