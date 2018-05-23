package ch.bfh.bti7081.s2018.green.models.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

public class Medication {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private LocalDateTime start;
    private LocalDateTime stop;
    private int periode;
    private float dose;

    @OneToOne
    @JoinColumn(name = "prescriberId", nullable = false)
    private Staff prescriber;
    private LocalDateTime updated;
    private LocalDateTime created;

    public Medication () {
        // required by JPA
    }

    public Medication (String name, LocalDateTime start, LocalDateTime end, int periode, float dose, Staff prescriber) {
        this.name = name;
        this.start = start;
        this.stop = end;
        this.periode = periode;
        this.dose = dose;
        this.prescriber = prescriber;
    }

    @PrePersist
    public void onPrePersist() {
        created = LocalDateTime.now();
    }
      
    @PreUpdate
    public void onPreUpdate() {
    	updated = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return start;
    }

    public void setStartDate(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEndDate() {
        return stop;
    }

    public void setEndDate(LocalDateTime end) {
        this.stop = end;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public float getDose() {
        return dose;
    }

    public void setdose(float dose) {
        this.dose = dose;
    }

    public Staff getPrescriber() {
        return prescriber;
    }

    public void setPrescriber(Staff prescriber) {
        this.prescriber = prescriber;
    }
}