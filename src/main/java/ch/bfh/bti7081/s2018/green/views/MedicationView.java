package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.MedicationAppLayout;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPresenter;

import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MedicationView extends MedicationAppLayout implements View {

    public static String NAME = "medication";
    // TODO: add a way to get the actual pat dossier name instead settin of a static text
    private Label lblMedicationOverview = new Label("Medication");

    private Label lblAddMedication = new Label ("Medication +");

    private Grid<Medication> grdMedicamentGridView = new Grid<>();

    public MedicationView () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();

        Medication med = em.find(Medication.class, 1);
        List<Medication> medDummyList = new ArrayList<>();
        medDummyList.add(med);

        buildView(medDummyList);
        new MedicationPresenter(this);
    }

    public MedicationView(List<Medication> medDataList) {
        buildView(medDataList);
    }

    private void buildView(List<Medication> mList) {
        this.addComponent(lblMedicationOverview, "mainTab");
        this.addComponent(lblAddMedication, "addMedicationTab");

        medGridBuilder(mList);
    }

    private void medGridBuilder (List<Medication> data) {
        grdMedicamentGridView.setItems(data);

        grdMedicamentGridView.setSelectionMode(Grid.SelectionMode.SINGLE);
        // Allow column reordering
        grdMedicamentGridView.setColumnReorderingAllowed(true);
        // Allow column hiding
        grdMedicamentGridView.getColumns().stream().forEach(column -> column.setHidable(true));

        grdMedicamentGridView.addColumn(Medication::getId).setCaption("ID");
        grdMedicamentGridView.addColumn(Medication::getName).setCaption("Medicament");
        grdMedicamentGridView.addColumn(Medication::getStartDate).setCaption("Start of");
        grdMedicamentGridView.addColumn(Medication::getEndDate).setCaption("End of");
        grdMedicamentGridView.addColumn(Medication::getPeriode).setCaption("Frequency");
        grdMedicamentGridView.addColumn(Medication::getDose).setCaption("Dosis");
        grdMedicamentGridView.addColumn(m -> m.getPrescriber().getFullName()).setCaption("Prescriber");

        grdMedicamentGridView.setId("medicationAppGrid");
        this.addComponent(grdMedicamentGridView, "dataGrid");
    }

    public Grid<Medication> getMedicamentGrid() {
        return grdMedicamentGridView;
    }
}
