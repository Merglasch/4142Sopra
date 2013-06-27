package model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class IDGenerator {
	
	@PersistenceContext
	private static EntityManager ems;
	
	public static int getID() {
		//bei Erweiterung der DB hier mehr IDs ueberpruefen
		List<int> uids = ems.createQuery("SELECT MAX(u.uid) FROM User u").getResultList();
		int mid = ems.createQuery("SELECT MAX(m.modulid) FROM Modul m").getResultList();
		int mhid = ems.createQuery("SELECT MAX(mh.handbuchid) FROM Modulhandbuch mh").getResultList();
		int nid = ems.createQuery("SELECT MAX(n.nachrichtid) FROM Benachrichtigung n").getResultList();
		
		int id = Math.max(Math.max(Math.max(uid,mid),mhid),nid)+1;
		return id;
	}
	
}
