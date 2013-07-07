package model.account;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Stellvertreter;
import klassenDB.StellvertreterPK;
import klassenDB.User;

@Stateless
public class StellvertreterService {
	
	@PersistenceContext(name="SopraPU")
	private EntityManager em;

	/**
	 * 
	 * @param StellvertreterID
	 * @return Die Hauptperson zu gegebenem Stellvertreter
	 */
	public List<Integer> getHauptPers(int stvid){
		return em.createNativeQuery("SELECT hauptpers FROM Stellvertreter WHERE stv=?")
				.setParameter(1, stvid)
				.getResultList();
	}
	
	/**
	 * 
	 * @param Hauptperson
	 * @return Alle Stellvertreter der Hauptperson
	 */
	public List<User> getStellvertreter(User u){
		int hauptPers = u.getUid();
		List<Integer> stvIDs = em.createQuery("SELECT stv FROM Stellvertreter WHERE hauptPers = ?", Integer.class)
				.setParameter(1, hauptPers)
				.getResultList();
		List<User> resultList = new LinkedList<User>();
		for(int stvID : stvIDs){
			resultList.add(em.createQuery("SELECT u FROM User u WHERE u.id = :stvID", User.class)
					.setParameter("stvID", stvID)
					.getSingleResult());
		}
		return resultList;
	}
	
	/**
	 * Fuegt einer gegebenen Person einen Stellvertreter hinzu.
	 * 
	 * @param Hauptperson
	 * @param Stellvertreter
	 * @return boolean, ob die Transaktion erfolgreich war
	 */
	public boolean setStellvertreter(User hauptPers, User stv){
		System.out.println("Methode stellvertreter");
		boolean success = true;
		try{
			StellvertreterPK tmp = new StellvertreterPK();
			tmp.setHauptpers(hauptPers.getUid());
			tmp.setStv(stv.getUid());
			Stellvertreter result = new Stellvertreter();
			result.setId(tmp);
			em.persist(result);
		}catch(Exception e){	
			success = false;
			e.printStackTrace();
		}
		return success;
	}
	
}
