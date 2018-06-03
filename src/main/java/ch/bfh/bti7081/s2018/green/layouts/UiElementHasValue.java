package ch.bfh.bti7081.s2018.green.layouts;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;

/*
 * A simple class who contains an element to add to a data-location of a custom layout and location given as string.
 * It allows to create lists of elements and add them in a foreach or similar to bigger forms
 */
public class UiElementHasValue {


    private String location;
    private AbstractField component;
    private boolean protectedField;

    private Button btn;

    public String getLocation() {
        return location;
    }
    public Component getComponent() {
        return component;
    }

    public boolean isProtectedField() {
        return protectedField;
    }

    public <T extends AbstractField>  UiElementHasValue (T t, String location) {
        this.component = t;
        this.location = location;
        this.protectedField = false;
    }
    public <T extends AbstractField> UiElementHasValue (T t, String location, boolean isProtected) {
        this.component = t;
        this.location = location;
        this.protectedField = isProtected;
    }

    public Object getActualValue() {

        return this.component.getValue();
    }

    //@ToDo: handle all relevant datatypes or create a better solution for this
    public <T> void setValue (T t) {
        if (t instanceof Integer) {
            this.component.setValue(t.toString());
        }
        else if (t instanceof Float) {
            this.component.setValue(Float.toString((Float) t));
        }
        else {
            this.component.setValue(t);
        }

    }
    public void addElement (CustomLayout panel) {
        panel.addComponent(this.component, this.location);
    }
}
