package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.managers.MedicationManager;
import com.vaadin.ui.*;
import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.views.MedicationView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MedicationPresenter {

	private MedicationView view;
	private DataContainer data;

	public MedicationPresenter(MedicationView view) {
		this.view = view;
		this.data = DataContainer.getInstance();

        //this.setGrdMedicamentGridViewItems(setMedicamentList(data.getCurrentPatient()));
        view.setGrdMedicamentGridViewItems(setMedicamentList(data.getCurrentPatient()));


        view.getMedicamentGrid().setSelectionMode(Grid.SelectionMode.SINGLE);

        // add edit button with event listener
        view.getMedicamentGrid().addComponentColumn(med -> {
            Button btn = new Button("View/Edit");
            btn.addClickListener(click -> {
                new MedicationPrescriptionPresenter(med, view);
            });
            return btn;
        });

        view.getMedicamentGrid().setRowHeight(40);
	}

	private List<Medication> setMedicamentList (Patient pat) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();

        MedicationManager mmgr = new MedicationManager();

        List<Medication> medOfPatient = mmgr.findBy(pat);
        return medOfPatient;
    }



}
