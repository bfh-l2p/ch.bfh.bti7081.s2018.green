package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.layouts.DiagnosisLayout;
import ch.bfh.bti7081.s2018.green.navViews.NavigationView;

public class DiagnosisView extends NavigationView implements View, PmsView {

	DiagnosisLayout diagnosisLayout;

	public DiagnosisView() {
	}

	@Override
	public void enter(ViewChangeEvent event) {

		// Mandatory method to set a new clean view-instance;
		setDisplay();

		Button nameBtn = diagnosisLayout.getBtnSetName();
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

		String tAreaText = diagnosisLayout.getTextFieldResult().getValue();
		String tFieldtext = diagnosisLayout.getTextFieldName().getValue();
		diagnosisLayout.getTextFieldResult().setValue(tAreaText + "\n" + tFieldtext);
	}

	public void setTextFieldContent(String txt) {

		diagnosisLayout.getTextFieldResult().setValue(txt);
	}

	@Override
	public void setDisplay() {
		
		if(diagnosisLayout != null) {
		this.removeComponent(diagnosisLayout);
		}		
		diagnosisLayout = new DiagnosisLayout();
		this.addComponent(diagnosisLayout);		
	}
}
