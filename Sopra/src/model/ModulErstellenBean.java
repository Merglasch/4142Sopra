package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;

/**
 * Bean zum Erstellen der Module
 *
 */
public class ModulErstellenBean implements Serializable{
	private String modulname;
	
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
	private int uid ;
	private Timestamp zeitstempel;
	private boolean modulErfolgreich=false;
	private boolean modulGescheitert=false;

	@EJB
	TreeService treeService;
	
	//TODO
	@EJB
	ModulhandbuchService mhbService;
	
	
	// modulhandbuch
	List<SelectItem> studiengaenge;
	String studiengangAuswahl;
//	String newStudiengang;
	
//	List<SelectItem> abschluesse;
	List<String> abschluesse;
	String abschlussAuswahl;
//	String newAbschluss;
	
	List<SelectItem> pruefungsordnungen;
	String pruefungsordnungAuswahl;
//	String newPruefungsordnung;
	// vor modulhandbuch speichern abfragen ob -Auswahl jeweils Neu ??
	
	
	
	/**
	 * fuellt das Modul mit den eingegebenen Daten und speichert es in der Datenbank ab.
	 * 
	 * @return laedt als naechstes die Seite modulErstellen
	 */
	public String modulSpeichern(){
		
		boolean erfolg=true;
		Modul m = new Modul();
		m.setUid(uid);
		m.setModulname(modulname);
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
		if (erfolg == true) {
			int i = moduleService.createModule(m);
			if (i == -1) {
				erfolg = false;
				modulErfolgreich = false;
				modulGescheitert = true;
			} else {

				modulErfolgreich = true;
				modulGescheitert = false;

				// if erstellen erfolgreich eingabefelder löschen
				modulname = "";
				arbeitsaufwand = "";
				dauer = "";
				dozenten = "";
				einordnung = "";
				englisch = "";
				grundlagefuer = "";
				inhalt = "";
				lehrformen = "";
				leistungsnachweis = "";
				leistungspunkte = "";
				lernziele = "";
				literatur = "";
				modulverantwortlicher = "";
				notenbildung = "";
				sprache = "";
				freiVerantwortlicher = 0;
				freiKoordinator = 0;
				freiDekan = 0;
				wahlpflicht = "0";
				voraussetzungenfor = "";
				voraussetzungenin = "";
				turnus = "";
				wochenstunden = "";
			}
		} else {
			modulErfolgreich = false;
			modulGescheitert = true;
		}

		return "modulErstellen";
	}
	
	/**
	 * Standard-Konstruktor.
	 */
	public ModulErstellenBean(){
		super();
	}

	/**
	 * Konvertiert String ind SelectItem-Objekte, die fuer die Verwendeung der Checkboxen benoetigt werden.
	 * 
	 * @param StringList
	 * @return SelectItemList
	 */
	private List<SelectItem> toSelectItem(List<String> sl){
		List<SelectItem> sil = new LinkedList<SelectItem>();
		for(String s : sl){
			sil.add(new SelectItem(s,s));
		}
		return sil;
	}

	
	
	///////////////////////////////////
	/// Getter und setter
	///////////////////////////////////
	
	

	/**
	 * 
	 * @return the abschluesse
	 */
	public List<String> getAbschluesse() {
		//TODO
		// einlesen mit db methode
		abschluesse = treeService.getAllAbschluss();
		
		return abschluesse;
	}

	/**
	 * 
	 * @return pruefungsordnung
	 */
	public List<SelectItem> getPruefungsordnungen() {
		pruefungsordnungen = toSelectItem(treeService.getAllPruefungsordnung());
		return pruefungsordnungen;
	}

	/**
	 * 
	 * @return the studiengaenge
	 */
	public List<SelectItem> getStudiengaenge() {
		studiengaenge = toSelectItem(treeService.getAllStudiengang());
		return studiengaenge;
	}

	/**
	 * @return the modulname
	 */
	public String getModulname() {
		return modulname;
	}

	/**
	 * @param modulname the modulname to set
	 */
	public void setModulname(String modulname) {
		this.modulname = modulname;
	}


	/**
	 * @return the arbeitsaufwand
	 */
	public String getArbeitsaufwand() {
		return arbeitsaufwand;
	}

	/**
	 * @param arbeitsaufwand the arbeitsaufwand to set
	 */
	public void setArbeitsaufwand(String arbeitsaufwand) {
		this.arbeitsaufwand = arbeitsaufwand;
	}

