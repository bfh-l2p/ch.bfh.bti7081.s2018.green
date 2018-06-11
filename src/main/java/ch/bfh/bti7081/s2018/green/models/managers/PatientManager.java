package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class PatientManager extends Manager<Patient> {
    public PatientManager() {
    	this.entityclass = Patient.class;
	}

    public Patient update(Patient patient) throws PersistenceException {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(patient);
        closeTransaction(updateTransaction);

        return patient;
    }
    
    public Patient remove(Patient patient) throws PersistenceException {

    	EntityTransaction updateTransaction = beginTransaction();
        manager.remove(manager.contains(patient) ? patient : manager.merge(patient));
        closeTransaction(updateTransaction);
        
        return patient;
    }
}
