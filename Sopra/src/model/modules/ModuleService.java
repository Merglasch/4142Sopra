package model.modules;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.HBVWtabellenausgabe;

import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import klassenDB.User;

@Stateless
public class ModuleService {
	
	@PersistenceContext(name="SopraPU")
	private EntityManager em;
	
	/**
	 * Liest alle Module zu gegebenem Abschluss, Studiengang, Pruefungsordnung und Modulnamen aus.
	 * 
	 * @param abschluss
	 * @param studiengang
	 * @param pruefungsordnung
	 * @param modulname
	 * @return Modulliste
	 */
	//Start Modulsuche
	public List<Modul> aktModulsuche(String abschluss, String studiengang, String pruefungsordnung, String modulname){
		List<Modul> result = new LinkedList<Modul>();;
		
		if(abschluss.equals("alles")){
		abschluss = "%";
		}
		if(studiengang.equals("alles")){
			studiengang = "%";
		}
		if(pruefungsordnung.equals("alles")){
			pruefungsordnung = "%";
		}
		
		String s = "%";
		if(modulname.isEmpty()||modulname.equals("")){
			//nothing
		}else{
			s += modulname +"%";
		}
		
		
		List<Modul> tmp = em.createQuery("SELECT m FROM Modul m " +
				" WHERE m.modulname LIKE :modulname ",Modul.class)
				.setParameter("modulname", s)
				.getResultList();
//		List<Integer> modulids = em.createQuery("SELECT modulid FROM Handbuchverwalter AS hv  " +
//				" JOIN Modulhandbuch AS mh ON hv.handbuchid = mh.handbuchid" +
//				" WHERE mh.abschluss LIKE :abschluss" +
//				" AND mh.studiengang LIKE :studiengang " +
//				" AND mh.pruefungsordnung LIKE :pruefungsordnung ")
//				.setParameter("abschluss", abschluss)
//				.setParameter("studiengang", studiengang)
//				.setParameter("pruefungsordnung",pruefungsordnung)
//				.getResultList();
//		List<Integer> modulids = em.createQuery("SELECT DISTINCT hv.modulid FROM Handbuchverwalter AS hv   " +
//				" WHERE hv.handbuchid IN " +
//				" (SELECT hb.handbuchid " +
//				"FROM Modulhandbuch hb " +
//				"WHERE hb.abschluss LIKE :abschluss" +
//				" AND hb.studiengang LIKE :studiengang " +
//				" AND hb.pruefungsordnung LIKE :pruefungsordnung )",Integer.class)
//				.setParameter("abschluss", abschluss)
//				.setParameter("studiengang", studiengang)
//				.setParameter("pruefungsordnung",pruefungsordnung)
//				.getResultList();
		
		System.out.println("MODULSUCHE: Modname= "+s+"    abschluss= "+abschluss+"       studiengang= "+studiengang+"      " +
				" pruefungsordnung= "+pruefungsordnung);
		
//		for(Modul m: tmp){
//			for(int id : modulids){
//				if(m.getModulid()==id){
//					result.add(m);
//				}
//				
//			}
//		}
		
		
		return aktFilter(tmp);
		
	}
	
	
	
	
	
	
	
	
	
	
	
//	public List<Modul> aktModulsuche(String abschluss, String studiengang, String pruefungsordnung, String modulname){
//		List<Modul> resultList = new LinkedList<Modul>();
//		List<Modul> aktModules = getAktModules();
//		//drei leer		
//		if(abschluss.equals("alles")&&studiengang.equals("alles")&&pruefungsordnung.equals("alles")) 
//			resultList.add(aktSearchByName(modulname));
//		else if(studiengang.equals("alles")&&pruefungsordnung.equals("alles")&&modulname.equals("")) {
//			resultList = aktSearchByAbschluss(abschluss);			
//		}		
//		else if(abschluss.equals("alles")&&studiengang.equals("alles")&&modulname.equals("")) {
//			resultList = aktSearchByPruefungsordnung(pruefungsordnung);
//		}	
//		else if(abschluss.equals("alles")&&pruefungsordnung.equals("alles")&&modulname.equals("")) {
//			resultList = aktSearchByStudiengang(studiengang);
//		} //zwei leer
//		else if(abschluss.equals("alles")&&studiengang.equals("alles")) {
//			
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung", Integer.class)
//					.setParameter("pruefungsordnung", pruefungsordnung)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname", Modul.class)
//								.setParameter("modulid", modulID)
//								.setParameter("modulname", modulname)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		}
//		else if(abschluss.equals("alles")&&pruefungsordnung.equals("alles")) {
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.studiengang = :studiengang", Integer.class)
//					.setParameter("studiengang", studiengang)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname", Modul.class)
//								.setParameter("modulid", modulID)
//								.setParameter("modulname", modulname)
//								.getSingleResult());
//			}
//			return  aktFilter(resultList);
//		}
//		else if(studiengang.equals("alles")&&pruefungsordnung.equals("alles")) {
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss", Integer.class)
//					.setParameter("abschluss", abschluss)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname", Modul.class)
//								.setParameter("modulid", modulID)
//								.setParameter("modulname", modulname)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		}
//		else if(abschluss.equals("alles")&&modulname.equals("")) {
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.studiengang = :studiengang", Integer.class)
//					.setParameter("pruefungsordnung", pruefungsordnung)
//					.setParameter("studiengang", studiengang)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid", Modul.class)
//								.setParameter("modulid", modulID)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		}
//		else if(studiengang.equals("alles")&&modulname.equals("")) {
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.abschluss = :abschluss", Integer.class)
//					.setParameter("pruefungsordnung", pruefungsordnung)
//					.setParameter("abschluss", abschluss)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid", Modul.class)
//								.setParameter("modulid", modulID)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		}
//		else if(pruefungsordnung.equals("alles")&&modulname.equals("")) {
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss AND mh.studiengang = :studiengang", Integer.class)
//					.setParameter("abschluss", abschluss)
//					.setParameter("studiengang", studiengang)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid", Modul.class)
//								.setParameter("modulid", modulID)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		} //eins leer
//		else if(abschluss.equals("alles")) {
//			
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung " +
//					"AND mh.studiengang = :studiengang", Integer.class)
//					.setParameter("pruefungsordnung", pruefungsordnung)
//					.setParameter("studiengang", studiengang)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname" , Modul.class)
//								.setParameter("modulid", modulID)
//								.setParameter("modulname", modulname)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		}
//		else if(pruefungsordnung.equals("alles")) {
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss " +
//					"AND mh.studiengang = :studiengang", Integer.class)
//					.setParameter("abschluss", abschluss)
//					.setParameter("studiengang", studiengang)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname" , Modul.class)
//								.setParameter("modulid", modulID)
//								.setParameter("modulname", modulname)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		}
//		else if(modulname.equals("alles")) {
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung " +
//					"AND mh.abschluss = :abschluss AND mh.studiengang = :studiengang", Integer.class)
//					.setParameter("pruefungsordnung", pruefungsordnung)
//					.setParameter("studiengang", studiengang)
//					.setParameter("abschluss", abschluss)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid", Modul.class)
//								.setParameter("modulid", modulID)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		}
//		else if(studiengang.equals("alles")) {
//			List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung " +
//					"AND mh.abschluss = :abschluss", Integer.class)
//					.setParameter("pruefungsordnung", pruefungsordnung)
//					.setParameter("abschluss", abschluss)
//					.getResultList();
//			
//			List<Integer> modulIDs = new LinkedList<Integer>();
//			for(int handbuchID : handbuchIDs){
//				modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
//				.setParameter(1, handbuchID)
//				.getSingleResult().toString()));
//			}
//			
//			for(int modulID : modulIDs){
//				resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname" , Modul.class)
//								.setParameter("modulid", modulID)
//								.setParameter("modulname", modulname)
//								.getSingleResult());
//			}
//			return aktFilter(resultList);
//		}
//		else {
//			resultList = getAllModules();
//		}
//		return aktFilter(resultList);
//	}
//	
	/*******************************
	***** Ende Modulsuche **********
	*********************************/
	
