package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.JournalEntryLayout;
import ch.bfh.bti7081.s2018.green.layouts.JournalLayout;
import ch.bfh.bti7081.s2018.green.presenters.JournalPresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import java.util.List;

public class JournalView extends JournalLayout implements View {

    public static final String NAME = "journal";
    private Button btnChange = new Button("Change");

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the Journal View");
        new JournalPresenter(this);
        this.addComponent(btnChange); // only for demonstration purposes
    }

    public void setJournalEntries(List<String> journalEntries) {
        this.removeAllComponents();

        for (String journalEntry : journalEntries) {
            JournalEntryLayout jel = new JournalEntryLayout();
            TextField tf = jel.getTextFieldJournalEntry();
            tf.setValue(journalEntry);
            this.addComponent(jel);
        }

    }

    public Button getBtnChange() {
        return btnChange;
    }

}
