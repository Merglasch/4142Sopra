package model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TreeService {
	
	@PersistenceContext
	EntityManager em;
	
	public List<String> getAllPruefungsordnung(){	
		return em.createQuery("SELECT DISTINCT mh.pruefungsordnung FROM Modulhandbuch mh", String.class).getResultList();	
	}
	
	public List<String> getAllAbschluss(){
		return em.createQuery("SELECT DISTINCT mh.abschluss FROM Modulhandbuch mh", String.class).getResultList();
	}
	
	public List<String> getAllStudiengang(){
		return em.createQuery("SELECT DISTINCT mh.studiengang FROM Modulhandbuch mh", String.class).getResultList();
	}
	
}
