package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.MedicationAppLayout;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;
import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.components.grid.SingleSelectionModel;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MedicationView extends MedicationAppLayout implements View {

    public static String NAME = "medication";
    // @ToDo: later add a way to get the actual pat dossier name instead settin of a static text
    private Label lblMedicationOverview = new Label("Medication");

    private Label lblAddMedication = new Label ("Medication +");

    private Grid<Medication> grdMedicamentGridView = new Grid<>();

    public MedicationView () {

        // ToDo: remove the dummy values!
        Staff prescriber = new Staff("Ali", "Baba", LocalDate.now(), "Bachweg 123", "1234", "Example City", "a.baba@test.ch", "+41 12 345 67 89", StaffType.PSYCHIATRIST);
        LocalDateTime start = LocalDateTime.of(2018, 4, 10, 18, 34);
        LocalDateTime end = LocalDateTime.of(2018, 4, 12, 18, 34);
        Medication med = new Medication("Ponstan", start, end, 4, 125, prescriber);

        ArrayList<Medication> medDummyList = new ArrayList();
        medDummyList.add(med);

        buildView(medDummyList);
    }

    public MedicationView (List<Medication> medDataList) {
        buildView(medDataList);
    }

    private void buildView(List<Medication> mList) {
        this.addComponent(lblMedicationOverview, "mainTab");
        this.addComponent(lblAddMedication, "addMedicationTab");

        medGridBuilder(mList);
    }

    private void medGridBuilder (List<Medication> data) {

        grdMedicamentGridView.setItems(data);
        //grdMedicamentGridView.setSizeFull();

        grdMedicamentGridView.setSelectionMode(Grid.SelectionMode.SINGLE);
        // Allow column reordering
        grdMedicamentGridView.setColumnReorderingAllowed(true);
        // Allow column hiding
        grdMedicamentGridView.getColumns().stream().forEach(column -> column.setHidable(true));

        grdMedicamentGridView.addSelectionListener(event -> {
            Notification.show("You have clicked on element");
        });

        SingleSelectionModel<Medication> selectionModel = (SingleSelectionModel<Medication>) grdMedicamentGridView.setSelectionMode(Grid.SelectionMode.SINGLE);
        selectionModel.addSingleSelectionListener(event -> {
            Notification.show(selectionModel.getSelectedItem().get().getName());
        });

        grdMedicamentGridView.addColumn(Medication::getId).setCaption("ID");
        grdMedicamentGridView.addColumn(Medication::getName).setCaption("Medicament");
        //grdMedicamentGridView.addColumn(Medication::getChemAgent).setCaption("Chemical Agent");
        grdMedicamentGridView.addColumn(Medication::getStartDate).setCaption("Start of");
        grdMedicamentGridView.addColumn(Medication::getEndDate).setCaption("End of");
        grdMedicamentGridView.addColumn(Medication::getPeriode).setCaption("Frequency");
        grdMedicamentGridView.addColumn(Medication::getDose).setCaption("Dosis");
        grdMedicamentGridView.addColumn(m -> m.getPrescriber().getFullName()).setCaption("Prescriber");

        grdMedicamentGridView.setId("medicationAppGrid");
        this.addComponent(grdMedicamentGridView, "dataGrid");


    }
}
