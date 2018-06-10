package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.NavigatorUI;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

public class NavigationButton extends Button{

    public NavigationButton() {

    }

    public NavigationButton(String caption, String targetViewName, AbstractComponentContainer containingView) {
        this.setCaption(caption);
        changeStyle();
        this.addClickListener((clickEvent) ->
                setActive(this, targetViewName, containingView)
        );
    }

    public NavigationButton(String caption, AbstractComponentContainer containingView) {
        this.setCaption(caption);
        changeStyle();
        this.addClickListener((clickEvent) ->
                setActiveNoListener(this, containingView)
        );
    }

    private void changeStyle() {
        this.removeStyleNames("menuBarButton",".v-button", ".v-widget");
    }
    private static void setActive(NavigationButton ly, String targetViewName, AbstractComponentContainer containingView) {
        // remove all CSS styles who mark a div as visited in the whole navMenu view
        for (Component co : containingView)
        {
            co.removeStyleName("NavVisited");
        }
        // dynamically add the CSS style to mark a div as visited
        ly.addStyleName("NavVisited");
        NavigatorUI.navigator.navigateTo(targetViewName);
    }

    private static void setActiveNoListener(NavigationButton ly, AbstractComponentContainer containingView) {
        // remove all CSS styles who mark a div as visited in the whole navMenu view
        for (Component co : containingView)
        {
            co.removeStyleName("NavVisited");
        }
        // dynamically add the CSS style to mark a div as visited
        ly.addStyleName("NavVisited");
    }

}
