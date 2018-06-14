package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Component;

public class NavigationMenuView extends CustomLayout implements View{

    public static final String NAME = "navmenu";

    private NavigationButton btnJournal;
    private NavigationButton btnMedication;
    private NavigationButton btnSchedules;
    /*
    * To add a menu entry, simple add a lines here
     */
    public NavigationMenuView() {

        this.setTemplateName("navmenu");

        this.btnJournal = new NavigationButton("Journal", JournalView.NAME, this);
        this.addComponent(this.btnJournal, "navMenuJournal");
        this.btnJournal.addStyleName("active");

        this.btnMedication = new NavigationButton("Medication", MedicationView.NAME, this);
        this.addComponent(this.btnMedication, "navMenuMedication");

        this.btnSchedules = new NavigationButton("Appointments", EventListView.NAME, this);
        this.addComponent(this.btnSchedules, "navMenuSchedules");
    }


    public CustomLayout getNavMenuLayout () {
        return this;
    }

    protected void resetAllButtonsStyles() {
        for (Component button : this)
        {
            button.removeStyleName("active");
        }
    }

    public NavigationButton getBtnJournal() {
        return btnJournal;
    }

    public NavigationButton getBtnMedication() {
        return btnMedication;
    }

    public NavigationButton getBtnSchedules() {
        return btnSchedules;
    }
}
