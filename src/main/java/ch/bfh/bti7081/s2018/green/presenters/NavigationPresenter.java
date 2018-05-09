package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.views.JournalView;
import ch.bfh.bti7081.s2018.green.views.MedicationView;
import ch.bfh.bti7081.s2018.green.views.NavigationView;
import com.vaadin.ui.Notification;

public class NavigationPresenter {
    private NavigationView view;

    public NavigationPresenter(NavigationView view) {
        this.view = view;

        registerNavigationEventListeners();
    }

    private void registerNavigationEventListeners() {
        this.view.getBtnMedication().addClickListener((clickEvent) ->
                NavigatorUI.navigator.navigateTo(MedicationView.NAME)
        );

        this.view.getBtnJournal().addClickListener((clickEvent) ->
                NavigatorUI.navigator.navigateTo(JournalView.NAME)
        );

        this.view.getBtnTherapy().addClickListener((clickEvent) ->
                Notification.show(
                        "Therapy view not yet implemented",
                        Notification.Type.WARNING_MESSAGE)
        );

        this.view.getBtnDiagnosis().addClickListener((clickEvent) ->
                Notification.show(
                        "Diagnosis view not yet implemented",
                        Notification.Type.WARNING_MESSAGE)
        );
    }


}
