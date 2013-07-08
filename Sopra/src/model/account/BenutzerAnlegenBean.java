package model.account;

import javax.ejb.EJB;

import klassenDB.User;

/**
 * Bean zum Anlegen von neuen Benutzern.
 *
 */
public class BenutzerAnlegenBean {

	private String vorname="";
	private String nachname="";
	private String passwort="";
	private String passwortBestaetigen="";
	private String email="";
	private String fakultaet="";
	private String rechtetyp="";
	private String anlegestatus="";
	
	@EJB
	UserService userService;
	
	/**
	 * Konstruktor, der die Standardausgabe festlegt.
	 */
	public BenutzerAnlegenBean() {
		super();
		anlegestatus="Eingaben nicht vollständig";
	}

	/**
	 * Legt einen neuen Benutzer in der Datenbank an.
	 * 
	 * @return setzt die als naechstes aufzurufende Seite auf benutzerAlegen.
	 */
	public String createNewUser(){
		if (vorname.equals("") || nachname.equals("")){
			anlegestatus = "Bitte Namen korrekten eintragen!";
		}else if(passwort.equals("")   ||  !passwort.equals(passwortBestaetigen)){
			anlegestatus = "Passwörter stimmen nicht überein!";
		}else if(email.equals("") ){
			anlegestatus = "Ungültige E-Mail!";
		}else if(rechtetyp.equals("Rolleneinteilung")){
			anlegestatus = "Bitte eine Rolle wählen!";
		}else if(fakultaet.equals("")){
			anlegestatus ="Bitte Fakultät eingeben!";
		}else{
			
			// DB Methode
			// ohne attribut fakultaet
			// create User  liefert true wenn erfolgreich angelegt, else anlegestatus = "fehler"
			
			
			//if createuser liefert true
			User u = new User();
			u.setName(nachname);
			u.setVorname(vorname);
			u.setPasswort(passwort);
			u.setEmail(email);
			u.setFakultaet(fakultaet);
			u.setRolle(Integer.parseInt(rechtetyp)); //0: Autoren / Modulverantwortlicher 1: Koordinatoren / 2: Freigabeberechtigter / Studiendekan 3: Admin
			
			userService.createUser(u);
			anlegestatus = "Benutzer "+nachname+" wurde erfolgreich angelegt!";
			
		}
		
		return "benutzerAnlegen";
	}
	
	
	
	//////////////////////////
	//getter und setter
	//////////////////////////
	
	
	
	/**
	 * 
	 * @return vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * 
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Setzt den Nachnamen auf einen leeren String.
	 * 
	 * @return ""
	 */
	public String getNachname() {
//		return nachname;// wird mit admin name gefuellt
		return "";
	}

	/**
	 * 
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * Setzt das passwort auf einen leeren String.
	 * 
	 * @return ""
	 */
	public String getPasswort() {
		passwort ="";
		return passwort;// raus, da sonst immer das passwort des aktuell eingeloggten admin schon drin steht
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * 
	 * @return passwortBestaetigen
	 */
	public String getPasswortBestaetigen() {
		return passwortBestaetigen;
	}

	/**
	 * 
	 * @param passwortBestaetigen
	 */
	public void setPasswortBestaetigen(String passwortBestaetigen) {
		this.passwortBestaetigen = passwortBestaetigen;
	}

	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		email="";
		return email;// raus, da sonst immer die email des akt eingeloggten admin darin steht
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return rechtetyp
	 */
	public String getRechtetyp() {
		return rechtetyp;
	}

	/**
	 * 
	 * @param rechtetyp
	 */
	public void setRechtetyp(String rechtetyp) {
		this.rechtetyp = rechtetyp;
	}

	/**
	 * 
	 * @return anlegestatus
	 */
	public String getAnlegestatus() {
		return anlegestatus;
	}

	/**
	 * 
	 * @param anlegestatus
	 */
	public void setAnlegestatus(String anlegestatus) {
		this.anlegestatus = anlegestatus;
	}

	/**
	 * 
	 * @return fakultaet
	 */
	public String getFakultaet() {
		return fakultaet;
	}

	/**
	 * 
	 * @param fakultaet
	 */
	public void setFakultaet(String fakultaet) {
		this.fakultaet = fakultaet;
	}
	
	

}
