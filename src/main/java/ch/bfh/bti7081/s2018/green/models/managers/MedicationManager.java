package ch.bfh.bti7081.s2018.green.models.managers;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;

public class MedicationManager extends Manager<Medication> {
    public MedicationManager() {
		this.entityclass = Medication.class;
	}
    
    public List<Medication> findBy(Staff staff) throws PersistenceException {
        TypedQuery<Medication> query = manager.createQuery("SELECT j FROM Medication j WHERE prescriberId = :prescriberId", entityclass);
		query.setParameter("prescriberId", staff.getId());
		return findByQuery(query);
    }
    
    public List<Medication> findBy(Patient patient) throws PersistenceException {
        TypedQuery<Medication> query = manager.createQuery("SELECT j FROM Medication j WHERE patientId = :patientId", entityclass);
		query.setParameter("patientId", patient.getId());
		return findByQuery(query);
    }
    
    public Medication update(Medication medication) throws PersistenceException {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(medication);
        closeTransaction(updateTransaction);

        return medication;
    }

    private List<Medication> findByQuery(TypedQuery<Medication> query) throws PersistenceException {
        List<Medication> medications = query.getResultList();
        return medications;
    }
}
