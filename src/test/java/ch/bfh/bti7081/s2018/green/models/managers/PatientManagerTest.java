package ch.bfh.bti7081.s2018.green.models.managers;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Person;

public class PatientManagerTest {
	
	@Test
	public void patientManagerTest() throws ClassNotFoundException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dob1 = new Date(format.parse("14.10.1991").getTime());
        Person testcontact = new Person(
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
        Patient testpatient1 = new Patient(
                "Tobias",
                "Scheck",
                dob2,
                "Chutzenstrasse 28",
                "3007",
                "Bern",
                "martinscheck92@gmail.com",
                "0798340598",
                testcontact
        );
        
        Date dob3 = new Date(format.parse("15.10.1993").getTime());
        Patient testpatient2 = new Patient(
                "Lukas",
                "Scheck",
                dob3,
                "Chutzenstrasse 29",
                "3007",
                "Bern",
                "martinscheck93@gmail.com",
                "0798340597",
                testcontact
        );
        
        Date dob4 = new Date(format.parse("15.10.1994").getTime());
        Patient testpatient3 = new Patient(
                "Lorenz",
                "Scheck",
                dob4,
                "Chutzenstrasse 29",
                "3007",
                "Bern",
                "martinscheck94@gmail.com",
                "0798340596",
                testcontact
        );
        
        PatientManager testmanager = new PatientManager();
        PersonManager persontestmanager = new PersonManager();
        
        //persist emergency contact
        persontestmanager.add(testcontact);
        
        //persist test patient
        testmanager.add(testpatient1);
        
        //retrieval of persisted test patient
        Person retrievedtestpatient1 = testmanager.get(testpatient1.getId());
        
        //test if persisted and retrieved patient is identical
        Assert.assertEquals(testpatient1.getId(), retrievedtestpatient1.getId());
        
        //update test patient
        testpatient1.setFirstName("Jakob");
        testmanager.update(testpatient1);
        retrievedtestpatient1 = testmanager.get(testpatient1.getId());
        Assert.assertEquals(testpatient1.getFirstName(), retrievedtestpatient1.getFirstName());
        
        //remove patient
        testmanager.remove(testpatient1);
        
        //persist all test patients
        List<Patient> listoftestpatients = Arrays.asList(testpatient2, testpatient3);
        listoftestpatients.stream().forEach(tp -> testmanager.add(tp));
        
        //retrieval of all test patients
        List<Patient> listofretrievedtestpatients = testmanager.findAll();
        
        //remove all test patients
        listoftestpatients.stream().forEach(tp -> testmanager.remove(tp));
        
        //test if ids of persisted and retrieved persons are identical
        int[] arrayoftestpatientsids = listoftestpatients.stream().mapToInt(tp -> tp.getId()).toArray();
        int[] arrayofretrievedtestpatientsids = listofretrievedtestpatients.stream().mapToInt(tp -> tp.getId()).toArray();
        Assert.assertArrayEquals(arrayoftestpatientsids, arrayofretrievedtestpatientsids);
	}
}
