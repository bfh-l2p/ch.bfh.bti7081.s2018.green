package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.MasterNavigator;
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
		
		// sets the name of the button
		String patientName = data.getCurrentPatient().getName();
		view.setBtnPatient("Show medication of patient " + patientName);
		view.setDiagnosis("Has this patient suicidal tendencies?:");
		
		// after all information has been passed to the view: Display view
		view.setDisplay();

	}

	@Override
	public void buttonClick(String input) {

		if(input.equals("")) {
			MasterNavigator.navigator.navigateTo(PageName.MEDICATION.getName());
		}

	}
}
