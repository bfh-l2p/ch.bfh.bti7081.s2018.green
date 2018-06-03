package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.NavigatorUI;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

public class NavigationButton extends Button{

    public NavigationButton () {

    }

    public NavigationButton (String caption, String enumView, AbstractComponentContainer vw) {
        this.setCaption(caption);
        changeStyle();
        this.addClickListener((clickEvent) ->
                setActive(this, enumView, vw)
        );
    }

    public NavigationButton (String caption, AbstractComponentContainer vw) {
        this.setCaption(caption);
        changeStyle();
        this.addClickListener((clickEvent) ->
                setActiveNoListener(this, vw)
        );
    }

    private void changeStyle() {
        this.removeStyleNames("menuBarButton",".v-button", ".v-widget");
        this.setId("navButton");
    }
    private static void setActive (NavigationButton ly, String enumView, AbstractComponentContainer vw) {
        // remove all CSS styles who mark a div as visited in the whole navMenu view
        for (Component co : vw)
        {
            co.removeStyleName("NavVisited");
        }
        // dynamically add the CSS style to mark a div as visited
        ly.addStyleName("NavVisited");
        NavigatorUI.navigator.navigateTo(enumView);
    }

    private static void setActiveNoListener (NavigationButton ly, AbstractComponentContainer vw) {
        // remove all CSS styles who mark a div as visited in the whole navMenu view
        for (Component co : vw)
        {
            co.removeStyleName("NavVisited");
        }
        // dynamically add the CSS style to mark a div as visited
        ly.addStyleName("NavVisited");
    }

}
