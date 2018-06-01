package ch.bfh.bti7081.s2018.green.layouts;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.TextField;

public class DiagnosisLayout extends CustomLayout {

    public DiagnosisLayout() {
        this.setTemplateName("diagnosisApp");
        this.addComponents();
    }

    private void addComponents () {
        this.addComponent(new TextField(), "username");
        this.addComponent(new TextField(), "password");
        this.addComponent(new Button("Login"), "okbutton");
    }
}
