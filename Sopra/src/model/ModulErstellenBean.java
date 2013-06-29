package model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import klassenDB.Modul;
import model.modules.ModuleService;


public class ModulErstellenBean implements Serializable{
	private String modulname;
	private String code;
	private String arbeitsaufwand;
	private String dauer; //short
	private String dozenten;
	private String einordnung;
	private String englisch;
	private String grundlagefuer;
	private String inhalt;
	private String lehrformen;
	private String leistungsnachweis;
	private String leistungspunkte;
	private String lernziele;
	private String literatur;
	private String modulverantwortlicher;
	private String notenbildung;
	private String sprache;
	private short freigegeben=0;
	private String wahlpflicht="0";
	
	@EJB
	ModuleService moduleService;
	
	private String voraussetzungenfor;
	private String voraussetzungenin;
	private String turnus;
	private String wochenstunden; //short
	//wird vom MMS erstellt
	private int uid ;
	private Timestamp zeitstempel;
	private boolean modulErfolgreich=false;
	private boolean modulGescheitert=false;
//	
//	
//	
//	
//	David muss noch die uid machen =)
//	
//	
//	
//	
//	

	public String modulSpeichern(){
		boolean erfolg=true;
		//DB Methode modul speichern
		Modul m = new Modul();
		//m.setModulid(modulid);
		m.setUid(uid);
		m.setModulname(modulname);
		m.setCode(code);
		m.setArbeitsaufwand(arbeitsaufwand);
		m.setDozenten(dozenten);
		m.setEinordnung(einordnung);
		m.setEnglisch(englisch);
		m.setGrundlagefuer(grundlagefuer);
		m.setInhalt(inhalt);
		m.setLehrformen(lehrformen);
		m.setLeistungsnachweis(leistungsnachweis);
		m.setLeistungspunkte(leistungspunkte);
		m.setLernziele(lernziele);
		m.setLiteratur(literatur);
		m.setModulverantwortlicher(modulverantwortlicher);
		m.setNotenbildung(notenbildung);
		m.setSprache(sprache);
		//Fake datum
		//in Moduleservice.java
		//
		m.setVoraussetzungenfor(voraussetzungenfor);
		m.setVoraussetzungenin(voraussetzungenin);
		m.setTurnus(turnus);
		m.setFreigegeben(freigegeben);
		//Zeitstempel zur aktuellen Zeit
		zeitstempel = new Timestamp(System.currentTimeMillis());
		m.setZeitstempel(zeitstempel);
		//Name empty?
		if(modulname.isEmpty()){
			erfolg=false;			
		}
		//typecasts
		try{
			m.setDauer(Short.parseShort(dauer));			
		}catch(Exception e){
			dauer="Bitte hier nur zahlen!!";
			erfolg=false;
		}
		try{
			m.setWochenstunden(Short.parseShort(wochenstunden));			
		}catch(Exception e){
			wochenstunden="Bitte hier nur zahlen!!";
			erfolg=false;
		}
		try{
			m.setWahlpflicht(Short.parseShort(wahlpflicht));
		}catch(Exception e){
			erfolg=false;			
		}
		//DB Methode
		//modul speichern
		if(erfolg==true){
			erfolg = moduleService.createModule(m);			
		}
		if(erfolg==false){
			modulErfolgreich=false;
			modulGescheitert=true;
		}
		else{
			modulErfolgreich=true;
			modulGescheitert=false;			
		}
		return "modulErstellen";
	}
	
	public ModulErstellenBean(){
		super();
		modulname = "Modulname";
	}


