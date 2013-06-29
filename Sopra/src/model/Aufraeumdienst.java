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
		String s = em.createQuery("values current timestamp", String.class)
				.getSingleResult();
		for(s : 4){
			
		}
		
	}
}