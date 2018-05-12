package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.JournalEntryLayout;
import ch.bfh.bti7081.s2018.green.layouts.JournalLayout;
import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.presenters.JournalPresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import java.util.List;

public class JournalView extends JournalLayout implements View {

    public static final String NAME = "journal";
    private Button btnSave = new Button("Save");
    private TextField txtEntry = new TextField("NewEntry");
    
    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the Journal View");
        new JournalPresenter(this);
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.removeAllComponents();
        this.addComponent(txtEntry);
        this.addComponent(btnSave); // only for demonstration purposes

        for (JournalEntry journalEntry : journalEntries) {
            JournalEntryLayout jel = new JournalEntryLayout();
            TextField tf = jel.getTextFieldJournalEntry();
            tf.setValue(journalEntry.getContent());
            this.addComponent(jel);
        }
    }

    public void addJournalEntry(JournalEntry journalEntry) {
    	JournalEntryLayout jel = new JournalEntryLayout();
    	TextField tf = jel.getTextFieldJournalEntry();
        tf.setValue(journalEntry.getContent());
    	this.addComponent(jel);
    }
    
    public TextField getTxtEntry() {
    	return txtEntry;
    }
    
    public Button getBtnSave() {
        return btnSave;
    }

}
