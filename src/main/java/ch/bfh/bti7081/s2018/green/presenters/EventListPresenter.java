package ch.bfh.bti7081.s2018.green.presenters;

import java.util.List;

import javax.persistence.PersistenceException;

import com.vaadin.server.Page;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.managers.EventManager;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
import ch.bfh.bti7081.s2018.green.views.EventListView;
import ch.bfh.bti7081.s2018.green.views.ScheduleAddView;

public class EventListPresenter {

	private EventListView view;
	private DataContainer data;

	public EventListPresenter(EventListView view) {
		this.view = view;
		this.data = DataContainer.getInstance();

		enteredView();

		this.view.getBtnNew().addClickListener(
				clickEvent -> {ScheduleAddView scheduleAddView = new ScheduleAddView(); this.view.getUI().addWindow(scheduleAddView);}
		);
	}

	private void enteredView() {
		EventManager manager = new EventManager();
		
		try {
			
			List<Event> e = manager.findBy(data.getCurrentPatient());
			view.setEvents(e);
			
		} catch (PersistenceException e) {			
			ErrorView.showError("Couldn't read events from database.\nPlease try again!", Page.getCurrent());
		}
	}
}
