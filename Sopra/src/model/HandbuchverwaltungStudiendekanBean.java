package model;

import java.util.List;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;

public class HandbuchverwaltungStudiendekanBean {

	List<Modul> modulListe;
	String modulAuswahl; // an modulid
	List<Fach> fachListe;
	String fachAuswahl;
	List<Modulhandbuch> modulhandbuchListe;
	String modulhandbuchAuswahl;
	
	/**
	 * Standard-Konstruktor.
	 */
	public HandbuchverwaltungStudiendekanBean() {
		super();
	}

	/**
	 * 
	 * 
	 * @return 
	 */
	public String handbuchAnlegen(){
		
		int modulid = Integer.parseInt(modulAuswahl);
		int fachid = Integer.parseInt(fachAuswahl);
		int mhdid = Integer.parseInt(modulAuswahl);
		
		// db methode(n?) fuer zum anlegen von dem zeug
		
		return "handbuchverwaltungStudiendekan";
	}
	
}
