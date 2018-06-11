package ch.bfh.bti7081.s2018.green.presenters;

import java.util.List;

import javax.persistence.PersistenceException;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.ComboBox;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.managers.PatientManager;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
import ch.bfh.bti7081.s2018.green.views.HeaderView;
import ch.bfh.bti7081.s2018.green.views.JournalView;

public class HeaderPresenter {

	private HeaderView view;
	private DataContainer data;

	public HeaderPresenter(HeaderView view) {
		this.view = view;
		this.data = DataContainer.getInstance();

		enteredView();
	}

	private void enteredView() {

		try {
		// Assemble ComboBox to select a patient and...
		assembleCboxPatients();

		// ...set Change-Listener
		view.getCboxPatients().addValueChangeListener(event -> {
			data.setCurrentPatient(event.getValue());

			// Upon switch to other patient: Go to Patient-File
			if(NavigatorUI.navigator != null) {
				NavigatorUI.navigator.navigateTo(JournalView.NAME);
			}
		});
		} catch (PersistenceException e) {
			
			ErrorView.showError("Couldn't load patients-list", Page.getCurrent());									
		}			
	}

	private void assembleCboxPatients() throws PersistenceException {
			
		// Get ComboBox from View
		ComboBox<Patient> cboxPatients = this.view.getCboxPatients();

		// Get all available patients from DB
		List<Patient> pList = new PatientManager().findAll();

		cboxPatients.setCaptionAsHtml(true);
		cboxPatients.setCaption("<b style=\"font-size:16px\"> Patient: &nbsp;&nbsp;</b>");
		cboxPatients.setItems(pList);

		// Set property that will be displayed on combobox
		cboxPatients.setItemCaptionGenerator(Patient::getFullName);

		// Set size of ComboBox
		cboxPatients.setHeight(25, Unit.PIXELS);
		cboxPatients.setWidth(200, Unit.PIXELS);

		// First patient in list will be set by default
		// Prevent empty selection
		if (!pList.isEmpty()) {
			cboxPatients.setValue(pList.get(0));
			cboxPatients.setEmptySelectionAllowed(false);
		}
	}

	public void addUserName(String username) {
		view.getLblLoggedOnUser().setValue(username);
	}
}
