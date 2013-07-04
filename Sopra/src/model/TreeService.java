package model;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;

@Stateless
public class TreeService {
	
	@PersistenceContext(name="SopraPU")
	EntityManager em;
	
	public List<String> getAllPruefungsordnung(String abschluss, String studiengang){	
		return em.createQuery("SELECT DISTINCT mh.pruefungsordnung FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss AND mh.studiengang = :studiengang", String.class)
				.setParameter("abschluss", abschluss)
				.setParameter("studiengang", studiengang)
				.getResultList();	
	}
	
	public List<String> getAllAbschluss(){
		return em.createQuery("SELECT DISTINCT mh.abschluss FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	public List<String> getAllStudiengang(String abschluss){
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss", String.class)
				.setParameter("abschluss", abschluss)
				.getResultList();
	}
	
	public List<String> getAllStudiengang(){
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	public List<String> getAllPruefungsordnung(){
		return em.createQuery("SELECT DISTINCT mh.pruefungsordnung FROM Modulhandbuch mh", String.class).getResultList();
	}

	public List<Fach> getFachTree(Modulhandbuch mh){
		int mhid = mh.getHandbuchid();
		List<Fach> resultList = new LinkedList<Fach>();
		List<Integer> fachIDs = em.createNativeQuery("SELECT fID FROM Handbuchverwalter WHERE handbuchID = ?")
								.setParameter(1, mhid)
								.getResultList();
		for(int id : fachIDs){
			resultList.add(em.find(Fach.class, id));
		}
		return resultList;
	}
	
	public List<Modul> getModulTree(Modulhandbuch mh, Fach f){
		int mhid=mh.getHandbuchid();
		int fachid=f.getFid();
		List<Modul> resultList = new LinkedList<Modul>();
		List<Integer> ModulIDs = em.createNativeQuery("SELECT modulID FROM Handbuchverwalter " +
				"WHERE handbuchID=? AND fID=?")
				.setParameter(1, mhid)
				.setParameter(2, fachid)
				.getResultList();
		
		for(int id : ModulIDs){
			resultList.add(em.find(Modul.class, id));
		}
		return resultList;
	}
	
}
