package model.stichtag;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Stichtag;
import klassenDB.User;

@Stateless
public class StichtagService {
	
	TimerService timerService;
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean updateStichtag (Stichtag s){
		boolean b = false;
		try{
			em.merge(s);
			setTimer(s);
		}catch (Exception e) {
			return b;
		}
		return true;
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
	

	public void setTimer(Stichtag stichtag) {
		String zeitpunkt = stichtag.getStichtag() + " 12";
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH");
		Date date = new Date();
		System.out.println("Programmatic Timer: " + date.toString()); //nur zu Testzwercken
		try {
			date = sdf.parse(zeitpunkt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timer timer = timerService.createSingleActionTimer(date,
				new TimerConfig());
		stichtagTimout(timer);
	}

	@Timeout
	public void stichtagTimout(Timer timer) {
		System.out.println("Programmatic Timer: timeout!");
		
	}
}
