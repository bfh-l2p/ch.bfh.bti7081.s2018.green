package ch.bfh.bti7081.s2018.green.views;

import java.util.ArrayList;
import java.util.List;
import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Grid;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPresenter;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.vaadin.ui.renderers.Renderer;
import org.vaadin.teemu.switchui.Switch;


public class MedicationView extends CustomLayout implements View {

    public static final String NAME = "medication";

    private NavigationButton btnAddMedication;
    private Grid<Medication> grdMedicamentGridView = new Grid<>();

    private Switch showExpired = new Switch("",false);
    public Switch getShowExpired() {
        return showExpired;
    }

    public MedicationView() {
        this.setTemplateName("medication");
        buildView();
        new MedicationPresenter(this, true);
    }
    public MedicationView(boolean filter) {
        this.setTemplateName("medication");
        buildView();
        if (filter)
        {
            new MedicationPresenter(this, true);
        }
        else {
            new MedicationPresenter(this, false);
        }

    }

    private void buildView() {
        List<String> customButtonStyles = new ArrayList<>();
        customButtonStyles.add("appMenuTab");

        // Adding button to create new medication
        this.btnAddMedication = new NavigationButton("New Medication", this);
        // Start with an empty medication because it's a new one to be created
        this.btnAddMedication.addClickListener((clickEvent) -> this.getUI().addWindow(new MedicationPrescriptionView(null, false)) );

        this.addComponent(this.btnAddMedication, "addMedicationTab");
        // @ToDO: add a toggle switch here to display expired (or "disabled") medications
        
        showExpired.addStyleName("compact");
        this.addComponent(showExpired, "showExpired");

        medGridBuilder();
    }

    private void medGridBuilder() {

        grdMedicamentGridView.setSelectionMode(Grid.SelectionMode.SINGLE);
        // Allow column reordering
        grdMedicamentGridView.setColumnReorderingAllowed(true);

        grdMedicamentGridView.addColumn(Medication::getName).setCaption("Medicament");
        grdMedicamentGridView.addColumn(Medication::isActive).setCaption("Active");
        grdMedicamentGridView.addColumn(Medication::getStartDate).setCaption("Start of").setId("medStartDate");
        grdMedicamentGridView.getColumn("medStartDate").setRenderer((Renderer) new LocalDateTimeRenderer("dd.MM.yyyy - HH:mm'h'"));
        grdMedicamentGridView.addColumn(Medication::getEndDate).setCaption("End of").setId("medEndDate");
        grdMedicamentGridView.getColumn("medEndDate").setRenderer((Renderer) new LocalDateTimeRenderer("dd.MM.yyyy - HH:mm'h'"));
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
