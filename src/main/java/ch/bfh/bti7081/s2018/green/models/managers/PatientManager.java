package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import javax.persistence.EntityTransaction;

public class PatientManager extends Manager<Patient> {
    public PatientManager(Class<Patient> entityclass) {
		super(entityclass);
	}

    public Patient update(Patient item) {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(item);
        closeTransaction(updateTransaction);

        return item;
    }
    
    public Patient remove(Patient item) {

    	EntityTransaction updateTransaction = beginTransaction();
        manager.remove(manager.contains(item) ? item : manager.merge(item));
        closeTransaction(updateTransaction);
        
        return item;
    }
}
