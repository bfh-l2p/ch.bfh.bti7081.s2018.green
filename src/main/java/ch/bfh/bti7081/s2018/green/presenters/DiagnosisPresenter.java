package ch.bfh.bti7081.s2018.green.presenters;
import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.PageName;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.views.DiagnosisView;

public class DiagnosisPresenter implements PmsViewListener {

	DiagnosisView view;
	DataContainer data;

	public DiagnosisPresenter(DiagnosisView view, DataContainer data) {
		this.view = view;
		this.data = data;
		this.view.addListener(this);
	}

	@Override
	public void enteredView() {
		
		// will be called when corresponding view is about to open
		// use this method to populate the view-elements with data
		
		// Example
		// get patient name and do something with it...
		String patientName = data.getCurrentPatient().getName();
		view.setTextFieldContent(patientName);
		
		
	}

	@Override
	public void buttonClick(String input) {
					
		// Important: DomId was manually specified as "BtnSetName" in VaadinDesigner to make this work:
		if(input.equals("BtnSetName")) {
			view.setTextFieldContent();
		} 				
	}

	@Override
	public void selectionChange(String input) {
		// TODO Auto-generated method stub
		
	}


	
	
}
