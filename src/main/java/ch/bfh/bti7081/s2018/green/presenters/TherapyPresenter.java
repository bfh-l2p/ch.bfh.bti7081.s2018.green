package ch.bfh.bti7081.s2018.green.presenters;
import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.PageName;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.views.TherapyView;

public class TherapyPresenter implements PmsViewListener {

	TherapyView view;
	DataContainer data;

	public TherapyPresenter(TherapyView view, DataContainer data) {
		this.view = view;
		this.data = data;
		this.view.addListener(this);
	}

	@Override
	public void enteredView() {
		
		// set your data to the view components here
		
		// sets the name of the button
		String patientName = data.getCurrentPatient().getName();
		
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
