package ch.bfh.bti7081.s2018.green.models.managers;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;

public class JournalEntryManager extends Manager<JournalEntry> {
    public JournalEntryManager() {
    	this.entityclass = JournalEntry.class;
	}
    
    public List<JournalEntry> findByPatient(Integer id) {
        setNewEntityManager();
        TypedQuery<JournalEntry> query = manager.createQuery("SELECT j FROM JournalEntry j WHERE patientId = :patientId", JournalEntry.class);
        query.setParameter("patientId", id);
        List<JournalEntry> items = query.getResultList();

        manager.close();
        return items;
    }
}
