package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class IDGenerator {

	@PersistenceContext
	private static EntityManager ems;
		
	//durchsucht DB nach hoechster id und liefert dann id+1 zurueck
	public static int getID() {
		int uid=0;
		int mid=0;
		int mhid=0;
		int nid=0;
		//bei Erweiterung der DB hier mehr IDs ueberpruefen
		try{
			
				mid = ems.createQuery("SELECT MAX(u.modulid) FROM Modul u", Integer.class).getSingleResult().intValue();
			
		}catch(Exception e){
			System.out.println("IDGenException");
			System.out.println(e.getMessage());
		}
		System.out.println("udi "+uid);
		System.out.println("mid "+mid);
		System.out.println("mhid "+mhid);
		System.out.println("nid "+nid);
		Integer id = Math.max(Math.max(Math.max(uid,mid),mhid),nid)+1;
		return id;
	}
	
}
