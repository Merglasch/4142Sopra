package model.account;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.User;

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
		em.persist(u);
	}
	
	public void deleteUser(List<User> users) {
		for(User u : users){
			em.remove(em.merge(u));
		}
	}
	
	
	public List<User> getAllUsers() { 
		return em.createQuery("Select u FROM User u", User.class).getResultList();
	}
	
	public void updateUser(User u){
		em.merge(u);
	}
	
	
	
}