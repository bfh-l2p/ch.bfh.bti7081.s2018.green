package ch.bfh.bti7081.s2018.green.models.entities;

import java.sql.Date;
import javax.persistence.*;

public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int ID;
    //private boolean canEdit = true;

    private String name;
    //private String chemAgent;
    private Date start;
    private Date stop;
    private int periode;
    private float dose;

    @ManyToOne
    private Staff prescriber;
    private Date updated;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return start;
    }

    public void setStartDate(Date startDate) {
        this.start = startDate;
    }

    public Date getEndDate() {
        return stop;
    }

    public void setEndDate(Date endDate) {
        this.stop = endDate;
    }

    public int getFrequency() {
        return periode;
    }

    public void setFrequency(int frequency) {
        this.periode = frequency;
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

    public String getPrescriberName () {
        return this.getPrescriber().getFirstName() + " " + this.getPrescriber().getLastName();
    }

    public void setPrescriber(Staff doctorPrescribed) {
        this.prescriber = doctorPrescribed;
    }

    public Date getLasModifed() {
        return updated;
    }

    public void setLasModifed(Date lasModifed) {
        this.updated = lasModifed;
    }

    public Medication (int ID, String name, String chemAgent, Date startDate, Date endDate, int frequency, float dose, Staff doctorPrescribed, Date updated) {

        this.ID = ID;
        this.name = name;
        this.start = startDate;
        this.stop = endDate;
        this.periode = frequency;
        this.dose = dose;
        this.prescriber = doctorPrescribed;
        this.updated = updated;
    }

    public Medication (int ID, String name, String chemAgent, Date startDate, Date endDate, int frequency, float dose, Staff doctorPrescribed) {

        this.ID = ID;
        this.name = name;
        this.start = startDate;
        this.stop = endDate;
        this.periode = frequency;
        this.dose = dose;
        this.prescriber = doctorPrescribed;
    }

    public Medication () {
        // required by JPA
    }


}