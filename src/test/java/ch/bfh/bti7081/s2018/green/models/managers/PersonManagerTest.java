package ch.bfh.bti7081.s2018.green.models.managers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Test;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.Person;

public class PersonManagerTest {
	
	@Test
	public void personManagerTest() throws ClassNotFoundException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dob = new Date(format.parse("14.10.1991").getTime());
        Person persistedtestperson1 = new Person(
                "Martin",
                "Scheck",
                dob,
                "Chutzenstrasse 27",
                "3007",
                "Bern",
                "martinscheck91@gmail.com",
                "0798340599"
        );
        
        PersonManager testmanager = new PersonManager();
        EntityManager em = DataContainer.getInstance("test1234").createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //persist test person
        testmanager.add(persistedtestperson1);
        
        
        //retrieval of persisted test person
        Person retrievedtestperson1 = testmanager.get(persistedtestperson1.getId());
        
        //test if persisted and retrieved person is identical
        Assert.assertEquals(persistedtestperson1.getId(), retrievedtestperson1.getId());
        Assert.assertEquals(persistedtestperson1.getFirstName(), retrievedtestperson1.getFirstName());
        Assert.assertEquals(persistedtestperson1.getEmail(), retrievedtestperson1.getEmail());    
	}
}
