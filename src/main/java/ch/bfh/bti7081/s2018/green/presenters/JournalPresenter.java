package ch.bfh.bti7081.s2018.green.presenters;

import javax.persistence.PersistenceException;

import com.vaadin.server.Page;
import com.vaadin.ui.TextArea;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.models.managers.JournalEntryManager;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
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

		if (content != null && !content.isEmpty()) {
			JournalEntry journalEntry = new JournalEntry(content, data.getCurrentPatient(), data.getCurrentStaff());
			JournalEntryManager manager = new JournalEntryManager();

			try {

				manager.add(journalEntry);
				txtEntry.clear();
				view.addJournalEntry(journalEntry);

			} catch (PersistenceException e) {
				ErrorView.showError("Journal entry couldn't be saved. Please try again!",
						Page.getCurrent());
			}

		}
	}

	private void enteredView() {
		JournalEntryManager manager = new JournalEntryManager();

		try {
			view.setJournalEntries(manager.findBy(data.getCurrentPatient()));
		} catch (PersistenceException e) {
			ErrorView.showError("Couldn't get journal entries from database", Page.getCurrent());
		}
	}
}