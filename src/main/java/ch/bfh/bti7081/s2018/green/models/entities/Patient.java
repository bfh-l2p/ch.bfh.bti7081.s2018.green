package ch.bfh.bti7081.s2018.green.models.entities;

import java.util.ArrayList;
import java.util.List;

public class Patient {

	private String name;
	private List<String> journalEntries;

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
			journalEntries = new ArrayList<>();
		}
		journalEntries.add(jEntry);
	}

	public List<String> getJournalEntries() {
		return journalEntries;
	}
	
}
