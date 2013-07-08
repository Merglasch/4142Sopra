package model.modules;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import model.HBVWtabellenausgabe;

@Stateless
public class ModulhandbuchService {
	
	@PersistenceContext(name="SopraPU")
	private EntityManager em;
	
	/**
	 * Sucht alle Modulhandbuecher, die zu dem uebergenenen Abschluss gehoeren.
	 * 
	 * @param abschluss
	 * @return Modulhandbuchliste
	 */
	public List<Modulhandbuch> searchByAbschluss(String abschluss){
		return em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.abschluss= :abschluss", Modulhandbuch.class)
				.setParameter("abschluss", abschluss)
				.getResultList();
	}
	
	/**
	 * Sucht alle Modulhandbuecher, die zu dem uebergenenen Studiengang gehoeren.
	 * 
	 * @param studiengang
	 * @return Modulhandbuchliste
	 */
	public List<Modulhandbuch> searchByStudiengang(String studiengang){
		return em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.studiengang= :studiengang", Modulhandbuch.class)
				.setParameter("studiengang", studiengang)
				.getResultList();
	}
	
	/**
	 * Sucht alle Modulhandbuecher, die zu der uebergenenen Pruefungsordnung gehoeren.
	 * 
	 * @param pruefungsordnung
	 * @return Modulhandbuchliste
	 */
	public List<Modulhandbuch> searchByPruefungsordnung(String pruefungsordnung){
		return em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.pruefungsordnung= :pruefungsordnung", Modulhandbuch.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
	}
	
	/**
	 *Sucht alle Modulhandbuecher, die zu dem uebergenenen Abschluss, Studiengang und der passenden Pruefungsordnung gehoeren.
	 * 
	 * @param pruefungsordnung
	 * @param studiengang
	 * @param abschluss
	 * @return Modulhandbuchliste
	 */
	public List<Modulhandbuch> search(String pruefungsordnung, String studiengang, String abschluss){		
		return em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.studiengang = :studiengang AND mh.abschluss = :abschluss", Modulhandbuch.class)
		.setParameter("pruefungsordnung", pruefungsordnung)
		.setParameter("studiengang", studiengang)
		.setParameter("abschluss", abschluss)
		.getResultList();
	}
	
	public boolean searchModulhandbuch(String pruefungsordnung, String studiengang, String abschluss){		
		if(em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.studiengang = :studiengang AND mh.abschluss = :abschluss", Modulhandbuch.class)
		.setParameter("pruefungsordnung", pruefungsordnung)
		.setParameter("studiengang", studiengang)
		.setParameter("abschluss", abschluss)
		.getResultList().isEmpty())
			return false;
		else
			return true;
	}
		
	/**
	 * Erstellt einen neuen Eintrag in der Handbuchverwaltertabelle, der einem Modulhandbuch ein Fach und ein Modul hinzufuegt.
	 * 
	 * @param modulid
	 * @param fachid
	 * @param handbuchid
	 * @return boolean, ob das Anlegen erfolgreich war
	 */
	//erzeugt einen neuen Eintrag im Handbuchverwalter
	public boolean insertIntoHandbuchverwalter(int modulid, int fachid, int handbuchid){
		int check=0;
		try{
		check=em.createNativeQuery("INSERT INTO Handbuchverwalter VALUES(?,?,?)")
		.setParameter(1, modulid)
		.setParameter(2, fachid)
		.setParameter(3, handbuchid)
		.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Fehler beim Schreiben der Datenbank");
		}
		
		if(check>0)
			return true;
		else
			return false;
	}
	
	/**
	 * Erstellt ein neues Modulhandbuch in der Datenbank.
	 * 
	 * @param Modulhandbuch
	 * @return ID des neuen Modulhandbuchs
	 */
	public int createModulhandbuch(Modulhandbuch mh){
		//Genertiert eine neue ID fürs Modulhandbuch
		int maxID=0;
		maxID = em.createQuery("SELECT MAX(mh.handbuchid) FROM Modulhandbuch mh", Integer.class)
		.getResultList()
		.get(0);
		int id=maxID+1;
		mh.setHandbuchid(id);
		em.persist(mh);
		
		return id;
	}
	
	/**
	 * Loescht einen oder mehrere Eintrag aus dem Handbuchverwalter.
	 * Wenn -1 als ID uebergeben wird wird die entsprechende ID auf eine Wildcard(%) gesetzt.
	 * 
	 * @param moduleID als String
	 * @param fachID als String
	 * @param handbuchID als String
	 */
	public void deleteHandbuchverwalter(String moduleID, String fachID, String handbuchID){
		if(moduleID.equals("-1"))
			moduleID="%";
		if(fachID.equals("-1"))
			fachID="%";
		if(handbuchID.equals("-1"))
			handbuchID="%";
		em.createNativeQuery("DELETE FROM Handbuchverwalter WHERE modulID LIKE ?1 AND fID LIKE ?2 AND handbuchID LIKE ?3")
		.setParameter(1, moduleID)
		.setParameter(2, fachID)
		.setParameter(3, handbuchID)
		.executeUpdate();
	}
	
