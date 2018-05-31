package ch.bfh.bti7081.s2018.green.models.entities;


import ch.bfh.bti7081.s2018.green.models.managers.EventManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Program {

    private Therapy therapy;

    private LocalDate startDate;

    private LocalDate endDate;

    private Period frequency;

    private Event firstEvent;

    public Program(Therapy therapy, LocalDate startDate, LocalDate endDate, Period frequence, Event firstEvent) throws IllegalArgumentException {
        this.therapy = therapy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequence;
        this.firstEvent = createEvents(firstEvent);
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

    public Event getFirstEvent() {
        return firstEvent;
    }

    private Event createEvents(Event firstEvent) throws IllegalArgumentException {
        // verify that the date of the first recurring event corresponds to the startDate date of the program
        if (firstEvent.getStart().toLocalDate().compareTo(this.startDate) == 0) {
            firstEvent.setTherapy(this.therapy);
            Event previousEvent = firstEvent;

            // creates identical recurring events in a defined frequency from the startDate until the endDate of the program
            while (previousEvent.getStart().toLocalDate().compareTo(this.endDate) <= 0) {
                LocalDateTime newStartDateTime = previousEvent.getStart().plus(this.frequency);
                LocalDateTime newStopDateTime = previousEvent.getStop().plus(this.frequency);
                Event currentEvent = new Event(newStartDateTime, newStopDateTime, previousEvent.getDescription(),
                        previousEvent.getTitle(), previousEvent.getPatient(), previousEvent.getTherapist());
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

    public void saveAllEventsOfProgram() {
        Event currentEvent = firstEvent;
        EventManager eventManager = new EventManager();
        while (currentEvent != null) {
            eventManager.add(currentEvent);
            currentEvent = currentEvent.getNext();
        }
    }
}
