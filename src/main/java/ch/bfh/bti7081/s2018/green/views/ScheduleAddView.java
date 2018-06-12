package ch.bfh.bti7081.s2018.green.views;

import java.time.LocalDateTime;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.TextArea;

import ch.bfh.bti7081.s2018.green.presenters.SchedulePresenter;

public class ScheduleAddView extends CustomLayout implements View {

    public static final String NAME = "scheduleAdd";

    private DateTimeField dtfFrom = new DateTimeField();
    private DateTimeField dtfTo = new DateTimeField();
    private TextArea tfContent = new TextArea();
    private Button btnSave = new Button("Save appointment");

    @Override
    public void enter(ViewChangeEvent event) {
        // Set initial values to fields
        dtfFrom.setValue(LocalDateTime.now());
        dtfTo.setValue(LocalDateTime.now());
        
        // Place Java-Components in HTML DIVs 
        this.setTemplateName("scheduleAdd");
        this.addComponent(dtfFrom, "fromField");
        this.addComponent(dtfTo, "toField");
        this.addComponent(tfContent, "contentField");
        this.addComponent(btnSave, "saveButton");

        new SchedulePresenter(this);
    }
    
    public DateTimeField getDtfFrom() {
        return dtfFrom;
    }

    public DateTimeField getDtfTo() {
        return dtfTo;
    }
    
    public Button getBtnSave() {
        return btnSave;
    }
    
    public TextArea getTfContent() {
        return tfContent;
    }
}
