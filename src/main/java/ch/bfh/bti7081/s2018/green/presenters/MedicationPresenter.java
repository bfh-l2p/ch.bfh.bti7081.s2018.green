package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.managers.MedicationManager;

import ch.bfh.bti7081.s2018.green.presenters.filters.MedicationFilter;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
import ch.bfh.bti7081.s2018.green.views.MedicationPrescriptionView;
import ch.bfh.bti7081.s2018.green.views.MedicationView;

import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * This presenter class is getting medications from database of the current patient.
 * Then it gets added to the vie (MedicatioView) GridView.
 */
public class MedicationPresenter {

    private MedicationView view;
    private DataContainer data;

    private Predicate<Medication> medFilter = new MedicationFilter().getMedFilter();

    /*
     * Constructor is building the Grid-Content of the view
     * @param: the MedicationView
     * @param: true or false to decide if results need to be filtered
     */
    public MedicationPresenter(MedicationView view, boolean filter) {
        this.view = view;
        this.data = DataContainer.getInstance();

        if (filter) {
            view.setGrdMedicamentGridViewItems(setMedicamentList(data.getCurrentPatient(), medFilter));
            view.getMedicamentGrid().getDataProvider().refreshAll();
        }
        else {
            view.setGrdMedicamentGridViewItems(setMedicamentList(data.getCurrentPatient(), medFilter));
            view.getMedicamentGrid().getDataProvider().refreshAll();
        }

        this.build();
    }

    private void build () {
        view.getMedicamentGrid().setSelectionMode(Grid.SelectionMode.SINGLE);

        // add edit button with event listener to each row
        if (view.getMedicamentGrid().getColumn("EditButtonRow") == null)
        {
            view.getMedicamentGrid().addComponentColumn(med -> {
                Button btn = new Button("Edit");
                btn.addClickListener(click -> {
                    MedicationPrescriptionView medicationPrescriptionView = new MedicationPrescriptionView(med, true);
                    this.view.getUI().addWindow(medicationPrescriptionView);
                });
                return btn;
            }).setId("EditButtonRow");
        }

        //add "show expired medication" on/off switch
        view.getShowExpired().addValueChangeListener((event) ->
                showExpiredMedication(event.getValue())
        );

        view.getMedicamentGrid().setRowHeight(40);
    }

    /*
     * Builds a list containing a patients medication records via MedicationManager out of the DB.
     * @param: Patient - get this out of the DB (ex. by calling DataContainer.getInstance.getCurrentPatient()
     * @param: Predicate<Medication> filter --> any stream compatible filter predicate applyable to medication entity
     * @return: List<Medication> containing all medication records of a given patient. List is empty if nothing has been found
     * @catch: ErrorView.showError if could not get items out of the database
     */
    private List<Medication> setMedicamentList (Patient pat, Predicate<Medication> filter) {
        MedicationManager manager = new MedicationManager();

        try {

            List<Medication> medList =  manager.findBy(pat);
            //now filter out all expired medications
            if (filter != null) {
                medList = medList
                        .stream()
                        .filter(filter)
                        .collect(Collectors.toList());

            }
            // else return full list
            return medList;

        } catch (PersistenceException e) {

            ErrorView.showError("Could not get medication-list from database", Page.getCurrent());
        }
        // return empty list if an error has occurred
        return new ArrayList<Medication>();
    }

    // rebuilds the list if the "show inactive medication" switch has been clicked
    private void showExpiredMedication (boolean showExpiredRecords) {

        if (showExpiredRecords) {
            view.setGrdMedicamentGridViewItems(setMedicamentList(data.getCurrentPatient(), null));
            this.build();
        }
        else {
            view.setGrdMedicamentGridViewItems(setMedicamentList(data.getCurrentPatient(), medFilter));
            this.build();
        }
    }

}
