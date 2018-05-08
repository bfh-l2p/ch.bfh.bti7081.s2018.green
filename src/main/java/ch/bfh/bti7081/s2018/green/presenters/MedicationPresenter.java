package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.views.MedicationView;

public class MedicationPresenter {

	private MedicationView view;
	private DataContainer data;
	
	public MedicationPresenter(MedicationView view, DataContainer data) {		
		this.view = view;
		this.data = data;
	}
}
