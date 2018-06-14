package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.DataContainer;

import com.vaadin.ui.Button;

public class NavigationButton extends Button{
    private String targetViewName;
    private NavigationMenuView menu;

    public NavigationButton(String caption, String targetViewName, NavigationMenuView menu) {
        this.setCaption(caption);
        this.targetViewName = targetViewName;
        this.menu = menu;
        this.removeStyleNames("menuBarButton",".v-button", ".v-widget");
        this.addClickListener((clickEvent) -> changeView());
    }

    private void setStyle() {
        menu.resetAllButtonsStyles();
        if (targetViewName.equals(DataContainer.getInstance().getCurrentViewName())) {
            this.addStyleName("active");
        }
    }

    private void changeView() {
        DataContainer.getInstance().setCurrentViewName(targetViewName);
        setStyle();
        DataContainer.getInstance().getCurrentNavigator().navigateTo(this.targetViewName);
    }
}
