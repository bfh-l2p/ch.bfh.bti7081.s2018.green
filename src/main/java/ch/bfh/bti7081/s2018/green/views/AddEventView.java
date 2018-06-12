package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.green.presenters.AddEventPresenter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AddEventView extends Window implements View {

    public static final String NAME = "eventAdd";

    // Attributes of default ScheduleAddView
    private int intervals=0;
    private Label lbFrom = new Label("Event begins at:");
    private Label lbTo = new Label("Event ends at:");
    private Label lbTitle = new Label("Title");
    private Label lbContent = new Label("Reason of event and notes:");
    private Label lbIntervals = new Label(""+intervals);
    private DateTimeField dtfFrom = new DateTimeField();
    private DateTimeField dtfTo = new DateTimeField();
    private TextField tfTitle = new TextField();
    private TextArea tfContent = new TextArea();
    private Button btnSave = new Button("Save Event");
    private Button btnIncInt = new Button("+");
    private Button btnDecInt = new Button("-");
    private CheckBox cbRecurringEvent = new CheckBox("Is this a recurring Event?");
    RadioButtonGroup<String> rbgSetRecurringInterval = new RadioButtonGroup<String>("Select intervals");

    public AddEventView() {
    	
        // Set initial values to fields
        rbgSetRecurringInterval.setItems("Daily", "Weekly", "Monthly");
        rbgSetRecurringInterval.setEnabled(false);
        dtfFrom.setValue(LocalDateTime.now());
        dtfTo.setValue(LocalDateTime.now());
        
        Panel panel = new Panel("Add an Event");
        CustomLayout panelContent = new CustomLayout("eventAdd");
        setModal(true);
        
        // Place Java-Components in HTML DIVs fromLabel
        panelContent.addComponent(lbFrom, "fromLabel");
        panelContent.addComponent(lbTo, "toLabel");
        panelContent.addComponent(lbTitle, "titleLabel");
        panelContent.addComponent(lbContent, "contentLabel");
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
