package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.TextField;

public class DiagnosisView extends CustomLayout implements View {

    public static final String NAME = "diagnosisApp";

    public DiagnosisView () {
        this.setTemplateName("diagnosisApp");
        this.addComponents();
    }
    private void addComponents () {
        this.addComponent(new TextField(), "username");
        this.addComponent(new TextField(), "password");
        this.addComponent(new Button("Login"), "okbutton");
    }

}
