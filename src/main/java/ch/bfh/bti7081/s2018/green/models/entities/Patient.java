package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.DangerLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "Patient")
public class Patient extends Person {

    @ManyToOne
    @JoinColumn(name = "id")
	private Person emergencyContact;

	@Column(name = "selfEndangerment")
	private DangerLevel selfEndangerment;

    @Column(name = "dangerToOthers")
    private DangerLevel dangerToOthers;

	public Patient(int id, String firstName, String lastName, Date birthDate, String address, String zip, String city, String email, String phone, Person emergencyContact) {
		super(id, firstName, lastName, birthDate, address, zip, city, email, phone);
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
