package ch.bfh.bti7081.s2018.green.presenters;

import java.util.List;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.ComboBox;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.managers.PatientManager;
import ch.bfh.bti7081.s2018.green.views.HeaderView;
import ch.bfh.bti7081.s2018.green.views.MedicationView;

public class HeaderPresenter {

    private HeaderView view;
	private DataContainer data;

    public HeaderPresenter (HeaderView view) {
        this.view = view;
        this.data = DataContainer.getInstance();
        
        // Sets the patient that was selected by the user
        view.getCboxPatients().addValueChangeListener(event -> {            	
        	// Check if selected patient is previous patient, if not, set new patient
        	if(data.getCurrentPatient() == null ||data.getCurrentPatient().getId() != event.getValue().getId()) {
            	data.setCurrentPatient(event.getValue());
            	
        	}
        });
        
        enteredView();
    }

    private void enteredView(){
    	assembleCboxPatients();
    }
    
    private void assembleCboxPatients(){
    	
    	// Get ComboBox from View
    	ComboBox<Patient> cboxPatients = this.view.getCboxPatients();
                
        // Get all available patients from DB
        List<Patient> pList = new PatientManager().findAll();
        
        cboxPatients.setCaption("Select Patient:");        
        cboxPatients.setItems(pList);
        
        // Set property that will be displayed on combobox
        cboxPatients.setItemCaptionGenerator(Patient::getFullName);
        
        // Set size of ComboBox
        cboxPatients.setHeight(30, Unit.PIXELS);
        cboxPatients.setWidth(200, Unit.PIXELS);
        
        // First patient in list will be set by default
        // Prevent empty selection
        if(!pList.isEmpty()) {
        	cboxPatients.setValue(pList.get(0));
        	cboxPatients.setEmptySelectionAllowed(false);            
        }
    }
    
    public void addUserName(String username) {
        view.getLblLoggedOnUser().setValue(username);
    }
}
