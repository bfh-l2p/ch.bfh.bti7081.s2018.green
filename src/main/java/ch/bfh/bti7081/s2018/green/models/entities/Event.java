package ch.bfh.bti7081.s2018.green.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorColumn(name = "nextId", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "null")
public class Event {
    @JoinColumn(name = "nextId", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    @OneToOne(optional = true)
    protected Event next = null;
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Event() {
    }

    public Event(LocalDateTime start, LocalDateTime stop, String desc, String title, Patient patient, Staff therapist) throws IllegalArgumentException {
        this.start = start;
        this.stop = stop;
        this.desc = desc;
        this.title = title;
        this.patient = patient;
        this.therapist = therapist;

        if (!isValid()) {
            throw new IllegalArgumentException("The stop of an event must always be AFTER its start.");
        }
    }

    private boolean isValid() {
        return start.isBefore(stop);
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        LocalDateTime old = this.start;
        this.start = start;

        if (!isValid()) {
            this.start = old;
            throw new IllegalArgumentException("The stop of an event must always be AFTER its start.");
        }
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setStop(LocalDateTime stop) {
        LocalDateTime old = this.stop;
        this.stop = stop;

        if (!isValid()) {
            this.stop = old;
            throw new IllegalArgumentException("The stop of an event must always be AFTER its start.");
        }
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
}
