package klassenDB;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MODUL database table.
 * 
 */
@Entity
public class Modul implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int modulid;
	
	private String pruefungsordnung;

	private String studiengang;
	
	private String abschluss;
	
	private String arbeitsaufwand;

	private String code;

	private short dauer;

	private short dezernat;

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
	
	@Column(nullable=false)
	private short freigegeben;

	private String turnus;

	private int uid;

	private String voraussetzungenfor;

	private String voraussetzungenin;

	private short wochenstunden;
	
	@Column(nullable=false)
	private Timestamp zeitstempel;

	//bi-directional many-to-one association to Benachrichtigung
	@OneToMany(mappedBy="modul")
	private List<Benachrichtigung> benachrichtigungs;
	
	@Column(nullable=false)
	private short wahlpflicht;

	/**
	 * @return the wahlpflicht
	 */
	public short getWahlpflicht() {
		return wahlpflicht;
	}
	
	
	/**
	 * @param wahlpflicht the wahlpflicht to set
	 */
	public void setWahlpflicht(short wahlpflicht) {
		this.wahlpflicht = wahlpflicht;
	}
	
	public Modul() {
	}
	
	public Modul(String modulname) {
		this.modulname=modulname;
	}

	public int getModulid() {
		return this.modulid;
	}

	public void setModulid(int modulid) {
		this.modulid = modulid;
	}

	public String getArbeitsaufwand() {
		return this.arbeitsaufwand;
	}

	public void setArbeitsaufwand(String arbeitsaufwand) {
		this.arbeitsaufwand = arbeitsaufwand;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPruefungsordnung() {
		return pruefungsordnung;
	}


	public void setPruefungsordnung(String pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}


	public String getStudiengang() {
		return studiengang;
	}


	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}


	public String getAbschluss() {
		return abschluss;
	}


	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}


	public short getDauer() {
		return this.dauer;
	}

	public void setDauer(short dauer) {
		this.dauer = dauer;
	}

	public short getDezernat() {
		return this.dezernat;
	}

	public void setDezernat(short dezernat) {
		this.dezernat = dezernat;
	}

	public String getDozenten() {
		return this.dozenten;
	}

	public void setDozenten(String dozenten) {
		this.dozenten = dozenten;
	}

	public String getEinordnung() {
		return this.einordnung;
	}

	public void setEinordnung(String einordnung) {
		this.einordnung = einordnung;
	}

	public String getEnglisch() {
		return this.englisch;
	}

	public void setEnglisch(String englisch) {
		this.englisch = englisch;
	}

	public String getGrundlagefuer() {
		return this.grundlagefuer;
	}

	public void setGrundlagefuer(String grundlagefuer) {
		this.grundlagefuer = grundlagefuer;
	}

	public String getInhalt() {
		return this.inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public String getLehrformen() {
		return this.lehrformen;
	}

	public void setLehrformen(String lehrformen) {
		this.lehrformen = lehrformen;
	}

	public String getLeistungsnachweis() {
		return this.leistungsnachweis;
	}

	public void setLeistungsnachweis(String leistungsnachweis) {
		this.leistungsnachweis = leistungsnachweis;
	}

	public String getLeistungspunkte() {
		return this.leistungspunkte;
	}

	public void setLeistungspunkte(String leistungspunkte) {
		this.leistungspunkte = leistungspunkte;
	}

	public String getLernziele() {
		return this.lernziele;
	}

	public void setLernziele(String lernziele) {
		this.lernziele = lernziele;
	}

	public String getLiteratur() {
		return this.literatur;
	}

	public void setLiteratur(String literatur) {
		this.literatur = literatur;
	}

	public String getModulname() {
		return this.modulname;
	}

	public void setModulname(String modulname) {
		this.modulname = modulname;
	}

	public String getModulverantwortlicher() {
		return this.modulverantwortlicher;
	}

	public void setModulverantwortlicher(String modulverantwortlicher) {
		this.modulverantwortlicher = modulverantwortlicher;
	}

	public String getNotenbildung() {
		return this.notenbildung;
	}

	public void setNotenbildung(String notenbildung) {
		this.notenbildung = notenbildung;
	}

	public String getSprache() {
		return this.sprache;
	}

	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	public String getTurnus() {
		return this.turnus;
	}

	public void setTurnus(String turnus) {
		this.turnus = turnus;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getVoraussetzungenfor() {
		return this.voraussetzungenfor;
	}

	public void setVoraussetzungenfor(String voraussetzungenfor) {
		this.voraussetzungenfor = voraussetzungenfor;
	}

	public String getVoraussetzungenin() {
		return this.voraussetzungenin;
	}

	public void setVoraussetzungenin(String voraussetzungenin) {
		this.voraussetzungenin = voraussetzungenin;
	}

	public short getWochenstunden() {
		return this.wochenstunden;
	}

	public void setWochenstunden(short wochenstunden) {
		this.wochenstunden = wochenstunden;
	}

	public Timestamp getZeitstempel() {
		return this.zeitstempel;
	}

	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	public List<Benachrichtigung> getBenachrichtigungs() {
		return this.benachrichtigungs;
	}

	public void setBenachrichtigungs(List<Benachrichtigung> benachrichtigungs) {
		this.benachrichtigungs = benachrichtigungs;
	}

	public Benachrichtigung addBenachrichtigung(Benachrichtigung benachrichtigung) {
		getBenachrichtigungs().add(benachrichtigung);
		benachrichtigung.setModul(this);

		return benachrichtigung;
	}

	public Benachrichtigung removeBenachrichtigung(Benachrichtigung benachrichtigung) {
		getBenachrichtigungs().remove(benachrichtigung);
		benachrichtigung.setModul(null);

		return benachrichtigung;
	}

	
	public short getFreigegeben() {
		return freigegeben;
	}
	public void setFreigegeben(short freigegeben) {
		this.freigegeben = freigegeben;
	}
	public String toString(){
		return modulname;
	}
}