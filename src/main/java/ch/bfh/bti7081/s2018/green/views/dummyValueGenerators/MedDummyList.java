package ch.bfh.bti7081.s2018.green.views.dummyValueGenerators;

import ch.bfh.bti7081.s2018.green.models.entities.Medication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class MedDummyList {

    public static List<Medication> buildDummyMedList () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();

        Medication med = em.find(Medication.class, 1);
        List<Medication> medDummyList = new ArrayList<>();
        medDummyList.add(med);
        return medDummyList;
    }
}
