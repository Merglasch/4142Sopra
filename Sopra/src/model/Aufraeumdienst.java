package model;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Aufraeumdienst {
	
	@PersistenceContext
	private EntityManager em;
	
	@Schedule(second = "1",
			minute = "1",
			hour = "3",
			dayOfWeek = "*",
			dayOfMonth = "1",
			month = "1",
			year = "*",
			info = "Aufraeumtimer")
	public void aufraumer() {
		String sysDate;
		final long year = 31556952000L;
		long startTime = System.currentTimeMillis() -year;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sysDate = sdf.format(startTime);
		
		em.createQuery("Delete FROM Modul WHERE freigegeben = 0 AND zeitstempel < :sysDate")
		.setParameter("sysDate",sysDate);
		
		em.createQuery("Delete FROM Modulhandbuch WHERE freigegeben = 0 AND zeitstempel < :sysDate")
		.setParameter("sysDate",sysDate);
	}
}