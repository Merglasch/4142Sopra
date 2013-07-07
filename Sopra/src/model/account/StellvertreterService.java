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

	public List<Integer> getHauptPers(int stvid){
		return em.createNativeQuery("SELECT hauptpers FROM Stellvertreter WHERE stv=?")
				.setParameter(1, stvid)
				.getResultList();
	}
	
	public List<User> getStellvertreter(User u){
		int hauptPers = u.getUid();
		List<Integer> stvIDs = em.createNativeQuery("SELECT stv FROM Stellvertreter WHERE hauptPers = ?")
				.setParameter(1, hauptPers)
				.getResultList();
		List<User> resultList = new LinkedList<User>();
		for(int stvID : stvIDs){
			resultList.add(em.createQuery("SELECT u FROM User u WHERE u.uid = :stvID", User.class)
					.setParameter("stvID", stvID)
					.getSingleResult());
		}
		return resultList;
	}
	
	public boolean isStellvertreter(User hauptPers, User stv){
		int hauptPersID= hauptPers.getUid();
		int stvID = stv.getUid();
		if(em.createNativeQuery("SELECT * FROM Stellvertreter WHERE stv=? AND hauptPers=?")
				.setParameter(1, stvID)
				.setParameter(2, hauptPersID)
				.getResultList()
				.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean deleteStellvertreter(User hauptPers, User stv){
		int stvID = stv.getUid();
		int hauptPersID = hauptPers.getUid();
		boolean success = true;
		try{
			em.createNativeQuery("DELETE FROM Stellvertreter WHERE stv=? AND hauptPers=?")
			.setParameter(1, stvID)
			.setParameter(2, hauptPersID)
			.executeUpdate();
		} catch(Exception e){
			success=false;
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean setStellvertreter(User hauptPers, User stv){
		boolean success = true;
		if(!isStellvertreter(hauptPers, stv)){
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
		}
		return success;
	}
	
}
