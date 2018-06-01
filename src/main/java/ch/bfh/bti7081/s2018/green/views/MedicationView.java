package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.MedicationLayout;
import ch.bfh.bti7081.s2018.green.layouts.UiElement;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPresenter;

import ch.bfh.bti7081.s2018.green.views.dummyValueGenerators.MedDummyList;
import ch.bfh.bti7081.s2018.green.views.interfaces.IClickableDivPanel;
import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MedicationView extends MedicationLayout implements View,IClickableDivPanel {

    public static String NAME = "medication";
    // @ToDo: later add a way to get the actual pat dossier name instead settin of a static text
    private Label lblMedicationOverview = new Label("Medication");

    private Label lblAddMedication = new Label ("Medication +");
    public Label getLblAddMedication() {
        return lblAddMedication;
    }

    private Grid<Medication> grdMedicamentGridView = new Grid<>();
    public Grid<Medication> getMedicamentGrid() {
        return grdMedicamentGridView;
    }

    public MedicationView () {

        List<Medication> medDummyList = MedDummyList.buildDummyMedList();

        buildView(medDummyList);
        new MedicationPresenter(this);
    }

    public MedicationView (List<Medication> medDataList) {
        buildView(medDataList);
    }

    private void buildView(List<Medication> mList) {
        List<UiElement> elements = new ArrayList<>();
        elements.add(new UiElement(wrapElement(lblMedicationOverview), "mainTab"));
        elements.add(new UiElement(wrapElement(lblAddMedication), "addMedicationTab"));
        buildComponentList(elements);


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

    @Override
    public void buildComponentList(List<UiElement> clickableElements) {
        for (UiElement el : clickableElements) {
            el.addElement(this);
        }
    }
}
