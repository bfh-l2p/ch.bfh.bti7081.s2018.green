package ch.bfh.bti7081.s2018.green.views.interfaces;

import ch.bfh.bti7081.s2018.green.layouts.UiElement;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import java.util.List;

public interface IClickableDivPanel {

    default <U extends Component> HorizontalLayout wrapElement(U elementToWrap) {
        HorizontalLayout ly = new HorizontalLayout();
        ly.setSizeFull();
        ly.addComponent(elementToWrap);
        ly.setStyleName("menuBarButton");
        return ly;
    }

    void buildComponentList (List<UiElement> clickableElements);

}
