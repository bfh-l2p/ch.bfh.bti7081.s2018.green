package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.views.MedciationPrescriptionView;
import ch.bfh.bti7081.s2018.green.views.enumerations.TxtBtnSaveEdit;
import com.vaadin.ui.*;

import javax.persistence.*;
import java.time.LocalDateTime;

public class MedicationPrescriptionPresenter extends MedciationPrescriptionView {

    public MedicationPrescriptionPresenter (Medication med, CustomLayout body) {
        super(med, body);
        if (med == null) addCreateNewButtonListener(TxtBtnSaveEdit.SAVE.toString());
        else addEditButtonListener(TxtBtnSaveEdit.EDIT.toString());
    }

    /*
    * Adds Edit Button, onClick event disables fieldprotection
     */
    private void addEditButtonListener (String btnText) {
        Button btnEdit = new Button(btnText);

        btnEdit.addClickListener(click -> {
            btnEdit.setCaption("Cancel");
            this.addAllComponents(true);
            //this.addCancleButton();
            this.addCancelAction(btnEdit);
            this.addSaveButton();
        });

        this.addComponent(btnEdit, "medEditButton");
    }
    private void addCreateNewButtonListener (String btnText) {

        this.addSaveNewEntryButton();
        this.addCancelButton();
    }

    /*
    * Adds cancel button. OnClick protectes the fields and reloads the data from object
     */
    private void addCancelAction(Button btnCancelEdit) {
        btnCancelEdit.addClickListener(click ->{
            this.addTheComponents(false);
            addEditButtonListener("Edit");
            this.removeComponent("medSaveButton");

        });
    }

    /*
    * Adds a save button, the function is not implementend yet
     */
    private void addSaveButton () {
        Button btnSave = new Button("Save");
        btnSave.addClickListener(click -> {
            this.saveDataChanges();
            // maybe display "your data has been saved" notification here?
            //Notification.show("Your changes have been saved (not really, just a test)");
        });
        this.addComponent(btnSave, "medSaveButton");
    }

    private void addSaveNewEntryButton () {
        Button btnSave = new Button("Save");
        btnSave.addClickListener(click -> {
            this.saveNewData();
            // maybe display "your data has been saved" notification here?
            //Notification.show("New record has been saved (not really, just a test)");
        });
        this.addComponent(btnSave, "medEditButton");
    }

    private void addCancelButton () {
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(click-> {
            this.window.close();
        });
        this.addComponent(btnCancel, "medCancelEditButton");
    }

    //@ToDo: implement a proper function to retrieve data and create a new Medication object.
    private void saveNewData (){

        System.out.println("Medi Name:" + getMedName().getActualValue());
        System.out.println("Start Date: " + getMedStartDate().getActualValue());
        System.out.println("End Date: " + getMedEndDate());
        System.out.println("Period: " + getMedPeriod().getActualValue());
        System.out.println("Dose: " + getMedDose().getActualValue());
        //System.out.println("Prescriber: " + getMedPrescibed().getPrescriber().getFullName());
        //System.out.println("Patient: " + getMedPrescibed().getPatient().getFullName());


        Medication newMed = new Medication((String) getMedName().getActualValue(),
                                            (LocalDateTime) getMedStartDate().getActualValue(),
                                            (LocalDateTime) getMedEndDate().getActualValue(),
                Integer.parseInt((String) getMedPeriod().getActualValue()),
                Float.parseFloat((String) getMedDose().getActualValue()),
                            this.getStaffDummy(),
                           this.getPatDummy());

        // to use later when ToDo above is done...
        /*
         Medication newMed = new Medication((String) getMedName().getActualValue(),
                                            (LocalDateTime) getMedStartDate().getActualValue(),
                                            (LocalDateTime) getMedEndDate().getActualValue(),
                (int) getMedPeriod().getActualValue(),
                (float) getMedDose().getActualValue(),
                            getMedPrescibed().getPrescriber(),
                            getMedPrescibed().getPatient());
         */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //insert the new med. record
        tx.begin();
        em.persist(newMed);
        tx.commit();

        this.window.close();
    }
    private void saveDataChanges() {
        Notification.show("Not yet implemented");
    }
}
