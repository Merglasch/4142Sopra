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
	
	
	// gib handbuchid by modulid
	public List<Integer> findHandbuchid(int modulid, String abschluss, String studiengang, String pruefungsordnung){
		List<Integer> result = new LinkedList<Integer>();
		result = em.createNativeQuery("SELECT mh.handbuchid FROM Handbuchverwalter AS hv " +
				"JOIN Modulhandbuch AS mh ON hv.handbuchid = mh.handbuchid " +
				"   WHERE hv.modulid = ?  " +
				" AND mh.abschluss LIKE ? " +
				" AND mh.studiengang LIKE ? " +
				" AND mh.pruefungsordnung LIKE? ")
				.setParameter(1, ""+modulid)
				.setParameter(2,abschluss)
				.setParameter(3,studiengang)
				.setParameter(4,pruefungsordnung)
				.getResultList();
		
		return result;
	}
	
	public Modulhandbuch findById(int handbuchid){
		return em.createQuery("SELECT mhb FROM Modulhandbuch mhb WHERE mhb.handbuchid = :handbuchid",Modulhandbuch.class).setParameter("handbuchid", handbuchid).getSingleResult();
	}
	
	public List<Integer> findFachidByHandbuchidAndModulid(int handbuchid, int modulid){
		return em.createNativeQuery("SELECT fid FROM Handbuchverwalter  " +
				"  WHERE handbuchid = ? AND modulid = ?")
				.setParameter(1, handbuchid)
				.setParameter(2, modulid).getResultList();
	}
}
