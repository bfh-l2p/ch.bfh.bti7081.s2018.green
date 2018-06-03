package ch.bfh.bti7081.s2018.green;


import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.views.JournalView;
import ch.bfh.bti7081.s2018.green.views.MedicationView;
import ch.bfh.bti7081.s2018.green.layouts.BaseLayoutFabric;
import ch.bfh.bti7081.s2018.green.presenters.FooterPresenter;
import ch.bfh.bti7081.s2018.green.presenters.HeaderPresenter;
import ch.bfh.bti7081.s2018.green.presenters.NavigationMenuPresenter;
import ch.bfh.bti7081.s2018.green.views.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        // Get the base layout
        BaseLayoutFabric layout = new BaseLayoutFabric();

        setContent(layout);

        // header row
        layout.setContainer(getHeader(), BaseLayoutFabric.SECTIONS.headSection);

        // add navigation menu
        layout.setContainer(getNavigation(), BaseLayoutFabric.SECTIONS.navSection);

        // add footer bar
        layout.setContainer(getFooterBar(), BaseLayoutFabric.SECTIONS.footerSection);

        // the app container itself which displays the current views of the application
        VerticalLayout canvas = getCanvas();
        layout.setContainer(canvas, BaseLayoutFabric.SECTIONS.appSection);

        // tell the navigation to use
        navigator = new Navigator(this,canvas);

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

        VerticalLayout main = new VerticalLayout();
        main.setWidth("100%");
        main.setHeight("100%");
        return main;
    }

    /**
     * Create navigation view and presenter and return in i a custom layout
     *
     * @return navigation
     */
    private CustomLayout getNavigation() {
        NavigationMenuView navMen = new NavigationMenuView();
        // add the click listeners now
        new NavigationMenuPresenter(navMen);
        return navMen;
    }

    private CustomLayout getFooterBar() {
        FooterView footer = new FooterView();
        FooterPresenter fp = new FooterPresenter(footer);
        fp.setFooterText("Programm under construction. You are not viewing a final or representative version");
        return footer;
    }
    private CustomLayout getHeader() {
        //HeaderView header = new HeaderView();
        HeaderView header = new HeaderView();
        HeaderPresenter hp = new HeaderPresenter(header);
        hp.addUserName("User: " + System.getProperty("user.name"));

        return header;
    }

    /**
     * Initialise data access layer, views and presenters and add the to the navigator
     */
    private void initializeClasses() {

        DataContainer data = DataContainer.getInstance();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();

        // TODO: add patient list instead of hardcoding the id
        Patient matthias = em.find(Patient.class, 2);
        Staff exampleDoctor = em.find(Staff.class, 3);
        System.out.println(matthias.getFirstName());
        data.setCurrentPatient(matthias);
        data.setCurrentStaff(exampleDoctor);

        // ViewID, ClassToInitiate
        navigator.addView(JournalView.NAME, JournalView.class);
        navigator.addView(MedicationView.NAME, MedicationView.class);
        navigator.addView(DiagnosisView.NAME, DiagnosisView.class);
        navigator.addView(MedicationView.NAME, MedicationView.class);
        navigator.addView(EventListView.NAME, EventListView.class);
        navigator.addView(PatientFile.NAME, PatientFile.class);
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    }
}
