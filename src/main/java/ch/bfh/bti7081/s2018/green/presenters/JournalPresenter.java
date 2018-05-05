package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.PageName;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.views.JournalView;

public class JournalPresenter implements PmsViewListener {

	JournalView view;
	DataContainer data;

	public JournalPresenter(JournalView view, DataContainer data) {
		this.view = view;
		this.data = data;
		this.view.addListener(this);
	}

	@Override
	public void enteredView() {

		// set your data to the view components here
		
	}

	@Override
	public void buttonClick(String input) {
		// TODO Auto-generated method stub
	}

	@Override
	public void selectionChange(String input) {
		// TODO Auto-generated method stub
		
	}

}
