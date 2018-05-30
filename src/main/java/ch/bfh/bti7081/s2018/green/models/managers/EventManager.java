package ch.bfh.bti7081.s2018.green.models.managers;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.bti7081.s2018.green.models.entities.Event;


public class EventManager extends Manager<Event> {
    public EventManager() {
    	this.entityclass = Event.class;
	}

    public List<Event> findByPatient(Integer id) {
        setNewEntityManager();
        TypedQuery<Event> query = manager.createQuery("SELECT j FROM Event j WHERE patientId = :patientId", Event.class);
        query.setParameter("patientId", id);
        List<Event> items = query.getResultList();

        manager.close();
        return items;
    }
}
