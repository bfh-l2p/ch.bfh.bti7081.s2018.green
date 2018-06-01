package ch.bfh.bti7081.s2018.green.presenters;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.models.managers.EventManager;
import ch.bfh.bti7081.s2018.green.views.EventListView;
import ch.bfh.bti7081.s2018.green.views.JournalView;
import ch.bfh.bti7081.s2018.green.views.ScheduleAddView;

public class SchedulePresenter {

    private ScheduleAddView addview;
    private DataContainer data;

    public SchedulePresenter(ScheduleAddView view) {
        this.addview = view;
        this.data = DataContainer.getInstance();
      
        this.addview.getBtnSave().addClickListener(clickEvent -> SaveSchedule());
    }
    
    private void SaveSchedule() {
    	
    	// Get data that was defined with ScheduleAddView
    	LocalDateTime dtfFrom = addview.getDtfFrom().getValue();
    	LocalDateTime dtfTo = addview.getDtfTo().getValue();
    	TextArea tfContent = addview.getTfContent();
    	
    	// Prepare persistance
    	EventManager emSchedule = new EventManager();
    	
    	// Insertion
    	if (dtfFrom.isBefore(dtfTo) && tfContent.isEmpty() == false ) {
    		emSchedule.add(new Event(dtfFrom,dtfTo,tfContent.getValue(),"",data.getCurrentPatient(),data.getCurrentStaff()));
    		NavigatorUI.navigator.navigateTo(EventListView.NAME);
    	}
    }
}