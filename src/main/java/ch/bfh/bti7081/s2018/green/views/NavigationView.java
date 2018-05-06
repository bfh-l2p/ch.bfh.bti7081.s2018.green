package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.PageName;
import ch.bfh.bti7081.s2018.green.designs.NavigationDesign;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class NavigationView extends NavigationDesign implements View, ClickListener, SelectionListener {

	PmsViewListener listener;

	public NavigationView() {

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
