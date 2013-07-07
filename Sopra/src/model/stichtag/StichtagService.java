package model.stichtag;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import klassenDB.Stichtag;

@Stateless
@Startup
public class StichtagService {
	
	@Resource
	TimerService timerService;
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean updateStichtag (Stichtag s){
		boolean b = false;
		try{
			em.merge(s);
			b=true;
			setTimer(s);
			return b;
		}catch (Exception e) {
			return b;
		}
	}
	
	public Stichtag getStichtag(){
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
		System.out.println("----setTimer Methode----");
		String date = stichtag.getStichtag();
		String[] splitStichtag = date.split("\\.");
		int[] splitInt = new int[3];
		int day;
		int month;
		int year;
		
		for(int i=0; i<splitStichtag.length; i++) {
			splitInt[i] = Integer.parseInt(splitStichtag[i]);
		}
		day = splitInt[0];
		month = splitInt[1];
		year = splitInt[2];
		
		ScheduleExpression schedule = new ScheduleExpression();
		schedule.year(year);
		schedule.month(month);
		schedule.dayOfMonth(day);
		schedule.hour(17);
		schedule.minute(02);
		Timer timer = timerService.createCalendarTimer(schedule, new TimerConfig("Stichtag Timer 1", true));
		System.out.println("----Timer geladen!----");
	}

	@Timeout
	public void stichtagTimout() {
		System.out.println("Programmatic Timer Stichtag: timeout!");
		em.createNativeQuery("UPDATE modulhandbuch SET veroeffentlicht = 1 WHERE freigegeben = 1");
		System.out.println("Handbücher wurden veröffentlicht!");		
	}
}