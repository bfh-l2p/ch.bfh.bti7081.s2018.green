package ch.bfh.bti7081.s2018.green.models.managers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class Manager<T> {
	protected EntityManager manager;
	private final Class<T> entityclass;
	
	public Manager(Class<T> entityclass) {
		this.entityclass = entityclass;
	}

	public T get(int id) {
        EntityTransaction transaction = beginTransaction();
        T entity = manager.find(entityclass, id);
        closeTransaction(transaction);
        return entity;
	}
	
	public T add(T item) {
		EntityTransaction addTransaction = beginTransaction();
		manager.persist(item);
		addTransaction.commit();
		return item;
	}
	
	public List<T> findAll() {
        setNewEntityManager();
		CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityclass);
        Root<T> rootEntry = cq.from(entityclass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = manager.createQuery(all);
        List<T> items = allQuery.getResultList();

        manager.close();

        return items;
	}
    
    public EntityTransaction beginTransaction() {
    	setNewEntityManager();
    	EntityTransaction transaction = manager.getTransaction();
    	transaction.begin();
    	return transaction;
    }
    
    public EntityTransaction closeTransaction(EntityTransaction transaction) {
    	transaction.commit();
    	manager.close();
    	return transaction;
    }
    
    public EntityManager createPmsEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        return emf.createEntityManager();
    }
    
    public void setNewEntityManager() {
    	this.manager = createPmsEntityManager();
    }
}
