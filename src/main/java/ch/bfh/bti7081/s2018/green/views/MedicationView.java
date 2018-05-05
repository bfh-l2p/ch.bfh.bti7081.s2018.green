package ch.bfh.bti7081.s2018.green.views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.green.designs.MedicationDesign;
import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class MedicationView extends NavigationView implements View, PmsView {

	MedicationDesign medicationDesign;
	
	public MedicationView() {		

		medicationDesign = new MedicationDesign();
		this.addComponent(medicationDesign);
				
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		Notification.show("Welcome to the Medication View");
		
		// tells the presenter that the view was opened
		listener.enteredView();
	}
	
	@Override
	public void addListener(PmsViewListener listener) {
		
		// this.listener is inherited from superclass
		this.listener = listener;
	}
	
}
