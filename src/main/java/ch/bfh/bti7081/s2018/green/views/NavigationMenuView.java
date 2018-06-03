package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;

public class NavigationMenuView extends CustomLayout implements View{

    public static final String NAME = "navmenu";

    public NavigationButton btnPatFile;
    public NavigationButton btnJournal;
    public NavigationButton btnMedication;
    public NavigationButton btnDiagnosis;
    public NavigationButton btnSchedules;

    public NavigationButton getBtnPatFile() {
        return btnPatFile;
    }

    public NavigationButton getBtnJournal() {
        return btnJournal;
    }

    public NavigationButton getBtnMedication() {
        return btnMedication;
    }

    public NavigationButton getBtnDiagnosis() {
        return btnDiagnosis;
    }

    public NavigationButton getBtnSchedules() {
        return btnSchedules;
    }

    /*
    * To add a menu entry, simple add a lines here
     */
    public NavigationMenuView() {

        this.setTemplateName("navmenu");

        this.btnPatFile = new NavigationButton("Patient File", PatientFile.NAME, this );
        addBtn(this.btnPatFile, "navMenuPatFile");

        this.btnJournal = new NavigationButton("Journal", JournalView.NAME, this);
        addBtn(this.btnJournal, "navMenuJournal");

        this.btnMedication = new NavigationButton("Medication", MedicationView.NAME, this);
        addBtn(this.btnMedication, "navMenuMedication");

        this.btnDiagnosis = new NavigationButton("Diagnosis", DiagnosisView.NAME, this);
        addBtn(this.btnDiagnosis, "navMenuDiagnosis");

        this.btnSchedules = new NavigationButton("Schedules", EventListView.NAME, this);
        addBtn(this.btnSchedules, "navMenuSchedules");
    }


    public CustomLayout getNavMenuLayout () {
        return this;
    }

    public void addBtn(NavigationButton btn, String location) {
        this.addComponent(btn, location);

    }
}
