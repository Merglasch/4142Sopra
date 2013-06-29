package model;

import javax.ejb.Stateless;
import javax.persistence.*;

import com.itextpdf.text.log.SysoCounter;

@Stateless
public class IDGenerator {

	@PersistenceContext
	private EntityManager ems;
		
	//durchsucht DB nach hoechster id und liefert dann id+1 zurueck
	public int getID() {
		Integer uid=0;
		Integer mid=0;
		Integer mhid=0;
		Integer nid=0;
		//bei Erweiterung der DB hier mehr IDs ueberpruefen
		try{
			uid = ems.createQuery("SELECT MAX(u.uid) FROM User u", Integer.class).getSingleResult();
		}catch(NoResultException e){
			System.out.println("User ID no Result");
			uid = 0;
		}catch(NonUniqueResultException e){
			System.out.println("User ID not unique");
		}catch(QueryTimeoutException e){
			System.out.println("User ID timeout");
		}catch(TransactionRequiredException e){
			System.out.println("User ID TransactinRequired");
		}catch(PessimisticLockException e){
			System.out.println("User ID PessimisticLock");
		}catch(LockTimeoutException e){
			System.out.println("User ID LockTimeout");
		}catch(PersistenceException e){
			System.out.println("User ID PersistenceException");
		}catch(IllegalStateException e){
			System.out.println("User ID IllegalState");
		}
		try{
			mid = ems.createQuery("SELECT MAX(m.modulid) FROM Modul m", Integer.class).getSingleResult();
		}catch(NoResultException e){
			System.out.println("Module ID no Result");
			mid = 0;
		}catch(NonUniqueResultException e){
			System.out.println("Module ID not unique");
		}catch(QueryTimeoutException e){
			System.out.println("Module ID timeout");
		}catch(TransactionRequiredException e){
			System.out.println("Module ID TransactinRequired");
		}catch(PessimisticLockException e){
			System.out.println("Module ID PessimisticLock");
		}catch(LockTimeoutException e){
			System.out.println("Module ID LockTimeout");
		}catch(PersistenceException e){
			System.out.println("Module ID PersistenceException");
		}catch(IllegalStateException e){
			System.out.println("Module ID IllegalState");
		}try{
			mhid = ems.createQuery("SELECT MAX(mh.handbuchid) FROM Modulhandbuch mh", Integer.class).getSingleResult();
		}catch(NoResultException e){
			System.out.println("Modulhandbuch ID no Result");
			mhid = 0;
		}catch(NonUniqueResultException e){
			System.out.println("Modulhandbuch ID not unique");
		}catch(QueryTimeoutException e){
			System.out.println("Modulhandbuch ID timeout");
		}catch(TransactionRequiredException e){
			System.out.println("Modulhandbuch ID TransactinRequired");
		}catch(PessimisticLockException e){
			System.out.println("Modulhandbuch ID PessimisticLock");
		}catch(LockTimeoutException e){
			System.out.println("Modulhandbuch ID LockTimeout");
		}catch(PersistenceException e){
			System.out.println("Modulhandbuch ID PersistenceException");
		}catch(IllegalStateException e){
			System.out.println("Modulhandbuch ID IllegalState");
		}try{	
			nid = ems.createQuery("SELECT MAX(n.nachrichtid) FROM Benachrichtigung n", Integer.class).getSingleResult();
		}catch(NoResultException e){
			System.out.println("Nachricht ID no Result");
			nid = 0;
		}catch(NonUniqueResultException e){
			System.out.println("Nachricht ID not unique");
		}catch(QueryTimeoutException e){
			System.out.println("Nachricht ID timeout");
		}catch(TransactionRequiredException e){
			System.out.println("Nachricht ID TransactinRequired");
		}catch(PessimisticLockException e){
			System.out.println("Nachricht ID PessimisticLock");
		}catch(LockTimeoutException e){
			System.out.println("Nachricht ID LockTimeout");
		}catch(PersistenceException e){
			System.out.println("Nachricht ID PersistenceException");
		}catch(IllegalStateException e){
			System.out.println("Nachricht ID IllegalState");
		}	
		
		Integer id = Math.max(Math.max(Math.max(uid,mid),mhid),nid)+1;
		return id;
	}
	
}
