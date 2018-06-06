package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;

public class PatientFile extends CustomLayout implements View {

    public static final String NAME = "patientFile";

    public PatientFile () {
        this.setTemplateName("patientFile");
    }
}
