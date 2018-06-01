package ch.bfh.bti7081.s2018.green.views.dummyValueGenerators;

import ch.bfh.bti7081.s2018.green.models.entities.Patient;
import ch.bfh.bti7081.s2018.green.models.managers.PatientManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PatDummy {

    public static Patient getPatDummy () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pmsDB");
        EntityManager em = emf.createEntityManager();

        PatientManager patMgr = new PatientManager();
        return patMgr.get(2);
    }
}
