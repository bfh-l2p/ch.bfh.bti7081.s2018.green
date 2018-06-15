package ch.bfh.bti7081.s2018.green.models.managers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;


public class EventManager extends Manager<Event> {
    public EventManager() {
    	this.entityclass = Event.class;
	}

    public List<Event> findBy(Staff staff) throws PersistenceException {
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE therapistId = :therapistId ORDER BY start", entityclass);
		query.setParameter("therapistId", staff.getId());
		return findByQuery(query);
    }

    public List<Event> findBy(Patient patient) throws PersistenceException {
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE patientId = :patientId ORDER BY start", entityclass);
		query.setParameter("patientId", patient.getId());
		return findByQuery(query);
    }

    public List<Event> findBy(LocalDate date) throws PersistenceException {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atStartOfDay().plusDays(1);
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE start >= :startLocalDateTime and stop < :endLocalDateTime"
                + " or start < :startLocalDateTime and stop > :endLocalDateTime"
                + " or stop > :startLocalDateTime and stop <= :endLocalDateTime"
                + " or start >= :startLocalDateTime and start < :endLocalDateTime ORDER BY start", entityclass);
        query.setParameter("startLocalDateTime", start);
        query.setParameter("endLocalDateTime", end);
        return findByQuery(query);
    }

    public List<Event> findConflicting(Event event)  throws PersistenceException {
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j"
                + " WHERE (start >= :startLocalDateTime and stop < :endLocalDateTime"
                + " or start < :startLocalDateTime and stop > :endLocalDateTime"
                + " or stop > :startLocalDateTime and stop <= :endLocalDateTime"
                + " or start >= :startLocalDateTime and start < :endLocalDateTime)"
                + " and (patientId = :patientId or therapistId = :therapistId)",
                entityclass);
        query.setParameter("startLocalDateTime", event.getStart());
        query.setParameter("endLocalDateTime", event.getStop());
        query.setParameter("patientId", event.getPatient().getId());
        query.setParameter("therapistId", event.getTherapist().getId());

        return findByQuery(query);
    }

    public Event update(Event item) throws PersistenceException {
        EntityTransaction updateTransaction = beginTransaction();
        manager.merge(item);
        closeTransaction(updateTransaction);

        return item;
    }

    public Event remove(Event item) throws PersistenceException {

        EntityTransaction updateTransaction = beginTransaction();
        manager.remove(manager.contains(item) ? item : manager.merge(item));
        closeTransaction(updateTransaction);

        return item;
    }

    private List<Event> findByQuery(TypedQuery<Event> query) throws PersistenceException {
        List<Event> events = query.getResultList();
    	return events;
    }
}
