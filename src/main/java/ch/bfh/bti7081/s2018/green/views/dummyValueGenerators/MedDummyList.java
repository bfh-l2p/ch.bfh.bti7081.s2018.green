package ch.bfh.bti7081.s2018.green.views.dummyValueGenerators;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;
import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.managers.MedicationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class MedDummyList {

    public static List<Medication> buildDummyMedList (Patient pat) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();

        MedicationManager mmgr = new MedicationManager();

        List<Medication> medOfPatient = mmgr.findBy(pat);
        return medOfPatient;

        /*Medication med = em.find(Medication.class, 1);
        List<Medication> medDummyList = new ArrayList<>();
        medDummyList.add(med);
        return medDummyList;*/
    }
}
