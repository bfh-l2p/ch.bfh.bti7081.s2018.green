package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.PageName;
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
		
		// will be called when corresponding view is about to open
		// use this method to populate the view-elements with data
		
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
