package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.views.HeaderView;

public class HeaderPresenter {

    private HeaderView head;

    public HeaderPresenter (HeaderView h) {
        head = h;
    }

    public void addUserName(String username) {
        head.getLblLoggedOnUser().setValue(username);
    }
}
