package ch.bfh.bti7081.s2018.green.layouts;

import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;

/*
* A simple class who contains an element to add to a data-location of a custom layout and location given as string.
* It allows to creat lists of elements and add them in a foreach or similar to bigger forms
 */
public class UiElement {

    public String getLocation() {
        return location;
    }

    public Component getComponent() {
        return component;
    }

    protected String location;
    private Component component;

    public boolean isProtectedField() {
        return protectedField;
    }

    protected boolean protectedField;

    //default constructor
    public UiElement() {}

    public UiElement (AbstractComponent cmp, String location) {
        this.component = cmp;
        this.location = location;
        this.protectedField = false;
    }

    public UiElement (AbstractComponent cmp, String location, boolean isProtected) {
        this.component = cmp;
        this.location = location;
        this.protectedField = isProtected;
    }

    public void addElement (CustomLayout panel) {
        panel.addComponent(this.component, this.location);
    }

}
