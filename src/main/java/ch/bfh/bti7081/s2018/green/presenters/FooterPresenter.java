package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.views.FooterView;

public class FooterPresenter {

    private FooterView footer;
    public FooterPresenter (FooterView f) {
        footer = f;
    }

    public void setFooterText (String txt) {
        footer.getLblFooterTxt().setValue(txt);
    }
}
