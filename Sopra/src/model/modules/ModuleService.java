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
	public List<Modul> Modulsuche(String abschluss, String studiengang, String pruefungsordnung, String modulname){
		List<Modul> resultList = new LinkedList<Modul>();
		//drei leer		
		if(abschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) 
			resultList.add(searchByName(modulname));
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
	
	
	//liefert -1 zurück falls das Modul schon existiert
	public int createModule(Modul m){
		int maxID = 0;
		maxID = em.createQuery("SELECT MAX(m.modulid) FROM Modul m", Integer.class).getResultList().get(0);
		int id = maxID+1;
		List<Modul> resultList = em.createQuery("SELECT m FROM Modul m", Modul.class).getResultList();
		boolean moduleExists = false;
		for(Modul n : resultList){
			if (m.getModulid()== n.getModulid())
				moduleExists = true;
		}
		if (moduleExists==false){
			m.setModulid(id);
			try{
				em.persist(m);	
			}catch(Exception e){
				e.printStackTrace();
			}
			return id;
		}	
		else 
			System.out.println("modul existiert bereits");
			return -1;
			
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
	
	public Modul searchByName(String name){
		
		return em.createQuery("SELECT m FROM Modul m WHERE m.modulname = :name", Modul.class)
				.setParameter("name", name)
				.getSingleResult();		
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
	
	public List<Modul> getMyModules(User u) {
		int uID = u.getUid();
		return em.createQuery("SELECT m FROM Modul WHERE m.uid = :uid",Modul.class)
		.setParameter("uid", uID)
		.getResultList();
	}
	
	
	public List<Modul> getMyModules(int uID) {
		
		List<Integer> hauptPersIds = em.createNativeQuery("SELECT hauptpers FROM Stellvertreter WHERE stv=?").setParameter(1, uID).getResultList();
		hauptPersIds.add(uID);
		List<Modul> myModules = new LinkedList<Modul>();
		for(int id : hauptPersIds){
			List<Modul> tmp = em.createQuery("SELECT m FROM Modul m WHERE m.uid = :uid",Modul.class) //// geaendert, diese version lauft =)
			.setParameter("uid", id)
			.getResultList();
			
			for(Modul t : tmp){
				myModules.add(t);
			}
		}
		return myModules;
	}
	
	
}
