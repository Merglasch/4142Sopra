package model.account;

import javax.ejb.EJB;

import klassenDB.User;

public class BenutzerAendernBean {

	private String vorname="";
	private String name="";
	private String altespasswort="";
	private String altespasswortEingabe="";
	private String neuespasswort="";
	private String neuespasswortBestaetigen="";
	private String email="";
	private String status="";
	private User newMe = null;
	
	@EJB 
	private UserService userService;
	
	public BenutzerAendernBean() {
		super();
		status="bitte Daten eingeben";
		//email einlesen, fled fuer namen anzeigen lassen -> namen initialisieren
	}

	public String datenAendern(){
		String neu=new Kodierer().code(neuespasswort);
		if(new Kodierer().code(altespasswort).equals(newMe.getPasswort())){
			if(neuespasswort.equals(neuespasswortBestaetigen)){
				if(email.equals("") || !email.contains("@")  ){
					status="Bitte korrekte E-Mail eingeben";
				}else{
					
					//DB Methode
					// user abspeichern, bzw daten aendern
					newMe.setEmail(email);
					newMe.setPasswort(neu);
					newMe.setName(name);
					newMe.setVorname(vorname);
					userService.updateUser(newMe);					
					status="Benutzerdaten wurden geändert";
					
					
				}
			}else{
				status="Passwörter stimmen nicht überein";
			}
		}else{
			status="Sorry, altes Passwort passt nicht!";
		}
		
		
		return "benutzerdatenAendern";
	}
	
	
	
	
	
	
	
	
	
	public String getAltespasswort() {
		return altespasswort;
	}

	public void setAltespasswort(String altespasswort) {
		this.altespasswort = altespasswort;
	}

	public String getNeuespasswort() {
		return neuespasswort;
	}

	public void setNeuespasswort(String neuespasswort) {
		this.neuespasswort = neuespasswort;
	}

	public String getNeuespasswortBestaetigen() {
		return neuespasswortBestaetigen;
	}

	public void setNeuespasswortBestaetigen(String neuespasswortBestaetigen) {
		this.neuespasswortBestaetigen = neuespasswortBestaetigen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAltespasswortEingabe() {
		return altespasswortEingabe;
	}

	public void setAltespasswortEingabe(String altespasswortEingabe) {
		this.altespasswortEingabe = altespasswortEingabe;
	}

	public User getNewMe() {
		return newMe;
	}

	public void setNewMe(User newMe) {
		this.newMe = newMe;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
