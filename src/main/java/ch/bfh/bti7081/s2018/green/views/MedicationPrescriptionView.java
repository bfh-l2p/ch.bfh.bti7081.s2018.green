package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPrescriptionPresenter;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPresenter;
import com.vaadin.data.*;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

import java.time.LocalDateTime;

public class MedicationPrescriptionView extends Window implements View {

    public static final String NAME = "medicationPrescription";
    protected Window window;
    private MedicationPresenter medViewBehind;
    private Panel panel;
    private Medication medication;
    private Binder<Medication> binder = new Binder<>();
    private TextField medName = new TextField();
    private DateTimeField medStartDate = new DateTimeField();
    private DateTimeField medStopDate = new DateTimeField();
    private TextField medPeriod = new TextField();
    private TextField medDose = new TextField();
    private TextField medPrescriberFullName = new TextField();
    private DateTimeField medRecordCreated = new DateTimeField();
    private DateTimeField medRecordModified = new DateTimeField();
    private Button btnSave = new Button("Save");

    public MedicationPrescriptionView(MedicationPresenter viewBehind, Medication med, boolean isEditMode) {

        this.medViewBehind = viewBehind;

        if (med == null) {
            // The user is adding a new medication
            panel = new Panel("New Medication");
            med = new Medication();
        } else {
            panel = new Panel("Edit Medication");
        }
        this.medication = med;
        bindMedication(this.medication);
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
        this.setContent(panel);

        // make sure an element can just be edited 20 minutes long after start time of medication
        if (isEditMode && !this.medication.getStartDate().isAfter(LocalDateTime.now().minusMinutes(20))) {
            medName.setEnabled(false);
            medStartDate.setEnabled(false);
            medPeriod.setEnabled(false);
            medDose.setEnabled(false);
        }
        new MedicationPrescriptionPresenter(this, viewBehind);
    }

    // performs field validation for new records
    private void bindMedication(Medication medication) {
        this.validateFields();
        binder.readBean(medication);
    }

    //perform field validation when saving a record - for values that could only be validated when editing an existing record
    public Medication getMedication() {
        try {
            BinderValidationStatus<Medication> medStat = binder.validate();
            //check if there are any form validation errors
            if (medStat.hasErrors()) {
                Notification.show("Some fields contain invalid information (marked in red)");
                return null;
            } else {
                binder.writeBean(this.medication);
            }
        } catch (ValidationException e) {
            Notification.show("Validation failed");
            e.printStackTrace();
        }
        return medication;
    }

    private void validateFields() {
        binder.forField(medName)
                .asRequired()
                .withValidator(new StringLengthValidator("Medicament name must have 1-120 characters", 1, 120))
                .bind(Medication::getName, Medication::setName);
        binder.forField(medStartDate)
                .asRequired()
                .bind(Medication::getStartDate, Medication::setStartDate);
        binder.forField(medStopDate)
                .asRequired()
                .withValidator((Validator<LocalDateTime>) (localDateTime, valueContext) -> {
                    if (medStartDate.getValue().isBefore(medStopDate.getValue())) {
                        return ValidationResult.ok();
                    }
                    return ValidationResult.error("End date must start after start date");
                })
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