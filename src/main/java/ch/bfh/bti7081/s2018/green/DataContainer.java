package ch.bfh.bti7081.s2018.green;

import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import com.vaadin.server.VaadinSession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;


// holds the data currently processed/ edited in the application

public class DataContainer {

    private static Map<String, DataContainer> instance = new HashMap<>();
    private Patient currentPatient;
    private Staff currentStaff;

    private DataContainer() {
    }

    /**
     * Make sure to get exactly one instance per http session.
     * This ensures consistency for the current user and
     * isolation against other users.
     *
     * @return instance
     */
    public static DataContainer getInstance() {
        String currentSessionId = VaadinSession.getCurrent().getSession().getId();

        return DataContainer.getInstance(currentSessionId);
    }

    /**
     * Get instance by providing a session id manually.
     *
     * @param artificialSessionId the session id
     * @return
     */
    public static DataContainer getInstance(String artificialSessionId) {
        if (!instance.containsKey(artificialSessionId)) {
            instance.put(artificialSessionId, new DataContainer());
        }
    	
    	return instance.get(artificialSessionId);
    }

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    public Staff getCurrentStaff() {
        return currentStaff;
    }

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }

    public EntityManager createPmsEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        return emf.createEntityManager();
    }
}
