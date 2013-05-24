package klassenDB;

public class User {
//	private String id;
	private String name;
	private String vorName;
	private String passwort;
	private String eMail;
	private int rolle;
	private String[] rechtetyp = {"Modulverantwortlicher", "Dekan", "Dez2", "blabla"};
	
	public User() {
	}
	public User( String vorName, String name, String eMail, int rolle) {
//		this.id = id;
		this.name = name;
		this.vorName = vorName;
//		this.passwort = passwort;
		this.eMail = eMail;
		this.rolle = rolle;
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
	
}
