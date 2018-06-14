package ch.bfh.bti7081.s2018.green.views;

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

public class MedicationView extends CustomLayout implements View {

    public static final String NAME = "medication";

    private Button btnAddMedication;
    private Grid<Medication> grdMedicamentGridView = new Grid<>();

    private Switch showExpired = new Switch("Show inactive medication",false);

    public MedicationView() {
        setTemplateName("medication");
        setId(NAME);
        buildView();
        if (showExpired.getValue())
        {
            new MedicationPresenter(this, true);
        }
        else {
            new MedicationPresenter(this, false);
        }

    }

    public Switch getShowExpired() {
        return showExpired;
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

    private void buildView() {
        showExpired.addStyleName("compact");
        addComponent(showExpired, "showExpired");

        // Adding button to create new medication
        btnAddMedication = new Button("New Medication");
        addComponent(this.btnAddMedication, "addMedicationTab");

        medGridBuilder();
    }

    private void medGridBuilder() {
        grdMedicamentGridView.setSelectionMode(Grid.SelectionMode.SINGLE);
        // Allow column reordering
        grdMedicamentGridView.setColumnReorderingAllowed(true);

        // populate columns
        grdMedicamentGridView.addColumn(Medication::getName).setCaption("Medicament");
        grdMedicamentGridView.addColumn(Medication::getStartDate).setCaption("Start").setId("medStartDate");
        grdMedicamentGridView.getColumn("medStartDate").setRenderer((Renderer) new LocalDateTimeRenderer("dd.MM.yy HH:mm"));
        grdMedicamentGridView.addColumn(Medication::getEndDate).setCaption("Stop").setId("medEndDate");
        grdMedicamentGridView.getColumn("medEndDate").setRenderer((Renderer) new LocalDateTimeRenderer("dd.MM.yy HH:mm"));
        grdMedicamentGridView.addColumn(Medication::getPeriode).setCaption("Frequency");
        grdMedicamentGridView.addColumn(Medication::getDose).setCaption("Dosis");
        grdMedicamentGridView.addColumn(m -> m.getPrescriber().getFullName()).setCaption("Prescriber");

        grdMedicamentGridView.setId("medicationAppGrid");
        grdMedicamentGridView.setWidth(100, Unit.PERCENTAGE);
        grdMedicamentGridView.setStyleGenerator(medication -> medication.isActive() ? "medActive" : "medNotActive");

        addComponent(grdMedicamentGridView, "dataGrid");
    }
}
