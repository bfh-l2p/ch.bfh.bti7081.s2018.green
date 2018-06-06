package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

public class ErrorView {

	public static void showError(String description, Page page) {

		new Notification("Error", description, Notification.Type.ERROR_MESSAGE, true).show(page);

	}

}
