package model.modules;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import model.IDGenerator;

@Stateless
public class ModuleService {
	
	@PersistenceContext(name="SopraPU")
	private EntityManager em;
	
	
	public List<Modul> Modulsuche(String studienabschluss, String studiengang, String pruefungsordnung, String modulname){
		List<Modul> resultList = new LinkedList<Modul>();
		//drei leer		
		if(studienabschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) 
			resultList.add(searchByName(modulname));
		else if(studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
			resultList = searchByPruefungsordnung(studienabschluss);			
		}		
		else if(studienabschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&modulname.equals("")) {
			resultList = searchByPruefungsordnung(pruefungsordnung);
		}	
		else if(studienabschluss.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
			resultList = searchByStudiengang(studiengang);
		} //zwei leer
		else if(studienabschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.pruefungsordnung = :pruefungsordnung and m.modulname = :modulname", Modul.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(studienabschluss.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studiengang = :studiengang and m.modulname = :modulname", Modul.class)
				.setParameter("studiengang", studiengang)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studienabschluss = :studienabschluss and m.modulname = :modulname", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(studienabschluss.equals("Alles auswaehlen")&&modulname.equals("")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studiengang = :studiengang and m.pruefungsordnung = :pruefungsordnung", Modul.class)
				.setParameter("studiengang", studiengang)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		}
		else if(studienabschluss.equals("Alles auswaehlen")&&modulname.equals("")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studiengang = :studiengang and m.pruefungsordnung = :pruefungsordnung", Modul.class)
				.setParameter("studiengang", studiengang)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		}
		else if(studiengang.equals("Alles auswaehlen")&&modulname.equals("")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studienabschluss = :studienabschluss and m.pruefungsordnung = :pruefungsordnung", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		}
		else if(pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studienabschluss = :studienabschluss and m.studiengang = :studiengang", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("studiengang", studiengang)
				.getResultList();
		} //eins leer
		else if(studienabschluss.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studiengang = :studiengang and m.pruefungsordnung = :pruefungsordnung"
				+"and m.modulname = :modulname", Modul.class)
				.setParameter("studiengang", studiengang)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(pruefungsordnung.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studiengang = :studiengang and m.studienabschluss = :studienabschluss"
				+"and m.modulname = :modulname", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("studiengang", studiengang)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(modulname.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.studiengang = :studiengang and m.studienabschluss = :studienabschluss"
				+"and m.pruefungsordnung = :pruefungsordnung", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("studiengang", studiengang)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		}
		else if(studiengang.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m WHERE m.pruefungsordnung = :pruefungsordnung and m.studienabschluss = :studienabschluss"
				+"and m.modulname = :modulname", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else {
			resultList = getAllModules();
		}
		return resultList;
	}
	
	public boolean createModule(Modul m){
		List<Modul> resultList = em.createQuery("SELECT m FROM Modul m", Modul.class).getResultList();
		boolean moduleExists = false;
		for(Modul n : resultList){
			if (m.getModulname().equals(n.getModulname()))
				moduleExists = true;
		}
		if (moduleExists==false){
			m.setModulid(new IDGenerator().getID());
			em.persist(m);				
			return !moduleExists;
		}	
		else 
			return !moduleExists;
			
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
		
		return em.createQuery("SELECT m FROM Modul m WHERE m.studiengang = :studiengang ", Modul.class)
					.setParameter("studiengang", studiengang)
					.getResultList();		
	}
	
	public List<Modul> searchByPruefungsordnung(String pruefungsordnung){
		
		return em.createQuery("SELECT m FROM Modul m WHERE m.pruefungsordnung = :pruefungsordnung ", Modul.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();		
	}
	
	public List<Modul> searchByAbschluss(String abschluss){
		
		return em.createQuery("SELECT m FROM Modul m WHERE m.abschluss = :abschluss ", Modul.class)
				.setParameter("abschluss", abschluss)
				.getResultList();		
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
		List<Integer> modulIds = em.createNativeQuery("SELECT modulid FROM Handbuchverwalter WHERE handbuchid = ?", Integer.class)
				.setParameter(1, mhid)
				.getResultList();
		List<Modul> resultList = new LinkedList<Modul>();
		for(int modulId : modulIds){
			Modul m = em.find(Modul.class, modulId);
			if(m.getVeroeffentlicht() == 1)
				resultList.add(m);
		}
		return resultList;
	}
	
	
	
}
