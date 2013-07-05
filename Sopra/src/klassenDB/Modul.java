package klassenDB;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Datenbanktabelle fuer die Modultabelle.
 * 
 */
@Entity
public class Modul implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int modulid;
	
	
	private String arbeitsaufwand;

	private String code;

	private short dauer;

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
	
	@Column(unique=true)
	private String modulname;

	private String modulverantwortlicher;

	private String notenbildung;

	private String sprache;
	
	private short freiVerantwortlicher;
	
	private short freiKoordinator;
	
	private short freiDekan;

	private String turnus;

	private int uid;

	private String voraussetzungenfor;

	private String voraussetzungenin;

	private short wochenstunden;
	
	@Column(nullable=false)
	private Timestamp zeitstempel;
	
	@Column(nullable=false)
	private short wahlpflicht;
	
	/**
	 * Standard-Konstruktor.
	 */
	public Modul() {
		}

	/**
	 * Konstruktor der dem Modul einen Namen zuweist.
	 * 
	 * @param Modulname
	 */
	public Modul(String modulname) {
		this.modulname=modulname;
	}
		
	/**
	 * 
	 * @return Wahlpflicht
	 */
	public short getWahlpflicht() {
		return wahlpflicht;
	}
	
	
	/**
	 * @param Wahlpflicht, die gesetzt werden soll
	 */
	public void setWahlpflicht(short wahlpflicht) {
		this.wahlpflicht = wahlpflicht;
	}

	/**
	 * 
	 * @return ModulID
	 */
	public int getModulid() {
		return this.modulid;
	}

	/**
	 * @param ModulID, die gesetzt werden soll
	 */
	public void setModulid(int modulid) {
		this.modulid = modulid;
	}

	/**
	 * 
	 * @return Arbeitsaufwand
	 */
	public String getArbeitsaufwand() {
		return this.arbeitsaufwand;
	}

	/**
	 * @param Arbeitsaufwand, der gesetzt werden soll
	 */
	public void setArbeitsaufwand(String arbeitsaufwand) {
		this.arbeitsaufwand = arbeitsaufwand;
	}

	/**
	 * 
	 * @return Code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param Code, der gesetzt werden soll
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return Dauer
	 */
	public short getDauer() {
		return this.dauer;
	}

	/**
	 * @param Dauer, die gesetzt werden soll
	 */
	public void setDauer(short dauer) {
		this.dauer = dauer;
	}

	/**
	 * 
	 * @return Dozenten
	 */
	public String getDozenten() {
		return this.dozenten;
	}

	/**
	 * @param Dozenten, die gesetzt werden sollen
	 */
	public void setDozenten(String dozenten) {
		this.dozenten = dozenten;
	}

	/**
	 * 
	 * @return Einordnung
	 */
	public String getEinordnung() {
		return this.einordnung;
	}

	/**
	 * @param Einordnung, die gesetzt werden soll
	 */
	public void setEinordnung(String einordnung) {
		this.einordnung = einordnung;
	}

	/**
	 * 
	 * @return Den englischen Modultitel
	 */
	public String getEnglisch() {
		return this.englisch;
	}

	/**
	 * @param Der englische Modultitel
	 */
	public void setEnglisch(String englisch) {
		this.englisch = englisch;
	}

	/**
	 * 
	 * @return GrundlageFuer
	 */
	public String getGrundlagefuer() {
		return this.grundlagefuer;
	}

	/**
	 * @param GrundlageFuer
	 */
	public void setGrundlagefuer(String grundlagefuer) {
		this.grundlagefuer = grundlagefuer;
	}

	/**
	 * 
	 * @return Inhalt
	 */
	public String getInhalt() {
		return this.inhalt;
	}

	/**
	 * @param Inhalt, der gesetzt werden soll
	 */
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	/**
	 * 
	 * @return Lehrformen
	 */
	public String getLehrformen() {
		return this.lehrformen;
	}

	/**
	 * @param Lehrformen, die gesetzt werden sollen
	 */
	public void setLehrformen(String lehrformen) {
		this.lehrformen = lehrformen;
	}

	/**
	 * 
	 * @return Leistungsnachweis
	 */
	public String getLeistungsnachweis() {
		return this.leistungsnachweis;
	}

	/**
	 * @param Leistungsnachweise, die gesetzt werden sollen
	 */
	public void setLeistungsnachweis(String leistungsnachweis) {
		this.leistungsnachweis = leistungsnachweis;
	}

	/**
	 * 
	 * @return Leistungspunkte
	 */
	public String getLeistungspunkte() {
		return this.leistungspunkte;
	}

	/**
	 * @param Leistungspunkte die man fuer das Modul erhaelt
	 */
	public void setLeistungspunkte(String leistungspunkte) {
		this.leistungspunkte = leistungspunkte;
	}

	/**
	 * 
	 * @return Lernziele
	 */
	public String getLernziele() {
		return this.lernziele;
	}

	/**
	 * @param Lernzieledes Moduls
	 */
	public void setLernziele(String lernziele) {
		this.lernziele = lernziele;
	}

	/**
	 * 
	 * @return Literatur
	 */
	public String getLiteratur() {
		return this.literatur;
	}

	/**
	 * @param Literaturvorschlaege fuer das Modul
	 */
	public void setLiteratur(String literatur) {
		this.literatur = literatur;
	}

	/**
	 * 
	 * @return Modulname
	 */
	public String getModulname() {
		return this.modulname;
	}

	/**
	 * @param Modulname, der gesetzt werden soll
	 */
	public void setModulname(String modulname) {
		this.modulname = modulname;
	}

	/**
	 * 
	 * @return Modulverantwortlicher
	 */
	public String getModulverantwortlicher() {
		return this.modulverantwortlicher;
	}

	/**
	 * @param Modulverantworlicher, der fuer das Modul verantwortlich ist
	 */
	public void setModulverantwortlicher(String modulverantwortlicher) {
		this.modulverantwortlicher = modulverantwortlicher;
	}

	/**
	 * 
	 * @return Notenbildung
	 */
	public String getNotenbildung() {
		return this.notenbildung;
	}

	/**
	 * @param Notenbildung, die gesetzt werden soll
	 */
	public void setNotenbildung(String notenbildung) {
		this.notenbildung = notenbildung;
	}

	/**
	 * Listet die Sprachen auf in denen das Modul angeboten wird.
	 * 
	 * @return Sprache
	 */
	public String getSprache() {
		return this.sprache;
	}

	/**
	 * @param Sprache, in der das Modul gehalten wird
	 */
	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	/**
	 * 
	 * @return Turnus
	 */
	public String getTurnus() {
		return this.turnus;
	}

	/**
	 * @param Turnus, in dem das Modul angeboten wird
	 */
	public void setTurnus(String turnus) {
		this.turnus = turnus;
	}

	/**
	 * Speichert den Benutzer der das Modul erstellt hat.
	 * 
	 * @return UserID
	 */
	public int getUid() {
		return this.uid;
	}

	/**
	 * @param Die ID des Benutzers, der das Modul erstellt hat
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * 
	 * @return VoraussetzungenFor
	 */
	public String getVoraussetzungenfor() {
		return this.voraussetzungenfor;
	}

	/**
	 * @param VoraussetzungenFor, die gesetzt werden sollen
	 */
	public void setVoraussetzungenfor(String voraussetzungenfor) {
		this.voraussetzungenfor = voraussetzungenfor;
	}

	/**
	 * 
	 * @return VorrausetzungenIn
	 */
	public String getVoraussetzungenin() {
		return this.voraussetzungenin;
	}

	/**
	 * @param VoraussetzungenIn, die gesetzt werden sollen
	 */
	public void setVoraussetzungenin(String voraussetzungenin) {
		this.voraussetzungenin = voraussetzungenin;
	}

	/**
	 * 
	 * @return Wochenstunden
	 */
	public short getWochenstunden() {
		return this.wochenstunden;
	}

	/**
	 * @param Anzahl der Wochenstunden fuer das Modul
	 */
	public void setWochenstunden(short wochenstunden) {
		this.wochenstunden = wochenstunden;
	}

	/**
	 * 
	 * @return Zeitstempel
	 */
	public Timestamp getZeitstempel() {
		return this.zeitstempel;
	}

	/**
	 * Der Zeitstempel wird beim Erstellen und Updaten des Moduls automatisch auf den
	 * aktuellen Wert gesetzt.
	 * 
	 * @param Zeitstempel 
	 */
	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}
	
	/**
	 * Wenn dieses Attribut auf 1 gesetzt ist, ist das Modul vom Modulverantworlichen freigegeben.
	 * 
	 * @return freiVerantwortlicher
	 */
	public short getFreiVerantwortlicher() {
		return freiVerantwortlicher;
	}
	
	/**
	 * @param FreiVerantworlicher
	 */
	public void setFreiVerantwortlicher(short freiVerantwortlicher) {
		this.freiVerantwortlicher = freiVerantwortlicher;
	}
	
	/**
	 * Wenn dieses Attribut auf 1 gesetzt ist, ist das Modul vom Koordinator freigegeben.
	 * 
	 * @return freiKoordinator
	 */
	public short getFreiKoordinator() {
		return freiKoordinator;
	}
	
	/**
	 * @param freiKoordinator
	 */
	public void setFreiKoordinator(short freiKoordinator) {
		this.freiKoordinator = freiKoordinator;
	}
	
	/**
	 * Wenn dieses Attribut auf 1 gesetzt ist, ist das Modul vom Dekan freigegeben.
	 * 
	 * @return freiDekan
	 */
	public short getFreiDekan() {
		return freiDekan;
	}
	
	/**
	 * @param freiDekan
	 */
	public void setFreiDekan(short freiDekan) {
		this.freiDekan = freiDekan;
	}
	
	public String toString(){
		return modulname;
	}
	
}