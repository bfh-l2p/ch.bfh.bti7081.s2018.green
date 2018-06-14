package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.views.FooterView;

public class FooterPresenter {

    private FooterView view;

    public FooterPresenter (FooterView view) {
        this.view = view;
    }

    public void setFooterText (String txt) {
        this.view.getLblFooterTxt().setValue(txt);
    }
}
