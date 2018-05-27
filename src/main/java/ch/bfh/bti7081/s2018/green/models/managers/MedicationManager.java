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
    
    public List<Medication> findBy(Staff item) {
        TypedQuery<Medication> query = manager.createQuery("SELECT j FROM Medication j WHERE prescriberId = :prescriberId", Medication.class);
		query.setParameter("prescriberId", item.getId());
		return findByQuery(query);
    }
    
    public List<Medication> findBy(Patient item) {
        TypedQuery<Medication> query = manager.createQuery("SELECT j FROM Medication j WHERE patientId = :patientId", Medication.class);
		query.setParameter("patientId", item.getId());
		return findByQuery(query);
    }
    
    public List<Medication> findBy(LocalDateTime date) {
        TypedQuery<Medication> query = manager.createQuery("SELECT j FROM Medication j WHERE created = :created", Medication.class);
        query.setParameter("created", date);
        return findByQuery(query);
    }
    
    private List<Medication> findByQuery(TypedQuery<Medication> query) {
    	setNewEntityManager();
        List<Medication> items = query.getResultList();
        manager.close();
    	return items;
    }
    
    public Medication update(Medication item) {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(item);
        closeTransaction(updateTransaction);

        return item;
    }
}
