package ch.bfh.bti7081.s2018.green.presenters;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import com.vaadin.server.Page;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.managers.EventManager;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
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
		this.view.getCbRecurringEvent().addValueChangeListener(event -> SetVisibility());
	}

	// Update DateTimeFields without impacting Time when changing Dates
	private void SetToDate(LocalDateTime from) {
		this.view.getDtfTo().setValue(from.withHour(this.view.getDtfTo().getValue().getHour()));
	}

	private void SetFromDate(LocalDateTime to) {
		this.view.getDtfFrom().setValue(to.withHour(this.view.getDtfFrom().getValue().getHour())
				.withMinute(this.view.getDtfFrom().getValue().getMinute()));
	}

	private void SetVisibility() {
		if (this.view.getRbgSetRecurringInterval().isEnabled() == false) {
			this.view.getRbgSetRecurringInterval().setEnabled(true);
		}
	}

	private void SaveSchedule() {
 
    	// Get data that was defined with ScheduleAddView
    	LocalDateTime dtfFrom = view.getDtfFrom().getValue();
    	LocalDateTime dtfTo = view.getDtfTo().getValue();
    	TextArea tfContent = view.getTfContent();
    	TextField tfTitle = view.getTfTitle();
    	
    	// Prepare persistence
    	EventManager emSchedule = new EventManager();
    	
    	/* Insertion
		Including the following checks:
			-- To-Date is after From-Date
			-- From-Date and To-Date is on the same Day and Month
			-- Schedule duration is not longer than +- 8 hours
    	*/
    	try {
    	if (dtfFrom.isBefore(dtfTo) && tfContent.isEmpty() == false && dtfTo.getHour()-dtfFrom.getHour()<=8) {
    		emSchedule.add(new Event(dtfFrom,dtfTo,tfContent.getValue(),"",data.getCurrentPatient(),data.getCurrentStaff()));
    		NavigatorUI.navigator.navigateTo(EventListView.NAME);
    		}
    	} catch(PersistenceException e) {
    		ErrorView.showError("Event couldn't be saved. Please try again!", Page.getCurrent());    		
    	}
    }
}