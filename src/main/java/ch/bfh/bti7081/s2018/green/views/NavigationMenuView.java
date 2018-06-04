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
        this.addComponent(this.btnPatFile, "navMenuPatFile");

        this.btnJournal = new NavigationButton("Journal", JournalView.NAME, this);
        this.addComponent(this.btnJournal, "navMenuJournal");

        this.btnMedication = new NavigationButton("Medication", MedicationView.NAME, this);
        this.addComponent(this.btnMedication, "navMenuMedication");

        this.btnDiagnosis = new NavigationButton("Diagnosis", DiagnosisView.NAME, this);
        this.addComponent(this.btnDiagnosis, "navMenuDiagnosis");

        this.btnSchedules = new NavigationButton("Schedules", EventListView.NAME, this);
        this.addComponent(this.btnSchedules, "navMenuSchedules");
    }


    public CustomLayout getNavMenuLayout () {
        return this;
    }

}
