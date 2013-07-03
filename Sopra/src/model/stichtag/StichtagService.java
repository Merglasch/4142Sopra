package model.stichtag;

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

@Stateless
public class StichtagService {
	
	TimerService timerService;
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean updateStichtag (Stichtag s){
		boolean b = false;
		try{
			em.merge(s);
		}catch (Exception e) {
			return b;
		}
		return true;
	}
	
	public Stichtag getStichtag(){		
		return em.createQuery("SELECT s FROM Stichtag s", Stichtag.class).getResultList().get(0);
	}
	
	public void setTimer(Stichtag stichtag) {
		String zeitpunkt = stichtag.getStichtag() + " 24";
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH");
		Date date = new Date();
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
