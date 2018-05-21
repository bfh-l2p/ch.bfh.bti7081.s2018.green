package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;

public class MedicationManager extends Manager<Medication> {
    public MedicationManager() {
		this.entityclass = Medication.class;
	}
}
