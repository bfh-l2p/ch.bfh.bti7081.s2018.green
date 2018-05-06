package ch.bfh.bti7081.s2018.green.navViews;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.PageName;
import ch.bfh.bti7081.s2018.green.interfaces.NavView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;
import ch.bfh.bti7081.s2018.green.layouts.NavigationLayout;

public class NavigationView extends NavigationLayout implements View, NavView, ClickListener, SelectionListener {

	protected PmsViewListener listener;

	public NavigationView() {

		
		// As an alternative to using "setId()" the DomIds can be specified manually in Vaadin Designer!
		
		navBtnMedication.addClickListener(this);
		navBtnMedication.setId("nav" + PageName.MEDICATION.getName());

		navBtnJournal.addClickListener(this);
		navBtnJournal.setId("nav" + PageName.JOURNAL.getName());

		navBtnTherapy.addClickListener(this);
		navBtnTherapy.setId("nav" + PageName.THERAPY.getName());

		navBtnDiagnosis.addClickListener(this);
		navBtnDiagnosis.setId("nav" + PageName.DIAGNOSIS.getName());
	}
	
	@Override
	public void addComponent(Component c){
		
		// TODO: Make sure the sub-layout is added at the right place in the super-layout
		
		super.addComponent(c);		
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {

		String btnId = event.getButton().getId();

		if (btnId != null) {

			// if event is a call to switch to another page, do it here
			if (btnId.substring(0, 3).equals("nav")) {
				NavigatorUI.navigator.navigateTo(btnId.substring(3));

		    // else, pass the id of the firing button to the presenter
			} else {
				listener.buttonClick(btnId);
			}

		}
	}

	@Override
	public void selectionChange(SelectionEvent event) {

		// TODO Auto-generated method stub
	}
	
	

}
