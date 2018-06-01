package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

public class HeaderView extends CustomLayout implements View {

    public static final String NAME = "header";

    public HeaderView () {
        this.setTemplateName("header");
        this.addComponent(new Label("BreadCrumbs Placeholder"), "breadcrumbs");
        this.addComponent(new Label("Team Green: Patient Management System"),"pageLogo");
        this.addComponent(lblLoggedOnUser, "userlogo");
    }

    private Label lblLoggedOnUser = new Label("User Logo Placeholder");

    public Label getLblLoggedOnUser() {
        return lblLoggedOnUser;
    }

}

