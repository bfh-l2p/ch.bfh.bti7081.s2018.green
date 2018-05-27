package ch.bfh.bti7081.s2018.green.presenters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.views.EventView;
import ch.bfh.bti7081.s2018.green.views.JournalView;

public class EventPresenter {

    private EventView view;
    private DataContainer data;

    public EventPresenter(EventView view) {
        this.view = view;
        this.data = DataContainer.getInstance();

        enteredView();

        this.view.getBtnNew().addClickListener(clickEvent -> newEvent());
    }

    private void newEvent() {
  
    }
    private void enteredView() {
        // will be called when corresponding view is about to open
        // use this method to populate the view-elements with data
        view.setEvents(data.getCurrentPatient().getEvents());
    }
}
