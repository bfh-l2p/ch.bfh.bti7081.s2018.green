package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Layout;

import ch.bfh.bti7081.s2018.green.interfaces.PmsViewListener;

public class NavigationFrame extends CustomComponent implements View, ClickListener {

	PmsViewListener listener;	
	Layout layout;
	
	public NavigationFrame() { }
	
	
	public void initializeBaseLayout() {
		
		layout = new VerticalLayout();
		layout.addComponent(new Button("Button of Navigation-View", this));		
	}
	
    

	@Override
	public void buttonClick(ClickEvent event) {
		
		listener.buttonClick("");
	}	
}
