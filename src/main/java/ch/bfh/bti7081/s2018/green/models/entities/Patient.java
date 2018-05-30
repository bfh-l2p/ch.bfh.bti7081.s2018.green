package ch.bfh.bti7081.s2018.green.models.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ch.bfh.bti7081.s2018.green.models.enumerations.DangerLevel;


@Entity
public class Patient extends Person {

    @ManyToOne
    @JoinColumn(name = "contactId", nullable = false)
	private Person emergencyContact;

	@Column(nullable = false)
	private DangerLevel selfEndangerment;

    @Column(nullable = false)
    private DangerLevel dangerToOthers;

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
}