	/**
	 * Erstellt eine neue ID und fuegt das uebergebene Modul mit dieser ID in die Datenbank ein.
	 * 
	 * @param Modul
	 * @return ID des neuen Moduls
	 */
	//liefert -1 zurück falls das Modul schon existiert
	public int createModule(Modul m){
		//neue ID generieren
		int maxID = 0;
		maxID = em.createQuery("SELECT MAX(m.modulid) FROM Modul m", Integer.class).getResultList().get(0);
		int id = maxID+1;

		//weißt dem neuen Modul die generierte id zu
		m.setModulid(id);
		try{
			//schreibt das Modul in die Datenbank
			em.persist(m);	
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
				
	}
	
	/**
	 * Loescht alle Module aus der uebergebenen Liste aus der Datenbank.
	 * 
	 * @param moduleList
	 */
	public void deleteModule(List<Modul> moduleList){
		for(Modul m : moduleList){
			em.remove(em.merge(m));
		}
	}
	
	/**
	 * Aktualisiert das uebergebene Modul in der Datenbank.
	 * 
	 * @param Modul
	 */
	public void updateModule(Modul m){
		em.merge(m);
	}
	
	/**
	 * Gibt alle Module des uebergebenen Studiengangs zurueck.
	 * 
	 * @param studiengang
	 * @return Modulliste
	 */
	public List<Modul> searchByStudiengang(String studiengang){
		
		List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.studiengang = :studiengang ", Integer.class)
					.setParameter("studiengang", studiengang)
					.getResultList();
		
		List<Integer> modulIDs = new LinkedList<Integer>();
		for(int handbuchID : handbuchIDs){
			modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
			.setParameter(1, handbuchID)
			.getSingleResult().toString()));
		}
		
