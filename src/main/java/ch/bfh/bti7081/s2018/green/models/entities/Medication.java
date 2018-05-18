package ch.bfh.bti7081.s2018.green.models.entities;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int ID;
    private boolean canEdit = true;

    private boolean isActive = true;
    private String mediName;
    private String chemAgent;
    private Date startDate;
    private Date endDate;
    private int frequency;
    private String dosis;

    @ManyToOne
    private Staff prescriber;
    private Date lasModifed;
    @ManyToOne
    private Staff modifiedBy;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMediName() {
        return mediName;
    }

    public void setMediName(String mediName) {
        this.mediName = mediName;
    }

    public String getChemAgent() {
        return chemAgent;
    }

    public void setChemAgent(String chemAgent) {
        this.chemAgent = chemAgent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
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
        return lasModifed;
    }

    public void setLasModifed(Date lasModifed) {
        this.lasModifed = lasModifed;
    }

    public Staff getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Staff modifiedBy) {
        this.modifiedBy = modifiedBy;
    }



    public Medication (int ID, String mediName, String chemAgent, Date startDate, Date endDate, int frequency, String dosis, Staff doctorPrescribed, Date lasModifed, Staff modifiedBy) {

        this.ID = ID;
        this.mediName = mediName;
        this.chemAgent = chemAgent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.dosis = dosis;
        this.prescriber = doctorPrescribed;
        this.lasModifed = lasModifed;
        this.modifiedBy = modifiedBy;
    }

    public Medication (int ID, String mediName, String chemAgent, Date startDate, Date endDate, int frequency, String dosis, Staff doctorPrescribed) {

        this.ID = ID;
        this.mediName = mediName;
        this.chemAgent = chemAgent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.dosis = dosis;
        this.prescriber = doctorPrescribed;
    }

    public Medication () {
        // required by JPA
    }


}