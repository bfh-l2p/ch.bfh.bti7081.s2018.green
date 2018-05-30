package ch.bfh.bti7081.s2018.green.presenters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.JournalEntry;
import ch.bfh.bti7081.s2018.green.models.managers.JournalEntryManager;
import ch.bfh.bti7081.s2018.green.views.JournalView;

public class JournalPresenter {

    private JournalView view;
    private DataContainer data;

    public JournalPresenter(JournalView view) {
        this.view = view;
        this.data = DataContainer.getInstance();

        enteredView();

        this.view.getBtnSave().addClickListener(clickEvent -> addEntry());
    }

    private void addEntry() {
        TextArea txtEntry = view.getTxtEntry();
        String content = txtEntry.getValue().trim();
        txtEntry.clear();
        if (content != null && !content.isEmpty()) {
            JournalEntry journalEntry = new JournalEntry(content, data.getCurrentPatient(), data.getCurrentStaff());

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            // insert test record
            tx.begin();
            em.persist(journalEntry);
            tx.commit();

            view.addJournalEntry(journalEntry);
        }
    }
    private void enteredView() {
	    JournalEntryManager manager = new JournalEntryManager();
        view.setJournalEntries(manager.findBy(data.getCurrentPatient()));
    }
}
