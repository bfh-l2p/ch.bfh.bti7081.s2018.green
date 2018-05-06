package ch.bfh.bti7081.s2018.green.views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.green.designs.MedicationDesign;
import ch.bfh.bti7081.s2018.green.designs.TherapyDesign;
import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class TherapyView extends NavigationView implements View, PmsView {

	TherapyDesign therapyDesign;
	
	public TherapyView() {		
				
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		setDisplay();
				
		Notification.show("Welcome to the Therapy View");
		
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
		if(therapyDesign != null) {
		this.removeComponent(therapyDesign);
		}		
		therapyDesign = new TherapyDesign();
		this.addComponent(therapyDesign);			
	}
	
}