	/**
	 * @return the dauer
	 */
	public String getDauer() {
		return dauer;
	}

	/**
	 * @param dauer the dauer to set
	 */
	public void setDauer(String dauer) {
		this.dauer = dauer;
	}

	/**
	 * @return the dozenten
	 */
	public String getDozenten() {
		return dozenten;
	}

	/**
	 * @param dozenten the dozenten to set
	 */
	public void setDozenten(String dozenten) {
		this.dozenten = dozenten;
	}

	/**
	 * @return the einordnung
	 */
	public String getEinordnung() {
		return einordnung;
	}

	/**
	 * @param einordnung the einordnung to set
	 */
	public void setEinordnung(String einordnung) {
		this.einordnung = einordnung;
	}

	/**
	 * @return the englisch
	 */
	public String getEnglisch() {
		return englisch;
	}

	/**
	 * @param englisch the englisch to set
	 */
	public void setEnglisch(String englisch) {
		this.englisch = englisch;
	}

	/**
	 * @return the grundlagefuer
	 */
	public String getGrundlagefuer() {
		return grundlagefuer;
	}

	/**
	 * @param grundlagefuer the grundlagefuer to set
	 */
	public void setGrundlagefuer(String grundlagefuer) {
		this.grundlagefuer = grundlagefuer;
	}

	/**
	 * @return the inhalt
	 */
	public String getInhalt() {
		return inhalt;
	}

	/**
	 * @param inhalt the inhalt to set
	 */
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	/**
	 * @return the lehrformen
	 */
	public String getLehrformen() {
		return lehrformen;
	}

	/**
	 * @param lehrformen the lehrformen to set
	 */
	public void setLehrformen(String lehrformen) {
		this.lehrformen = lehrformen;
	}

	/**
	 * @return the leistungsnachweis
	 */
	public String getLeistungsnachweis() {
		return leistungsnachweis;
	}

	/**
	 * @param leistungsnachweis the leistungsnachweis to set
	 */
	public void setLeistungsnachweis(String leistungsnachweis) {
		this.leistungsnachweis = leistungsnachweis;
	}

	/**
	 * @return the leistungspunkte
	 */
	public String getLeistungspunkte() {
		return leistungspunkte;
	}

	/**
	 * @param leistungspunkte the leistungspunkte to set
	 */
	public void setLeistungspunkte(String leistungspunkte) {
		this.leistungspunkte = leistungspunkte;
	}

	/**
	 * @return the lernziele
	 */
	public String getLernziele() {
		return lernziele;
	}

	/**
	 * @param lernziele the lernziele to set
	 */
	public void setLernziele(String lernziele) {
		this.lernziele = lernziele;
	}

	/**
	 * @return the literatur
	 */
	public String getLiteratur() {
		return literatur;
	}

	/**
	 * @param literatur the literatur to set
	 */
	public void setLiteratur(String literatur) {
		this.literatur = literatur;
	}

	/**
	 * @return the modulverantwortlicher
	 */
	public String getModulverantwortlicher() {
		return modulverantwortlicher;
	}

	/**
	 * @param modulverantwortlicher the modulverantwortlicher to set
	 */
	public void setModulverantwortlicher(String modulverantwortlicher) {
		this.modulverantwortlicher = modulverantwortlicher;
	}

	/**
	 * @return the notenbildung
	 */
	public String getNotenbildung() {
		return notenbildung;
	}

	/**
	 * @param notenbildung the notenbildung to set
	 */
	public void setNotenbildung(String notenbildung) {
		this.notenbildung = notenbildung;
	}

	/**
	 * @return the sprache
	 */
	public String getSprache() {
		return sprache;
	}

	/**
	 * @param sprache the sprache to set
	 */
	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	/**
	 * @return the freiVerantwortlicher
	 */
	public short getFreiVerantwortlicher() {
		return freiVerantwortlicher;
	}

	/**
	 * @param freiVerantwortlicher the freiVerantwortlicher to set
	 */
	public void setFreiVerantwortlicher(short freiVerantwortlicher) {
		this.freiVerantwortlicher = freiVerantwortlicher;
	}

	/**
	 * @return the freiKoordinator
	 */
	public short getFreiKoordinator() {
		return freiKoordinator;
	}

