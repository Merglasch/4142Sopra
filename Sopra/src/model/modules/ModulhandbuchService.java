package model.modules;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Modulhandbuch;

@Stateless
public class ModulhandbuchService {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Modulhandbuch> searchByAbschluss(String abschluss){
		return em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.abschluss= :abschluss", Modulhandbuch.class)
				.setParameter("abschluss", abschluss)
				.getResultList();
	}
	
	public List<Modulhandbuch> searchByStudiengang(String studiengang){
		return em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.studiengang= :studiengang", Modulhandbuch.class)
				.setParameter("studiengang", studiengang)
				.getResultList();
	}
	
	public List<Modulhandbuch> searchByPruefungsordnung(String pruefungsordnung){
		return em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.pruefungsordnung= :pruefungsordnung", Modulhandbuch.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
	}
	
	public Modulhandbuch search(String pruefungsordnung, String studiengang, String abschluss){
		Modulhandbuch tmp = null;
		try{
		tmp = em.createQuery("SELECT mh FROM Modulhandbuch WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.studiengang = :studiengang AND mh.abschluss = :abschluss", Modulhandbuch.class)
		.setParameter("pruefungsordnung", pruefungsordnung)
		.setParameter("studiengang", studiengang)
		.setParameter("abschluss", abschluss)
		.getSingleResult();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return tmp;
	}
	
}
