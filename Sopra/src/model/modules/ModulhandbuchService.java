package model.modules;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Modulhandbuch;

@Stateless
public class ModulhandbuchService {
	
	@PersistenceContext(name="SopraPU")
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
	
	public List<Modulhandbuch> search(String pruefungsordnung, String studiengang, String abschluss){		
		return em.createQuery("SELECT mh FROM Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung AND mh.studiengang = :studiengang AND mh.abschluss = :abschluss", Modulhandbuch.class)
		.setParameter("pruefungsordnung", pruefungsordnung)
		.setParameter("studiengang", studiengang)
		.setParameter("abschluss", abschluss)
		.getResultList();
	}
		
	//erzeugt einen neuen Eintrag im Handbuchverwalter
	public boolean insertIntoHandbuchverwalter(int modulid, int fachid, int handbuchid){
		int check=0;
		try{
		check=em.createNativeQuery("INSERT INTO Handbuchverwalter VALUES(?,?,?)")
		.setParameter(1, modulid)
		.setParameter(2, fachid)
		.setParameter(3, handbuchid)
		.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Fehler beim Schreiben der Datenbank");
		}
		
		if(check>0)
			return true;
		else
			return false;
	}
	
	public int createModulhandbuch(Modulhandbuch mh){
		//Genertiert eine neue ID fürs Modulhandbuch
		int maxID=0;
		maxID = em.createQuery("SELECT MAX(mh.handbuchid) FROM Modulhandbuch mh", Integer.class)
		.getResultList()
		.get(0);
		int id=maxID+1;
		mh.setHandbuchid(id);
		em.persist(mh);
		
		return id;
	}
	
	public List<Modulhandbuch> getModulhandbuch(){
		return em.createQuery("Select mh FROM Modulhandbuch mh", Modulhandbuch.class).getResultList();
	}
	
}
