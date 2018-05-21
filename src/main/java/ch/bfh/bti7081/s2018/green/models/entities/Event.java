package ch.bfh.bti7081.s2018.green.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorColumn(name = "nextId", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "null")
public class Event {
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

    @JoinColumn(name = "nextId", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    @OneToOne(optional = true)
    protected Event next = null;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapistId", nullable = false)
    private Staff therapist;

    public Event() {
    }

    public Event(LocalDateTime start, LocalDateTime stop, String desc, String title, Patient patient, Staff therapist) {
        this.start = start;
        this.stop = stop;
        this.desc = desc;
        this.title = title;
        this.patient = patient;
        this.therapist = therapist;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setStop(LocalDateTime stop) {
        this.stop = stop;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTherapist(Staff therapist) {
        this.therapist = therapist;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public Patient getPatient() {
        return patient;
    }

    public Staff getTherapist() {
        return therapist;
    }
}
