package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class StaffTest {

    @Test
    public void testStaff() throws ClassNotFoundException, ParseException {
        LocalDate dob = LocalDate.of(1989, 1, 20);
        Staff staff = new Staff(
                "testestCyrill",
                "testestBolliger",
                dob,
                "Turnweg 19",
                "3013",
                "Bern",
                "bolliger@gmx.ch",
                "0788079297",
                StaffType.PSYCHIATRIST
        );

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // insert test record
        tx.begin();
        em.persist(staff);
        tx.commit();

        // read test record
        tx.begin();
        Staff testStaff = em.find(Staff.class, staff.getId());
        tx.commit();

        // delete test record
        tx.begin();
        em.remove(staff);
        tx.commit();
        em.close();

        // test if persisted and read staff are identical
        Assert.assertEquals(testStaff.getId(), staff.getId());
        Assert.assertEquals(testStaff.getFirstName(), staff.getFirstName());
        Assert.assertEquals(testStaff.getType(), staff.getType());

        // test date persistence
        Assert.assertEquals(testStaff.getDob().toString(), "1989-01-20");
    }
}