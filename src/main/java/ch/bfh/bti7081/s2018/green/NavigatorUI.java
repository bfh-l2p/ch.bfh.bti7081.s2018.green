package ch.bfh.bti7081.s2018.green;


import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.presenters.NavigationPresenter;
import ch.bfh.bti7081.s2018.green.views.JournalView;
import ch.bfh.bti7081.s2018.green.views.MedicationView;
import ch.bfh.bti7081.s2018.green.views.NavigationView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")

// The main-class of the application

public class NavigatorUI extends UI {


    public static Navigator navigator;


    /**
     * Entry point: Custom part of application starts here!
     * <p>
     * Sets the basic layout and instantiates the views and
     * it's presenters, as well as the data access layer.
     *
     * @param request provided by vaadin
     */
    @Override
    protected void init(VaadinRequest request) {

        // html <title> attribute
        getPage().setTitle("Patient Management System");

        // instantiate a full screen layout and add it
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);

        // header row
        layout.addComponent(getHeader());

        // main container
        HorizontalLayout main = getMainContainer();
        layout.addComponent(main);
        layout.setExpandRatio(main, 1.0f); // make main container use all available space
        main.addComponent(getNavigation());
        VerticalLayout canvas = getCanvas();
        main.addComponent(canvas);

        // tell the navigation to use
        navigator = new Navigator(this, canvas);

        // Assembles all presenters/views and adds them to the navigator
        initializeClasses();

        // Navigates to the startpage
        navigator.navigateTo(JournalView.NAME);

    }

    /**
     * Creates and configures the main container that holds the navigation and the canvas
     *
     * @return main container
     */
    private HorizontalLayout getMainContainer() {
        HorizontalLayout main = new HorizontalLayout();
        main.setWidth("100%");
        main.setHeight("100%");

        return main;
    }

    /**
     * Create the canvas for the specific views (medication, journal etc)
     *
     * @return canvas
     */
    private VerticalLayout getCanvas() {
        return new VerticalLayout();
    }

    /**
     * Create navigation view and presenter and return in i a vertical layout
     *
     * @return navigation
     */
    private VerticalLayout getNavigation() {
        VerticalLayout nav = new VerticalLayout();

        NavigationView navigationView = new NavigationView();
        new NavigationPresenter(navigationView);
        nav.addComponent(navigationView);

        return nav;
    }

    /**
     * Create an configure the layouts header
     *
     * @return header
     */
    private HorizontalLayout getHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidth("100%");
        header.setHeight("100px");
        // TODO move this into a separate HeaderView class
        header.addComponent(new Label("Patient Management System Team Green"));
        return header;
    }

    /**
     * Initialise data access layer, views and presenters and add the to the navigator
     */
    private void initializeClasses() {

        // TODO: remove demo data
        Patient matthias = new Patient("Patrice Malade");
        DataContainer data = DataContainer.getInstance();
        data.setCurrentPatient(matthias);

        data.getCurrentPatient().addJournalEntry("14.05. Halluzinationen");
        data.getCurrentPatient().addJournalEntry("15.05. Wahnvorstellungen");
        data.getCurrentPatient().addJournalEntry("16.05. Präpsychose");

        navigator.addView(JournalView.NAME, JournalView.class);
        navigator.addView(MedicationView.NAME, MedicationView.class);

    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    }
}