	/**
	 * @param freiKoordinator the freiKoordinator to set
	 */
	public void setFreiKoordinator(short freiKoordinator) {
		this.freiKoordinator = freiKoordinator;
	}

	/**
	 * @return the freiDekan
	 */
	public short getFreiDekan() {
		return freiDekan;
	}

	/**
	 * @param freiDekan the freiDekan to set
	 */
	public void setFreiDekan(short freiDekan) {
		this.freiDekan = freiDekan;
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
	 * @return the moduleService
	 */
	public ModuleService getModuleService() {
		return moduleService;
	}

	/**
	 * @param moduleService the moduleService to set
	 */
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	/**
	 * @return the voraussetzungenfor
	 */
	public String getVoraussetzungenfor() {
		return voraussetzungenfor;
	}

	/**
	 * @param voraussetzungenfor the voraussetzungenfor to set
	 */
	public void setVoraussetzungenfor(String voraussetzungenfor) {
		this.voraussetzungenfor = voraussetzungenfor;
	}

	/**
	 * @return the voraussetzungenin
	 */
	public String getVoraussetzungenin() {
		return voraussetzungenin;
	}

	/**
	 * @param voraussetzungenin the voraussetzungenin to set
	 */
	public void setVoraussetzungenin(String voraussetzungenin) {
		this.voraussetzungenin = voraussetzungenin;
	}

	/**
	 * @return the turnus
	 */
	public String getTurnus() {
		return turnus;
	}

	/**
	 * @param turnus the turnus to set
	 */
	public void setTurnus(String turnus) {
		this.turnus = turnus;
	}

	/**
	 * @return the wochenstunden
	 */
	public String getWochenstunden() {
		return wochenstunden;
	}

	/**
	 * @param wochenstunden the wochenstunden to set
	 */
	public void setWochenstunden(String wochenstunden) {
		this.wochenstunden = wochenstunden;
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
	 * @return the zeitstempel
	 */
	public Timestamp getZeitstempel() {
		return zeitstempel;
	}

	/**
	 * @param zeitstempel the zeitstempel to set
	 */
	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
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

	/**
	 * @return the treeService
	 */
	public TreeService getTreeService() {
		return treeService;
	}

	/**
	 * @param treeService the treeService to set
	 */
	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	/**
	 * @return the mhbService
	 */
	public ModulhandbuchService getMhbService() {
		return mhbService;
	}

	/**
	 * @param mhbService the mhbService to set
	 */
	public void setMhbService(ModulhandbuchService mhbService) {
		this.mhbService = mhbService;
	}

	/**
	 * @return the studiengangAuswahl
	 */
	public String getStudiengangAuswahl() {
		return studiengangAuswahl;
	}

	/**
	 * @param studiengangAuswahl the studiengangAuswahl to set
	 */
	public void setStudiengangAuswahl(String studiengangAuswahl) {
		this.studiengangAuswahl = studiengangAuswahl;
	}

	/**
	 * @return the abschlussAuswahl
	 */
	public String getAbschlussAuswahl() {
		return abschlussAuswahl;
	}

	/**
	 * @param abschlussAuswahl the abschlussAuswahl to set
	 */
	public void setAbschlussAuswahl(String abschlussAuswahl) {
		this.abschlussAuswahl = abschlussAuswahl;
	}

	/**
	 * @return the pruefungsordnungAuswahl
	 */
	public String getPruefungsordnungAuswahl() {
		return pruefungsordnungAuswahl;
	}

	/**
	 * @param pruefungsordnungAuswahl the pruefungsordnungAuswahl to set
	 */
	public void setPruefungsordnungAuswahl(String pruefungsordnungAuswahl) {
		this.pruefungsordnungAuswahl = pruefungsordnungAuswahl;
	}

	/**
	 * @param studiengaenge the studiengaenge to set
	 */
	public void setStudiengaenge(List<SelectItem> studiengaenge) {
		this.studiengaenge = studiengaenge;
	}

	/**
	 * @param abschluesse the abschluesse to set
	 */
	public void setAbschluesse(List<String> abschluesse) {
		this.abschluesse = abschluesse;
	}

	/**
	 * @param pruefungsordnungen the pruefungsordnungen to set
	 */
	public void setPruefungsordnungen(List<SelectItem> pruefungsordnungen) {
		this.pruefungsordnungen = pruefungsordnungen;
	}
	
}
