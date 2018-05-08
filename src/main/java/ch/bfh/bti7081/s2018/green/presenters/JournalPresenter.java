package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.views.JournalView;

public class JournalPresenter {

	private JournalView view;
	private DataContainer data;

	public JournalPresenter(JournalView view, DataContainer data) {
		this.view = view;
		this.data = data;

		this.view.setJournalEntries(data.getCurrentPatient().getJournalEntries());
	}
}
