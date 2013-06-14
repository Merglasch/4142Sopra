package klassenDB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BENACHRICHTIGUNG database table.
 * 
 */
@Entity
public class Benachrichtigung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int nachrichtid;
	
	@Column(nullable=false)
	private String betreff;
	
	@Column(nullable=false)
	private String text;
	
	@Column(nullable=false)
	private int uid;

	//bi-directional many-to-one association to Modul
	@ManyToOne
	@JoinColumn(name="MODULID")
	private Modul modul;

	public Benachrichtigung() {
	}

	public int getNachrichtid() {
		return this.nachrichtid;
	}

	public void setNachrichtid(int nachrichtid) {
		this.nachrichtid = nachrichtid;
	}

	public String getBetreff() {
		return this.betreff;
	}

	public void setBetreff(String betreff) {
		this.betreff = betreff;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Modul getModul() {
		return this.modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

}