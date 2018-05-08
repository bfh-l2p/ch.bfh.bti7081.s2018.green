package ch.bfh.bti7081.s2018.green.views;

import ch.bfh.bti7081.s2018.green.layouts.NavigationLayout;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;

public class NavigationView extends NavigationLayout implements View {

	public Button getBtnMedication() {
		return this.navBtnMedication;
	}

	public Button getBtnJournal() {
		return this.navBtnJournal;
	}

	public Button getBtnDiagnosis() {
		return this.navBtnDiagnosis;
	}

	public Button getBtnTherapy() {
		return this.navBtnTherapy;
	}
}
