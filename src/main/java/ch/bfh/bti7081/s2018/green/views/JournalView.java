package ch.bfh.bti7081.s2018.green.views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.green.designs.DiagnosisDesign;
import ch.bfh.bti7081.s2018.green.designs.JournalDesign;
import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class JournalView extends NavigationView implements View, PmsView {

	JournalDesign journalDesign;
	
	public JournalView() {		
				
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		setDisplay();
		
		Notification.show("Welcome to the Journal View");
		
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
		
		if(journalDesign != null) {
		this.removeComponent(journalDesign);
		}		
		journalDesign = new JournalDesign();
		this.addComponent(journalDesign);		
		
	}
	
}
