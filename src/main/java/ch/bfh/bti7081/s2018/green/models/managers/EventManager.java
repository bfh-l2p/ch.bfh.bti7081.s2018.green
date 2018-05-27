package ch.bfh.bti7081.s2018.green.models.managers;

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
    
    public List<Event> findBy(Staff item) {
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE therapistId = :therapistId", entityclass);
		query.setParameter("tId", item.getId());
		return findByQuery(query);
    }
    
    public List<Event> findBy(Patient item) {
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE patientId = :patientId", entityclass);
		query.setParameter("patientId", item.getId());
		return findByQuery(query);
    }
    
    public List<Event> findBy(LocalDateTime date) {
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE start = :start", entityclass);
        query.setParameter("created", date);
        return findByQuery(query);
    }
    
    private List<Event> findByQuery(TypedQuery<Event> query) {
    	setNewEntityManager();
        List<Event> items = query.getResultList();
        manager.close();
    	return items;
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
