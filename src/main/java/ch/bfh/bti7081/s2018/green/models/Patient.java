package ch.bfh.bti7081.s2018.green.models;

import java.util.ArrayList;
import java.util.List;

public class Patient {

	String name;
	List<String> journalEntries;
	
	public Patient(String name) {
		this.name = name;		
	}
		

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addJournalEntry(String jEntry) {
		if(journalEntries == null) {
			journalEntries = new ArrayList<String>();
		}
		journalEntries.add(jEntry);
	}


	public List<String> getJournalEntries() {
		return journalEntries;
	}
	
}
