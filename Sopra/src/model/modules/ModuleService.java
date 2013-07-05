package model.modules;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import klassenDB.User;

@Stateless
public class ModuleService {
	
	@PersistenceContext(name="SopraPU")
	private EntityManager em;
	
	
	//Start Modulsuche
	public List<Modul> aktModulsuche(String abschluss, String studiengang, String pruefungsordnung, String modulname){
		List<Modul> resultList = new LinkedList<Modul>();
		List<Modul> aktModules = getAktModules();
		//drei leer		
		if(abschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) 
			resultList.add(aktSearchByName(modulname));
		else if(studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
			resultList = aktSearchByAbschluss(abschluss);			
		}		
		else if(abschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&modulname.equals("")) {
			resultList = aktSearchByPruefungsordnung(pruefungsordnung);
		}	
		else if(abschluss.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
			resultList = aktSearchByStudiengang(studiengang);
		} //zwei leer
		else if(abschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")) {
			
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
			return aktFilter(resultList);
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
			return  aktFilter(resultList);
		}
		else if(studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) {
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
			return aktFilter(resultList);
		}
		else if(abschluss.equals("Alles auswaehlen")&&modulname.equals("")) {
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
			return aktFilter(resultList);
		}
		else if(studiengang.equals("Alles auswaehlen")&&modulname.equals("")) {
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
			return aktFilter(resultList);
		}
		else if(pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
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
			return aktFilter(resultList);
		} //eins leer
		else if(abschluss.equals("Alles auswaehlen")) {
			
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
			return aktFilter(resultList);
		}
		else if(pruefungsordnung.equals("Alles auswaehlen")) {
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
			return aktFilter(resultList);
		}
		else if(modulname.equals("Alles auswaehlen")) {
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
			return aktFilter(resultList);
		}
		else if(studiengang.equals("Alles auswaehlen")) {
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
			return aktFilter(resultList);
		}
		else {
			resultList = getAllModules();
		}
		return aktFilter(resultList);
	}
	
	/*******************************
	***** Ende Modulsuche **********
	*********************************/
	
	
	//liefert -1 zurück falls das Modul schon existiert
	public int createModule(Modul m){
		int id=0;
		try{
			id =  em.createQuery("SELECT MAX(m.modulid) FROM Modul m", Integer.class).getSingleResult().intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		id ++;
		System.out.println("CREATE METHODE: MODULID neu = "+id);
//		List<Modul> resultList = em.createQuery("SELECT m FROM Modul m", Modul.class).getResultList();
			m.setModulid(id);
			try{
				em.persist(m);	
				return id;
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Fehler");
				return -1;
			}
			
	}
	
	public void deleteModule(List<Modul> moduleList){
		for(Modul m : moduleList){
			em.remove(em.merge(m));
		}
	}
	
	public void updateModule(Modul m){
		em.merge(m);
	}
	
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
	
	public Modul aktSearchByName(String name){
		List<Modul> aktModules = getAktModules();
		for(Modul aktModul : aktModules){
			if(aktModul.getModulname().equals(name))
				return aktModul;
		}
		return null;
	}

	public List<Modul> oldSearchByName(String name){
		return altFilter(searchByName(name)); // methode kommt beim mergen hinzu
	}

	
	public Modul searchByModulid(int id){
		return em.find(Modul.class, id);
	}
	
	
	public List<Modul> getAllModules(){
		return em.createQuery("Select m FROM Modul m", Modul.class).getResultList();
	}	
	
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
	
	public List<Modul> getMyModules(User u) {
		int uID = u.getUid();
		return em.createQuery("SELECT m FROM Modul WHERE m.uid = :uid",Modul.class)
		.setParameter("uid", uID)
		.getResultList();
	}
	
	
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
	public List<Modul> getMyModulesAlt(int uid) {
		
		List<Integer> hauptPersIds = em.createNativeQuery("SELECT hauptpers FROM Stellvertreter WHERE stv=?").setParameter(1, uid).getResultList();
		hauptPersIds.add(uid);
		List<Modul> myModules = new LinkedList<Modul>();
		for(int id : hauptPersIds){
			List<Modul> tmp = em.createQuery("SELECT m FROM Modul m WHERE m.uid = :uid",Modul.class) //// geaendert, diese version lauft =)
					.setParameter("uid", id)
					.getResultList();
			
			for(Modul t : tmp){
				myModules.add(t);
			}
		}
		return altFilter(myModules);
	}
	
	
	
	public List<Modul> getAktModules(){// leerzeichen in querra vergessen -.-
		System.out.println("############### Methode getAktModules1");
		List<Integer> ids = em.createNativeQuery("select modulid"+
				" from modul"+
				" where zeitstempel IN"+
					" (select  MAX(Zeitstempel) AS zeitstempel"+
					" from MODUL"+
					" group by  modulname)").getResultList(); // klammer vergessen am ende der querry -.-
		
		
		System.out.println("############### Methode getAktModules2");
		List<Modul> modulList =new LinkedList<Modul>();
		for(int id : ids){
			List<Modul> mm = em.createQuery("SELECT m FROM Modul m WHERE m.modulid = :modulid", Modul.class)
					.setParameter("modulid", id)
					.getResultList();
			for(Modul m : mm){
				modulList.add(m);
			}
		}
		System.out.println("############### END Methode getAktModules");
		return modulList;
	}
	
	public List<Modul> getOldModules(){ // leerzeichen in querra vergessen -.-
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
	
	public List<Modul> searchByName(String name){
			
			return em.createQuery("SELECT m FROM Modul m WHERE m.modulname = :name", Modul.class)
					.setParameter("name", name)
					.getResultList();		
	}
		
	public List<Modul> searchPublicModules(){
		return em.createQuery("SELECT m FROM Modul m WHERE freiverantwortlicher=1 AND freikoordinator=1", Modul.class)
		.getResultList();
		
	}
	
	//Start Modulsuche
		public List<Modul> Modulsuche(String abschluss, String studiengang, String pruefungsordnung, String modulname){
			List<Modul> resultList = new LinkedList<Modul>();
			//drei leer		
			if(abschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) 
			{	
				List<Modul> moduleList = searchByName(modulname);
				for(Modul m : moduleList){
					resultList.add(m);
				}
			}		
			else if(studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
				resultList = searchByAbschluss(abschluss);			
			}		
			else if(abschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&modulname.equals("")) {
				resultList = searchByPruefungsordnung(pruefungsordnung);
			}	
			else if(abschluss.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
				resultList = searchByStudiengang(studiengang);
			} //zwei leer
			else if(abschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")) {
				
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
			else if(studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) {
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
			else if(abschluss.equals("Alles auswaehlen")&&modulname.equals("")) {
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
			else if(studiengang.equals("Alles auswaehlen")&&modulname.equals("")) {
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
			else if(pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
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
			else if(abschluss.equals("Alles auswaehlen")) {
				
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
			else if(pruefungsordnung.equals("Alles auswaehlen")) {
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
			else if(modulname.equals("Alles auswaehlen")) {
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
			else if(studiengang.equals("Alles auswaehlen")) {
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
