package ch.bfh.bti7081.s2018.green.views.interfaces;

import ch.bfh.bti7081.s2018.green.layouts.UiElementHasValue;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.HorizontalLayout;

import java.util.List;

/*
* ToDO: using "clickable" DIV's is deprecated or let's say not so nice. Change all to buttons later
* A functional interface to generate clickable DIV-Panels like used in the navigation menu.
 */
public interface IClickableDivPanel {

    default <U extends AbstractComponent> HorizontalLayout wrapElement(U elementToWrap) {
        HorizontalLayout ly = new HorizontalLayout();
        ly.setSizeFull();
        ly.addComponent(elementToWrap);
        ly.setStyleName("menuBarButton");
        return ly;
    }

    void buildComponentList (List<UiElementHasValue> clickableElements);

}
