package ch.bfh.bti7081.s2018.green.views;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2018.green.presenters.EventListPresenter;

public class EventListView extends CustomLayout implements View {

    public static final String NAME = "events";

    private Button btnNew = new Button("New appointment");
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy").withLocale(Locale.GERMAN);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.GERMAN);
        String timeRange = event.getStart().format(timeFormatter) + " - " + event.getStop().format(timeFormatter);
        addLabel(eventLayout, "title", event.getTitle());
        addLabel(eventLayout, "therapist", event.getTherapist().getFullName());
        addLabel(eventLayout, "description", event.getDescription());
        addLabel(eventLayout, "date", event.getStart().format(dateFormatter));
        addLabel(eventLayout, "time", timeRange);

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
