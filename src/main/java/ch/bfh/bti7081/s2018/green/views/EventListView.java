package ch.bfh.bti7081.s2018.green.views;

import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2018.green.presenters.EventListPresenter;

public class EventListView extends CustomLayout implements View {

    public static final String NAME = "events";

    private Button btnNew = new Button("Neuer Termin");
    private VerticalLayout eventList;

    @Override
    public void enter(ViewChangeEvent event) {
        //Notification.show("Welcome to the Event View");
        this.setTemplateName("eventList");
        this.addComponent(btnNew, "newbutton");

        new EventListPresenter(this);
    }

    public void setEvents(List<ch.bfh.bti7081.s2018.green.models.entities.Event> events) {
        eventList = new VerticalLayout();
        for (ch.bfh.bti7081.s2018.green.models.entities.Event event : events) {
            addEvent(event);
        }
        this.addComponent(eventList, "events");
    }

    public void addEvent(ch.bfh.bti7081.s2018.green.models.entities.Event event) {
        CustomLayout eventLayout = new CustomLayout("event");

        addLabel(eventLayout, "title", event.getTitle());
        addLabel(eventLayout, "therapist", event.getTherapist().getFullName());
        addLabel(eventLayout, "description", event.getDescription());
        addLabel(eventLayout, "start", event.getStart().toString());
        addLabel(eventLayout, "stop", event.getStop().toString());

        eventList.addComponentAsFirst(eventLayout);
    }
    
    private void addLabel(CustomLayout layout, String slot, String value) {
        Label label = new Label();
        label.setValue(value);
        label.setWidth("300px");
        layout.addComponent(label, slot);
    }

    public Button getBtnNew() {
        return btnNew;
    }
}
