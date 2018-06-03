package ch.bfh.bti7081.s2018.green.presenters;

import java.time.LocalDateTime;

import com.vaadin.ui.TextArea;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.managers.EventManager;
import ch.bfh.bti7081.s2018.green.views.EventListView;
import ch.bfh.bti7081.s2018.green.views.ScheduleAddView;

public class SchedulePresenter {

    private ScheduleAddView view;
    private DataContainer data;

    public SchedulePresenter(ScheduleAddView view) {
        this.view = view;
        this.data = DataContainer.getInstance();
      
        this.view.getBtnSave().addClickListener(clickEvent -> SaveSchedule());
    }
    
    private void SaveSchedule() {
    	
    	// Get data that was defined with ScheduleAddView
<<<<<<< HEAD
    	LocalDateTime dtfFrom = addview.getDtfFrom().getValue();
    	LocalDateTime dtfTo = addview.getDtfTo().getValue();
    	TextArea tfContent = addview.getTfContent();
    	TextArea tfTitle = addview.getTfTitle();
=======
    	LocalDateTime dtfFrom = view.getDtfFrom().getValue();
    	LocalDateTime dtfTo = view.getDtfTo().getValue();
    	TextArea tfContent = view.getTfContent();
>>>>>>> master
    	
    	// Prepare persistance
    	EventManager emSchedule = new EventManager();
    	
    	// Insertion
    	if (dtfFrom.isBefore(dtfTo) && tfContent.isEmpty() == false && tfTitle.isEmpty() == false ) {
    		emSchedule.add(new Event(
    								dtfFrom,
    								dtfTo,
    								tfContent.getValue(),
    								tfTitle.getValue(),
    								data.getCurrentPatient(),
    								data.getCurrentStaff()
    								)
    						);
    		NavigatorUI.navigator.navigateTo(EventListView.NAME);
    	}
    }
}