package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2018.green.interfaces.PmsView;
import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class JournalView extends NavigationFrame implements View, PmsView, ClickListener {

	// NO Listener-Property here: Will be inherited from superclass

	// NO Layout-Property here: Will be inherited from superclass
	Button btnPatient;

	@Override
	public void enter(ViewChangeEvent event) {

		// "layout"-property is inherited from Superclass

		// Removes all components before assembling the new layout
		// Makes sure components won't be multiplied as a result of multiple class-invocations
		if (layout != null) {
			layout.removeAllComponents();
		}

		// Re-build base-layout (stored in Superclass)
		initializeBaseLayout();

		btnPatient = new Button("", this);

		layout.addComponent(btnPatient);

		// Always at the end of enter-method!
		// Lets the presenter know that it can now fill the view-elements with data
		listener.enteredView();

	}

	public void setBtnPatient(String name) {

		btnPatient.setCaption(name);
	}

	public void setDiagnosis(String text) {

		TextField tf = new TextField(text);
		layout.addComponent(tf);
	}

	@Override
	public void addListener(PmsViewListener listener) {

		// "this.listener" is inherited from superclass !!!
		this.listener = listener;
	}

	@Override
	public void buttonClick(ClickEvent event) {

		// listener is inherited from superclass !!!
		listener.buttonClick("");

	}

	@Override
	public void setDisplay() {
		setCompositionRoot(layout);
	}

}
