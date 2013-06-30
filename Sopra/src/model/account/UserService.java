package model.account;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Stellvertreter;
import klassenDB.StellvertreterPK;
import klassenDB.User;
import model.IDGenerator;

@Stateless
public class UserService {

	@PersistenceContext(name="SopraPU")
	private EntityManager em;
	
	
	
	public User login(String email, String passwort) {
		
		User tmp=null;
		try{
			tmp = (User) em.createQuery(
					"SELECT u FROM User u WHERE u.email = :email AND u.passwort = :passwort", User.class)
					.setParameter("email", email)
					.setParameter("passwort", passwort)
					.getSingleResult();
		}catch(javax.ejb.EJBException e){
			
		}catch(javax.persistence.NoResultException e){
			
		}
		return tmp;
	}
	
	public void createUser(User u) {
//		u.setUid(IDGenerator.getID()); // IDGen geht nicht =/
		
		int uid = em.createQuery("SELECT MAX(u.uid) FROM User u", Integer.class).getSingleResult().intValue();
		u.setUid(uid+1);
		
		
		em.persist(u);
	}
	
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
	
	public boolean setStellvertreter(User hauptPers, User stv){
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
	
	public void deleteUser(List<String> emailList) {
		for(String email: emailList){
			User u = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
			.setParameter("email", email)
			.getSingleResult();
			em.remove(em.merge(u));
		}
	}
	
	
	public List<User> getAllUsers() { 
		return em.createQuery("Select u FROM User u", User.class).getResultList();
	}
	
	public void updateUser(User u){
		em.merge(u);
	}

	public User getUser(String email) {
		User tmp = null;
		try{
			tmp = em.createQuery("Select u FROM User u Where u.email = :email",User.class).setParameter("email", email).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return tmp;
	}
	
}