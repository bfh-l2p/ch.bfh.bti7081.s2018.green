package ch.bfh.bti7081.s2018.green.presenters.interfaces;

import com.vaadin.ui.HorizontalLayout;

public interface IClickableDivAddEvent {


    //default <U extends Layout> void addClickListener (U el) {
    void addClickListener (HorizontalLayout el);

    void setAction(HorizontalLayout ly, String enumView);

}
