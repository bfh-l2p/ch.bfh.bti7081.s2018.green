package ch.bfh.bti7081.s2018.green.views;

import java.time.LocalDateTime;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToFloatConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPrescriptionPresenter;

public class MedicationPrescriptionView extends Window implements View {
    
    public static final String NAME = "medicationPrescription";

    Medication medication;
    Binder<Medication> binder;
    Panel panel;
    TextField medName = new TextField();
    DateTimeField medStartDate = new DateTimeField();
    DateTimeField medStopDate = new DateTimeField();
    TextField medPeriod = new TextField();
    TextField medDose = new TextField();
    TextField medPrescriberFullName = new TextField();
    Button btnSave = new Button("Save medication");
    
    public MedicationPrescriptionView(Medication med) {
        if (med == null) {
        	// The user is adding a new medication
            med = new Medication();
            panel = new Panel("New Medication");
        } else {
        	panel = new Panel("Edit Medication");
        }
        this.medication = med;
        bindMedication(med);
        this.setModal(true);
        CustomLayout panelContent = new CustomLayout("medicationPrescription");
        panelContent.addComponent(medName, "medName");
        panelContent.addComponent(medStartDate, "medStartDate");
        panelContent.addComponent(medStopDate, "medStopDate");

        panelContent.addComponent(medPeriod, "medFrequency");
        panelContent.addComponent(medDose, "medDose");
        panelContent.addComponent(medPrescriberFullName, "medPrescriber");
        panelContent.addComponent(btnSave, "medSaveButton");
        panel.setContent(panelContent);
        setContent(panel);
        new MedicationPrescriptionPresenter(this);
    }
    
    private void bindMedication(Medication medication) {
        binder = new Binder<>();
        binder.forField(medName).asRequired("Please enter a medication name!").bind(Medication::getName, Medication::setName);
        binder.forField(medStartDate).bind(Medication::getStartDate, Medication::setStartDate);
        binder.forField(medStopDate).bind(Medication::getEndDate, Medication::setEndDate);
        // TODO: support integers: https://vaadin.com/docs/v8/framework/datamodel/datamodel-forms.html#datamodel.forms.conversion
        binder.forField(medPeriod).withConverter(new StringToIntegerConverter("Must enter a number")).bind(Medication::getPeriode, Medication::setPeriode);
        binder.forField(medDose).withConverter(new StringToFloatConverter("Must enter a number")).bind(Medication::getDose, Medication::setDose);
        //binder.forField(medPrescriberFullName).bind(Medication::getFullName, Medication::setEndDate);

        binder.readBean(medication);
    }

    public Medication getMedication() throws ValidationException{
        // TODO: validate input
        binder.writeBean(this.medication);
        System.out.println(medication.getStartDate());
        System.out.println(LocalDateTime.now());
        return medication;
    }
    
    public TextField getMedPeriod() {
        return medPeriod;
    }

    public TextField getMedDose() {
        return medDose;
    }

    public TextField getMedPrescriberFullName() {
        return medPrescriberFullName;
    }

    public DateTimeField getMedStartDate() {
        return medStartDate;
    }

    public DateTimeField getMedEndDate() {
        return medStopDate;
    }

    public TextField getMedName() {
        return medName;
    }
    
    public Button getSaveButton() {
        return btnSave;
    }
}