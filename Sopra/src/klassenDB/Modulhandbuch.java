package klassenDB;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * The persistent class for the MODULHANDBUCH database table.
 * 
 */
@Entity
public class Modulhandbuch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int handbuchid;

	private String abschluss;

	private String dekan;

	private String pruefungsordnung;

	private String studiengang;

	private int uid;
	
	@Column(nullable=false)
	private Timestamp zeitstempel;

	public Modulhandbuch() {
	}

	public int getHandbuchid() {
		return this.handbuchid;
	}

	public void setHandbuchid(int handbuchid) {
		this.handbuchid = handbuchid;
	}

	public String getAbschluss() {
		return this.abschluss;
	}

	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}

	public String getDekan() {
		return this.dekan;
	}

	public void setDekan(String dekan) {
		this.dekan = dekan;
	}

	public String getPruefungsordnung() {
		return this.pruefungsordnung;
	}

	public void setPruefungsordnung(String pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}

	public String getStudiengang() {
		return this.studiengang;
	}

	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Timestamp getZeitstempel() {
		return this.zeitstempel;
	}

	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

}