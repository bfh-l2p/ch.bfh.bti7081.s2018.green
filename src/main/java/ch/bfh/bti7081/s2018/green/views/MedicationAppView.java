package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.MedicationAppLayout;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;
import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class MedicationAppView extends MedicationAppLayout implements View {

    public static String NAME = "medicationapp";
    // @ToDo: later add a way to get the actual pat dossier name instead settin of a static text
    private Label lblMedicationOverview = new Label("Medication");

    private Label lblAddMedication = new Label ("Medication +");

    private Grid<Medication> grdMedicamentGridView = new Grid<>();

    public MedicationAppView () {

        // ToDo: remove the dummy values!
       /* Staff perscriber = new Staff("Ali", "Baba", new java.sql.Date(75,5,16), "Bachweg 123", "1234", "Example City", "a.baba@test.ch", "+41 12 345 67 89", StaffType.PSYCHIATRIST);
        Date start = new Date(118,2,15);
        Date end = new Date(118,6,12);
        Medication med = new Medication(1,"Ponstan", "Mefamine Acid", start, end, 4, "125mg", perscriber);

        ArrayList<Medication> medDummyList = new ArrayList();
        medDummyList.add(med);*/

      //  buildView();
    }

    public MedicationAppView (List<Medication> medDataList) {
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

        /*SingleSelectionModel<Medication> selectionModel = (SingleSelectionModel<Medication>) grdMedicamentGridView.setSelectionMode(Grid.SelectionMode.SINGLE);
        selectionModel.addSingleSelectionListener(event -> {
            Notification.show(getMedicationChoosed(selectionModel.getSelectedItem());
        });*/

       /* grdMedicamentGridView.addColumn(Medication::getID).setCaption("ID");
        grdMedicamentGridView.addColumn(Medication::getMediName).setCaption("Medicament");
        grdMedicamentGridView.addColumn(Medication::getChemAgent).setCaption("Chemical Agent");
        grdMedicamentGridView.addColumn(Medication::getStartDate).setCaption("Start of");
        grdMedicamentGridView.addColumn(Medication::getEndDate).setCaption("End of");
        grdMedicamentGridView.addColumn(Medication::getFrequency).setCaption("Frequency");
        grdMedicamentGridView.addColumn(Medication::getDosis).setCaption("Dosis");
        grdMedicamentGridView.addColumn(Medication::getPrescriberName).setCaption("Prescriber");

        grdMedicamentGridView.setId("medicationAppGrid");
        this.addComponent(grdMedicamentGridView, "dataGrid");*/


    }
}
