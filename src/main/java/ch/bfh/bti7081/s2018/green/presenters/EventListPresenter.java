package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.managers.EventManager;
import ch.bfh.bti7081.s2018.green.views.EventListView;
import ch.bfh.bti7081.s2018.green.views.ScheduleAddView;

public class EventListPresenter {

    private EventListView view;
    private DataContainer data;

    public EventListPresenter(EventListView view) {
        this.view = view;
        this.data = DataContainer.getInstance();

        enteredView();

        this.view.getBtnNew().addClickListener(clickEvent -> NavigatorUI.navigator.navigateTo(ScheduleAddView.NAME));
    }

    private void enteredView() {
    	EventManager manager = new EventManager();
        view.setEvents(manager.findBy(data.getCurrentPatient()));
    }
}
