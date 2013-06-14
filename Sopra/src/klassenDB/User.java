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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int uid;
	
	@Column(nullable=false, unique=true)
	private String email;
	@Column(nullable=false)
	private String fakultaet;
	@Column(nullable=false)
	private String name;
<<<<<<< HEAD
	@Column(nullable=false)
=======
	private String vorname;
>>>>>>> 3b7f1d8a172ca5b409c73cebfbe7b1e29a102d31
	private String passwort;
	@Column(nullable=false)
	private int rolle;
	@Column(nullable=false)
	private String vorname;

	public User() {
	}
<<<<<<< HEAD

	
	public User(int uid, String email, String fakultaet, String name,
			String passwort, int rolle, String vorname) {
		super();
		this.uid = uid;
		this.email = email;
		this.fakultaet = fakultaet;
		this.name = name;
		this.passwort = passwort;
=======
	public User( int id,String eMail,String name, String vorname, int rolle, String fakultaet) {
		this.id = id;
		this.name = name;
		this.vorname = vorname;
//		this.passwort = passwort;
		this.eMail = eMail;
>>>>>>> 3b7f1d8a172ca5b409c73cebfbe7b1e29a102d31
		this.rolle = rolle;
		this.vorname = vorname;
	}

<<<<<<< HEAD

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
=======
	
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}
	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	/**
	 * @return the rechtetyp
	 */
	public String[] getRechtetyp() {
		return rechtetyp;
	}
	public String getVorName() {
		return vorname;
	}
	public void setVorName(String vorName) {
		this.vorname = vorName;
>>>>>>> 3b7f1d8a172ca5b409c73cebfbe7b1e29a102d31
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
