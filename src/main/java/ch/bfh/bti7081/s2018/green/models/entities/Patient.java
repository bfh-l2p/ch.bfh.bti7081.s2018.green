package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.DangerLevel;
import ch.bfh.bti7081.s2018.green.models.managers.JournalEntryManager;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;


@Entity
public class Patient extends Person {

    @ManyToOne
    @JoinColumn(name = "contactId", nullable = false)
	private Person emergencyContact;

	@Column(nullable = false)
	private DangerLevel selfEndangerment;

    @Column(nullable = false)
    private DangerLevel dangerToOthers;

    //@OneToMany
	//private List<JournalEntry> journalEntries = new ArrayList<>();

    public Patient() {
		// required by JPA
	}

	public Patient(String firstName, String lastName, LocalDate dob, String address, String zip, String city, String email, String phone, Person emergencyContact) {
		super(firstName, lastName, dob, address, zip, city, email, phone);
		this.emergencyContact = emergencyContact;
		this.selfEndangerment = DangerLevel.LOW;
		this.dangerToOthers = DangerLevel.LOW;
	}

	public Person getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(Person emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public DangerLevel getSelfEndangerment() {
		return selfEndangerment;
	}

	public void setSelfEndangerment(DangerLevel selfEndangement) {
		this.selfEndangerment = selfEndangement;
	}

	public DangerLevel getDangerToOthers() {
		return dangerToOthers;
	}

	public void setDangerToOthers(DangerLevel dangerToOthers) {
		this.dangerToOthers = dangerToOthers;
	}

	public List<JournalEntry> getJournalEntries() {
	    JournalEntryManager manager = new JournalEntryManager();
        return manager.findByPatient(this.getId());
	}

    public List<Event> getEvents() {
       return null;
    }
}
