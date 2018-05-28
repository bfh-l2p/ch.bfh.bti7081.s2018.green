package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Person;

import javax.persistence.EntityTransaction;

public class PersonManager extends Manager<Person> {
	
	public PersonManager() {
		this.entityclass = Person.class;
	}

    public Person update(Person person) {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(person);
        closeTransaction(updateTransaction);

        return person;
    }
    
    public Person remove(Person person) {

    	EntityTransaction updateTransaction = beginTransaction();
        manager.remove(manager.contains(person) ? person : manager.merge(person));;
        closeTransaction(updateTransaction);
        
        return person;
    }
}
