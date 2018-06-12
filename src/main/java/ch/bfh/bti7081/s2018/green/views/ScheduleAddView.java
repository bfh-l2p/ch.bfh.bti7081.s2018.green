package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.presenters.AddEventPresenter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ScheduleAddView extends Window implements View {

    public static final String NAME = "scheduleAdd";

    // Attributes of default ScheduleAddView
    private DateTimeField dtfFrom = new DateTimeField();
    private DateTimeField dtfTo = new DateTimeField();
    private TextField tfTitle = new TextField();
    private TextArea tfContent = new TextArea();
    private Button btnSave = new Button("Save Schedule");
    private CheckBox cbRecurringEvent = new CheckBox("Is this a recurring Event?");
    RadioButtonGroup<String> rbgSetRecurringInterval = new RadioButtonGroup<String>("Select intervals");

    public ScheduleAddView() {
    	
        // Set initial values to fields
        rbgSetRecurringInterval.setItems("Daily", "Weekly", "Monthly");
        rbgSetRecurringInterval.setEnabled(false);
        dtfFrom.setValue(LocalDateTime.now());
        dtfTo.setValue(LocalDateTime.now());
        
        Panel panel = new Panel("This is a Panel");
        CustomLayout panelContent = new CustomLayout("scheduleAdd");
        setModal(true);
        
        // Place Java-Components in HTML DIVs 
        panelContent.addComponent(dtfFrom, "fromField");
        panelContent.addComponent(dtfTo, "toField");
        panelContent.addComponent(tfTitle, "titleField");
        panelContent.addComponent(tfContent, "contentField");
        panelContent.addComponent(btnSave, "saveButton");
        panelContent.addComponent(cbRecurringEvent, "RecurringCheckBox");
        panelContent.addComponent(rbgSetRecurringInterval, "IntervalRadioButtonGroup");
        panel.setContent(panelContent);
        setContent(panel);

        new AddEventPresenter(this);
    }
    
    public RadioButtonGroup<String> getRbgSetRecurringInterval() {
		return rbgSetRecurringInterval;
	}

	public DateTimeField getDtfFrom() {
        return dtfFrom;
    }

    public CheckBox getCbRecurringEvent() {
		return cbRecurringEvent;
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
    
    public TextField getTfTitle() {
        return tfTitle;
    }
    
    public Date getFromDate() {
    	Date tmp = Date.from(dtfFrom.getValue().atZone(ZoneId.systemDefault()).toInstant());
    	return tmp;
    }
    
    public Date getToDate() {
    	Date tmp = Date.from(dtfTo.getValue().atZone(ZoneId.systemDefault()).toInstant());
    	return tmp;
    }
}
