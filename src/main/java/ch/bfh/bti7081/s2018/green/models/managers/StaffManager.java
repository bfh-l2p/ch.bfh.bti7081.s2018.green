package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StaffManager implements IManager<Staff> {
    @Override
    public Staff get(int id) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Staff staff = em.find(Staff.class, id);
        tx.commit();

        em.close();

        return staff;
    }

    @Override
    public List<Staff> findAll() {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
        Root<Staff> rootEntry = cq.from(Staff.class);
        CriteriaQuery<Staff> all = cq.select(rootEntry);
        TypedQuery<Staff> allQuery = em.createQuery(all);
        List<Staff> staffs = allQuery.getResultList();

        em.close();

        return staffs;
    }

    @Override
    public Staff add(Staff item) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(item);
        tx.commit();
        em.close();

        return item;
    }

    public Staff update(Staff item) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.merge(item);
        tx.commit();
        em.close();

        return item;
    }

    public List<Staff> findBy(StaffType type) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
        Root<Staff> rootEntry = cq.from(Staff.class);
        CriteriaQuery<Staff> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("type"), type));
        TypedQuery<Staff> allQuery = em.createQuery(all);
        List<Staff> staffs = allQuery.getResultList();

        em.close();

        return staffs;
    }
    
    public Staff remove(Staff item) {
        DataContainer dc = DataContainer.getInstance();
        EntityManager em = dc.createPmsEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        em.remove(em.contains(item) ? item : em.merge(item));
        tx.commit();
        em.close();
        
        return item;
    }
}
