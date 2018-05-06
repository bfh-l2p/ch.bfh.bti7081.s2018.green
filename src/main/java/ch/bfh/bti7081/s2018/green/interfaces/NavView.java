package ch.bfh.bti7081.s2018.green.interfaces;

import com.vaadin.ui.Component;

public interface NavView {

	// Used to ensure that the sub-layout is added at the right place in the super-layout
	void addComponent(Component c);
	
}
