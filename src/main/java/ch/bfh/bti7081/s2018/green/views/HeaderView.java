package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.HeaderLayout;
import com.vaadin.navigator.View;
import com.vaadin.ui.Label;

public class HeaderView extends HeaderLayout implements View {

    // Enum to load views in the "NavigatorUI" class
    public static final String NAME = "header";

    public HeaderView () {
        addUserLogoContainer();
    }

    private Label lblLoggedOnUser = new Label("User Logo Placeholder");

    // add a "dynamical" component which can be accessed/derived by the presenter according MVP pattern
    private void addUserLogoContainer () {
       this.addComponent(lblLoggedOnUser, "userlogo");
    }

    // typical getter
    public Label getLblLoggedOnUser() {
        return lblLoggedOnUser;
    }

}

