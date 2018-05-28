package ch.bfh.bti7081.s2018.green.models.managers;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;

public class MedicationManager extends Manager<Medication> {
    public MedicationManager() {
		this.entityclass = Medication.class;
	}
    
    public List<Medication> findBy(Staff staff) {
        TypedQuery<Medication> query = manager.createQuery("SELECT j FROM Medication j WHERE prescriberId = :prescriberId", entityclass);
		query.setParameter("prescriberId", staff.getId());
		return findByQuery(query);
    }
    
    public List<Medication> findBy(Patient patient) {
        TypedQuery<Medication> query = manager.createQuery("SELECT j FROM Medication j WHERE patientId = :patientId", entityclass);
		query.setParameter("patientId", patient.getId());
		return findByQuery(query);
    }
    
    public List<Medication> findBy(LocalDateTime date) {
        TypedQuery<Medication> query = manager.createQuery("SELECT j FROM Medication j WHERE created = :created", entityclass);
        query.setParameter("created", date);
        return findByQuery(query);
    }
    
    private List<Medication> findByQuery(TypedQuery<Medication> query) {
    	setNewEntityManager();
        List<Medication> medications = query.getResultList();
        manager.close();
    	return medications;
    }
    
    public Medication update(Medication medication) {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(medication);
        closeTransaction(updateTransaction);

        return medication;
    }
}
