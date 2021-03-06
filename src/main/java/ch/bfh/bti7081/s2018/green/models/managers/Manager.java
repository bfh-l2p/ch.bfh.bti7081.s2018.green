package ch.bfh.bti7081.s2018.green.models.managers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class Manager<T> {
	protected static final EntityManager manager = Persistence.createEntityManagerFactory("pmsDB").createEntityManager();
	protected Class<T> entityclass; 

	public T get(int id) throws PersistenceException {
		EntityTransaction getTransaction = beginTransaction();
		T entity = manager.find(entityclass, id);
		closeTransaction(getTransaction);
		if (entity == null) {
			throw new PersistenceException();
		}
		return entity;
	}

	public T add(T item) throws PersistenceException {
		EntityTransaction addTransaction = beginTransaction();
		manager.persist(item);
		closeTransaction(addTransaction);
		return item;
	}

	public List<T> findAll() throws PersistenceException {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityclass);
		Root<T> rootEntry = cq.from(entityclass);
		CriteriaQuery<T> all = cq.select(rootEntry);
		TypedQuery<T> allQuery = manager.createQuery(all);
		List<T> items = allQuery.getResultList();
		
		return items;
	}

	protected EntityTransaction beginTransaction() {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		return transaction;
	}

	protected EntityTransaction closeTransaction(EntityTransaction transaction) throws PersistenceException {
		transaction.commit();
		return transaction;
	}
}
