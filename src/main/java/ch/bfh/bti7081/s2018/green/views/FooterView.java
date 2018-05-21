package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.FooterLayout;
import com.vaadin.navigator.View;
import com.vaadin.ui.Label;

public class FooterView extends FooterLayout implements View {

    private Label lblFooterTxt;

    public FooterView () {
        lblFooterTxt = (Label) this.getComponent("footerBar");
    }

    public Label getLblFooterTxt () {
        return lblFooterTxt;
    }

    public void setLabelText (String text) {
        lblFooterTxt.setValue(text);
    }

}
