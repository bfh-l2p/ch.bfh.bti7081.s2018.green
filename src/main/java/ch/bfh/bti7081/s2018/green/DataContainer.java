package ch.bfh.bti7081.s2018.green;

import ch.bfh.bti7081.s2018.green.models.entities.Doctor;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import com.vaadin.server.VaadinSession;

import java.util.HashMap;
import java.util.Map;


// holds the data currently processed/ edited in the application

public class DataContainer {

    private static Map<String, DataContainer> instance = new HashMap<>();
    private Patient currentPatient;
    private Doctor currentDoctor;

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

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    public void setCurrentDoctor(Doctor currentDoctor) {
        this.currentDoctor = currentDoctor;
    }


}
