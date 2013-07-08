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

/**
 * Dieser Service stellt alle noetigen Datenbankmethoden zur Darstellung der Baeume bereit. 
 *
 */
@Stateless
public class TreeService {
	
	@PersistenceContext(name="SopraPU")
	EntityManager em;
	
	/**
	 * Gibt alle Pruefungsordnungen zu einem gegebenen Studiengang und Abschluss zurueck.
	 * 
	 * @param Abschluss
	 * @param Studiengang
	 * @return Pruefungsordnungen
	 */
	public List<String> getAllPruefungsordnung(String abschluss, String studiengang){	
		return em.createQuery("SELECT DISTINCT mh.pruefungsordnung FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss " +
				"AND mh.studiengang = :studiengang", String.class)
				.setParameter("abschluss", abschluss)
				.setParameter("studiengang", studiengang)
				.getResultList();	
	}
	

	/**
	 * Liefert alle in der Datenbank enthaltenen Abschluesse zurueck.
	 * 
	 * @return Abschlussliste
	 */
	public List<String> getAllAbschluss(){
		return em.createQuery("SELECT DISTINCT mh.abschluss FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	/**
	 * Liest alle veroeffentlichten und aktuellen Abschluesse aus der Datenbank aus.
	 * 
	 * @return aktuelle Abschlussliste
	 */
	public List<String> getAllAktAbschluss(){
		Timestamp maxZeitstempel = em.createQuery("SELECT MAX(mh.zeitstempel) FROM Modulhandbuch mh", Timestamp.class).getResultList().get(0);
		return em.createQuery("SELECT DISTINCT mh.abschluss FROM Modulhandbuch mh WHERE mh.zeitstempel = :zeitstempel AND mh.veroeffentlicht=1", String.class)
		.setParameter("zeitstempel", maxZeitstempel).getResultList();
	}
	
	/**
	 * Gibt alle in der Datenbank vorhandenen Studiengaenge zurueck.
	 * 
	 * @param abschluss
	 * @return Studiengangliste
	 */
	public List<String> getAllStudiengang(String abschluss){
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss", String.class)
				.setParameter("abschluss", abschluss)
				.getResultList();
	}
	
	/**
	 * Gibt alle veroeffentlichen und aktuellen Studiengaenge aus der Datenbank zurueck.
	 * 
	 * @param abschluss
	 * @return aktuelle Studiengangliste
	 */
	public List<String> getAllAktStudiengang(String abschluss){
		Timestamp maxZeitstempel = em.createQuery("SELECT MAX(mh.zeitstempel) FROM Modulhandbuch mh",Timestamp.class).getResultList().get(0);
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh " +
				"WHERE mh.zeitstempel=:zeitstempel AND mh.abschluss = :abschluss AND mh.veroeffentlicht=1", String.class)
				.setParameter("zeitstempel", maxZeitstempel)
				.setParameter("abschluss", abschluss)
				.getResultList();
	}
	
	/**
	 * Gibt eine Liste aller Studiengaenge zurueck, die in der Datenbank sind.
	 * 
	 * @return Studiengangliste
	 */
	public List<String> getAllStudiengang(){
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	/**
	 * Gibt alle Pruefungsordnungen aus der Datenbank zurueck.
	 * 
	 * @return Liste aller Pruefungsordnungen
	 */
	public List<String> getAllPruefungsordnung(){
		return em.createQuery("SELECT DISTINCT mh.pruefungsordnung FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	/**
	 * Gibt alle aktuellen Pruefungsordnungen zu einem gegebenen Abschluss und Studiengang zurueck, die in einem veroeffentlichten Modulhanbuch aufgefuehrt sind.
	 * 
	 * @param abschluss
	 * @param studiengang
	 * @return alle aktuellen Pruefungsordnungen
	 */
	public List<String> getAllAktPruefungsordnung(String abschluss, String studiengang){
		Timestamp maxZeitstempel = em.createQuery("SELECT MAX(mh.zeitstempel) FROM Modulhandbuch mh", Timestamp.class).getResultList().get(0);
		return em.createQuery("SELECT DISTINCT mh.pruefungsordnung FROM Modulhandbuch mh " +
				"WHERE mh.zeitstempel = :zeitstempel AND mh.abschluss = :abschluss AND mh.studiengang = :studiengang AND mh.veroeffentlicht=1", String.class)
				.setParameter("zeitstempel", maxZeitstempel)
				.setParameter("abschluss", abschluss)
				.setParameter("studiengang", studiengang)
				.getResultList();
	}

	/**
	 * Gibt alle zu einem Modulhandbuch gehoerenden Faecher zurueck.
	 * 
	 * @param Modulhandbuch
	 * @return Fachliste
	 */
	public List<Fach> getFachTree(Modulhandbuch mh){
		int mhid = mh.getHandbuchid();
		List<Fach> resultList = new LinkedList<Fach>();
		List<Integer> fachIDs = em.createNativeQuery("SELECT DISTINCT fID FROM Handbuchverwalter WHERE handbuchID = ?")
								.setParameter(1, mhid)
								.getResultList();
		for(int id : fachIDs){
			resultList.add(em.find(Fach.class, id));
		}
		return resultList;
	}
	
	/**
	 * Gibt alle zu einem Modulhandbuch und Fach gehoerenden Module zurueck.
	 * 
	 * @param Modulhandbuch
	 * @param Fach
	 * @return Modulliste
	 */
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
	
	/**
	 * Gibt alle aktuellen und veroeffentlichten Modulhandbuecher zurueck.
	 * 
	 * @param pruefungsordnung
	 * @param studiengang
	 * @param abschluss
	 * @return Modulhandbuchliste
	 */
	public List<Modulhandbuch> getAllAktModulhandbuch(String pruefungsordnung, String studiengang, String abschluss){
		List<Modulhandbuch> allHandbuch = search(pruefungsordnung, studiengang, abschluss);
		Modulhandbuch AktModulhandbuch = allHandbuch.get(0);
		for(Modulhandbuch mh : allHandbuch){
			if(mh.getVeroeffentlicht()==1){
				if(mh.getZeitstempel().after(AktModulhandbuch.getZeitstempel()))
					AktModulhandbuch = mh;
			}	
		}
		List<Modulhandbuch> resultList = new LinkedList<Modulhandbuch>();
		resultList.add(AktModulhandbuch);
		return resultList;
	}
	
	/**
	 * Gibt alle Modulhandbuecher zu einer gegebenen pruefungsordnung, einem gegebenen studiengang und abschluss zurueck.
	 * 
	 * @param pruefungsordnung
	 * @param studiengang
	 * @param abschluss
	 * @return Modulhandbuchliste
	 */
	public List<Modulhandbuch> search(String pruefungsordnung, String studiengang, String abschluss){		
		return em.createQuery("SELECT mh FROM Modulhandbuch mh " +
				"WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.studiengang = :studiengang AND mh.abschluss = :abschluss " +
				"ORDER BY mh.zeitstempel DESC", Modulhandbuch.class)
		.setParameter("pruefungsordnung", pruefungsordnung)
		.setParameter("studiengang", studiengang)
		.setParameter("abschluss", abschluss)
		.getResultList();
	}
	
	/**
	 * Liefert alle Module zu gegebenem Modulhandbuch und gegebenem Fach zurueck.
	 * 
	 * @param Fach
	 * @param Modulhandbuch
	 * @return Modulliste
	 */
	public List<Modul> getAllAktModules(Fach f, Modulhandbuch mh){
		//Zu jedem Namen von Modulen den maximalen Zeitstempel suchen
		List<Timestamp> maxZeitstempel = em.createQuery("SELECT MAX(m.zeitstempel) FROM Modul m GROUP BY m.modulname", Timestamp.class).getResultList();
		
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
