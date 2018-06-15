package ch.bfh.bti7081.s2018.green.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.presenters.HeaderPresenter;

public class HeaderView extends CustomLayout implements View {

	public static final String NAME = "header";

	private ComboBox<Patient> cboxSelectPatient;
	private ComboBox<Staff> cboxSelectStaff;
	private Label lblLoggedOnUser = new Label("User Logo Placeholder");


	public HeaderView() {

		// Create new Combobox to select the patient
		cboxSelectPatient = new ComboBox<>("");
		this.addComponent(cboxSelectPatient, "cboxSelectPatients");

		this.setTemplateName("header");
		this.addComponent(new Label("Team Green: Patient Management System"), "pageLogo");
		this.addComponent(lblLoggedOnUser, "userlogo");
		
		cboxSelectStaff = new ComboBox<>("");
		this.addComponent(cboxSelectStaff, "cboxSelectStaff");
		
		new HeaderPresenter(this);
	}

	public ComboBox<Patient> getCboxPatients() {
		return cboxSelectPatient;
	}
	
	public ComboBox<Staff> getCboxStaff() {
		return cboxSelectStaff;
	}

	public Label getLblLoggedOnUser() {
		return lblLoggedOnUser;
	}

}
