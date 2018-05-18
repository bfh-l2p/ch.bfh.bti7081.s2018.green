package ch.bfh.bti7081.s2018.green.models.managers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import ch.bfh.bti7081.s2018.green.models.entities.Person;

public class PersonManagerTest {
	
	@Test
	public void personManagerTest() throws ClassNotFoundException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dob1 = new Date(format.parse("14.10.1991").getTime());
        Person testperson1 = new Person(
                "Martin",
                "Scheck",
                dob1,
                "Chutzenstrasse 27",
                "3007",
                "Bern",
                "martinscheck91@gmail.com",
                "0798340599"
        );
        
        Date dob2 = new Date(format.parse("15.10.1992").getTime());
        Person testperson2 = new Person(
                "Tobias",
                "Scheck",
                dob2,
                "Chutzenstrasse 28",
                "3007",
                "Bern",
                "martinscheck92@gmail.com",
                "0798340598"
        );
        
        Date dob3 = new Date(format.parse("15.10.1993").getTime());
        Person testperson3 = new Person(
                "Lukas",
                "Scheck",
                dob3,
                "Chutzenstrasse 29",
                "3007",
                "Bern",
                "martinscheck93@gmail.com",
                "0798340597"
        );
        
        PersonManager testmanager = new PersonManager();
                
        //persist test person
        testmanager.add(testperson1);
        
        //retrieval of persisted test person
        Person retrievedtestperson1 = testmanager.get(testperson1.getId());
        
        //test if persisted and retrieved person is identical
        Assert.assertEquals(testperson1.getId(), retrievedtestperson1.getId());
        
        //update test person
        testperson1.setFirstName("Jakob");
        testmanager.update(testperson1);
        retrievedtestperson1 = testmanager.get(testperson1.getId());
        Assert.assertEquals(testperson1.getFirstName(), retrievedtestperson1.getFirstName());
        
        //remove person
        testmanager.remove(testperson1);
        
        //persist all test persons
        List<Person> listoftestpersons = Arrays.asList(testperson2, testperson3);
        listoftestpersons.stream().forEach(tp -> testmanager.add(tp));
        
        //retrieval of all test persons
        List<Person> listofretrievedtestpersons = testmanager.findAll();
        
        //remove all test persons
        listoftestpersons.stream().forEach(tp -> testmanager.remove(tp));
        
        //test if ids of persisted and retrieved persons are identical
        int[] arrayoftestpersonsids = listoftestpersons.stream().mapToInt(tp -> tp.getId()).toArray();
        int[] arrayofretrievedtestpersonsids = listofretrievedtestpersons.stream().mapToInt(tp -> tp.getId()).toArray();
        Assert.assertArrayEquals(arrayoftestpersonsids, arrayofretrievedtestpersonsids);
        
	}
}
