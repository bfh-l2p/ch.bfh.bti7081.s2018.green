package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.views.HeaderView;

public class HeaderPresenter {

    private HeaderView view;

    public HeaderPresenter (HeaderView view) {
        this.view = view;
        //addUserName();
    }

    public void addUserName(String username) {
        view.getLblLoggedOnUser().setValue(username);
    }
}
