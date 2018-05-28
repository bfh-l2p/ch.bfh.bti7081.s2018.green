package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ProgramTest {
    @Test
    public void creatingProgram_ValidParameters_ShouldSucceed() {
        LocalDateTime start1 = LocalDateTime.of(2018, 5, 21, 13, 45);
        LocalDateTime stop1 = LocalDateTime.of(2018, 5, 21, 14, 30);
        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);
        Event firstEvent = new Event(start1, stop1, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, null);

        Therapy therapy = new Therapy("Test Therapy");

        LocalDate startDate = LocalDate.of(2018, 5, 21);
        LocalDate stopDate = LocalDate.of(2018, 6, 21);
        Period frequence = Period.ofDays(2);

        try {
            Program program = new Program(therapy, startDate, stopDate, frequence, firstEvent);
            Event secondEvent = firstEvent.getNext();
            Assert.assertNotEquals(firstEvent, secondEvent);
            Assert.assertEquals(firstEvent.getStart(), secondEvent.getStart().minus(program.getFrequency()));
        }
        catch (IllegalArgumentException ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingProgram_DifferentStartDates_ThrowsException() {
        LocalDateTime start1 = LocalDateTime.of(2018, 4, 21, 13, 45);
        LocalDateTime stop1 = LocalDateTime.of(2018, 4, 21, 14, 30);
        Person person = new Person("Emergency", "Contact", null, null, null, null, null, null);
        Patient patient = new Patient("Patrice", "lastname", null, null, null, null, null, null, person);
        Staff staff = new Staff("doctor", "staff", null, null, null, null, null, null, StaffType.PSYCHIATRIST);
        Event firstEvent = new Event(start1, stop1, "Sprechstunde weil nicht gut", "Sprechstunde", patient, staff, null);

        Therapy therapy = new Therapy("Test Therapy");

        LocalDate startDate = LocalDate.of(2018, 5, 21);
        LocalDate stopDate = LocalDate.of(2018, 6, 21);
        Period frequence = Period.ofDays(2);

        new Program(therapy, startDate, stopDate, frequence, firstEvent);
    }
}