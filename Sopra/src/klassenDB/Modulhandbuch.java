package klassenDB;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * Datenbankklasse fuer die Modulhandbuchtabelle.
 * 
 */
@Entity
public class Modulhandbuch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int handbuchid;

	private String abschluss;

	private short freigegeben;

	private String pruefungsordnung;

	private String studiengang;

	private int uid;
	
	private short veroeffentlicht;
	
	@Column(nullable=false)
	private Timestamp zeitstempel;

	/**
	 * Standard-Konstruktor der Modulhandbuchklasse.
	 */
	public Modulhandbuch() {
	}

	/**
	 * 
	 * @return HandbuchID
	 */
	public int getHandbuchid() {
		return this.handbuchid;
	}

	/**
	 * 
	 * @param HandbuchID 
	 */
	public void setHandbuchid(int handbuchid) {
		this.handbuchid = handbuchid;
	}

	/**
	 * 
	 * @return Abschluss
	 */
	public String getAbschluss() {
		return this.abschluss;
	}

	/**
	 * 
	 * @param Abschluss
	 */
	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}

	/**
	 * 
	 * @return Pruefungsordnung
	 */
	public String getPruefungsordnung() {
		return this.pruefungsordnung;
	}

	/**
	 * 
	 * @param Pruefungsordnung
	 */
	public void setPruefungsordnung(String pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}

	/**
	 * 
	 * @return Studiengang
	 */
	public String getStudiengang() {
		return this.studiengang;
	}

	/**
	 * 
	 * @param Studiengang
	 */
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	/**
	 * 
	 * @return UserID, des Users, der das Modulhandbuch erstellt hat
	 */
	public int getUid() {
		return this.uid;
	}

	/**
	 * 
	 * @param UserID, des Users, der das Modulhandbuch erstellt hat
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * 
	 * @return Zeitstempel, Erstellungszeitpunkt 
	 */
	public Timestamp getZeitstempel() {
		return this.zeitstempel;
	}

	/**
	 * 
	 * @param Zeitstempel, Erstellungszeitpunkt wird festgelegt wenn neuer Tupel erstellt wird
	 */
	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	/**
	 * 
	 * @return veroeffentlicht, Wert der festlegt, ob Modulhandbuch veröffentlicht wurde
	 */
	public short getVeroeffentlicht() {
		return veroeffentlicht;
	}

	/**
	 * 
	 * @param veroeffentlicht, Setzt Wert der festlegt, ob Modulhandbuch veröffentlicht wurde
	 */
	public void setVeroeffentlicht(short veroeffentlicht) {
		this.veroeffentlicht = veroeffentlicht;
	}

	/**
	 * 
	 * @return freigegeben, von Dekan gesetzter Wert für die Erlaubnis zur Freigabe
	 */
	public short getFreigegeben() {
		return freigegeben;
	}

	/**
	 * 
	 * @param freigegeben, von Dekan gesetzter Wert für die Erlaubnis zur Freigabe
	 */
	public void setFreigegeben(short freigegeben) {
		this.freigegeben = freigegeben;
	}

}