	public void deleteByModuleID(int modulID){
		try{
			em.remove(em.merge(em.find(Modul.class, modulID)));
			em.createNativeQuery("DELETE FROM Handbuchverwalter WHERE modulID=:modulID")
			.setParameter("modulID", modulID)
			.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteByFachID(int fachID){
		try{
			em.remove(em.merge(em.find(Modul.class, fachID)));
			em.createNativeQuery("DELETE FROM Handbuchverwalter WHERE fID=:fachID")
			.setParameter("fachID", fachID)
			.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteByHandbuchID(int handbuchID){
		try{
			em.remove(em.merge(em.find(Modul.class, handbuchID)));
			em.createNativeQuery("DELETE FROM Handbuchverwalter WHERE handbuchID=:handbuchID")
			.setParameter("handbuchID", handbuchID)
			.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean deleteHandbuchverwalter(int modulID, int fachID, int handbuchID){
		boolean success = true;
		
		try{
			em.createNativeQuery("DELETE FROM Handbuchverwalter " +
					"WHERE modulID = ?1 AND fID = ?2 AND handbuchID = ?3")
					.setParameter(1, modulID)
					.setParameter(2, fachID)
					.setParameter(3, handbuchID)
					.executeUpdate();
		}catch(Exception e){
			success=false;
			e.printStackTrace();
		}
		return success;
	}
	
	public List<Modulhandbuch> getModulhandbuch(){
		return em.createQuery("Select mh FROM Modulhandbuch mh", Modulhandbuch.class).getResultList();
	}
	
	/**
	 * Gibt alle Eintraege aus der Handbuchverwaltertabelle zurueck.
	 * 
	 * @return Liste mit Handbuchverwaltereintraegen
	 */
	public List<HBVWtabellenausgabe> getAllHandbuchverwalter(){
		HBVWtabellenausgabe result = new HBVWtabellenausgabe();
		List<HBVWtabellenausgabe> resultList = new LinkedList<HBVWtabellenausgabe>();
		List<Integer> moduleID = em.createNativeQuery("SELECT modulID FROM Handbuchverwalter").getResultList();
		List<Integer> fachID = em.createNativeQuery("SELECT fID FROM Handbuchverwalter").getResultList();
		List<Integer> handbuchID = em.createNativeQuery("SELECT handbuchID FROM Handbuchverwalter").getResultList();

		while(!moduleID.isEmpty()){
			result.setModul(em.find(Modul.class, moduleID.get(0)));
			result.setFach(em.find(Fach.class, fachID.get(0)));
			result.setModulhandbuch(em.find(Modulhandbuch.class, handbuchID.get(0)));
			resultList.add(result);
			moduleID.remove(0);
			fachID.remove(0);
			handbuchID.remove(0);
		}
		return resultList;
	}
	
	/**
	 * Sucht zu gegebener ModulID,Pruefungsordnung, Abschluss und Studiengang die entsprechende HandbuchID.
	 * 
	 * @param modulid
	 * @param abschluss
	 * @param studiengang
	 * @param pruefungsordnung
	 * @return HandbuchIDList
	 */
	// gib handbuchid by modulid
		public List<Integer> findHandbuchid(int modulid, String abschluss, String studiengang, String pruefungsordnung){
			List<Integer> result = new LinkedList<Integer>();
			result = em.createNativeQuery("SELECT mh.handbuchid FROM Handbuchverwalter AS hv " +
					"JOIN Modulhandbuch AS mh ON hv.handbuchid = mh.handbuchid " +
					"   WHERE hv.modulid = ?  " +
					" AND mh.abschluss LIKE ? " +
					" AND mh.studiengang LIKE ? " +
					" AND mh.pruefungsordnung LIKE? ")
					.setParameter(1, ""+modulid)
					.setParameter(2,abschluss)
					.setParameter(3,studiengang)
					.setParameter(4,pruefungsordnung)
					.getResultList();
			
			return result;
		}
		
		/**
		* Gibt zu einer uebergebenen ID das entsprechende Handbuch zurueck.
		* 
		* @param handbuchid
		* @return Modulhandbuch
		*/
		public Modulhandbuch findById(int handbuchid){
			return em.createQuery("SELECT mhb FROM Modulhandbuch mhb WHERE mhb.handbuchid = :handbuchid",Modulhandbuch.class).setParameter("handbuchid", handbuchid).getSingleResult();
		}
		
		/**
		* Sucht zu einer Modul- und HandbuchID alle zugehoerigen Faecher.
		* 
		* @param handbuchid
		* @param modulid
		* @return FachIDList
		*/
		public List<Integer> findFachidByHandbuchidAndModulid(int handbuchid, int modulid){
			return em.createNativeQuery("SELECT fid FROM Handbuchverwalter  " +
					"  WHERE handbuchid = ? AND modulid = ?")
					.setParameter(1, handbuchid)
					.setParameter(2, modulid).getResultList();
		}
		
		public void deleteModulhandbuch(int handbuchID){
			em.remove(em.merge(em.find(Modulhandbuch.class, handbuchID)));
		}
	
	/**
	 * Aktualiesiert ein vorhandenen Tupel in der Modulhandbuch Tabelle.
	 * 
	 * @param Modulhandbuch
	 */
	public void updateModulhandbuch(Modulhandbuch m){
		em.merge(m);
	}
}
