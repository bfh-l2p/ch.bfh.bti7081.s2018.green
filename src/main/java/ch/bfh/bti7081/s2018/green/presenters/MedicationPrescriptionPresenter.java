package ch.bfh.bti7081.s2018.green.presenters;

import javax.persistence.PersistenceException;

import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.views.MedicationView;
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

    public MedicationPrescriptionPresenter(MedicationPrescriptionView view) {
        this.view = view;
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

                // Show success-message in case saving was successful
                Notification.show("Data was saved!");

                view.close();
                NavigatorUI.navigator.navigateTo(MedicationView.NAME);
            }
        } catch (PersistenceException e) {

            ErrorView.showError("Medication couldn't be saved. Please try again!", Page.getCurrent());
        }
    }
}
