package model;

import java.util.ArrayList;
import java.util.List;

import klassenDB.Modul;

public class ModelBean {
	private String studienabschluss;
	private String studiengang;
	private String pruefungsordnung;
	private int nummer;
	private String modulName;
	private List<Modul> modulListe;

	
	public ModelBean(String studienabschluss, String studiengang,
			String pruefungsordnung, int nummer, String modulName) {
		super();
		this.studienabschluss = studienabschluss;
		this.studiengang = studiengang;
		this.pruefungsordnung = pruefungsordnung;
		this.nummer = nummer;
		this.modulName = modulName;
	}
	public ModelBean() {
		super();
	}


	public String sucheModul(){
		modulListe = new ArrayList<Modul>();
		
		///// Abfrage in DB
		/// modulListe erstellen
		
		// hier: dummy Modul zur
		
		Modul bsp = new Modul();
		bsp.setModulname("modulname");
		bsp.setInhalt("inhalt");
		modulListe.add(bsp);
		
		return "Antwortseite";
	}
	
	public String sucheModul(String studienabschluss, String studiengang,
			String pruefungsordnung, int nummer, String modulName){
		
		this.studienabschluss = studienabschluss;
		this.studiengang = studiengang;
		this.pruefungsordnung = pruefungsordnung;
		this.nummer = nummer;
		this.modulName = modulName;
		
		
		return sucheModul();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
////////////////////////////////////////////////////////
/////////////   Getter und setter   ///////////////////////////
////////////////////////////////////////////////////////
	public String getStudienabschluss() {
		return studienabschluss;
	}
	public void setStudienabschluss(String studienabschluss) {
		this.studienabschluss = studienabschluss;
	}
	public String getStudiengang() {
		return studiengang;
	}
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}
	public String getPruefungsordnung() {
		return pruefungsordnung;
	}
	public void setPruefungsordnung(String pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public String getModulName() {
		return modulName;
	}
	public void setModulName(String modulName) {
		this.modulName = modulName;
	}
	public List<Modul> getModulListe() {
		return modulListe;
	}
	
}
