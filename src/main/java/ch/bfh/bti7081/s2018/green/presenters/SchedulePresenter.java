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
      
        this.view.getDtfFrom().addValueChangeListener(valueChangeEvent -> SetToDate(view.getDtfFrom().getValue()));
        this.view.getDtfTo().addValueChangeListener(valueChangeEvent -> SetFromDate(view.getDtfTo().getValue()));
        this.view.getBtnSave().addClickListener(clickEvent -> SaveSchedule());
    }
    
    // Get data that was defined with ScheduleAddView
	
	private void SetToDate(LocalDateTime to) {
		this.view.getDtfTo().setValue(to);
	}
	
	private void SetFromDate(LocalDateTime from) {
		this.view.getDtfFrom().setValue(from);
	}
    
    private void SaveSchedule() {
 
    	LocalDateTime dtfFrom = view.getDtfFrom().getValue();
    	LocalDateTime dtfTo = view.getDtfTo().getValue();
    	TextArea tfContent = view.getTfContent();
    	TextArea tfTitle = view.getTfTitle();
    	
    	// Prepare persistance
    	EventManager emSchedule = new EventManager();
    	
    	/* Insertion
   	 	Including the following checks:
   	 		-- To-Date is after From-Date
   			-- From-Date and To-Date is on the same Day and Month
   			-- Schedule duration is not longer than +- 8 hours
    	*/
    	if(dtfTo.getHour()-dtfFrom.getHour()<=8) {
    		if (tfTitle.isEmpty() == false ) {
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
}