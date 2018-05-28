package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.views.*;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;


public class NavMenuPresenter {

    private NavMenuView view;

    public NavMenuPresenter (NavMenuView view) {
        this.view = view;

        registerNavBarEventHandlers();
    }

    private void registerNavBarEventHandlers() {

        HorizontalLayout lyb = (HorizontalLayout) this.view.getLblJournal().getParent();
        lyb.addLayoutClickListener((clickEvent) ->
                setActive(lyb, JournalView.NAME)
        );

        HorizontalLayout lyb4 = (HorizontalLayout) this.view.getLblMedication().getParent();
        lyb4.addLayoutClickListener((clickEvent) ->
                setActive(lyb4, MedicationView.NAME)
        );


        HorizontalLayout lyb2 = (HorizontalLayout) this.view.getLblDiagnosis().getParent();
        lyb2.addLayoutClickListener((clickEvent) ->
                setActive(lyb2, DiagnosisAppView.NAME)
        );

        HorizontalLayout lyb3 = (HorizontalLayout) this.view.getLblPatFile().getParent();
        lyb3.addLayoutClickListener((clickEvent) ->
                Notification.show(
                        "Patient File view not yet implemented",
                        Notification.Type.WARNING_MESSAGE)
        );
        
        HorizontalLayout lyb5 = (HorizontalLayout) this.view.getLblSchedules().getParent();
        lyb5.addLayoutClickListener((clickEvent) ->
                setActive(lyb5, ScheduleAddView.NAME)
        );
    }

    private void setActive (HorizontalLayout ly, String enumView) {
        // remove all CSS styles who mark a div as visited in the whole navMenu view
        for (Component co : this.view)
        {
            co.removeStyleName("NavVisited");
        }
        // dynamically add the CSS style to mark a div as visited
        ly.addStyleName("NavVisited");
        NavigatorUI.navigator.navigateTo(enumView);
    }
}
