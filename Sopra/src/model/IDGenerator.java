package model;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class IDGenerator {

	@PersistenceContext
	private static EntityManager ems;
		
	//durchsucht DB nach hoechster id und liefert dann id+1 zurueck
	public static int getID() {
		Integer uid=0;
		Integer mid=0;
		Integer mhid=0;
		Integer nid=0;
		//bei Erweiterung der DB hier mehr IDs ueberpruefen
		try{
			try{
				uid = ems.createQuery("SELECT MAX(u.uid) FROM User u", Integer.class).getSingleResult();
			}catch(NoResultException e){
				uid = 0;
			}try{
				mid = ems.createQuery("SELECT MAX(m.modulid) FROM Modul m", Integer.class).getSingleResult();
			}catch(NoResultException e){
				mid = 0;
			}try{
				mhid = ems.createQuery("SELECT MAX(mh.handbuchid) FROM Modulhandbuch mh", Integer.class).getSingleResult();
			}catch(NoResultException e){
				uid = 0;
			}try{	
				nid = ems.createQuery("SELECT MAX(n.nachrichtid) FROM Benachrichtigung n", Integer.class).getSingleResult();
			}catch(NoResultException e){
				uid = 0;
			}	
		}catch(Exception e){
			System.out.println("IDGenException");
		}
		
		Integer id = Math.max(Math.max(Math.max(uid,mid),mhid),nid)+1;
		return id;
	}
	
}
