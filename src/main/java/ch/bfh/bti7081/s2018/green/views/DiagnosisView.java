package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.green.designs.DiagnosisDesign;
import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class DiagnosisView extends NavigationView implements View, PmsView {

	DiagnosisDesign diagnosisDesign;

	public DiagnosisView() {
	}

	@Override
	public void enter(ViewChangeEvent event) {

		// Mandatory method to set a new clean view-instance;
		setDisplay();

		Button nameBtn = diagnosisDesign.getBtnSetName();
		nameBtn.setId("BtnSetName");
		nameBtn.addClickListener(this);		
		
		Notification.show("Welcome to the Diagnosis View");

		// tells the presenter that the view was opened
		listener.enteredView();
	}

	@Override
	public void addListener(PmsViewListener listener) {

		// this.listener is inherited from superclass
		this.listener = listener;

	}

	public void setTextFieldContent() {

		String tAreaText = diagnosisDesign.getTextFieldResult().getValue();
		String tFieldtext = diagnosisDesign.getTextFieldName().getValue();
		diagnosisDesign.getTextFieldResult().setValue(tAreaText + "\n" + tFieldtext);
	}

	public void setTextFieldContent(String txt) {

		diagnosisDesign.getTextFieldResult().setValue(txt);
	}

	@Override
	public void setDisplay() {
		
		if(diagnosisDesign != null) {
		this.removeComponent(diagnosisDesign);
		}		
		diagnosisDesign = new DiagnosisDesign();
		this.addComponent(diagnosisDesign);		
	}
}
