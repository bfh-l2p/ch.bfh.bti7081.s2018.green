package ch.bfh.bti7081.s2018.green.views;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import ch.bfh.bti7081.s2018.green.designs.NavigationDesign;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class NavigationView extends NavigationDesign implements View, ClickListener, SelectionListener {

	PmsViewListener listener;
	
	public NavigationView() {
		
		navBtnMedication.addClickListener(this);
		navBtnJournal.addClickListener(this);
		navBtnTherapy.addClickListener(this);
		navBtnDiagnosis.addClickListener(this);
	}	

	@Override
	public void buttonClick(ClickEvent event) {

		listener.buttonClick(event.getButton().getCaption());	
	}

	@Override
	public void selectionChange(SelectionEvent event) {

		// TODO Auto-generated method stub		
	}

}
