package model.account;

import javax.ejb.EJB;

import klassenDB.User;

public class BenutzerAnlegenBean {

	private String vorname="";
	private String nachname="";
	private String passwort="";
	private String passwortBestaetigen="";
	private String email="";
	private String rechtetyp="";
	private String anlegestatus="";
	
	@EJB
	UserService userService;
	
	public BenutzerAnlegenBean() {
		super();
		anlegestatus="Eingaben nicht vollständig";
	}

	public String createNewUser(){
		if (vorname.equals("") || nachname.equals("")){
			anlegestatus = "Bitte Namen korrekten eintragen!";
		}else if(passwort.equals("")   ||  !passwort.equals(passwortBestaetigen)){
			anlegestatus = "Passwörter stimmen nicht überein!";
		}else if(email.equals("") ){
			anlegestatus = "Ungültige e-mail";
		}else if(rechtetyp.equals("Rolleneinteilung")){
			anlegestatus = "Bitte eine Rolle wählen!";
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
			//u.setRolle(rechtetyp); umrechnung rechtetyp ind db int repraesentation
			
			userService.createUser(u);
			anlegestatus = "Benutzer "+nachname+" wurde erfolgreich angelegt!";
			
			//userService.createUser(u);
		}
		
		return "benutzerAnlegen";
	}
	
	
	//getter und setter
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPasswortBestaetigen() {
		return passwortBestaetigen;
	}

	public void setPasswortBestaetigen(String passwortBestaetigen) {
		this.passwortBestaetigen = passwortBestaetigen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRechtetyp() {
		return rechtetyp;
	}

	public void setRechtetyp(String rechtetyp) {
		this.rechtetyp = rechtetyp;
	}


	public String getAnlegestatus() {
		return anlegestatus;
	}

	public void setAnlegestatus(String anlegestatus) {
		this.anlegestatus = anlegestatus;
	}
	
	

}
