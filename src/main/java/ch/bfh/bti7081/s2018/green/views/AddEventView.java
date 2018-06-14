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
    private Label lbIntervals = new Label("Repetitions");
    private Label lbSetRecurringInterval = new Label("Interval");
    private TextField tfIntervals = new TextField();
    private DateTimeField dtfFrom = new DateTimeField();
    private DateTimeField dtfTo = new DateTimeField();
    private TextField tfTitle = new TextField();
    private TextArea tfContent = new TextArea();
    private Button btnSave = new Button("Save Event");
    private Button btnIncInt = new Button("+");
    private Button btnDecInt = new Button("-");
    private CheckBox cbRecurringEvent = new CheckBox("Repeat this event");
    private RadioButtonGroup<String> rbgSetRecurringInterval = new RadioButtonGroup<String>();

    public AddEventView() {
        // Set initial values to fields
        rbgSetRecurringInterval.setItems("Daily", "Weekly", "Monthly");
        dtfFrom.setValue(LocalDateTime.now());
        dtfTo.setValue(LocalDateTime.now().plusMinutes(15));
        tfIntervals.setReadOnly(true);
        rbgSetRecurringInterval.setEnabled(false);
        lbSetRecurringInterval.setEnabled(false);
        lbIntervals.setEnabled(false);
        btnIncInt.setEnabled(false);
        btnDecInt.setEnabled(false);
        tfIntervals.setEnabled(false);

        // Prepare panel container to fill in items
        Panel panel = new Panel("Add an Event");
        CustomLayout panelContent = new CustomLayout("eventAdd");
        panelContent.setId(NAME);
        setModal(true);

        // Place Java-Components in HTML DIVs
        panelContent.addComponent(new Label("Event begins at"), "fromLabel");
        panelContent.addComponent(new Label("Event ends at"), "toLabel");
        panelContent.addComponent(new Label("Title"), "titleLabel");
        panelContent.addComponent(new Label("Notes"), "contentLabel");
        panelContent.addComponent(dtfFrom, "fromField");
        panelContent.addComponent(dtfTo, "toField");
        panelContent.addComponent(tfTitle, "titleField");
        panelContent.addComponent(tfContent, "contentField");
        panelContent.addComponent(btnSave, "saveButton");
        panelContent.addComponent(new Label("Recurring?"), "recurringLabel");
        panelContent.addComponent(cbRecurringEvent, "recurringCheckBox");
        panelContent.addComponent(lbSetRecurringInterval, "recurringIntervalLabel");
        panelContent.addComponent(rbgSetRecurringInterval, "intervalRadioButtonGroup");
        panelContent.addComponent(lbIntervals, "intervalLabel");
        panelContent.addComponent(tfIntervals, "intervalTextfield");
        panelContent.addComponent(btnIncInt, "incButton");
        panelContent.addComponent(btnDecInt, "decButton");
        panel.setContent(panelContent);
        setContent(panel);

        new AddEventPresenter(this);
    }
    
    // Used Getters and Setters including some basic increase and decrease functions to set interval  
    public TextField getTfIntervals() {
		return tfIntervals;
	}

	public Label getLbIntervals() {
		return lbIntervals;
	}

    public Label getLbSetRecurringInterval() {
        return lbSetRecurringInterval;
    }
    
    public void setTfIntervals(int i) {
    	this.tfIntervals.setValue(""+i);
    }

	public int getIntervals() {
		return intervals;
	}
	
	public void incIntervals() {
		this.intervals++;
	}

	public void decIntervals() {
		this.intervals--;
	}

	public Button getBtnIncInt() {
		return btnIncInt;
	}

	public Button getBtnDecInt() {
		return btnDecInt;
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
    	return Date.from(dtfFrom.getValue().atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public Date getToDate() {
    	return Date.from(dtfTo.getValue().atZone(ZoneId.systemDefault()).toInstant());
    }
}
