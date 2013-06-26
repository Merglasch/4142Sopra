package model.modules;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;

import klassenDB.Modul;

@Stateless
public class ModuleService {
	
	@PersistenceContext
	EntityManager em;
	
	
	public List<Modul> Modulsuche(String studienabschluss, String studiengang, String pruefungsordnung, String modulname){
	//TODO Modulsuche
		List<Modul> resultList = null;
		
		if(studienabschluss.equals("Alles auswählen")&&studiengang.equals("Alles auswählen")&&pruefungsordnung.equals("Alles auswählen"))
			resultList.add(searchByName(modulname));
	}
	
	public boolean createModule(Modul m){
		List<Modul> resultList = em.createQuery("SELECT m FROM Modul m", Modul.class).getResultList();
		boolean moduleExists = false;
		for(Modul n : resultList){
			if (m.getModulname().equals(n.getModulname()))
				moduleExists = true;
		}
		if (moduleExists==false){
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
		
		return em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studiengang = :studiengang ", Modul.class)
					.setParameter("studiengang", studiengang)
					.getResultList();
		
	}
	
	public List<Modul> searchByPruefungsordnung(String pruefungsordnung){
		
		return em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung ", Modul.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		
	}
	
	public List<Modul> searchByAbschluss(String abschluss){
		
		return em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.abschluss = :abschluss ", Modul.class)
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
	
}
