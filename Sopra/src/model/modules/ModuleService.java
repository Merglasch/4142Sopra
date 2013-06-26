package model.modules;

import java.util.Calendar;
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
	
	
	public Modul Modulsuche(String Studienabschluss, String Studiengang, String Pruefungsordnung, String Modulname){
		
		String search = "SELECT DISTINCT m FROM Modul m JOIN m.Modulhandbuch mh " +
				"WHERE abschluss = :abschluss AND studiengang = :studiengang AND pruefungsordnung = :pruefungsordnung AND modulname= :modulname";
		
		Modul tmp = em.createQuery(search, Modul.class).getSingleResult();
		int modulid = tmp.getModulid();
		Modul searchResult = em.find(Modul.class, modulid);
		return searchResult;
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
	//TODO deleteModule		
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
