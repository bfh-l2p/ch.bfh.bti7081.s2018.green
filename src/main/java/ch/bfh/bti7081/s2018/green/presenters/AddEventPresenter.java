package ch.bfh.bti7081.s2018.green.presenters;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import javax.persistence.PersistenceException;

import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.managers.EventManager;
import ch.bfh.bti7081.s2018.green.views.AddEventView;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
import ch.bfh.bti7081.s2018.green.views.EventListView;

public class AddEventPresenter {

	private AddEventView view;
	private DataContainer data;
	private Binder<Event> binder = new Binder<>();

	public AddEventPresenter(AddEventView view) {
		this.view = view;
		this.data = DataContainer.getInstance();

		this.view.getDtfFrom().addValueChangeListener(valueChangeEvent -> SetToDate(
				view.getDtfFrom().getValue(),
				view.getDtfTo().getValue()));
		this.view.getDtfTo().addValueChangeListener(valueChangeEvent -> SetFromDate(
				view.getDtfFrom().getValue(),
				view.getDtfTo().getValue()));
		this.view.getBtnSave().addClickListener(clickEvent -> Save());
		this.view.getBtnIncInt().addClickListener(clickEvent -> IncreaseIntervals());
		this.view.getBtnDecInt().addClickListener(clickEvent -> DecreaseIntervals());
		this.view.getCbRecurringEvent().addValueChangeListener(valueChangeEvent -> SetVisibility(
				this.view.getCbRecurringEvent().getValue()));
	}

	// Update DateTimeFields without impacting Time when changing Dates
	private void SetToDate(LocalDateTime from, LocalDateTime to) {
		view.getDtfTo().setValue(to.withDayOfYear(from.getDayOfYear()));
	}

	private void SetFromDate(LocalDateTime from, LocalDateTime to) {
		view.getDtfFrom().setValue(from.withDayOfYear(to.getDayOfYear()));
	}
	
	// Change count of interval for recurring events
	private void IncreaseIntervals() {
		view.incIntervals();
		view.setTfIntervals(view.getIntervals());
	}
	
	private void DecreaseIntervals() {
		if(view.getIntervals()>0) {
			view.decIntervals();
		}
		view.setTfIntervals(view.getIntervals());
	}
	
	// Make items for recurring events visible or hide them, depended on the checkboxvalue
	private void SetVisibility(boolean state) {
		view.getRbgSetRecurringInterval().setEnabled(state);
	    view.getLbIntervals().setEnabled(state);
	    view.getBtnIncInt().setEnabled(state);
	    view.getBtnDecInt().setEnabled(state);
	    view.getTfIntervals().setEnabled(state);
	    view.getLbSetRecurringInterval().setEnabled(state);
	}

	// Save-type decider who's checking if a normal or a recurring event should be saved
	// if it is a recurring event, there's a if-statement for any kind of recurrence type
	private void Save() {
		// Interval miss-match check
		if(view.getCbRecurringEvent().getValue() && view.getIntervals()==0) {
			ErrorView.showError("Please set an interval, if you want to add a recurring event", Page.getCurrent());
		}
		
		// Trigger save event for a normal event
		if(!view.getCbRecurringEvent().getValue()) {
			SaveSchedule(	
				view.getDtfFrom().getValue(),
				view.getDtfTo().getValue(),
				view.getTfContent(),
				view.getTfTitle()
			);
		} else {
		    if (view.getIntervals()>0) {
		        switch (view.getRbgSetRecurringInterval().getValue()){
                    case "Daily":
                        saveInInterval(ChronoUnit.DAYS);
                        break;
                    case "Weekly":
                        saveInInterval(ChronoUnit.WEEKS);
                        break;
                    case "Monthly":
                        saveInInterval(ChronoUnit.MONTHS);
                        break;
                }
            }
        }
	}

    // Trigger save event for a recurring event
    // the configured timespan always stays the same
    private void saveInInterval(TemporalUnit temporalUnit) {
        int toAdd = view.getIntervals();
        int counter = 0;
        while(toAdd>0) {
            SaveSchedule(
                view.getDtfFrom().getValue().plus(counter, temporalUnit),
                view.getDtfTo().getValue().plus(counter, temporalUnit),
                view.getTfContent(),
                view.getTfTitle()
            );
            toAdd--;
            counter++;
        }
    }

    // General save-function who's saving the given data in persistance layer
	private void SaveSchedule(	
			LocalDateTime dtfFrom,
			LocalDateTime dtfTo,
			TextArea tfContent,
			TextField tfTitle) {

		this.bindEvent();

		try {
			BinderValidationStatus<Event> eventState = binder.validate();
			//check if there are any form validation errors
			if (eventState.hasErrors()) {
				Notification.show("Some fields contain invalid information (marked in red)");
			} else {
				// save data here
				EventManager emSchedule = new EventManager();
				Event eventToSave = new Event(dtfFrom, dtfTo, tfContent.getValue(), tfTitle.getValue(), data.getCurrentPatient(), data.getCurrentStaff());
				emSchedule.add(eventToSave);

				data.getCurrentNavigator().navigateTo(EventListView.NAME);
				data.setCurrentViewName(EventListView.NAME);

				view.close();
			}

		} catch (PersistenceException e) {
			ErrorView.showError("Event couldn't be saved. Please try again!", Page.getCurrent());
		}
	}

	// performs field validation for new records
	private void bindEvent() {
		validateFields();
	}

	private void validateFields() {
		binder.forField(view.getDtfFrom())
				.asRequired()
				.bind(Event::getStart, Event::setStart);

		binder.forField(view.getDtfTo())
				.asRequired()
				.withValidator((Validator<LocalDateTime>) (localDateTime, valueContext) -> {
					if (view.getDtfFrom().getValue().isBefore(view.getDtfTo().getValue())) {
						return ValidationResult.ok();
					}
					return ValidationResult.error("End date must start after start date");
				})
				.bind(Event::getStop, Event::setStop);

		binder.forField(view.getTfTitle())
				.asRequired()
				.withValidator(new StringLengthValidator("Event title must have 1-120 characters", 1, 120))
				.bind(Event::getTitle, Event::setTitle);
	}
}