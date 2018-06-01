package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class NavMenuView extends CustomLayout implements View {

    public static final String NAME = "navmenu";

    //private CustomLayout navMenuLayout;

    //private HorizontalLayout wrapPatFile;
    private Label lblPatFile = new Label("Patient File");
    //private HorizontalLayout wrapJournal;
    private Label lblJournal = new Label("Journal");
    //private HorizontalLayout wrapedication;
    private Label lblMedication = new Label("Medication");
    //private HorizontalLayout wrapDiagnosis;
    private Label lblDiagnosis = new Label("Diagnosis");
    //private HorizontalLayout wrapJournal;
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

    public NavMenuView () {
        this.setTemplateName("navmenu");
        buildMenu();
    }

    private void buildMenu() {
        //Button b1 = new Button("Diagnosis");
        //b1.setSizeFull();
        //b1.addStyleName(ValoTheme.BUTTON_BORDERLESS);


        this.addComponent(wrapLabel(lblPatFile), "navMenuPatFile");
        this.addComponent(wrapLabel(lblJournal), "navMenuDiagnosis");
        this.addComponent(wrapLabel(lblMedication), "navMenuMedication");
        this.addComponent(wrapLabel(lblDiagnosis), "navMenuJournal");
        this.addComponent(wrapLabel(lblSchedules), "navMenuSchedules");
    }

    private HorizontalLayout wrapLabel (Label lbl)
    {
        HorizontalLayout ly = new HorizontalLayout();
        ly.setSizeFull();
        ly.addComponent(lbl);
        ly.setStyleName("menuBarButton");
        return ly;
    }

    public CustomLayout getNavMenuLayout () {
        return this;
    }
}
