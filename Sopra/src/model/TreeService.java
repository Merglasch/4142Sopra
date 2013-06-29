package model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	public List<String> getAllAbschluss(){
		return em.createQuery("SELECT DISTINCT mh.abschluss FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	public List<String> getAllStudiengang(String abschluss){
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh WHERE mh.abschluss = :abschluss", String.class)
				.setParameter("abschluss", abschluss)
				.getResultList();
	}
	
}
