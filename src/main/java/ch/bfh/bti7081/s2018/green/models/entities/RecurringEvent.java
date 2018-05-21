package ch.bfh.bti7081.s2018.green.models.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("not null")
public class RecurringEvent extends Event {

    public RecurringEvent() {
    }

    public RecurringEvent(LocalDateTime start, LocalDateTime stop, String desc, String title, Patient patient, Staff therapist, Event next) {
        super(start, stop, desc, title, patient, therapist);
        this.next = next;
    }

    public Event getNext() {
        return next;
    }

    public void setNext(Event next) {
        this.next = next;
    }
}
