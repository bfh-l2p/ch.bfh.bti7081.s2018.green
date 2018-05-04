package ch.bfh.bti7081.s2018.green;

import ch.bfh.bti7081.s2018.green.models.Doctor;
import ch.bfh.bti7081.s2018.green.models.Patient;


// holds the data currently processed/ edited in the application

public class DataContainer {

	
	private Patient currentPatient;
	private Doctor currentDoctor;
	
	public Patient getCurrentPatient() {
		return currentPatient;
	}
	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	public Doctor getCurrentDoctor() {
		return currentDoctor;
	}
	public void setCurrentDoctor(Doctor currentDoctor) {
		this.currentDoctor = currentDoctor;
	}
	
	
}
