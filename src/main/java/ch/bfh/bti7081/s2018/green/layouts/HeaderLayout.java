package ch.bfh.bti7081.s2018.green.layouts;

import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

public class HeaderLayout extends CustomLayout {

    /*
        The constructor does add the concrete template and calls a method who adds the UI elements at their places.
        Keep it static and simple - as it would be like editor created classes.
     */
    protected HeaderLayout () {
        this.setTemplateName("header");
        this.addComponents();
    }

    /*
        Adds the static UI Elements at its data location.
        You should just place elements whose you don't want to access or change... kind of nonsense
        ex. in html: "<element data-location="XYZ"></element>"
        could be replaced using location name "XYZ"
     */
    private void addComponents () {
        this.addComponent(new Label("BreadCrumbs Placeholder"), "breadcrumbs");
        this.addComponent(new Label("Team Green: Patient Management System"),"pageLogo");
    }

}
