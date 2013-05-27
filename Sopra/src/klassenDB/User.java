package klassenDB;

public class User {
	private int id;  //PK
	private String eMail;
	private String name;
	private String vorName;
	private String passwort;
	private int rolle;
	private String fakultaet;
	private String[] rechtetyp = {"Modulverantwortlicher", "Dekan", "Dez2", "blabla"};
	
	public User() {
	}
	public User( int id,String eMail,String name, String vorName, int rolle, String fakultaet) {
		this.id = id;
		this.name = name;
		this.vorName = vorName;
//		this.passwort = passwort;
		this.eMail = eMail;
		this.rolle = rolle;
		this.fakultaet = fakultaet;
	}
	

	
	
	
	
	public String getVorName() {
		return vorName;
	}
	public void setVorName(String vorName) {
		this.vorName = vorName;
	}
	public int getRolle() {
		return rolle;
	}
	public void setRolle(int rolle) {
		this.rolle = rolle;
	}
	public void setRechtetyp(String[] rechtetyp) {
		this.rechtetyp = rechtetyp;
	}
	// getters and setters
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getFakultaet() {
		return fakultaet;
	}
	public void setFakultaet(String fakultaet) {
		this.fakultaet = fakultaet;
	}
	
}
