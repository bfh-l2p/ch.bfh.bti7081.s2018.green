package ch.bfh.bti7081.s2018.green.presenters;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.views.MedicationView;

public class MedicationPresenter {

	private MedicationView view;
	private DataContainer data;

	public MedicationPresenter(MedicationView view) {
		this.view = view;
		this.data = DataContainer.getInstance();

        view.getMedicamentGrid().addSelectionListener(event -> {
            Notification.show("You have clicked on element");
        });

        SingleSelectionModel<Medication> selectionModel = (SingleSelectionModel<Medication>) view.getMedicamentGrid().setSelectionMode(Grid.SelectionMode.SINGLE);
        selectionModel.addSingleSelectionListener(event -> {
            Notification.show(selectionModel.getSelectedItem().get().getName());
        });
	}
}
