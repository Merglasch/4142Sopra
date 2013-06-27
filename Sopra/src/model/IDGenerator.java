package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class IDGenerator {
	
	@PersistenceContext
	private static EntityManager ems;
	
	@PersistenceContext
	private EntityManager em;
	
	public int getID() {
		//bei Erweiterung der DB hier mehr IDs ueberpruefen
		int uid = em.createQuery("SELECT u.uid FROM User u").getMaxResults();
		int mid = em.createQuery("SELECT m.modulid FROM Modul m").getMaxResults();
		int mhid = em.createQuery("SELECT mh.handbuchid FROM Modulhandbuch mh").getMaxResults();
		int nid = em.createQuery("SELECT n.nachrichtid FROM Benachrichtigung n").getMaxResults();
		
		id = Math.max(Math.max(Math.max(uid,mid),mhid),nid);
		return id;
	}
	
}
