package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.UiElementHasValue;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.views.dummyValueGenerators.MedDummyList;
import ch.bfh.bti7081.s2018.green.views.interfaces.IRetSetAbstField;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import java.time.LocalDateTime;
import java.util.*;

public class MedciationPrescriptionView extends CustomLayout implements View, IRetSetAbstField {

    public Medication getMedPrescibed() {
        return medPrescibed;
    }

    private List<UiElementHasValue> listUiElements = new ArrayList<>();

    protected Window window;

    public UiElementHasValue getMedPeriod() {
        return medPeriod;
    }

    public UiElementHasValue getMedDose() {
        return medDose;
    }

    public UiElementHasValue getMedPrescriberFullName() {
        return medPrescriberFullName;
    }

    public UiElementHasValue getMedRecordCreated() {
        return medRecordCreated;
    }

    public UiElementHasValue getMedRecordModified() {
        return medRecordModified;
    }

    public UiElementHasValue getMedStartDate() {
        return medStartDate;
    }

    public UiElementHasValue getMedEndDate() {
        return medEndDate;
    }

    public UiElementHasValue getMedName() {
        return medName;
    }

    UiElementHasValue medName;
    UiElementHasValue medStartDate;
    UiElementHasValue medEndDate;
    UiElementHasValue medPeriod;
    UiElementHasValue medDose;
    UiElementHasValue medPrescriberFullName;
    UiElementHasValue medRecordCreated;
    UiElementHasValue medRecordModified;

    Medication medPrescibed;
    //List<UiElementHasValue> txtToAdd;

    public Patient getPatDummy() {
        return PatDummy;
    }

    public Staff getStaffDummy() {
        return StaffDummy;
    }

    Patient PatDummy = MedDummyList.buildDummyMedList().get(0).getPatient();
    Staff StaffDummy = MedDummyList.buildDummyMedList().get(0).getPrescriber();

    private CustomLayout body;
    public CustomLayout getBody() {
        return body;
    }

    //@ToDo: add a proper way to get logged in Staff-Personnel and the actually opened pat. dossier instead of hardcoding it

    public MedciationPrescriptionView (Medication med, CustomLayout body) {

        this.setTemplateName("medicationPrescription");

        this.medPrescibed = med;
        this.body = body;
        display(body);
    }

    // In case a new medication shall be created
    public MedciationPrescriptionView (Layout body) {
        this.medPrescibed = null;
    }

    private void display (Layout body) {

        this.window = new Window();
        this.window.setWidth("40%");

        this.addTheComponents(false);

        this.window.isDraggable();
        this.window.isModal();
        this.window.setContent(this);

        body.getUI().getUI().addWindow(window);
    }

    protected void addTheComponents (boolean editMode) {
        // set UI Elements
        this.medName = new UiElementHasValue(new TextField(), "medName");
        this.medStartDate = new UiElementHasValue(new DateTimeField(), "medStartDate");
        this.medEndDate = new UiElementHasValue(new DateTimeField(), "medStopDate");
        this.medPeriod = new UiElementHasValue(new TextField(),"medFrequency");
        this.medDose = new UiElementHasValue(new TextField(),"medDose");
        this.medPrescriberFullName = new UiElementHasValue(new TextField(),"medPrescriber", true);
        this.medRecordCreated = new UiElementHasValue(new DateTimeField(),"medCreated", true);
        this.medRecordModified = new UiElementHasValue(new DateTimeField(),"medUpdated", true);


        //if an existing record shall be edited
        if (this.medPrescibed != null) {
            this.medName.setValue( medPrescibed.getName());
            this.medStartDate.setValue(medPrescibed.getStartDate());
            this.medEndDate.setValue(medPrescibed.getEndDate());
            this.medPeriod.setValue(medPrescibed.getPeriode());
            this.medDose.setValue(new String().valueOf(medPrescibed.getDose()));
            this.medPrescriberFullName.setValue(medPrescibed.getPrescriber().getFullName());
            this.medRecordCreated.setValue(medPrescibed.getCreated());
            this.medRecordModified.setValue(medPrescibed.getUpdated());
        }
        // if a new empty form shall be created
        else {
            this.medStartDate.setValue(LocalDateTime.now());
            this.medEndDate.setValue(LocalDateTime.now());
            editMode = true;
        }
            listUiElements.add(this.medName);
            listUiElements.add(this.medStartDate);
            listUiElements.add(this.medEndDate);
            listUiElements.add(this.medPeriod);
            listUiElements.add(this.medDose);

            // just display time created/modified & prescriber if editing an existing one
            if (this.medPrescibed != null) {
                listUiElements.add(this.medRecordCreated);
                listUiElements.add(this.medRecordModified);
                // this field shall not be changed trough the user itself
                listUiElements.add(this.medPrescriberFullName);
            }
        // does add all elements from List txtToAdd
        addAllComponents(listUiElements, editMode);
    }

    protected void addAllComponents (List<UiElementHasValue> list, boolean state) {
        for (UiElementHasValue el : list) {
            if (!el.isProtectedField()) {
                el.getComponent().setEnabled(state);
            }
            else {
                el.getComponent().setEnabled(false);
            }
            this.addComponent(el.getComponent(), el.getLocation());
        }
    }
    protected void addAllComponents (boolean state) {
        for (UiElementHasValue el : this.listUiElements) {
            if (!el.isProtectedField()) {
                el.getComponent().setEnabled(state);
            }
            else {
                el.getComponent().setEnabled(false);
            }
            this.addComponent(el.getComponent(), el.getLocation());
        }
    }
}
