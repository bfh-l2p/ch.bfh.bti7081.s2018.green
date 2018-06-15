package ch.bfh.bti7081.s2018.green.presenters;

import javax.persistence.PersistenceException;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.managers.MedicationManager;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
import ch.bfh.bti7081.s2018.green.views.MedicationPrescriptionView;

public class MedicationPrescriptionPresenter {

    private DataContainer data;
    private MedicationPrescriptionView view;
    private MedicationPresenter medicationPresenter;
    public MedicationPrescriptionPresenter(MedicationPrescriptionView view, MedicationPresenter medicationPresenter) {
        this.view = view;
        this.medicationPresenter = medicationPresenter;
        this.data = DataContainer.getInstance();

        view.getSaveButton().addClickListener(click -> {
            this.saveData();
        });
    }

    private void saveData (){
        try {
            Medication medication = view.getMedication();
            if (medication != null)
            {
                medication.setPatient(data.getCurrentPatient());
                medication.setPrescriber(data.getCurrentStaff());
                MedicationManager manager = new MedicationManager();
                if (medication.getId() == 0) {
                    manager.add(medication);
                } else {
                    manager.update(medication);
                }

                Notification.show("Data was saved!");
                medicationPresenter.refreshMedicationGrid();
                view.close();
            }
        } catch (PersistenceException e) {
            ErrorView.showError("Medication couldn't be saved. Please try again!", Page.getCurrent());
        }
    }
}
