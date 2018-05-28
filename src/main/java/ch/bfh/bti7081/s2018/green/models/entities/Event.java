package ch.bfh.bti7081.s2018.green.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Event {
    @JoinColumn(name = "nextId", insertable = false, updatable = false)
    @OneToOne(optional = true)
    private Event next = null;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @OrderBy("start asc")
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime stop;

    @Column(name = "description")
    private String desc;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapistId", nullable = false)
    private Staff therapist;

    @ManyToOne
    @JoinColumn(name = "therapyId")
    private Therapy therapy;

    public Event() {
        // required by JPA
    }

    public Event(LocalDateTime start, LocalDateTime stop, String desc, String title, Patient patient, Staff therapist) throws IllegalArgumentException {
        if (!isValid(start, stop, null)) {
            throw new IllegalArgumentException("The stop of an event must always be AFTER its start.");
        }
        this.start = start;
        this.stop = stop;
        this.desc = desc;
        this.title = title;
        this.patient = patient;
        this.therapist = therapist;
    }

    public Event(LocalDateTime start, LocalDateTime stop, String desc, String title, Patient patient, Staff therapist, Event next) throws IllegalArgumentException {
        this(start, stop, desc, title, patient, therapist);

        if (!isValid(start, stop, next)) {
            throw new IllegalArgumentException("The start of the next event must always be AFTER the stop of the current one.");
        }

        this.next = next;
    }

    private boolean isValid(LocalDateTime start, LocalDateTime stop, Event next) {
        if (null != next && !next.getStart().isAfter(stop)) {
            return false;
        }

        return start.isBefore(stop);
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) throws IllegalArgumentException {
        if (!isValid(start, this.stop, next)) {
            throw new IllegalArgumentException("The stop of an event must always be AFTER its start.");
        }
        this.start = start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setStop(LocalDateTime stop) throws IllegalArgumentException {
        if (!isValid(this.start, stop, next)) {
            throw new IllegalArgumentException("The stop of an event must always be AFTER its start.");
        }
        this.stop = stop;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getTherapist() {
        return therapist;
    }

    public void setTherapist(Staff therapist) {
        this.therapist = therapist;
    }

    public Therapy getTherapy() {
        return therapy;
    }

    public void setTherapy(Therapy therapy) {
        this.therapy = therapy;
    }

    public Event getNext() {
        return next;
    }

    public void setNext(Event next) throws IllegalArgumentException {
        if (!isValid(start, stop, next)) {
            throw new IllegalArgumentException("The start of the next event must always be AFTER the stop of the current one.");
        }

        this.next = next;
    }
}
