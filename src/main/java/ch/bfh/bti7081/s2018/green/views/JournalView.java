package ch.bfh.bti7081.s2018.green.views;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2018.green.designs.JournalDesign;
import ch.bfh.bti7081.s2018.green.designs.JournalEntryDesign;
import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class JournalView extends NavigationView implements View, PmsView {

	JournalDesign journalDesign;
	List<JournalEntryDesign> journalEntryElements;
	
	int counter = 5;
	
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
		
		// Remove only main-component here
		// Attached components will automatically be removed as well
		
		if(journalDesign != null) {
		this.removeComponent(journalDesign);
		}
		
		journalDesign = new JournalDesign();
		this.addComponent(journalDesign);				
	}
	
	public void setJournalEntries(List<String> journalEntries) {
		
		journalEntryElements = new ArrayList<JournalEntryDesign>();		
		
		for(int i=0; i< counter; i++) {
			JournalEntryDesign jed = new JournalEntryDesign();
			TextField tf = jed.getTextFieldJournalEntry();
			tf.setValue(journalEntries.get(i));
			journalDesign.addComponent(jed);			
			journalEntryElements.add(jed);
		}
		
		counter --;
		
	}
	
}
