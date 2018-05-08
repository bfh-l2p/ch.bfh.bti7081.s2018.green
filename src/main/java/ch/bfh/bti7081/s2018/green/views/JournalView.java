package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.JournalEntryLayout;
import ch.bfh.bti7081.s2018.green.layouts.JournalLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import java.util.ArrayList;
import java.util.List;

public class JournalView extends JournalLayout implements View {

	private List<JournalEntryLayout> journalEntryElements;

	private int counter = 5;
	
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to the Journal View");
	}
	
	public void setJournalEntries(List<String> journalEntries) {

		journalEntryElements = new ArrayList<>();
		
		for(int i=0; i< counter; i++) {
			JournalEntryLayout jel = new JournalEntryLayout();
			TextField tf = jel.getTextFieldJournalEntry();
			tf.setValue(journalEntries.get(i));
			this.addComponent(jel);
			journalEntryElements.add(jel);
		}
		
		counter --;
		
	}
	
}
