package ch.bfh.bti7081.s2018.green.views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.layouts.MedicationLayout;

public class MedicationView extends NavigationView implements View, PmsView {

	MedicationLayout medicationLayout;
	
	public MedicationView() {		
				
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		setDisplay();
		
		Notification.show("Welcome to the Medication View");
		
		// tells the presenter that the view was opened
		listener.enteredView();
	}
	
	@Override
	public void addListener(PmsViewListener listener) {
		
		// this.listener is inherited from superclass
		this.listener = listener;
	}

	@Override
	public void setDisplay() {
		
		if(medicationLayout != null) {
		this.removeComponent(medicationLayout);
		}		
		medicationLayout = new MedicationLayout();
		this.addComponent(medicationLayout);	
		
	}
	
}
