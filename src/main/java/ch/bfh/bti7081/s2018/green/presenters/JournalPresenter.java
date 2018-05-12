package ch.bfh.bti7081.s2018.green.presenters;

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
    	TextField txtEntry = view.getTxtEntry();
    	JournalEntry journalEntry = new JournalEntry(txtEntry.getValue(), data.getCurrentPatient(), data.getCurrentDoctor());
        data.getCurrentPatient().addJournalEntry(journalEntry);
        txtEntry.clear();
        this.view.addJournalEntry(journalEntry);
    }
    private void enteredView() {

        // will be called when corresponding view is about to open
        // use this method to populate the view-elements with data

        // TODO: Implement method
        //view.setJournalEntries(data.getCurrentPatient().getJournalEntries());
    }
}
