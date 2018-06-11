package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.managers.MedicationManager;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
import ch.bfh.bti7081.s2018.green.views.MedicationPrescriptionView;
import ch.bfh.bti7081.s2018.green.views.MedicationView;
import ch.bfh.bti7081.s2018.green.views.ScheduleAddView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

public class MedicationPresenter {

	private MedicationView view;
	private DataContainer data;

	public MedicationPresenter(MedicationView view) {
		this.view = view;
		this.data = DataContainer.getInstance();

        view.setGrdMedicamentGridViewItems(setMedicamentList(data.getCurrentPatient()));

        view.getMedicamentGrid().setSelectionMode(Grid.SelectionMode.SINGLE);

        // add edit button with event listener
        view.getMedicamentGrid().addComponentColumn(med -> {
            Button btn = new Button("View/Edit");
            btn.addClickListener(click -> {
                MedicationPrescriptionView medicationPrescriptionView = new MedicationPrescriptionView(med);
                this.view.getUI().addWindow(medicationPrescriptionView);
            });
            return btn;
        });

        view.getMedicamentGrid().setRowHeight(40);
	}

	private List<Medication> setMedicamentList (Patient pat) {
        MedicationManager manager = new MedicationManager();
        
        try {
        	
        List<Medication> medList =  manager.findBy(pat);
        return medList;
        
        } catch (PersistenceException e) {
        	
        	ErrorView.showError("Could not get medication-list from database", Page.getCurrent());        
        }
        
        // return empty list if error has occurred
		return new ArrayList<Medication>();
    }



}
