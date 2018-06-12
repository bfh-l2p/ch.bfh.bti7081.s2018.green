package ch.bfh.bti7081.s2018.green.views;

import java.time.LocalDateTime;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.DateTimeRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPrescriptionPresenter;

public class MedicationPrescriptionView extends Window implements View {

    public static final String NAME = "medicationPrescription";

    protected Window window;
    private Medication medication;
    private Binder<Medication> binder;
    private TextField medName = new TextField();
    private DateTimeField medStartDate = new DateTimeField();
    private DateTimeField medStopDate = new DateTimeField();
    private TextField medPeriod = new TextField();
    private TextField medDose = new TextField();
    private TextField medPrescriberFullName = new TextField();
    private DateTimeField medRecordCreated = new DateTimeField();
    private DateTimeField medRecordModified = new DateTimeField();
    private Button btnSave = new Button("Save");

    public MedicationPrescriptionView(Medication med, boolean isEditMode) {
        if (med == null) {
            med = new Medication();
        }
        this.medication = med;
        bindMedication(this.medication);
        this.setModal(true);
        Panel panel = new Panel("This is a Panel");
        CustomLayout panelContent = new CustomLayout("medicationPrescription");
        panelContent.addComponent(medName, "medName");
        panelContent.addComponent(medStartDate, "medStartDate");
        panelContent.addComponent(medStopDate, "medStopDate");

        panelContent.addComponent(medPeriod, "medFrequency");
        panelContent.addComponent(medDose, "medDose");
        //panelContent.addComponent(medPrescriberFullName, "medPrescriber");

        panelContent.addComponent(btnSave, "medSaveButton");
        panel.setContent(panelContent);
        setContent(panel);

        // make sure an element can just be edited 20 minutes long after start time of medication
        if (isEditMode && !this.medication.getStartDate().isAfter(LocalDateTime.now().minusMinutes(20))) {
            medName.setEnabled(false);
            medStartDate.setEnabled(false);
            medPeriod.setEnabled(false);
            medDose.setEnabled(false);
        }
        new MedicationPrescriptionPresenter(this);
    }

    // performs field validation for new records
    private void bindMedication(Medication medication) {
        this.validateFields();
        binder.readBean(medication);
    }

    //perform field validation when saving a recaord - for values that could only be validated when editing an existing record
    public Medication getMedication() {
        try {
            this.validateFields();
            if (medStartDate.getValue() != null)
            {
                // validate the stop date
                binder.forField(medStopDate)
                        .asRequired()
                        .withValidator(new DateTimeRangeValidator("End date must be between 'start date' and < today + 99yrs", medStartDate.getValue(), medStartDate.getValue().plusYears(99)))
                        .bind(Medication::getEndDate, Medication::setEndDate);
            }
            BinderValidationStatus<Medication> medStat = binder.validate();

            //check if there are any form validation errors
            if (medStat.hasErrors()) {
                Notification.show("Some fields contain invalid information (marked in red)");
                return null;
            }
            else {
                binder.writeBean(this.medication);
                System.out.println(medication.getStartDate());
                System.out.println(LocalDateTime.now());
            }
        } catch (ValidationException e) {
            // TODO Auto-generated catch block
            Notification.show("VALIDATION failed");
            e.printStackTrace();
        }
        return medication;
    }

    private void validateFields () {
        if (binder == null) {
            binder = new Binder<>();
        }

        binder.forField(medName)
                .asRequired()
                .withValidator(new StringLengthValidator("Medicament name must have 1-120 characters",1,120))
                .bind(Medication::getName, Medication::setName);
        binder.forField(medStartDate)
                .asRequired()
                .bind(Medication::getStartDate, Medication::setStartDate);
        binder.forField(medStopDate)
                .asRequired()
                .bind(Medication::getEndDate, Medication::setEndDate);
        binder.forField(medPeriod)
                .withConverter(
                        Integer::valueOf,
                        String::valueOf,
                        "Medication period is not a valid integer number"
                )
                .bind(Medication::getPeriode, Medication::setPeriode);
        binder.forField(medDose)
                .withConverter(
                        Float::valueOf,
                        String::valueOf,
                        "Medication dose is not a valid float number"
                )
                .bind(Medication::getDose, Medication::setDose);
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