package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class StaffManagerTest {

    @Test
    public void staffManagerTest() throws ClassNotFoundException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dob1 = new Date(format.parse("14.10.1991").getTime());
        Staff teststaff1 = new Staff("Martin", "Scheck", dob1, "Chutzenstrasse 27", "3007", "Bern",
                "martinscheck91@gmail.com", "0798340599", StaffType.PSYCHIATRIST);

        Date dob2 = new Date(format.parse("15.10.1992").getTime());
        Staff teststaff2 = new Staff("Tobias", "Scheck", dob2, "Chutzenstrasse 28", "3007", "Bern",
                "martinscheck92@gmail.com", "0798340598", StaffType.PSYCHOLOGIST);

        Date dob3 = new Date(format.parse("15.10.1993").getTime());
        Staff teststaff3 = new Staff("Lukas", "Scheck", dob3, "Chutzenstrasse 29", "3007", "Bern",
                "martinscheck93@gmail.com", "0798340597", StaffType.PSYCHIATRIST);

        StaffManager testmanager = new StaffManager();

        // persist test staff
        testmanager.add(teststaff1);

        // retrieval of persisted test staff
        Staff retrievedteststaff1 = testmanager.get(teststaff1.getId());

        // test if persisted and retrieved staff is identical
        Assert.assertEquals(teststaff1.getId(), retrievedteststaff1.getId());

        // update test staff
        teststaff1.setFirstName("Jakob");
        testmanager.update(teststaff1);
        retrievedteststaff1 = testmanager.get(teststaff1.getId());

        Assert.assertEquals(teststaff1.getFirstName(), retrievedteststaff1.getFirstName());

        // remove staff
        testmanager.remove(teststaff1);

        // persist all test staffs
        List<Staff> listofteststaffs = Arrays.asList(teststaff2, teststaff3);
        listofteststaffs.stream().forEach(ts -> testmanager.add(ts));

        // test if ids of persisted and retrieved staffs are identical
        int[] arrayofteststaffsids = listofteststaffs.stream().mapToInt(ts -> ts.getId()).toArray();
        int[] arrayofretrievedteststaffsids = testmanager.findAll().stream().mapToInt(ts -> ts.getId()).toArray();

        Assert.assertArrayEquals(arrayofteststaffsids, arrayofretrievedteststaffsids);


        //test if all psychiatrists are retrieved correctly
        int[] arrayofallpsychiatristsids = listofteststaffs.stream().filter(ts -> ts.getType().equals(StaffType.PSYCHIATRIST)).mapToInt(ts -> ts.getId()).toArray();
        int[] arrayofallretrievedpsychiatristsids = testmanager.findBy(StaffType.PSYCHIATRIST).stream().mapToInt(ts -> ts.getId()).toArray();
        Arrays.stream(arrayofallpsychiatristsids).forEach(tp -> {
            Assert.assertTrue(Arrays.stream(arrayofallretrievedpsychiatristsids).anyMatch(r -> r == tp));
        });

        //test if all psychologists are retrieved correctly
        int[] arrayofallpsychologistsids = listofteststaffs.stream().filter(ts -> ts.getType().equals(StaffType.PSYCHOLOGIST)).mapToInt(ts -> ts.getId()).toArray();
        int[] arrayofallretrievedpsychologistsids = testmanager.findBy(StaffType.PSYCHOLOGIST).stream().mapToInt(ts -> ts.getId()).toArray();
        Arrays.stream(arrayofallpsychologistsids).forEach(tp -> {
            Assert.assertTrue(Arrays.stream(arrayofallretrievedpsychologistsids).anyMatch(r -> r == tp));
        });

        // remove all test staffs
        testmanager.findAll().stream().forEach(tp -> testmanager.remove(tp));
    }
}
