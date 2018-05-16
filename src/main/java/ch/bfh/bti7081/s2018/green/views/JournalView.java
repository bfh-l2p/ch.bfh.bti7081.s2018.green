package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.presenters.JournalPresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class JournalView extends FormLayout implements View {

    public static final String NAME = "journal";

    private Button btnSave = new Button("Save");
    private TextField txtEntry = new TextField();
    private VerticalLayout journalEntryList;

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the Journal View");
        new JournalPresenter(this);
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        CustomLayout journalLayout = new CustomLayout("journal");
        journalLayout.addComponent(txtEntry, "textentry");
        journalLayout.addComponent(btnSave, "savebutton");

        journalEntryList = new VerticalLayout();
        for (JournalEntry journalEntry : journalEntries) {
            CustomLayout journalEntryLayout = new CustomLayout("journalentry");
            TextField tf = new TextField();
            tf.setValue(journalEntry.getContent());
            journalEntryLayout.addComponent(tf, "content");
            journalEntryList.addComponentAsFirst(journalEntryLayout);
        }
        journalLayout.addComponent(journalEntryList, "journal");
        this.addComponent(journalLayout);
    }

    public void addJournalEntry(JournalEntry journalEntry) {
        CustomLayout journalEntryLayout = new CustomLayout("journalentry");
        TextField tf = new TextField();
        tf.setValue(journalEntry.getContent());
        journalEntryLayout.addComponent(tf, "content");
        journalEntryList.addComponentAsFirst(journalEntryLayout);
    }

    public TextField getTxtEntry() {
        return txtEntry;
    }

    public Button getBtnSave() {
        return btnSave;
    }
}
