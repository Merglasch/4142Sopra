package model.account;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.User;

/**
 * Dieser Service stellt alle Datenbankmethoden zur Verfuegung, die auf die User-Tabelle zugreifen.
 *
 */
@Stateless
public class UserService {

	@PersistenceContext(name="SopraPU")
	private EntityManager em;
	
	
	/**
	 * Gleicht die eingegebenen Benutzerdaten mit der Datenbank ab.
	 * 
	 * @param email
	 * @param passwort
	 * @return Den eingeloggten benutzer
	 */
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
	
	/**
	 * Legt einen Datenbankeintrag fuer den uebergebenen Benutzer an.
	 * 
	 * @param Der Benutzer, der angelegt werden soll
	 */
	public void createUser(User u) {
		int uid=0;
		uid = em.createQuery("SELECT MAX(u.uid) FROM User u", Integer.class).getSingleResult().intValue();
		u.setUid(uid+1);
		
		
		em.persist(u);
	}
	
	/**
	 *Liefert einen Benutzer zu einer gegebenen ID zurueck.
	 *
	 *@param UserID
	 */	public User getUserById(int id){
		return em.find(User.class, id);
	}
	
	/**
	 * Loescht alle ausgewaehlten Benutzer aus der Datenbank.
	 * 
	 * @param emailList
	 */
	public void deleteUser(List<String> emailList) {
		for(String email: emailList){
			User u = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
			.setParameter("email", email)
			.getSingleResult();
			em.remove(em.merge(u));
		}
	}
	
	/**
	 * 
	 * @return Gibt alle in der Datenbank vorhandenen Benutzer zurueck.
	 */
	public List<User> getAllUsers() { 
		return em.createQuery("Select u FROM User u", User.class).getResultList();
	}
	
	/**
	 * Methode um Benutzereintraege in der Datenbank zu aktualisieren.
	 * 
	 * @param Der Benutzer, der aktualisiert werden soll
	 */
	public void updateUser(User u){
		System.out.println("************************************************************\nMETHODEUPDATE USER");
		em.merge(u);
	}

	/**
	 * 
	 * @param email
	 * @return Den zur email passenden Benutzer
	 */
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