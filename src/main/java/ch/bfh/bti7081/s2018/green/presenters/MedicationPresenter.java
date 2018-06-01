package ch.bfh.bti7081.s2018.green.presenters;

import ch.bfh.bti7081.s2018.green.presenters.interfaces.IClickableDivAddEvent;
import com.vaadin.ui.*;
import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.views.MedicationView;

public class MedicationPresenter implements IClickableDivAddEvent{

	private MedicationView view;
	private DataContainer data;

	public MedicationPresenter(MedicationView view) {
		this.view = view;
		this.data = DataContainer.getInstance();

        view.getMedicamentGrid().setSelectionMode(Grid.SelectionMode.SINGLE);

        // add edit button with event listener
        view.getMedicamentGrid().addComponentColumn(med -> {
            Button btn = new Button("View/Edit");
            btn.addClickListener(click -> {
                new MedicationPrescriptionPresenter(med, view);
            });
            return btn;
        });

        view.getMedicamentGrid().setRowHeight(40);

        // add listeners to tab menu
        HorizontalLayout btn1 = (HorizontalLayout) this.view.getLblAddMedication().getParent();
        addClickListener(btn1);
	}

	public void addClickListener (HorizontalLayout el) {
        el.addLayoutClickListener((clickEvent) ->
                setAction(el, "ASDF")
        );
    }

    @Override
    public void setAction(HorizontalLayout ly, String enumView) {
        new MedicationPrescriptionPresenter(null, view);
    }

}
