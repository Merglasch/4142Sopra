package model;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import model.modules.ModuleService;

import klassenDB.Modul;

import model.modules.ModuleService;

public class Aufraeumdienst {
	private EntityManager em;
	
	private List<Modul> moduleList;
	private Date now = null;
	private String minTime;
	
	public void dateToString() {
		
			
	}
	
	public void aufraumer() {
		String sysDate;
		long year = 31556952000L;
		long startTime = System.currentTimeMillis() -year;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sysDate = sdf.format(startTime);
		
		
	}
}