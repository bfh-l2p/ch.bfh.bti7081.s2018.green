package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.managers.MedicationManager;
import ch.bfh.bti7081.s2018.green.views.MedicationPrescriptionView;
import ch.bfh.bti7081.s2018.green.views.enumerations.TxtBtnSaveEdit;
import com.vaadin.ui.*;

import javax.persistence.*;
import java.time.LocalDateTime;

public class MedicationPrescriptionPresenter {

    private DataContainer data;
    private MedicationPrescriptionView view;
    public MedicationPrescriptionPresenter(MedicationPrescriptionView view) {
        this.view = view;
       
        this.data = DataContainer.getInstance();
        addSaveButton();
    }

    /*
     * Adds a save button, the function is not implementend yet
     */
    private void addSaveButton () {
        Button btnSave = view.getSaveButton();
        btnSave.addClickListener(click -> {
            this.saveData();
            // maybe display "your data has been saved" notification here?
            //Notification.show("Your changes have been saved (not really, just a test)");
        });
       
    }

    private void saveData (){
        Medication medication = view.getMedication();
        
        MedicationManager manager = new MedicationManager();
        manager.add(medication);
  
        view.close();
    }
    private void saveDataChanges() {
        Notification.show("Not yet implemented");
    }

}