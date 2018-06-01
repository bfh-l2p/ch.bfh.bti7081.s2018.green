package ch.bfh.bti7081.s2018.green.layouts;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;

public class UiElementHasValue extends UiElement{

    private AbstractField component;
    public Component getComponent() {
        return component;
    }

    public <T extends AbstractField>  UiElementHasValue (T t, String location) {
        this.component = t;
        this.location = location;
        this.protectedField = false;
    }
    public <T extends AbstractField> UiElementHasValue (T t, String location, boolean isProtected) {
        this.component = t;
        this.location = location;
        this.protectedField = false;
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
}
