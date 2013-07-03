package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

import klassenDB.Modul;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;

public class ModulAendernBean implements Serializable{

	
	//fuer stellvertreter ged�ns
	private int rolle;
	private int aktUserID ;
	
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
	private short freiVerantwortlicher=0;
	private short freiKoordinator=0;
	private short freiDekan=0;
	private String wahlpflicht="0";
	
	@EJB
	ModuleService moduleService;
	
	private String voraussetzungenfor;
	private String voraussetzungenin;
	private String turnus;
	private String wochenstunden; //short
	//wird vom MMS erstellt
	private int uid=-1 ;
	private Timestamp zeitstempel;
	private boolean modulErfolgreich=false;
	private boolean modulGescheitert=false;

	List<Modul> listModul;
	String modulAuswahl;
	Modul aktModul;
	
	@EJB
	TreeService treeService;
	
	//TODO
	@EJB
	ModulhandbuchService mhbService;
	
	
	
	public ModulAendernBean() {
		super();
	}

	public String ausgewaehlt(){
		aktModul = moduleService.searchByName(modulAuswahl);
		
		uid=aktModul.getUid();
		modulname=aktModul.getModulname();
		code=aktModul.getCode();
		arbeitsaufwand=aktModul.getArbeitsaufwand();
		
		//TODO evt umrechnen short to string mit werten..
		dauer=""+aktModul.getDauer(); //short
		dozenten=aktModul.getDozenten();
		einordnung=aktModul.getEinordnung();
		englisch=aktModul.getEnglisch();
		grundlagefuer=aktModul.getGrundlagefuer();
		inhalt=aktModul.getInhalt();
		lehrformen=aktModul.getLehrformen();
		leistungsnachweis=aktModul.getLeistungsnachweis();
		leistungspunkte=aktModul.getLeistungspunkte();
		lernziele=aktModul.getLernziele();
		String literatur=aktModul.getLiteratur();
		modulverantwortlicher=aktModul.getModulverantwortlicher();
		notenbildung=aktModul.getNotenbildung();
		sprache=aktModul.getSprache();
//		private short freigegeben=0;
		
		wahlpflicht=""+aktModul.getWahlpflicht();//Short
		
		
		voraussetzungenfor=aktModul.getVoraussetzungenfor();
		voraussetzungenin=aktModul.getVoraussetzungenin();
		turnus=aktModul.getTurnus();
		wochenstunden=""+aktModul.getWochenstunden(); //short
			
		return "modulAendern2";
	}
		
	public String modulAendern(){
		Modul m = new Modul();
		System.out.println("MODUL AENDERN METHODE");
		boolean erfolg=true;
		//DB Methode modul speichern
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
		m.setFreiVerantwortlicher(freiVerantwortlicher);
		m.setFreiKoordinator(freiKoordinator);
		m.setFreiDekan(freiDekan);
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
			int erg =moduleService.createModule(m);
			if(-1 == erg){
				erfolg = 	false;
			}else{
				
				erfolg = 	true;
			}
			
			modulErfolgreich=true;
			modulGescheitert=false;	
		}
		System.out.println("Dozenten: " + dozenten);
		//moduleService.updateModule(m);
		//moduleService.createModule(m);
		return"modulAendern1";
	}	

	//Getter und setter

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




	public short getFreiVerantwortlicher() {
		return freiVerantwortlicher;
	}
	
	public void setFreiVerantwortlicher(short freiVerantwortlicher) {
		this.freiVerantwortlicher = freiVerantwortlicher;
	}
	
	public short getFreiKoordinator() {
		return freiKoordinator;
	}
	
	public void setFreiKoordinator(short freiKoordinator) {
		this.freiKoordinator = freiKoordinator;
	}
	
	public short getFreiDekan() {
		return freiDekan;
	}
	
	public void setFreiDekan(short freiDekan) {
		this.freiDekan = freiDekan;
	}




	public String getWahlpflicht() {
		return wahlpflicht;
	}




	public void setWahlpflicht(String wahlpflicht) {
		this.wahlpflicht = wahlpflicht;
	}




	public ModuleService getModuleService() {
		return moduleService;
	}




	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
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




	public String getTurnus() {
		return turnus;
	}




	public void setTurnus(String turnus) {
		this.turnus = turnus;
	}




	public String getWochenstunden() {
		return wochenstunden;
	}




	public void setWochenstunden(String wochenstunden) {
		this.wochenstunden = wochenstunden;
	}




	public int getUid() {
		return uid;
	}




	public void setUid(int uid) {
		this.uid = uid;
	}




	public Timestamp getZeitstempel() {
		return zeitstempel;
	}




	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}




	public boolean isModulErfolgreich() {
		return modulErfolgreich;
	}




	public void setModulErfolgreich(boolean modulErfolgreich) {
		this.modulErfolgreich = modulErfolgreich;
	}




	public boolean isModulGescheitert() {
		return modulGescheitert;
	}




	public void setModulGescheitert(boolean modulGescheitert) {
		this.modulGescheitert = modulGescheitert;
	}




	public List<Modul> getListModul() {
		
		if(rolle == 0){ //Mod verantwortlicher kann seine und die die er stellvertritt aendern
			listModul = moduleService.getMyModules(aktUserID); // aktuelle uID des bearbeitenden
		}else{ //Koordinator oder dekan  kann alle aendern 
			listModul = moduleService.getAllModules();
		}
		return listModul;
	}




	public void setListModul(List<Modul> listModul) {
		this.listModul = listModul;
	}




	public String getModulAuswahl() {
		return modulAuswahl;
	}




	public void setModulAuswahl(String modulAuswahl) {
		this.modulAuswahl = modulAuswahl;
	}




	public TreeService getTreeService() {
		return treeService;
	}




	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}




	public ModulhandbuchService getMhbService() {
		return mhbService;
	}




	public void setMhbService(ModulhandbuchService mhbService) {
		this.mhbService = mhbService;
	}

	public int getRolle() {
		return rolle;
	}

	public void setRolle(int rolle) {
		this.rolle = rolle;
	}

	public int getAktUserID() {
		return aktUserID;
	}

	public void setAktUserID(int aktUserID) {
		this.aktUserID = aktUserID;
	}
}