package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.presenters.JournalPresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class JournalView extends CustomLayout implements View {

    public static final String NAME = "journal";

    private Button btnSave = new Button("Save");
    private TextArea txtEntry = new TextArea();
    private VerticalLayout journalEntryList;

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the Journal View");
        this.setTemplateName("journal");
        this.addComponent(txtEntry, "textentry");
        this.addComponent(btnSave, "savebutton");

        new JournalPresenter(this);
    }

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
}