	/// Getter und setter
	public String getModulname() {
		return modulname;
	}
	public void setModulname(String modulname) {
		this.modulname = modulname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getArbeitsaufwand() {
		return arbeitsaufwand;
	}
	public void setArbeitsaufwand(String arbeitsaufwand) {
		this.arbeitsaufwand = arbeitsaufwand;
	}
	public String getDauer() {
		return dauer;
	}
	public void setDauer(String dauer) {
		this.dauer = dauer;
	}
	public String getDozenten() {
		return dozenten;
	}
	public void setDozenten(String dozenten) {
		this.dozenten = dozenten;
	}
	public String getEinordnung() {
		return einordnung;
	}
	public void setEinordnung(String einordnung) {
		this.einordnung = einordnung;
	}
	public String getEnglisch() {
		return englisch;
	}
	public void setEnglisch(String englisch) {
		this.englisch = englisch;
	}
	public String getGrundlagefuer() {
		return grundlagefuer;
	}
	public void setGrundlagefuer(String grundlagefuer) {
		this.grundlagefuer = grundlagefuer;
	}
	public String getInhalt() {
		return inhalt;
	}
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
	public String getLehrformen() {
		return lehrformen;
	}
	public void setLehrformen(String lehrformen) {
		this.lehrformen = lehrformen;
	}
	public String getLeistungsnachweis() {
		return leistungsnachweis;
	}
	public void setLeistungsnachweis(String leistungsnachweis) {
		this.leistungsnachweis = leistungsnachweis;
	}
	public String getLeistungspunkte() {
		return leistungspunkte;
	}
	public void setLeistungspunkte(String leistungspunkte) {
		this.leistungspunkte = leistungspunkte;
	}
	public String getLernziele() {
		return lernziele;
	}
	public void setLernziele(String lernziele) {
		this.lernziele = lernziele;
	}
	public String getLiteratur() {
		return literatur;
	}
	public void setLiteratur(String literatur) {
		this.literatur = literatur;
	}
	public String getModulverantwortlicher() {
		return modulverantwortlicher;
	}
	public void setModulverantwortlicher(String modulverantwortlicher) {
		this.modulverantwortlicher = modulverantwortlicher;
	}
	public String getNotenbildung() {
		return notenbildung;
	}
	public void setNotenbildung(String notenbildung) {
		this.notenbildung = notenbildung;
	}
	public String getSprache() {
		return sprache;
	}
	public void setSprache(String sprache) {
		this.sprache = sprache;
	}
	public String getTurnus() {
		return turnus;
	}
	public void setTurnus(String turnus) {
		this.turnus = turnus;
	}
	public String getVoraussetzungenfor() {
		return voraussetzungenfor;
	}
	public void setVoraussetzungenfor(String voraussetzungenfor) {
		this.voraussetzungenfor = voraussetzungenfor;
	}
	public String getVoraussetzungenin() {
		return voraussetzungenin;
	}
	public void setVoraussetzungenin(String voraussetzungenin) {
		this.voraussetzungenin = voraussetzungenin;
	}
	public String getWochenstunden() {
		return wochenstunden;
	}
	public void setWochenstunden(String wochenstunden) {
		this.wochenstunden = wochenstunden;
	}

	public Timestamp getZeitstempel() {
		return zeitstempel;
	}
	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the freigegeben
	 */
	public short getFreigegeben() {
		return freigegeben;
	}

	/**
	 * @param freigegeben the freigegeben to set
	 */
	public void setFreigegeben(short freigegeben) {
		this.freigegeben = freigegeben;
	}

	/**
	 * @return the wahlpflicht
	 */
	public String getWahlpflicht() {
		return wahlpflicht;
	}

	/**
	 * @param wahlpflicht the wahlpflicht to set
	 */
	public void setWahlpflicht(String wahlpflicht) {
		this.wahlpflicht = wahlpflicht;
	}

	/**
	 * @return the modulErfolgreich
	 */
	public boolean isModulErfolgreich() {
		return modulErfolgreich;
	}

	/**
	 * @param modulErfolgreich the modulErfolgreich to set
	 */
	public void setModulErfolgreich(boolean modulErfolgreich) {
		this.modulErfolgreich = modulErfolgreich;
	}

	/**
	 * @return the modulGescheitert
	 */
	public boolean isModulGescheitert() {
		return modulGescheitert;
	}

	/**
	 * @param modulGescheitert the modulGescheitert to set
	 */
	public void setModulGescheitert(boolean modulGescheitert) {
		this.modulGescheitert = modulGescheitert;
	}


	
}
