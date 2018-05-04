package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class MedicationView extends CustomComponent implements View, PmsView, ClickListener {

	PmsViewListener listener;
	VerticalLayout layout;

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to the Medication View");

		// Removes all components before assembling a new layout
		// Makes sure components won't be multiplied as a result of multiple class-invocations
		if (layout != null) {
			layout.removeAllComponents();
		}

		// Build up new layout
		layout = new VerticalLayout();

		// Always at the end of enter-method!
		// Lets the presenter know that it can now fill the view-elements with data
		listener.enteredView();
	}

	@Override
	public void addListener(PmsViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void buttonClick(ClickEvent event) {

		listener.buttonClick("");

		// getUI().getNavigator().navigateTo("Medication");
	}

	@Override
	public void setDisplay() {
		// TODO Auto-generated method stub

	}

}
