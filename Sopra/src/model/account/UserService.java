package model.account;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

import klassenDB.User;

public class UserService {

	//@PersistenceContext
	//private EntityManagerFactory emf;

	//@Resource
	//private UserTransaction utx;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User login(String email, String passwort) {
		User u = null;
		System.out.println("Hallo1");
		try {
			//if(utx==null)
			//	System.out.println("Hallo kein utx");
			//else
			//	System.out.println("Hallo utx");
				
			//utx.begin();
			System.out.println("Hallo2");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("SopraPU");
			EntityManager em = emf.createEntityManager();
			System.out.println("Hallo3");
			u = (User) em
					.createQuery(
							"SELECT u FROM users u WHERE u.email = :email AND u.passwort = :passwort, User.class)")
					.setParameter("email", email)
					.setParameter("passwort", passwort).getSingleResult();
			//utx.commit();
			em.close();
		} catch (Exception e) {
			System.out.println("HalloError1");
			try {
				System.out.println("HalloError2");
				//utx.rollback();
				System.out.println("HalloError3");
				e.printStackTrace();
			} catch (Exception e1) {
				System.out.println("HalloError4");
				e1.printStackTrace();
			}
		}
		return u;
	}

	/*
	 * public List<User> getAllUsers() { return
	 * em.createQuery("Select u FROM users u").getResultList(); }
	 * 
	 * public void addUser(User u){ em.persist(u); }
	 * 
	 * public void removeUser(User u){ em.remove(em.merge(u)); }
	 */
}