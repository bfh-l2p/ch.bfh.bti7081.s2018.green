package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.presenters.JournalPresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class JournalView extends CustomLayout implements View {

    public static final String NAME = "journal";

    private Button btnSave = new Button("Save journal entry");
    private TextArea txtEntry;
    private VerticalLayout journalEntryList;

    @Override
    public void enter(ViewChangeEvent event) {
        this.setTemplateName(NAME);
        this.setId(NAME);
        addTxtEntry();
        this.addComponent(btnSave, "savebutton");

        new JournalPresenter(this);
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        journalEntryList = new VerticalLayout();
        for (JournalEntry journalEntry : journalEntries) {
            addJournalEntry(journalEntry);
        }
        this.addComponent(journalEntryList, NAME);
    }

    public void addJournalEntry(JournalEntry journalEntry) {
        CustomLayout journalEntryLayout = new CustomLayout("journalentry");

        addLabel(journalEntryLayout, "content", journalEntry.getContent());
        addLabel(journalEntryLayout, "author", journalEntry.getStaff().getFullName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm").withLocale(Locale.GERMAN);
        addLabel(journalEntryLayout, "created", journalEntry.getCreated().format(formatter));

        journalEntryList.addComponentAsFirst(journalEntryLayout);
    }

    private void addLabel(CustomLayout layout, String slot, String value) {
        Label label = new Label();
        label.setValue(value);
        layout.addComponent(label, slot);
    }

    private void addTxtEntry() {
        txtEntry = new TextArea();
        txtEntry.setPlaceholder("Add new journal entry");
        txtEntry.setRows(6);
        this.addComponent(txtEntry, "textentry");
    }

    public TextArea getTxtEntry() {
        return txtEntry;
    }

    public Button getBtnSave() {
        return btnSave;
    }
}
