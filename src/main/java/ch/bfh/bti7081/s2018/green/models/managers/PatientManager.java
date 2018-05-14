package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PatientManager implements IManager<Patient> {
    @Override
    public Patient get(int id) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Patient patient = em.find(Patient.class, id);
        tx.commit();

        em.close();

        return patient;
    }

    @Override
    public List<Patient> findAll() {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
        Root<Patient> rootEntry = cq.from(Patient.class);
        CriteriaQuery<Patient> all = cq.select(rootEntry);
        TypedQuery<Patient> allQuery = em.createQuery(all);
        List<Patient> patients = allQuery.getResultList();

        em.close();

        return patients;
    }

    @Override
    public Patient add(Patient item) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(item);
        tx.commit();
        em.close();

        return item;
    }

    public Patient update(Patient item) {
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
