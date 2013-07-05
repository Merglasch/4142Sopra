package model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.modules.ModulhandbuchService;

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
	
	public List<Fach> getAllFach(){
		return em.createQuery("SELECT f FROM Fach f", Fach.class).getResultList();
	}

	public List<String> getAllFachNames(){
		return em.createNativeQuery("SELECT f.fach FROM Fach f").getResultList();
	}
	
	public List<String> getAllAbschluss(){
		return em.createQuery("SELECT DISTINCT mh.abschluss FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	public List<String> getAllAktAbschluss(){
		Timestamp maxZeitstempel = em.createQuery("SELECT MAX(mh.zeitstempel) FROM Modulhandbuch mh", Timestamp.class).getResultList().get(0);
		return em.createQuery("SELECT DISTINCT mh.abschluss FROM Modulhandbuch mh WHERE mh.zeitstempel = :zeitstempel", String.class)
		.setParameter("zeitstempel", maxZeitstempel).getResultList();
	}
	
	public List<String> getAllStudiengang(String abschluss){
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss", String.class)
				.setParameter("abschluss", abschluss)
				.getResultList();
	}
	
	public List<String> getAllAktStudiengang(String abschluss){
		Timestamp maxZeitstempel = em.createQuery("SELECT MAX(mh.zeitstempel) FROM Modulhandbuch mh",Timestamp.class).getResultList().get(0);
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh " +
				"WHERE mh.zeitstempel=:zeitstempel AND mh.abschluss = :abschluss", String.class)
				.setParameter("zeitstempel", maxZeitstempel)
				.setParameter("abschluss", abschluss)
				.getResultList();
	}
	
	public List<String> getAllStudiengang(){
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	public List<String> getAllPruefungsordnung(){
		return em.createQuery("SELECT DISTINCT mh.pruefungsordnung FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	public List<String> getAllAktPruefungsordnung(String abschluss, String studiengang){
		Timestamp maxZeitstempel = em.createQuery("SELECT MAX(mh.timestamp) FROM Modulhandbuch mh", Timestamp.class).getResultList().get(0);
		return em.createQuery("SELECT DISTINCT mh.pruefungsordnung FROM Modulhandbuch mh " +
				"WHERE mh.zeitstempel = :zeitstempel AND mh.abschluss = :abschluss AND mh.studiengang = :studiengang", String.class)
				.setParameter("zeitstempel", maxZeitstempel)
				.setParameter("abschluss", abschluss)
				.setParameter("studiengang", studiengang)
				.getResultList();
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
	
	public List<Modulhandbuch> getAllAktModulhandbuch (String pruefungsordnung, String studiengang, String abschluss){
		List<Modulhandbuch> allHandbuch = new ModulhandbuchService().search(pruefungsordnung, studiengang, abschluss);
		Modulhandbuch AktModulhandbuch = allHandbuch.get(0);
		for(Modulhandbuch mh : allHandbuch){
			if(mh.getZeitstempel().after(AktModulhandbuch.getZeitstempel()))
				AktModulhandbuch = mh;
		}
		List<Modulhandbuch> resultList = new LinkedList<Modulhandbuch>();
		resultList.add(AktModulhandbuch);
		return resultList;
	}
	
	public List<Modul> getAllAktModules(Fach f, Modulhandbuch mh){
		//Zu jedem Namen von Modulen den maximalen Zeitstempel suchen
		List<Timestamp> maxZeitstempel = em.createQuery("SELECT MAX(m.zeitstempel) FROM Modul m GROUP BY m.name", Timestamp.class).getResultList();
		
		//ModulIDs zu gegebenem Handbuch und Fach suchen
		int mhid=mh.getHandbuchid();
		int fachid=f.getFid();
		List<Integer> ModulIDs = em.createNativeQuery("SELECT modulID FROM Handbuchverwalter " +
				"WHERE handbuchID=? AND fID=?")
				.setParameter(1, mhid)
				.setParameter(2, fachid)
				.getResultList();
		
		//Modulliste aus Treffern erstellen
		List<Modul> moduleList = new LinkedList<Modul>();
		for(int id : ModulIDs){
			moduleList.add(em.find(Modul.class, id));
		}
		
		//Module mit passendem Zeitstempel(aktuelle Module) suchen
		List<Modul> resultList = new LinkedList<Modul>();
		
		for(Modul m : moduleList){
			for(Timestamp ts : maxZeitstempel){
				if(m.getZeitstempel().equals(ts))
					resultList.add(m);
			}
		}
		
		return resultList;
	}
	
}
