package model.account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import klassenDB.User;

@Stateless
public class UserService {

	@PersistenceContext(name="SopraPU")
	private EntityManager em;
	
	
	
	public User login(String email, String passwort) {
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("SopraPU");
		//EntityManager em = emf.createEntityManager();
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
	
	public void createUser() {
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("SopraPU");
		//EntityManager em = emf.createEntityManager();
		User u = new User(7321, "nacht@tag.de", "himmegugga", "Nachter","Hallo",1 , "Tag");
		em.persist(u);
	}
	/*
	 * public List<User> getAllUsers() { return
	 * em.createQuery("Select u FROM User u").getResultList(); }
	 * 
	 * public void addUser(User u){ em.persist(u); }
	 * 
	 * public void removeUser(User u){ em.remove(em.merge(u)); }
	 */
}