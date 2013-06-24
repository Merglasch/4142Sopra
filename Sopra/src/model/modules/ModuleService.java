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
	
	
	public Modul Modulsuche(String Studienabschluss, String Studiengang, String Pruefungsordnung, String Modulname){
		
		String search = "SELECT DISTINCT m FROM Modul m JOIN m.Modulhandbuch mh " +
				"WHERE abschluss = :abschluss AND studiengang = :studiengang AND pruefungsordnung = :pruefungsordnung AND modulname= :modulname";
		
		Modul tmp = em.createQuery(search, Modul.class).getSingleResult();
		int modulid = tmp.getModulid();
		Modul searchResult = em.find(Modul.class, modulid);
		return searchResult;
	}
	
	public boolean createModule(Modul m){
		List<Modul> resultList = em.createQuery("SELECT m FROM Modul m").getResultList();
		boolean moduleExists = false;
		for(Modul n : resultList){
			if (m.getModulname()==n.getModulname())
				moduleExists = true;
		}
		if (moduleExists==false){
			em.persist(m);
			return !moduleExists;
		}	
		else 
			return !moduleExists;
			
	}
	
	public void deleteModule(Modul m){
		em.remove(em.merge(m));
	}
	
}
