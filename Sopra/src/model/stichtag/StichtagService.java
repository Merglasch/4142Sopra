package model.stichtag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Stichtag;

@Stateless
public class StichtagService {
	
	@PersistenceContext
	private EntityManager em;
	
	private void updateStichtag (Stichtag s){
		em.merge(s);
	}
}
