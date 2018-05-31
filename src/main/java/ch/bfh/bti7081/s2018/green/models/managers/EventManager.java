package ch.bfh.bti7081.s2018.green.models.managers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;


public class EventManager extends Manager<Event> {
    public EventManager() {
    	this.entityclass = Event.class;
	}
    
    public List<Event> findBy(Staff staff) {
    	setNewEntityManager();
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE therapistId = :therapistId", entityclass);
		query.setParameter("therapistId", staff.getId());
		return findByQuery(query);
    }
    
    public List<Event> findBy(Patient patient) {
    	setNewEntityManager();
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE patientId = :patientId", entityclass);
		query.setParameter("patientId", patient.getId());
		return findByQuery(query);
    }
    
    public List<Event> findBy(LocalDate date) {
    	setNewEntityManager();
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atStartOfDay().plusDays(1);
    	return findBy(start, end);
    }

    public List<Event> findConflicting(Event event) {
        return findBy(event.getStart(), event.getStop());
    }

    private List<Event> findBy(LocalDateTime start, LocalDateTime end) {
        setNewEntityManager();
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE start >= :startLocalDateTime and stop < :endLocalDateTime"
                + " or start < :startLocalDateTime and stop > :endLocalDateTime"
                + " or stop > :startLocalDateTime and stop < :endLocalDateTime"
                + " or start >= :startLocalDateTime and start < :endLocalDateTime", entityclass);
        query.setParameter("startLocalDateTime", start);
        query.setParameter("endLocalDateTime", end);
        return findByQuery(query);
    }
    
    private List<Event> findByQuery(TypedQuery<Event> query) {
        List<Event> events = query.getResultList();
        manager.close();
    	return events;
    }
    
    public Event update(Event item) {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(item);
        closeTransaction(updateTransaction);

        return item;
    }
    
    public Event remove(Event item) {

    	EntityTransaction updateTransaction = beginTransaction();
        manager.remove(manager.contains(item) ? item : manager.merge(item));
        closeTransaction(updateTransaction);
        
        return item;
    }
}
