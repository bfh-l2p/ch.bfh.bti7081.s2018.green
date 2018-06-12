package ch.bfh.bti7081.s2018.green;

import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;

import java.util.HashMap;
import java.util.Map;


// holds the data currently processed/ edited in the application

public class DataContainer {

    private static Map<String, DataContainer> instance = new HashMap<>();
    private Patient currentPatient;
    private Staff currentStaff;
    private Navigator navigator;

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
    	VaadinSession currentSession = VaadinSession.getCurrent();
        String currentSessionId = "";
    	
        if (currentSession == null) {
        	currentSessionId = "test1234";
        } else {
        	currentSessionId = VaadinSession.getCurrent().getSession().getId();
        }
        
        if (!instance.containsKey(currentSessionId)) {
            instance.put(currentSessionId, new DataContainer());
        }
    	
    	return instance.get(currentSessionId);
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

    public Navigator getCurrentNavigator() { return navigator; }

    public void setCurrentNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
