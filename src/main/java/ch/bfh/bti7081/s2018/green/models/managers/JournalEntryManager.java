package ch.bfh.bti7081.s2018.green.models.managers;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;

public class JournalEntryManager extends Manager<JournalEntry> {
    public JournalEntryManager() {
    	this.entityclass = JournalEntry.class;
	}
    
    public List<JournalEntry> findBy(Staff item) {
        TypedQuery<JournalEntry> query = manager.createQuery("SELECT j FROM JournalEntry j WHERE authorId = :authorId", this.entityclass);
		query.setParameter("authorId", item.getId());
		return findByQuery(query);
    }
    
    public List<JournalEntry> findBy(Patient item) {
        TypedQuery<JournalEntry> query = manager.createQuery("SELECT j FROM JournalEntry j WHERE patientId = :patientId", this.entityclass);
		query.setParameter("patientId", item.getId());
		return findByQuery(query);
    }
    
    public List<JournalEntry> findBy(LocalDateTime date) {
        TypedQuery<JournalEntry> query = manager.createQuery("SELECT j FROM JournalEntry j WHERE created = :created", this.entityclass);
        query.setParameter("created", date);
        return findByQuery(query);
    }

    private List<JournalEntry> findByQuery(TypedQuery<JournalEntry> query) {
    	setNewEntityManager();
        List<JournalEntry> items = query.getResultList();
        manager.close();
    	return items;
    }
}
