package ch.bfh.bti7081.s2018.green.presenters;

import java.util.List;

import javax.persistence.PersistenceException;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.ComboBox;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.managers.PatientManager;
import ch.bfh.bti7081.s2018.green.models.managers.StaffManager;
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

				// Upon switch to other patient: Go to Journal-View
				if(data.getCurrentNavigator() != null) {
					data.getCurrentNavigator().navigateTo(JournalView.NAME);
				}
			});
		} catch (PersistenceException e) {

			ErrorView.showError("Couldn't load patients-list", Page.getCurrent());
		}

		try {
			// Assemble ComboBox to select a staff-member and...
			assembleCboxStaff();

			// ...set Change-Listener
			view.getCboxStaff().addValueChangeListener(event -> {
				data.setCurrentStaff(event.getValue());
			});
		} catch (PersistenceException e) {

			ErrorView.showError("Couldn't load staff-list", Page.getCurrent());
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

	private void assembleCboxStaff() throws PersistenceException {

		// Get ComboBox from View
		ComboBox<Staff> cboxStaff = this.view.getCboxStaff();

		// Get all available staff members from DB
		List<Staff> sList = new StaffManager().findAll();

		cboxStaff.setCaptionAsHtml(true);
		cboxStaff.setCaption("<b style=\"font-size:16px\"> User: &nbsp;&nbsp;</b>");
		cboxStaff.setItems(sList);

		// Set property that will be displayed on combobox
		cboxStaff.setItemCaptionGenerator(Staff::getFullName);

		// Set size of ComboBox
		cboxStaff.setHeight(25, Unit.PIXELS);
		cboxStaff.setWidth(200, Unit.PIXELS);

		// First staff member in list will be set by default
		// Prevent empty selection
		if (!sList.isEmpty()) {
			cboxStaff.setValue(sList.get(0));
			cboxStaff.setEmptySelectionAllowed(false);
		}
	}

	public void addUserName(String username) {
		view.getLblLoggedOnUser().setValue(username);
	}
}
