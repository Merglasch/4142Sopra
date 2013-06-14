package klassenDB;

public class Modul {


	private String modulname;
	private String code;
	private String englisch;
	private String leistungspunkte;
	private int wochenstunden;
	private String sprache;
	private String turnus;
	private int dauer;
	private String modulverandwortlicher;
	private String einordnung;
	private String voraussetzungenIn;
	private String lernziehle;
	private String inhalt;
	private String literatur;
	private String grundlagefuer;
	private String lehrformen;
	private String arbeitsaufwand;
	private String leistungsnachweis;
	private String voraussetzungenFor;
	private String notenbildung;
	private String stichtag;
	private String zeitstempel;
	private boolean dezernat;

	public Modul() {
	}

	public Modul(String modulname, String code, String englisch,
			String leistungspunkte, int wochenstunden, String sprache,
			String turnus, int dauer, String modulverandwortlicher,
			String einordnung, String voraussetzungenIn, String lernziehle,
			String inhalt, String literatur, String grundlagefuer,
			String lehrformen, String arbeitsaufwand, String leistungsnachweis,
			String voraussetzungenFor, String notenbildung, String stichtag,
			String zeitstempel, boolean dezernat) {
		super();
		this.modulname = modulname;
		this.code = code;
		this.englisch = englisch;
		this.leistungspunkte = leistungspunkte;
		this.wochenstunden = wochenstunden;
		this.sprache = sprache;
		this.turnus = turnus;
		this.dauer = dauer;
		this.modulverandwortlicher = modulverandwortlicher;
		this.einordnung = einordnung;
		this.voraussetzungenIn = voraussetzungenIn;
		this.lernziehle = lernziehle;
		this.inhalt = inhalt;
		this.literatur = literatur;
		this.grundlagefuer = grundlagefuer;
		this.lehrformen = lehrformen;
		this.arbeitsaufwand = arbeitsaufwand;
		this.leistungsnachweis = leistungsnachweis;
		this.voraussetzungenFor = voraussetzungenFor;
		this.notenbildung = notenbildung;
		this.stichtag = stichtag;
		this.zeitstempel = zeitstempel;
		this.dezernat = dezernat;
	}

	
	
	//getters and setters
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

	public String getEnglisch() {
		return englisch;
	}

	public void setEnglisch(String englisch) {
		this.englisch = englisch;
	}

	public String getLeistungspunkte() {
		return leistungspunkte;
	}

	public void setLeistungspunkte(String leistungspunkte) {
		this.leistungspunkte = leistungspunkte;
	}

	public int getWochenstunden() {
		return wochenstunden;
	}

	public void setWochenstunden(int wochenstunden) {
		this.wochenstunden = wochenstunden;
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

	public int getDauer() {
		return dauer;
	}

	public void setDauer(int dauer) {
		this.dauer = dauer;
	}

	public String getModulverandwortlicher() {
		return modulverandwortlicher;
	}

	public void setModulverandwortlicher(String modulverandwortlicher) {
		this.modulverandwortlicher = modulverandwortlicher;
	}

	public String getEinordnung() {
		return einordnung;
	}

	public void setEinordnung(String einordnung) {
		this.einordnung = einordnung;
	}

	public String getVoraussetzungenIn() {
		return voraussetzungenIn;
	}

	public void setVoraussetzungenIn(String voraussetzungenIn) {
		this.voraussetzungenIn = voraussetzungenIn;
	}

	public String getLernziehle() {
		return lernziehle;
	}

	public void setLernziehle(String lernziehle) {
		this.lernziehle = lernziehle;
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public String getLiteratur() {
		return literatur;
	}

	public void setLiteratur(String literatur) {
		this.literatur = literatur;
	}

	public String getGrundlagefuer() {
		return grundlagefuer;
	}

	public void setGrundlagefuer(String grundlagefuer) {
		this.grundlagefuer = grundlagefuer;
	}

	public String getLehrformen() {
		return lehrformen;
	}

	public void setLehrformen(String lehrformen) {
		this.lehrformen = lehrformen;
	}

	public String getArbeitsaufwand() {
		return arbeitsaufwand;
	}

	public void setArbeitsaufwand(String arbeitsaufwand) {
		this.arbeitsaufwand = arbeitsaufwand;
	}

	public String getLeistungsnachweis() {
		return leistungsnachweis;
	}

	public void setLeistungsnachweis(String leistungsnachweis) {
		this.leistungsnachweis = leistungsnachweis;
	}

	public String getVoraussetzungenFor() {
		return voraussetzungenFor;
	}

	public void setVoraussetzungenFor(String voraussetzungenFor) {
		this.voraussetzungenFor = voraussetzungenFor;
	}

	public String getNotenbildung() {
		return notenbildung;
	}

	public void setNotenbildung(String notenbildung) {
		this.notenbildung = notenbildung;
	}

	public String getStichtag() {
		return stichtag;
	}

	public void setStichtag(String stichtag) {
		this.stichtag = stichtag;
	}

	public String getZeitstempel() {
		return zeitstempel;
	}

	public void setZeitstempel(String zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	public boolean isDezernat() {
		return dezernat;
	}

	public void setDezernat(boolean dezernat) {
		this.dezernat = dezernat;
	}

	
	
	
}
