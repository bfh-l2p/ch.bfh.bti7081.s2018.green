package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;

import ch.bfh.bti7081.s2018.green.presenters.SchedulePresenter;

import java.time.LocalDateTime;

public class ScheduleAddView extends CustomLayout implements View {

    public static final String NAME = "scheduleAdd";

    private DateTimeField dtfFrom = new DateTimeField();
    private DateTimeField dtfTo = new DateTimeField();
    private TextArea tfContent = new TextArea();
    private Button btnSave = new Button("Save Schedule");

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the Schedule View");
        
        // Set initial values to fields
        dtfFrom.setValue(LocalDateTime.now());
        dtfTo.setValue(LocalDateTime.now());
        
        // Place Java-Components in HTML DIVs 
        this.setTemplateName("scheduleAdd");
        this.addComponent(dtfFrom, "fromField");
        this.addComponent(dtfTo, "toField");
        this.addComponent(tfContent, "contentField");
        this.addComponent(btnSave, "saveButton");

        new SchedulePresenter(this);
    }
    
    public DateTimeField getDtfFrom() {
        return dtfFrom;
    }

    public DateTimeField getDtfTo() {
        return dtfTo;
    }
    
    public Button getBtnSave() {
        return btnSave;
    }
    
    public TextArea getTfContent() {
        return tfContent;
    }
/*
    public void setJournalEntries(List<JournalEntry> journalEntries) {
        journalEntryList = new VerticalLayout();
        for (JournalEntry journalEntry : journalEntries) {
            addJournalEntry(journalEntry);
        }
        this.addComponent(journalEntryList, "journal");
    }

    public void addJournalEntry(JournalEntry journalEntry) {
        CustomLayout journalEntryLayout = new CustomLayout("journalentry");

        // set content
        Label content = new Label();
        content.setValue(journalEntry.getContent());
        content.setWidth("300px");
        //content.setContentMode(ContentMode.PREFORMATTED);
        journalEntryLayout.addComponent(content, "content");

        // set author
        Label author = new Label();
        author.setValue(journalEntry.getStaff().getFullName());
        journalEntryLayout.addComponent(author, "author");
        journalEntryList.addComponentAsFirst(journalEntryLayout);


        Label created = new Label();
        created.setValue(journalEntry.getCreated().toString());
        journalEntryLayout.addComponent(created, "created");
        journalEntryList.addComponentAsFirst(journalEntryLayout);
    }

    public TextArea getTxtEntry() {
        return txtEntry;
    }

    public Button getBtnSave() {
        return btnSave;
    }
*/
}
