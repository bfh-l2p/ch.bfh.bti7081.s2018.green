package ch.bfh.bti7081.s2018.green.views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.layouts.TherapyLayout;
import ch.bfh.bti7081.s2018.green.navViews.NavigationView;

public class TherapyView extends NavigationView implements View, PmsView {

	TherapyLayout therapyLayout;
	
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
		if(therapyLayout != null) {
		this.removeComponent(therapyLayout);
		}		
		therapyLayout = new TherapyLayout();
		this.addComponent(therapyLayout);			
	}
	
}
