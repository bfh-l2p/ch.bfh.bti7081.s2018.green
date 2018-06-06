package ch.bfh.bti7081.s2018.green.models.managers;

import java.time.LocalDate;
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
    
    public List<JournalEntry> findBy(Staff staff) {
        TypedQuery<JournalEntry> query = manager.createQuery("SELECT j FROM JournalEntry j WHERE authorId = :authorId", entityclass);
		query.setParameter("authorId", staff.getId());
		return findByQuery(query);
    }
    
    public List<JournalEntry> findBy(Patient patient) {
    	TypedQuery<JournalEntry> query = manager.createQuery("SELECT j FROM JournalEntry j WHERE patientId = :patientId", entityclass);
		query.setParameter("patientId", patient.getId());
		return findByQuery(query);
    }
    
    public List<JournalEntry> findBy(LocalDate date) {
        LocalDateTime startLocalDateTime = date.atStartOfDay();
        LocalDateTime endLocalDateTime = date.atStartOfDay().plusDays(1);
    	TypedQuery<JournalEntry> query = manager.createQuery("SELECT j FROM JournalEntry j WHERE created >= :startLocalDateTime and created < :endLocalDateTime", entityclass);
        query.setParameter("startLocalDateTime", startLocalDateTime);
        query.setParameter("endLocalDateTime", endLocalDateTime);
        return findByQuery(query);
    }

    private List<JournalEntry> findByQuery(TypedQuery<JournalEntry> query) {
        List<JournalEntry> journalEntries = query.getResultList();
    	return journalEntries;
    }
}
