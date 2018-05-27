package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;


import ch.bfh.bti7081.s2018.green.presenters.JournalPresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import ch.bfh.bti7081.s2018.green.models.entities.Event;
import java.util.List;

public class EventView extends CustomLayout implements View {

    public static final String NAME = "event";

    private Button btnNew = new Button("Neuer Termin");
    private VerticalLayout eventList;

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the Event View");
        this.setTemplateName("events");
        this.addComponent(btnNew, "newbutton");

        //new EventPresenter(this);
    }

    public void setEvents(List<ch.bfh.bti7081.s2018.green.models.entities.Event> events) {
        eventList = new VerticalLayout();
        for (ch.bfh.bti7081.s2018.green.models.entities.Event event : events) {
            addEvent(event);
        }
        this.addComponent(eventList, "events");
    }

    public void addEvent(ch.bfh.bti7081.s2018.green.models.entities.Event event) {
        CustomLayout journalEntryLayout = new CustomLayout("event");

        // set content
        Label lblTitle = new Label();
        lblTitle.setValue(event.getTitle());
        lblTitle.setWidth("300px");
        journalEntryLayout.addComponent(lblTitle, "title");


        eventList.addComponentAsFirst(journalEntryLayout);
    }


    public Button getBtnNew() {
        return btnNew;
    }
}
