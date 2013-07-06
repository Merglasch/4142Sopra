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

	private String dekan;

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
	 * @return Dekan, der fuer das Handbuch zustaendig ist
	 */
	public String getDekan() {
		return this.dekan;
	}

	/**
	 * 
	 * @param Dekan, der fuer das Handbuch zustaendig ist
	 */
	public void setDekan(String dekan) {
		this.dekan = dekan;
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
	 * @return Zeitstempel
	 */
	public Timestamp getZeitstempel() {
		return this.zeitstempel;
	}

	/**
	 * 
	 * @param Zeitstempel
	 */
	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	public short getVeroeffentlicht() {
		return veroeffentlicht;
	}

	public void setVeroeffentlicht(short veroeffentlicht) {
		this.veroeffentlicht = veroeffentlicht;
	}

}