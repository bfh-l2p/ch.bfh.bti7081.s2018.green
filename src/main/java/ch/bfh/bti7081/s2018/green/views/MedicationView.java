package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.MedicationLayout;
import ch.bfh.bti7081.s2018.green.presenters.MedicationPresenter;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

public class MedicationView extends MedicationLayout implements View {

    public static final String NAME = "medication";

	@Override
	public void enter(ViewChangeEvent event) {
			
		Notification.show("Welcome to the Medication View");
		
		new MedicationPresenter(this);
	}

	
}
