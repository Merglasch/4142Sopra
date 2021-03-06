package model.modules;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Fach;

/**
 * Dieser Service stellt alle Methoden zur Manipulation der Faecher bereit.
 * 
 */
@Stateless
public class FachService {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Legt ein neues Fach in der Datenbank an.
	 * 
	 * @param Fach
	 * @return ID des erstellten Fachs
	 */
	public int createFach(Fach f){
		int maxID=0;
		maxID=em.createQuery("SELECT MAX(f.fid) FROM Fach f",Integer.class).getResultList().get(0);
		int id=maxID+1;
		
		
		f.setFid(id);
		try{
			em.persist(f);
		}
		catch(Exception e){
			System.out.println("createFach fehlgeschlagen");
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * Aendert ein bereits existierendes Fach in der Datenbank.
	 * 
	 * @param Fach
	 * @return boolean, ob die Aktualisierung erfolgreich war
	 */
	public boolean changeFach(Fach f){
		boolean success=true;
		try{
			em.createNativeQuery("update fach set fach.fach = ? where fach.fid=?")
			.setParameter(1, f.getFach())
			.setParameter(2, f.getFid())
			.executeUpdate();
		}catch(Exception e){
			success=false;
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * 
	 * @return Liste aller Faecher
	 */
	public List<Fach> getAllFach(){
		return em.createQuery("SELECT f FROM Fach f", Fach.class).getResultList();
	}
	
	/**
	 * 
	 * @return Liste aller Fachnamen
	 */
	public List<String> getAllFachNames(){
		return em.createNativeQuery("SELECT DISTINCT f.fach FROM Fach f").getResultList();
	}
	
	
//	// gib fachid anhand modulid
//	public List<Integer> findFachid(int modulid){
//		List<Integer> result = new LinkedList<Integer>();
//		em.createNativeQuery("SELECT f.fid FROM Fach AS f JOIN Handbuchverwalter AS hv " +
//				" ON f.fid = hv.handbuchid  WHERE hv.modulid= :modulid").setParameter("modulid", modulid).getResultList();
//		
//		return result;
//	}
	
	/**
	 * Liefert zu gegebener FachID das Fach zurueck.
	 * 
	 * @param FachID
	 * @return Fach
	 */
	public Fach findById(int fid){
		return em.createQuery("SELECT f FROM Fach f WHERE f.fid = :fid", Fach.class).setParameter("fid", fid).getSingleResult();
		}
	
	/**
	*loescht faecher
	*/
	public void deleteFach(int fachID){
		em.remove(em.merge(em.find(Fach.class, fachID)));
	}

	/**
	 * Diese Methode gibt das Fach zu einer gegebenen ID zurueck.
	 * 
	 * @param FachID
	 * @return Fach
	 */
	public Fach getFach(int id){
		return em.find(Fach.class, id);
	}

}
