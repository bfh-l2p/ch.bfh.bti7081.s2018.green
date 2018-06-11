package ch.bfh.bti7081.s2018.green.views;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Grid;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPresenter;


public class MedicationView extends CustomLayout implements View {

    public static final String NAME = "medication";
    
    private NavigationButton btnAddMedication;
    private Grid<Medication> grdMedicamentGridView = new Grid<>();
  
    public MedicationView() {
        this.setTemplateName("medicationApp");
        buildView();
        new MedicationPresenter(this);
    }

    public MedicationView(List<Medication> medDataList) {
        this.setTemplateName("medicationApp");
        buildView();
    }

    private void buildView() {
        this.btnAddMedication = new NavigationButton("New medication", this);
        this.btnAddMedication.addClickListener((clickEvent) -> this.getUI().addWindow(new MedicationPrescriptionView(null)) );

        this.addComponent(this.btnAddMedication, "addMedicationTab");
        medGridBuilder();
    }

    private void medGridBuilder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm").withLocale(Locale.GERMAN);
        grdMedicamentGridView.setSelectionMode(Grid.SelectionMode.SINGLE);
        // Allow column reordering
        grdMedicamentGridView.setColumnReorderingAllowed(true);
        // Allow column hiding
        grdMedicamentGridView.getColumns().stream().forEach(column -> column.setHidable(true));

        grdMedicamentGridView.addColumn(Medication::getName).setCaption("Medicament");
        grdMedicamentGridView.addColumn(Medication::isActive).setCaption("Active");
        grdMedicamentGridView.addColumn(m -> m.getStartDate().format(formatter)).setCaption("Start");
        grdMedicamentGridView.addColumn(m -> m.getEndDate().format(formatter)).setCaption("Stop");
        grdMedicamentGridView.addColumn(Medication::getPeriode).setCaption("Frequency");
        grdMedicamentGridView.addColumn(Medication::getDose).setCaption("Dosis");
        grdMedicamentGridView.addColumn(m -> m.getPrescriber().getFullName()).setCaption("Prescriber");
        grdMedicamentGridView.setId("medicationAppGrid");
        this.addComponent(grdMedicamentGridView, "dataGrid");
    }
    
    public void setGrdMedicamentGridViewItems(List<Medication> items) {
        this.grdMedicamentGridView.setItems(items);
    }
    
    public Grid<Medication> getMedicamentGrid() {
        return grdMedicamentGridView;
    }
    
    public NavigationButton getBtnAddMedication() {
        return btnAddMedication;
    }
}
