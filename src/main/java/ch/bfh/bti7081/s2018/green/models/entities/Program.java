package ch.bfh.bti7081.s2018.green.models.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Program {

    private Therapy therapy;

    private LocalDate start;

    private LocalDate stop;

    private Period frequence;

    private RecurringEvent firstEvent;

    public Program(Therapy therapy, LocalDate start, LocalDate stop, Period frequence, RecurringEvent firstEvent) throws IllegalArgumentException {
        this.therapy = therapy;
        this.start = start;
        this.stop = stop;
        this.frequence = frequence;
        this.firstEvent = createRecuringEvents(firstEvent);
    }

    public Therapy getTherapy() {
        return therapy;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getStop() {
        return stop;
    }

    public Period getFrequence() {
        return frequence;
    }

    public RecurringEvent getFirstEvent() {
        return firstEvent;
    }

    private RecurringEvent createRecuringEvents(RecurringEvent firstEvent) throws IllegalArgumentException {
        if (firstEvent.getStart().toLocalDate().compareTo(this.start) == 0) {
            firstEvent.setTherapy(this.therapy);
            RecurringEvent lastEvent = firstEvent;

            while (lastEvent.getStart().toLocalDate().compareTo(this.stop) <= 0) {
                LocalDateTime newStart = lastEvent.getStart().plus(this.frequence);
                LocalDateTime newStop = lastEvent.getStop().plus(this.frequence);
                RecurringEvent actualEvent = new RecurringEvent(newStart, newStop, lastEvent.getDesc(), lastEvent.getTitle(), lastEvent.getPatient(), lastEvent.getTherapist(), null);
                actualEvent.setTherapy(this.therapy);
                lastEvent.setNext(actualEvent);
                lastEvent = actualEvent;
            }
        }
        else {
            throw new IllegalArgumentException("Start date of RecurringEvent must be the same as the one of the Program");
        }

        return firstEvent;
    }

    public void saveAllRecuringEventsOfProgram() {
        RecurringEvent currentEvent = firstEvent;
        while (currentEvent.getNext() != null) {
            //TODO: use EventManager to create RecurringEvents in the DB

            currentEvent = currentEvent.getNext();
        }
    }
}
