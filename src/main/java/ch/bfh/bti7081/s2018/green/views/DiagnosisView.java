package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;

import ch.bfh.bti7081.s2018.green.designs.DiagnosisDesign;
import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class DiagnosisView extends NavigationView implements View, PmsView {

	DiagnosisDesign diagnosisDesign;

	public DiagnosisView() {

		diagnosisDesign = new DiagnosisDesign();
		this.addComponent(diagnosisDesign);

		Button nameBtn = diagnosisDesign.getBtnSetName();
		nameBtn.setId("Set Name");
		nameBtn.addClickListener(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {

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

		TextArea tArea = diagnosisDesign.getTextFieldResult();
		String text = diagnosisDesign.getTextFieldName().getValue();
		tArea.setValue(text);
	}

}
