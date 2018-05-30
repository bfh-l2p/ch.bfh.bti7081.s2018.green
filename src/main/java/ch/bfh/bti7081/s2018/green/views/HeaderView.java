package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

public class HeaderView extends CustomLayout implements View {

    // Enum to load views in the "NavigatorUI" class
    public static final String NAME = "header";

    public HeaderView () {
        this.setTemplateName("header");
        this.addComponent(new Label("BreadCrumbs Placeholder"), "breadcrumbs");
        this.addComponent(new Label("Team Green: Patient Management System"),"pageLogo");
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

