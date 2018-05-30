package ch.bfh.bti7081.s2018.green.models.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Program {

    private Therapy therapy;

    private LocalDate startDate;

    private LocalDate endDate;

    private Period frequency;

    private RecurringEvent firstEvent;

    public Program(Therapy therapy, LocalDate startDate, LocalDate endDate, Period frequence, RecurringEvent firstEvent) throws IllegalArgumentException {
        this.therapy = therapy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequence;
        this.firstEvent = createRecurringEvents(firstEvent);
    }

    public Therapy getTherapy() {
        return therapy;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Period getFrequency() {
        return frequency;
    }

    public RecurringEvent getFirstEvent() {
        return firstEvent;
    }

    private RecurringEvent createRecurringEvents(RecurringEvent firstEvent) throws IllegalArgumentException {
        // verify that the date of the first recurring event corresponds to the startDate date of the program
        if (firstEvent.getStart().toLocalDate().compareTo(this.startDate) == 0) {
            firstEvent.setTherapy(this.therapy);
            RecurringEvent previousEvent = firstEvent;

            // creates identical recurring events in a defined frequency from the startDate until the endDate of the program
            while (previousEvent.getStart().toLocalDate().compareTo(this.endDate) <= 0) {
                LocalDateTime newStartDateTime = previousEvent.getStart().plus(this.frequency);
                LocalDateTime newStopDateTime = previousEvent.getStop().plus(this.frequency);
                RecurringEvent currentEvent = new RecurringEvent(newStartDateTime, newStopDateTime, previousEvent.getDescription(),
                        previousEvent.getTitle(), previousEvent.getPatient(), previousEvent.getTherapist(), null);
                currentEvent.setTherapy(this.therapy);

                previousEvent.setNext(currentEvent);
                previousEvent = currentEvent;
            }
        }
        else {
            throw new IllegalArgumentException("Recurring event's start date doesn't correspond to the start date of the program");
        }

        return firstEvent;
    }

    public void saveAllRecurringEventsOfProgram() {
        RecurringEvent currentEvent = firstEvent;
        while (currentEvent != null) {
            //TODO: use EventManager to create RecurringEvents in the DB

            currentEvent = currentEvent.getNext();
        }
    }
}
