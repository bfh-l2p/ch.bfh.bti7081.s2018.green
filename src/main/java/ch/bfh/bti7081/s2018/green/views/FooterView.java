package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

public class FooterView extends CustomLayout implements View {

    private Label lblFooterTxt;

    public FooterView () {
        this.setTemplateName("footer");
        lblFooterTxt = new Label("All rights reserved to the natural owners. Comes without any warranty");
        this.addComponent(lblFooterTxt, "footerBar");
    }

    public Label getLblFooterTxt () {
        return lblFooterTxt;
    }

    public void setLabelText (String text) {
        lblFooterTxt.setValue(text);
    }

}
