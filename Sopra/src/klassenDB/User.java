package klassenDB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int uid;
	
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String fakultaet;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String passwort;
	@Column(nullable=false)
	private int rolle;
	@Column(nullable=false)
	private String vorname;

	public User() {
	}

	public User(int uid, String email, String fakultaet, String name,
			String passwort, int rolle, String vorname) {
		super();
		this.uid = uid;
		this.email = email;
		this.fakultaet = fakultaet;
		this.name = name;
		this.passwort = passwort;
		this.rolle = rolle;
		this.vorname = vorname;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFakultaet() {
		return this.fakultaet;
	}

	public void setFakultaet(String fakultaet) {
		this.fakultaet = fakultaet;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public int getRolle() {
		return this.rolle;
	}

	public void setRolle(int rolle) {
		this.rolle = rolle;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

}