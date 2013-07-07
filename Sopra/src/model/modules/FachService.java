package model.modules;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Fach;

@Stateless
public class FachService {
	
	@PersistenceContext
	private EntityManager em;
	
	public int createFach(Fach f){
		int maxID=0;
		maxID=em.createQuery("SELECT MAX(f.fid) FROM Fach f",Integer.class).getResultList().get(0);
		int id=maxID+1;
		
		
		f.setFid(id);
		try{
			em.persist(f);
		}
		catch(Exception e){
			System.out.println("createFach fehlgeschlagen");
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean changeFach(Fach f){
		boolean success=true;
		try{
			em.createNativeQuery("update fach set fach.fach = ? where fach.fid=?")
			.setParameter(1, f.getFach())
			.setParameter(2, f.getFid())
			.executeUpdate();
		}catch(Exception e){
			success=false;
			e.printStackTrace();
		}
		return success;
	}
	
	public void deleteFach(int fachID){
		em.remove(em.merge(em.find(Fach.class, fachID)));
	}
	
	public List<Fach> getAllFach(){
		return em.createQuery("SELECT f FROM Fach f", Fach.class).getResultList();
	}
	
	public List<String> getAllFachNames(){
		return em.createNativeQuery("SELECT f.fach FROM Fach f").getResultList();
	}
	
	public Fach getFach(int id){
		return em.find(Fach.class, id);
	}
	
	public Fach findById(int fid){
		return em.createQuery("SELECT f FROM Fach f WHERE f.fid = :fid", Fach.class).setParameter("fid", fid).getSingleResult();
		}
}
