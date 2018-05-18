package ch.bfh.bti7081.s2018.green.layouts;

import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

public class FooterLayout extends CustomLayout {

    private Label lblFooterTxt = new Label("All rights reserved to the natural owners. Comes without any warranty");

    protected FooterLayout () {
        this.setTemplateName("footer");
        this.addComponents();
    }

    private void addComponents () {
        this.addComponent(lblFooterTxt, "footerBar");
    }

}
