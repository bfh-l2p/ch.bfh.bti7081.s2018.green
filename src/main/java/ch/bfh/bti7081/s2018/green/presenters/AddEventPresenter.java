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
import ch.bfh.bti7081.s2018.green.views.AddEventView;

public class AddEventPresenter {

	private AddEventView view;
	private DataContainer data;

	public AddEventPresenter(AddEventView view) {
		this.view = view;
		this.data = DataContainer.getInstance();

		this.view.getDtfFrom().addValueChangeListener(valueChangeEvent -> SetToDate(view.getDtfFrom().getValue()));
		this.view.getDtfTo().addValueChangeListener(valueChangeEvent -> SetFromDate(view.getDtfTo().getValue()));
		this.view.getBtnSave().addClickListener(clickEvent -> Save());
		this.view.getBtnIncInt().addClickListener(clickEvent -> IncreaseIntervals());
		this.view.getBtnDecInt().addClickListener(clickEvent -> DecreaseIntervals());
		this.view.getCbRecurringEvent().addValueChangeListener(valueChangeEvent -> SetVisibility(this.view.getCbRecurringEvent().getValue()));
	}

	// Update DateTimeFields without impacting Time when changing Dates
	private void SetToDate(LocalDateTime from) {
		this.view.getDtfTo().setValue(from.withHour(this.view.getDtfTo().getValue().getHour()));
	}

	private void SetFromDate(LocalDateTime to) {
		this.view.getDtfFrom().setValue(to.withHour(this.view.getDtfFrom().getValue().getHour())
				.withMinute(this.view.getDtfFrom().getValue().getMinute()));
	}
	
	private void IncreaseIntervals() {
		this.view.incIntervals();
		this.view.setTfIntervals(this.view.getIntervals());
	}
	
	private void DecreaseIntervals() {
		if(this.view.getIntervals()>0) {
			this.view.decIntervals();
		}
		this.view.setTfIntervals(this.view.getIntervals());
	}
	
	private void SetVisibility(boolean state) {
		this.view.getRbgSetRecurringInterval().setEnabled(state);
	    this.view.getLbIntervals().setEnabled(state);
	    this.view.getBtnIncInt().setEnabled(state);
	    this.view.getBtnDecInt().setEnabled(state);
	    this.view.getTfIntervals().setEnabled(state);
	}

	private void Save() {
		if(this.view.getCbRecurringEvent().getValue()==true && this.view.getIntervals()==0) {
			ErrorView.showError("Please set an interval, if you want to add a recurring event", Page.getCurrent());
		}
		if(this.view.getCbRecurringEvent().getValue()==false) {
			SaveSchedule(	
				view.getDtfFrom().getValue(),
				view.getDtfTo().getValue(),
				view.getTfContent(),
				view.getTfTitle()
			);
		}
		if(this.view.getRbgSetRecurringInterval().isSelected("Daily") && this.view.getIntervals()>0) {
			int toAdd = this.view.getIntervals();
			int days = 0;
			while(toAdd>0) {
				SaveSchedule(	
					view.getDtfFrom().getValue().plusDays(days),
					view.getDtfTo().getValue().plusDays(days),
					view.getTfContent(),
					view.getTfTitle()
				);
				toAdd--;
				days++;
			}
		}
		if(this.view.getRbgSetRecurringInterval().isSelected("Weekly") && this.view.getIntervals()>0) {
			int toAdd = this.view.getIntervals();
			int weeks = 0;
			while(toAdd>0) {
				SaveSchedule(	
					view.getDtfFrom().getValue().plusWeeks(weeks),
					view.getDtfTo().getValue().plusWeeks(weeks),
					view.getTfContent(),
					view.getTfTitle()
				);
				toAdd--;
				weeks++;
			}
		}
		if(this.view.getRbgSetRecurringInterval().isSelected("Monthly") && this.view.getIntervals()>0) {
			int toAdd = this.view.getIntervals();
			int months = 0;
			while(toAdd>0) {
				SaveSchedule(	
					view.getDtfFrom().getValue().plusMonths(months),
					view.getDtfTo().getValue().plusMonths(months),
					view.getTfContent(),
					view.getTfTitle()
				);
				toAdd--;
				months++;
			}
		}
	}
	private void SaveSchedule(	LocalDateTime dtfFrom,
								LocalDateTime dtfTo,
								TextArea tfContent,
								TextField tfTitle) {
    	
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
    		emSchedule.add(new Event(dtfFrom,dtfTo,tfContent.getValue(),tfTitle.getValue(),data.getCurrentPatient(),data.getCurrentStaff()));
    		NavigatorUI.navigator.navigateTo(EventListView.NAME);
    		}
    	} catch(PersistenceException e) {
    		ErrorView.showError("Event couldn't be saved. Please try again!", Page.getCurrent());    		
    	}
    	view.close();
    }
}