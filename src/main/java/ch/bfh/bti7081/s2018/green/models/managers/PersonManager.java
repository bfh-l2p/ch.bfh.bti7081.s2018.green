package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Person;

import javax.persistence.EntityTransaction;

public class PersonManager extends Manager<Person> {
    public PersonManager(Class<Person> entityclass) {
		super(entityclass);
	}

    public Person update(Person item) {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(item);
        closeTransaction(updateTransaction);

        return item;
    }
    
    public Person remove(Person item) {

    	EntityTransaction updateTransaction = beginTransaction();
        manager.remove(manager.contains(item) ? item : manager.merge(item));
        closeTransaction(updateTransaction);
        
        return item;
    }
}
