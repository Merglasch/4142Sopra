package model.modules;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Fach;

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
		maxID=em.createQuery("SELECT MAX(f.fID) FROM Fach f",Integer.class).getResultList().get(0);
		int id=maxID+1;
		
		
		f.setFid(id);
		try{
			em.persist(f);
		}
		catch(Exception e){
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
			em.merge(f);
		}catch(Exception e){
			success=false;
			e.printStackTrace();
		}
		return success;
	}
	
}
