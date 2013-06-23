package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;

import klassenDB.Modul;


public class ModulErstellenBean implements Serializable{
	private int modulid = -1;  // -1 heiﬂt neues modul, in modul speichern wird eine unbenutzte id gefunden und gesetzt
	private String modulname;
	private String code;
	private String arbeitsaufwand;
	private String dauer; //short
	private String dezernat;//short
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
	
	@EJB
	ModuleService moduleService;
	
	private String voraussetzungenfor;
	private String voraussetzungenin;
	private String turnus;
	private String wochenstunden; //short
	// folgende attribute werden im formular (noch?) nicht eingegeben
	private Date stichtag; //date
	//wird vom MMS erstellt
	private int uid ;
	private Timestamp zeitstempel;
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
		//DB Methode modul speichern
		Modul m = new Modul();
		m.setModulid(modulid);
		
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
		
		m.setVoraussetzungenfor(voraussetzungenfor);
		m.setVoraussetzungenin(voraussetzungenin);
		m.setTurnus(turnus);
		
		//Zeitstempel zur aktuellen Zeit
		zeitstempel = new Timestamp(System.currentTimeMillis());
		
		//typecasts
//		try{
//			m.setModulid(Integer.parseInt(modulid));			
//		}catch(Exception e){
//			modulid="seggl, hier nur zahlen!!";
//		}
		try{
			m.setDauer(Short.parseShort(dauer));			
		}catch(Exception e){
			dauer="Bitte hier nur zahlen!!";
		}
		try{
			m.setDezernat(Short.parseShort(dezernat));			
		}catch(Exception e){
			dezernat="Bitte hier nur zahlen!!";
		}
		try{
			m.setWochenstunden(Short.parseShort(wochenstunden));			
		}catch(Exception e){
			dezernat="Bitte hier nur zahlen!!";
		}
		
		//DB Methode
		//modul speichern
		boolean erg = moduleService.createModule(m);
		if(erg==false)
			System.out.println("nich geklappt");
		else
			System.out.println(m.getUid());			
		return "login";
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
	public int getModulid() {
		return modulid;
	}
	public void setModulid(int modulid) {
		this.modulid = modulid;
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
	public String getDezernat() {
		return dezernat;
	}
	public void setDezernat(String dezernat) {
		this.dezernat = dezernat;
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
	public Date getStichtag() {
		return stichtag;
	}
	public void setStichtag(Date stichtag) {
		this.stichtag = stichtag;
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
	
}
