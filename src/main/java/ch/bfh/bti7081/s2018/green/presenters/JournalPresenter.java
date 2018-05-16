package ch.bfh.bti7081.s2018.green.presenters;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.views.JournalView;

public class JournalPresenter {

    private JournalView view;
    private DataContainer data;

    public JournalPresenter(JournalView view) {
        this.view = view;
        this.data = DataContainer.getInstance();

        enteredView();

        this.view.getBtnSave().addClickListener(clickEvent -> addEntry());
    }

    private void addEntry() {
        TextArea txtEntry = view.getTxtEntry();
        String content = txtEntry.getValue().trim();
        txtEntry.clear();
        if (content != null && !content.isEmpty()) {
            JournalEntry journalEntry = new JournalEntry(content, data.getCurrentStaff());
            data.getCurrentPatient().addJournalEntry(journalEntry);
            view.addJournalEntry(journalEntry);
        }
    }
    private void enteredView() {
        // will be called when corresponding view is about to open
        // use this method to populate the view-elements with data
        view.setJournalEntries(data.getCurrentPatient().getJournalEntries());
    }
}
