package ch.bfh.bti7081.s2018.green.views;

import java.util.ArrayList;
import java.util.List;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Grid;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPresenter;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.vaadin.ui.renderers.Renderer;
import org.vaadin.teemu.switchui.Switch;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MedicationView extends CustomLayout implements View {

    public static final String NAME = "medication";

    private Button btnAddMedication;
    private Grid<Medication> grdMedicamentGridView = new Grid<>();

    private Switch showExpired = new Switch("",false);
    public Switch getShowExpired() {
        return showExpired;
    }

    public MedicationView() {
        this.setTemplateName("medication");
        this.setId(NAME);
        buildView();
        if (this.showExpired.getValue())
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
        this.btnAddMedication = new Button("New Medication");
        this.addComponent(this.btnAddMedication, "addMedicationTab");

        showExpired.addStyleName("compact");
        this.addComponent(showExpired, "showExpired");
        showExpired.setValue(false);

        medGridBuilder();
    }

    private void medGridBuilder() {
        grdMedicamentGridView.setSelectionMode(Grid.SelectionMode.SINGLE);
        // Allow column reordering
        grdMedicamentGridView.setColumnReorderingAllowed(true);

        // populate columns
        grdMedicamentGridView.addColumn(Medication::getName).setCaption("Medicament");
        grdMedicamentGridView.addColumn(Medication::isActive).setCaption("Active");
        grdMedicamentGridView.addColumn(Medication::getStartDate).setCaption("Start of").setId("medStartDate");
        grdMedicamentGridView.getColumn("medStartDate").setRenderer((Renderer) new LocalDateTimeRenderer("dd.MM.yyyy - HH:mm'h'"));
        grdMedicamentGridView.addColumn(Medication::getEndDate).setCaption("Stop").setId("medEndDate");
        grdMedicamentGridView.getColumn("medEndDate").setRenderer((Renderer) new LocalDateTimeRenderer("dd.MM.yyyy - HH:mm'h'"));
        grdMedicamentGridView.addColumn(Medication::getPeriode).setCaption("Frequency");
        grdMedicamentGridView.addColumn(Medication::getDose).setCaption("Dosis");
        grdMedicamentGridView.addColumn(m -> m.getPrescriber().getFullName()).setCaption("Prescriber");

        grdMedicamentGridView.setId("medicationAppGrid");
        grdMedicamentGridView.setWidth(100, Unit.PERCENTAGE);
        grdMedicamentGridView.setStyleGenerator(medication -> medication.isActive() ? "medActive" : "medNotActive");

        this.addComponent(grdMedicamentGridView, "dataGrid");
    }
    
    public void setGrdMedicamentGridViewItems(List<Medication> items) {
        this.grdMedicamentGridView.setItems(items);
    }

    public Grid<Medication> getMedicamentGrid() {
        return grdMedicamentGridView;
    }

    public Button getBtnAddMedication() {
        return btnAddMedication;
    }
}
