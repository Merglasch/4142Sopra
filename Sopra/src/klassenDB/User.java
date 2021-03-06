package klassenDB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//fuer toString darstellung
//	int fuerName=23;
//	int fuerVorname = 23;

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

	/**
	 * Standard-Konstruktor.
	 */
	public User() {
	}
	
	
/*	public String toString(){
		
//		// &nbsp;
//		String s ="";
//		s += name;
//		for(int i = 0; i<23 - name.length(); i++){
//			s += "&nbsp;" ;
//		}
//		
//		s += vorname;
//		for(int i = 0; i<23 - vorname.length(); i++){
//			s += "&nbsp;" ;
//		}
//		
//		s += email;
//		//test fuer ausgabe
//		System.out.println(name +"_"+vorname+"_"+email);
//		return s;
		
		String ausgabe = "";
		ausgabe +=name + "<pre>\t</pre>"+vorname+ "<pre>\t</pre>"+email;
		return ausgabe;
		
	}*/
	
//	public String Ausgabe(){
////		// &nbsp;
////		String s ="";
////		s += name;
////		for(int i = 0; i<23 - name.length(); i++){
////			s += "&nbsp;" ;
////		}
////		
////		s += vorname;
////		for(int i = 0; i<23 - vorname.length(); i++){
////			s += "&nbsp;" ;
////		}
////		
////		s += email;
////		//test fuer ausgabe
////		System.out.println(name +"_"+vorname+"_"+email);
////		return s;
//		
//		String ausgabe = "";
//		ausgabe +=name + "<pre>\t</pre>"+vorname+ "<pre>\t</pre>"+email;
//		return name;
//	}
	
	/**
	 * Konstruktor, der den Benutzer mit allen Informationen fuellt.
	 * 
	 * @param ID
	 * @param Email
	 * @param Fakultaet
	 * @param Name
	 * @param Passwort
	 * @param Rolle
	 * @param Vorname
	 */
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
	
	/**
	 * Kontruktor, der Vorname, Name und Email setzt.
	 * 
	 * @param Vorname
	 * @param Name
	 * @param Email
	 */
	public User(String vorname,String name,  String email) {
		super();
		this.email = email;
		this.name = name;
		this.vorname = vorname;
	}

	/**
	 * 
	 * @return UserID
	 */
	public int getUid() {
		return this.uid;
	}

	/**
	 * 
	 * @param UserID
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * 
	 * @return Email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return Fakultaet
	 */
	public String getFakultaet() {
		return this.fakultaet;
	}

	/**
	 * 
	 * @param Fakultaet
	 */
	public void setFakultaet(String fakultaet) {
		this.fakultaet = fakultaet;
	}

	/**
	 * 
	 * @return Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Passwort
	 */
	public String getPasswort() {
		return this.passwort;
	}

	/**
	 * 
	 * @param Passwort
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * 
	 * @return Rolle
	 */
	public int getRolle() {
		return this.rolle;
	}

	/**
	 * 
	 * @param Rolle
	 */
	public void setRolle(int rolle) {
		this.rolle = rolle;
	}

	/**
	 * 
	 * @return Vorname
	 */
	public String getVorname() {
		return this.vorname;
	}

	/**
	 * 
	 * @param Vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

}