		List<Modul> resultList= new LinkedList<Modul>();
		for(int modulID : modulIDs){
			resultList.add(em.find(Modul.class, modulID));
		}
		return resultList;
	}
	
	/**
	 * Gibt alle aktuellen Module des uebergebenen Studiengangs zurueck.
	 * 
	 * @param studiengang
	 * @return Modulliste
	 */
	public List<Modul> aktSearchByStudiengang(String studiengang){
		
		List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.studiengang = :studiengang ", Integer.class)
				.setParameter("studiengang", studiengang)
				.getResultList();
		List<Modul> aktModules = getAktModules();
		List<Modul> resultList= new LinkedList<Modul>();
		
		for(int handbuchID : handbuchIDs){
			int modID=Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString());
			
			for(Modul m : aktModules){
				if(m.getModulid()== modID){
					resultList.add(m);
				}
			}
		}
		
		return resultList;
	}
	
	/**
	 * Gibt alle aktuellen Module der uebergebenen Pruefungsordnung zurueck.
	 * 
	 * @param pruefungsordnung
	 * @return Modulliste
	 */
	public List<Modul> aktSearchByPruefungsordnung(String pruefungsordnung){
		
		List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung ", Integer.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		
		List<Modul> aktModules = getAktModules();
		List<Modul> resultList = new LinkedList<Modul>();
		for(int handbuchID : handbuchIDs){
			int modulID = Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
			.setParameter(1, handbuchID)
			.getSingleResult().toString());
			
			for(Modul m : aktModules){
				if(m.getModulid()==modulID){
					resultList.add(m);
				}
			}
			
		}
		return resultList;
	}
	
	/**
	 * Gibt alle aktuellen Module des uebergebenen Abschlusses zurueck.
	 * 
	 * @param abschluss
	 * @return Modulliste
	 */
	public List<Modul> aktSearchByAbschluss(String abschluss){
		
		List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss ", Integer.class)
				.setParameter("abschluss", abschluss)
				.getResultList();
		
		List<Modul> aktModule = new LinkedList<Modul>();
		List<Modul> resultList= new LinkedList<Modul>();
		
		for(int handbuchID : handbuchIDs){
			int modID = Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
			.setParameter(1, handbuchID)
			.getSingleResult().toString());
			
			for(Modul m : aktModule){
				if(m.getModulid()==modID){
					resultList.add(m);
				}
			}
			
		}
		
		return resultList;	
	}
	
	/**
	 * Gibt das aktuelle Modul zum uebergebenen Namen zurueck.
	 * 
	 * @param name
	 * @return Modul
	 */
	public Modul aktSearchByName(String name){
		List<Modul> aktModules = getAktModules();
		for(Modul aktModul : aktModules){
			if(aktModul.getModulname().equals(name))
				return aktModul;
		}
		return null;
	}

	/**
	 * Gibt alle alten Versionen von Modulen zu dem uebergebenen Namen zurueck.
	 * 
	 * @param name
	 * @return 
	 */
	public List<Modul> oldSearchByName(String name){
		return altFilter(searchByName(name)); // methode kommt beim mergen hinzu
	}

	/**
	 * Gibt zu einer gegebenen ID das passende Modul aus der Datenbank zurueck.
	 * 
	 * @param ModulID
	 * @return Modul
	 */
	public Modul searchByModulid(int id){
		return em.find(Modul.class, id);
	}
	
	/**
	 * Gibt alle Module aus der Datenbank zuerueck.
	 * 
	 * @return Modulliste
	 */
	public List<Modul> getAllModules(){
		return em.createQuery("Select m FROM Modul m ORDER BY m.zeitstempel DESC", Modul.class).getResultList();
	}	
	
	/**
	 * Gibt alle zu dem uebergebenen Modulhandbuch gehoerenden Module zurueck.
	 * 
	 * @param Modulhandbuch
	 * @return Modulliste
	 */
	public List<Modul> searchByModulhandbuch(Modulhandbuch mh){
		int mhid = mh.getHandbuchid();
		List<Integer> modulIds = em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
				.setParameter(1, mhid)
				.getResultList();
		List<Modul> resultList = new LinkedList<Modul>();
		for(int modulId : modulIds){
			Modul m = em.find(Modul.class, modulId);
//			if(m.getVeroeffentlicht() == 1) // auskommentiert, da kein veroeffentlicht mehr =)
//				resultList.add(m);
		}
		return resultList;
	}
	
	/**
	 * Gibt alle zu dem uebergebenen Modulhandbuch gehoerenden aktuellen Module zurueck.
	 * 
	 * @param Modulhandbuch
	 * @return Modulliste
	 */
	public List<Modul> aktSearchByModulhandbuch(Modulhandbuch mh){
		int mhid = mh.getHandbuchid();
		List<Integer> modulIds = em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
				.setParameter(1, mhid)
				.getResultList();
		
		List<Modul> resultList = new LinkedList<Modul>();
		List<Modul> aktModule = getAktModules();
		for(int i : modulIds){
			for(Modul m : aktModule){
				if(m.getModulid()==i){
					resultList.add(m);
				}
			}
			
		}
		return resultList;
	}
	
	/**
	 * Gibt alle vom uebergebenen User erstellten Module zurueck.
	 * 
	 * @param User
	 * @return Modulliste
	 */
	public List<Modul> getMyModules(User u) {
		int uID = u.getUid();
		return em.createQuery("SELECT m FROM Modul WHERE m.uid = :uid",Modul.class)
		.setParameter("uid", uID)
		.getResultList();
	}
	
	/**
	 * Gibt alle von einem User erstellten aktuellen Module anhand einer uebergebenen UserID zurueck.
	 * 
	 * @param UserID
	 * @return Modulliste
	 */
	public List<Modul> getMyModulesAktuell(int uid) {
		System.out.println("## GetMyModulesAktuell");
		
		List<Integer> hauptPersIds = em.createNativeQuery("SELECT hauptpers FROM Stellvertreter WHERE stv=?").setParameter(1, uid).getResultList();
		hauptPersIds.add(uid);
		List<Modul> myModules = new LinkedList<Modul>();
		for(int id : hauptPersIds){
			List<Modul> tmp = em.createQuery("SELECT m FROM Modul m WHERE m.uid = :uid",Modul.class) //// geaendert, diese version lauft =)
			.setParameter("uid", id)
			.getResultList();
			
			for(Modul t : tmp){
				myModules.add(t);
				System.out.println("Modul: "+ t.getModulname() + "  " + t.getZeitstempel());
			}
		}
		System.out.println("## END  GetMyModulesAktuell");
		return aktFilter(myModules);
	}
	
	/**
	 * Gibt alle von einem User erstellten alten Module anhand einer uebergebenen UserID zurueck.
	 * 
	 * @param UserID
	 * @return Modulliste
	 */
	public List<Modul> getMyModulesAlt(int uid) {
		
		List<Integer> hauptPersIds = em.createNativeQuery("SELECT hauptpers FROM Stellvertreter WHERE stv=?").setParameter(1, uid).getResultList();
		hauptPersIds.add(uid);
		List<Modul> myModules = new LinkedList<Modul>();
		for(int id : hauptPersIds){
			List<Modul> tmp = em.createQuery("SELECT m FROM Modul m WHERE m.uid = :uid",Modul.class)
					.setParameter("uid", id)
					.getResultList();
			
			for(Modul t : tmp){
				myModules.add(t);
			}
		}
		return altFilter(myModules);
	}
	
	/**
	 * Liefert alle aktuellen Module zurueck.
	 * 
	 * @return Modulliste
	 */
	public List<Modul> getAktModules(){// leerzeichen in querra vergessen -.-
		List<Integer> ids = em.createNativeQuery("select modulid"+
				" from modul"+
				" where zeitstempel IN"+
					" (select  MAX(Zeitstempel) AS zeitstempel"+
					" from MODUL"+
					" group by  modulname)").getResultList(); // klammer vergessen am ende der querry -.-
		
		
		List<Modul> modulList =new LinkedList<Modul>();
		for(int id : ids){
			List<Modul> mm = em.createQuery("SELECT m FROM Modul m WHERE m.modulid = :modulid", Modul.class)
					.setParameter("modulid", id)
					.getResultList();
			for(Modul m : mm){
				modulList.add(m);
			}
		}
		return modulList;
	}
	
	/**
	 * Liefert alle alten Module zurueck.
	 * 
	 * @return Modulliste
	 */
	public List<Modul> getOldModules(){
		List<Integer> ids = em.createNativeQuery(
				"select modulid"+
						" from modul"+
						" where zeitstempel NOT IN"+
						" (select  MAX(Zeitstempel) AS zeitstempel"+
						" from MODUL"+
				" group by  modulname)").getResultList();
		
		List<Modul> modulList =new LinkedList<Modul>();
		for(int id : ids){
			List<Modul> mm = em.createQuery("SELECT m FROM Modul m WHERE m.modulid = :modulid", Modul.class)
					.setParameter("modulid", id)
					.getResultList();
			for(Modul m : mm){
				modulList.add(m);
			}
		}
		return modulList;
		
		
	}
	
	/**
	 * Gibt alle zu der uebergebenen Pruefungsordnung gehoerenden Module zurueck.
	 * 
	 * @param pruefungsordnung
	 * @return Modulliste
	 */
	public List<Modul> searchByPruefungsordnung(String pruefungsordnung){
		
		List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung ", Integer.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		
		List<Integer> modulIDs = new LinkedList<Integer>();
		for(int handbuchID : handbuchIDs){
			modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
			.setParameter(1, handbuchID)
			.getSingleResult().toString()));
		}
		
		List<Modul> resultList= new LinkedList<Modul>();
		for(int modulID : modulIDs){
			resultList.add(em.find(Modul.class, modulID));
		}
		return resultList;
	}
	
	/**
	 * Gibt alle zu einem uebergebenen Abschluss gehoerenden Module zurueck.
	 * 
	 * @param abschluss
	 * @return Modulliste
	 */
	public List<Modul> searchByAbschluss(String abschluss){
		
		List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss ", Integer.class)
				.setParameter("abschluss", abschluss)
				.getResultList();
		
		List<Integer> modulIDs = new LinkedList<Integer>();
		for(int handbuchID : handbuchIDs){
			modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
			.setParameter(1, handbuchID)
			.getSingleResult().toString()));
		}
		
		List<Modul> resultList= new LinkedList<Modul>();
		for(int modulID : modulIDs){
			resultList.add(em.find(Modul.class, modulID));
		}
		return resultList;		
	}
	
	/**
	 * Gibt alle zu einem uebergebenen Namen gehoerenden Module zurueck.
	 * 
	 * @param name
	 * @return Modulliste
	 */
	public List<Modul> searchByName(String name){
			
			return em.createQuery("SELECT m FROM Modul m WHERE m.modulname = :name", Modul.class)
					.setParameter("name", name)
					.getResultList();		
	}
	
	/**
	 * Gibt alle vom Modulverantwortlichen und Koordinator freigegebenen Module zurueck.
	 * 
	 * @return Modulliste
	 */
	public List<Modul> searchPublicModules(){
		return em.createQuery("SELECT m FROM Modul m WHERE m.freiVerantwortlicher=1 AND m.freiKoordinator=1", Modul.class)
		.getResultList();
		
	}
	
	/**
	 * Die Modulsuche gibt zu einem uebergebenen Abschluss, Studiengang, Pruefungsordnung und Namen alle passenden Module zurueck.
	 * Falls nicht alle Felder ausgefuellt wurden filtert er entsprechend nur nach den ausgefuellten Informationen und falls kein Feld
	 * ausgefuellt ist liefert sie einfach alle Module zurueck.
	 * 
	 * @param abschluss
	 * @param studiengang
	 * @param pruefungsordnung
	 * @param modulname
	 * @return Modulliste
	 */
	//Start Modulsuche
		public List<Modul> Modulsuche(String abschluss, String studiengang, String pruefungsordnung, String modulname){
			List<Modul> resultList = new LinkedList<Modul>();
			//drei leer		
			if(abschluss.equals("alles")&&studiengang.equals("alles")&&pruefungsordnung.equals("alles")) 
			{	
				List<Modul> moduleList = searchByName(modulname);
				for(Modul m : moduleList){
					resultList.add(m);
				}
			}		
			else if(studiengang.equals("alles")&&pruefungsordnung.equals("alles")&&modulname.equals("")) {
				resultList = searchByAbschluss(abschluss);			
			}		
			else if(abschluss.equals("alles")&&studiengang.equals("alles")&&modulname.equals("")) {
				resultList = searchByPruefungsordnung(pruefungsordnung);
			}	
			else if(abschluss.equals("alles")&&pruefungsordnung.equals("alles")&&modulname.equals("")) {
				resultList = searchByStudiengang(studiengang);
			} //zwei leer
			else if(abschluss.equals("alles")&&studiengang.equals("alles")) {
				
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung", Integer.class)
						.setParameter("pruefungsordnung", pruefungsordnung)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname", Modul.class)
									.setParameter("modulid", modulID)
									.setParameter("modulname", modulname)
									.getSingleResult());
				}
				return resultList;
			}
			else if(abschluss.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) {
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.studiengang = :studiengang", Integer.class)
						.setParameter("studiengang", studiengang)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname", Modul.class)
									.setParameter("modulid", modulID)
									.setParameter("modulname", modulname)
									.getSingleResult());
				}
				return resultList;
			}
			else if(studiengang.equals("alles")&&pruefungsordnung.equals("alles")) {
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss", Integer.class)
						.setParameter("abschluss", abschluss)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname", Modul.class)
									.setParameter("modulid", modulID)
									.setParameter("modulname", modulname)
									.getSingleResult());
				}
				return resultList;
			}
			else if(abschluss.equals("alles")&&modulname.equals("")) {
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.studiengang = :studiengang", Integer.class)
						.setParameter("pruefungsordnung", pruefungsordnung)
						.setParameter("studiengang", studiengang)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid", Modul.class)
									.setParameter("modulid", modulID)
									.getSingleResult());
				}
				return resultList;
			}
			else if(studiengang.equals("alles")&&modulname.equals("")) {
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.abschluss = :abschluss", Integer.class)
						.setParameter("pruefungsordnung", pruefungsordnung)
						.setParameter("abschluss", abschluss)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid", Modul.class)
									.setParameter("modulid", modulID)
									.getSingleResult());
				}
				return resultList;
			}
			else if(pruefungsordnung.equals("alles")&&modulname.equals("")) {
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss AND mh.studiengang = :studiengang", Integer.class)
						.setParameter("abschluss", abschluss)
						.setParameter("studiengang", studiengang)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid", Modul.class)
									.setParameter("modulid", modulID)
									.getSingleResult());
				}
				return resultList;
			} //eins leer
			else if(abschluss.equals("alles")) {
				
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung " +
						"AND mh.studiengang = :studiengang", Integer.class)
						.setParameter("pruefungsordnung", pruefungsordnung)
						.setParameter("studiengang", studiengang)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname" , Modul.class)
									.setParameter("modulid", modulID)
									.setParameter("modulname", modulname)
									.getSingleResult());
				}
				return resultList;
			}
			else if(pruefungsordnung.equals("alles")) {
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss " +
						"AND mh.studiengang = :studiengang", Integer.class)
						.setParameter("abschluss", abschluss)
						.setParameter("studiengang", studiengang)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname" , Modul.class)
									.setParameter("modulid", modulID)
									.setParameter("modulname", modulname)
									.getSingleResult());
				}
				return resultList;
			}
			else if(modulname.equals("alles")) {
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung " +
						"AND mh.abschluss = :abschluss AND mh.studiengang = :studiengang", Integer.class)
						.setParameter("pruefungsordnung", pruefungsordnung)
						.setParameter("studiengang", studiengang)
						.setParameter("abschluss", abschluss)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid", Modul.class)
									.setParameter("modulid", modulID)
									.getSingleResult());
				}
				return resultList;
			}
			else if(studiengang.equals("alles")) {
				List<Integer> handbuchIDs = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung " +
						"AND mh.abschluss = :abschluss", Integer.class)
						.setParameter("pruefungsordnung", pruefungsordnung)
						.setParameter("abschluss", abschluss)
						.getResultList();
				
				List<Integer> modulIDs = new LinkedList<Integer>();
				for(int handbuchID : handbuchIDs){
					modulIDs.add(Integer.parseInt(em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?")
					.setParameter(1, handbuchID)
					.getSingleResult().toString()));
				}
				
				for(int modulID : modulIDs){
					resultList.add(em.createQuery("SELECT m FROM Modul m WHERE modulid = :modulid AND modulname = :modulname" , Modul.class)
									.setParameter("modulid", modulID)
									.setParameter("modulname", modulname)
									.getSingleResult());
				}
				return resultList;
			}
			else {
				resultList = getAllModules();
			}
			return resultList;
		}
		
		/*******************************
		***** Ende Modulsuche **********
		*********************************/
		
	
	
	/**
	 * Gibt die Liste aller aktuellen Module zurueck.
	 * 
	 * @param Liste aller Module
	 * @return Modulliste aller aktuellen Module
	 */
	public List<Modul> aktFilter(List<Modul> allesListe){
		List<Modul> aktErg = new LinkedList<Modul>();
		List<Modul> aktModules = getAktModules();
		for(Modul m : allesListe){
			for(Modul mAkt : aktModules){
				if(m.getModulid() == mAkt.getModulid()){
					aktErg.add(mAkt);
				}
			}
		}
		return aktErg;
	}
	
	/**
	 * Gibt die Liste aller alten Module zurueck.
	 * 
	 * 
	 * @param Liste aller Module
	 * @return Modullilste aller alten Module
	 */
	public List<Modul> altFilter(List<Modul> allesListe){
		List<Modul> aktErg = new LinkedList<Modul>();
		List<Modul> altModules = getOldModules();
		for(Modul m : allesListe){
			for(Modul mAlt : altModules){
				if(m.getModulid() == mAlt.getModulid()){
					aktErg.add(mAlt);
				}
			}
		}
		return aktErg;
	}
	
	
	
	
	
}
