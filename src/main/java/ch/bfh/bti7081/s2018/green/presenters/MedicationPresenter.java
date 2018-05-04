package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.views.MedicationView;

public class MedicationPresenter implements PmsViewListener {

	MedicationView view;
	DataContainer data;
	
	public MedicationPresenter(MedicationView view, DataContainer data) {		
		this.view = view;
		this.data = data;
	    this.view.addListener(this);
	}

	@Override
	public void enteredView() {	

		// after all information has been passed to the view: Display view
		view.setDisplay();
		
	}
		
	@Override
	public void buttonClick(String input) {
		// TODO Auto-generated method stub		
	}
	
}
