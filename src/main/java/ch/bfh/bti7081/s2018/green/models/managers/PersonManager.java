package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PersonManager implements IManager<Person> {
    @Override
    public Person get(int id) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Person person = em.find(Person.class, id);
        tx.commit();

        em.close();

        return person;
    }

    @Override
    public List<Person> findAll() {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> rootEntry = cq.from(Person.class);
        CriteriaQuery<Person> all = cq.select(rootEntry);
        TypedQuery<Person> allQuery = em.createQuery(all);
        List<Person> persons = allQuery.getResultList();

        em.close();

        return persons;
    }

    @Override
    public Person add(Person item) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(item);
        tx.commit();
        em.close();

        return em.find(Person.class, item.getId());
    }

    public Person update(Person item) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.merge(item);
        tx.commit();
        em.close();

        return item;
    }
}
