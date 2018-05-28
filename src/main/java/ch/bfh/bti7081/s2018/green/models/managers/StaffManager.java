package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StaffManager extends Manager<Staff> {

    public StaffManager() {
    	this.entityclass = Staff.class;
	}

	public Staff update(Staff staff) {
    	EntityTransaction updateTransaction = beginTransaction();
        manager.merge(staff);
        closeTransaction(updateTransaction);

        return staff;
    }
    
    public Staff remove(Staff staff) {

    	EntityTransaction updateTransaction = beginTransaction();
        manager.remove(manager.contains(staff) ? staff : manager.merge(staff));
        closeTransaction(updateTransaction);
        
        return staff;
    }
	
    public List<Staff> findBy(StaffType type) {
    	setNewEntityManager();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
        Root<Staff> rootEntry = cq.from(Staff.class);
        CriteriaQuery<Staff> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("type"), type));
        TypedQuery<Staff> allQuery = manager.createQuery(all);
        List<Staff> staffs = allQuery.getResultList();

        manager.close();

        return staffs;
    }
}
