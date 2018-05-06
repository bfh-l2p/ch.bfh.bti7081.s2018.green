package ch.bfh.bti7081.s2018.green.views;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.layouts.JournalLayout;
import ch.bfh.bti7081.s2018.green.navViews.NavigationView;
import ch.bfh.bti7081.s2018.green.layouts.JournalEntryLayout;

public class JournalView extends NavigationView implements View, PmsView {

	JournalLayout journalLayout;
	List<JournalEntryLayout> journalEntryElements;
	
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
		
		if(journalLayout != null) {
		this.removeComponent(journalLayout);
		}
		
		journalLayout = new JournalLayout();
		this.addComponent(journalLayout);				
	}
	
	public void setJournalEntries(List<String> journalEntries) {
		
		journalEntryElements = new ArrayList<JournalEntryLayout>();		
		
		for(int i=0; i< counter; i++) {
			JournalEntryLayout jel = new JournalEntryLayout();
			TextField tf = jel.getTextFieldJournalEntry();
			tf.setValue(journalEntries.get(i));
			journalLayout.addComponent(jel);			
			journalEntryElements.add(jel);
		}
		
		counter --;
		
	}
	
}
