package ch.bfh.bti7081.s2018.green.models.managers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ch.bfh.bti7081.s2018.green.models.entities.Person;
import org.junit.Assert;

public class PersonTryManagerTest {
	@Test
	public void getPersonTryManager() throws ParseException {
		PersonManager testmanager = new PersonManager(Person.class);
        
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
        

        List<Person> testPersons = Arrays.asList(testperson2, testperson3);
        testPersons.stream().forEach(p -> testmanager.add(p));
        testmanager.remove(testperson1);
        List<Person> retrievedTestPersons = testmanager.findAll();
        int[] testPersonsIds = testPersons.stream().mapToInt(p -> p.getId()).toArray();
        int[] retrievedTestPersonsIds = retrievedTestPersons.stream().mapToInt(p -> p.getId()).toArray();
        Assert.assertArrayEquals(testPersonsIds, retrievedTestPersonsIds);
	}
}
