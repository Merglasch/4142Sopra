package model.stichtag;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Stichtag;
import klassenDB.User;

@Stateless
public class StichtagService {
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean updateStichtag (Stichtag s){
		try{
			em.merge(s);	
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public Stichtag getStichtag(){
		//alte methode ging nicht, diese hier tuts =)
		List<Stichtag> sl = em.createQuery("Select u FROM Stichtag u",Stichtag.class).getResultList();
		Stichtag s =null;
		for(Stichtag x:sl){
			s = x;
		}
		
		if(null == s){
			s= new Stichtag();
			s.setStichtag("01.01.0001");
		}
		return s;
	}
	
}
