package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.NavigationMenuLayout;
import ch.bfh.bti7081.s2018.green.layouts.UiElement;
import ch.bfh.bti7081.s2018.green.views.interfaces.IClickableDivPanel;
import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

import java.util.ArrayList;
import java.util.List;

public class NavigationMenuView extends NavigationMenuLayout implements View, IClickableDivPanel {

    public static final String NAME = "navmenu";

    private Label lblPatFile = new Label("Patient File");
    private Label lblJournal = new Label("Journal");
    private Label lblMedication = new Label("Medication");
    private Label lblDiagnosis = new Label("Diagnosis");
    private Label lblSchedules = new Label("Schedules");

    public Label getLblPatFile() {
        return lblPatFile;
    }

    public Label getLblJournal() {
        return lblJournal;
    }

    public Label getLblMedication() {
        return lblMedication;
    }

    public Label getLblDiagnosis() {
        return lblDiagnosis;
    }

    public Label getLblSchedules() {
        return lblSchedules;
    }

    /*
    * To add a menu entry, simple add a line like this:
    * elements.add( new UiElement(wrapElement(<Label>ofNewElement), "DataLocationInHTMLTemplate"));
     */
    public NavigationMenuView() {

        List<UiElement> elements = new ArrayList<>();
        elements.add(new UiElement(wrapElement(lblPatFile), "navMenuPatFile"));
        elements.add( new UiElement(wrapElement(lblJournal), "navMenuDiagnosis"));
        elements.add(new UiElement(wrapElement(lblMedication), "navMenuMedication"));
        elements.add( new UiElement(wrapElement(lblDiagnosis), "navMenuJournal"));
        elements.add(new UiElement(wrapElement(lblSchedules), "navMenuSchedules"));
        buildComponentList(elements);
    }


    public CustomLayout getNavMenuLayout () {
        return this;
    }

    @Override
    public void buildComponentList(List<UiElement>  clickableElements) {
        for (UiElement el : clickableElements) {
            el.addElement(this);
        }

    }
}
