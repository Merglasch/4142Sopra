package model;

import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Aufraeumdienst {
	
	@PersistenceContext
	private EntityManager em;
	
	private Date lastTimeout;
	
	
	/**
	 * Automatischer Timer @Schedule gibt Zeitintervall an wann er ausgeloest werden soll (3 Uhr am ersten Tag des Jahres)
	 * Die erweckte Methode aufraeumer entfernt alle nicht veroeffentlichten Module die aelter als ein Jahr sind.
	 */
	@Schedule(second = "1",
			minute = "1",
			hour = "3",
			dayOfWeek = "*",
			dayOfMonth = "1",
			month = "1",
			year = "*",
			info = "Aufraeumtimer")
	public void aufraeumer() {
		String sysDate;
		final long year = 31556952000L;
		long startTime = System.currentTimeMillis() -year;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sysDate = sdf.format(startTime);
		
		em.createNativeQuery("Delete FROM Modul WHERE freiVerantwortlicher = 0 OR freiDekan = 0 OR freiKoordinator = 0 AND zeitstempel < ?")
		.setParameter(1, sysDate);
		
		em.createNativeQuery("Delete FROM Modulhandbuch WHERE freigegeben = 0 AND zeitstempel < ?")
		.setParameter(1, sysDate);
	}
	
	/**
	 * Schedule Timer Test-Methode
	 */
	@Schedule(second = "10",
			minute = "*",
			hour = "*",
			dayOfWeek = "*",
			dayOfMonth = "*",
			month = "*",
			year = "*",
			info = "Aufraeumtimer")
	public void timerTest() {
		this.setLastTimeout(new Date());
		System.out.println("Schedule Timout Test: " + lastTimeout.toString());
	}
	
	/** 
	 * Setzt Timout-Zeitpunkt fuer die Test-Methode
	 * @param lastTimeout
	 */
	public void setLastTimeout(Date lastTimeout){
		this.lastTimeout=lastTimeout;
	}
	
}