package model;

import javax.persistence.EntityManager;

public class Aufraeumdienst {
	private EntityManager em;
	
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