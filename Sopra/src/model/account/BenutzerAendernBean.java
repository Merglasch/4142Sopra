package model.account;

import javax.ejb.EJB;

import klassenDB.User;

/**
 * Bean zum Aendern der Benutzerdaten.
 *
 */
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
	
	/**
	 * Konstruktor der die Standardausgabe feslegt.
	 */
	public BenutzerAendernBean() {
		super();
		status="bitte Daten eingeben";
		//email einlesen, fled fuer namen anzeigen lassen -> namen initialisieren
	}

	/**
	 * Mit dieser Methode koennen Benutzer ihre Benutzerdaten aendern.
	 * 
	 * @return setzt die als naechstes aufzurufende Seite auf benutzerdatenAendern
	 */
	public String datenAendern(){
		String neu=new Kodierer().code(neuespasswort);
		if(new Kodierer().code(altespasswortEingabe).equals(newMe.getPasswort())){
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
				status="Neue Passwörter stimmen nicht überein!";
			}
		}else{
			status="Altes Passwort inkorrekt";
		}
		
		
		return "benutzerdatenAendern";
	}

	
	
	
	////////////////////////////////
	//Getter und Setter
	////////////////////////////////

	
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the altespasswort
	 */
	public String getAltespasswort() {
		return altespasswort;
	}

	/**
	 * @param altespasswort the altespasswort to set
	 */
	public void setAltespasswort(String altespasswort) {
		this.altespasswort = altespasswort;
	}

	/**
	 * @return the altespasswortEingabe
	 */
	public String getAltespasswortEingabe() {
		return altespasswortEingabe;
	}

	/**
	 * @param altespasswortEingabe the altespasswortEingabe to set
	 */
	public void setAltespasswortEingabe(String altespasswortEingabe) {
		this.altespasswortEingabe = altespasswortEingabe;
	}

	/**
	 * @return the neuespasswort
	 */
	public String getNeuespasswort() {
		return neuespasswort;
	}

	/**
	 * @param neuespasswort the neuespasswort to set
	 */
	public void setNeuespasswort(String neuespasswort) {
		this.neuespasswort = neuespasswort;
	}

	/**
	 * @return the neuespasswortBestaetigen
	 */
	public String getNeuespasswortBestaetigen() {
		return neuespasswortBestaetigen;
	}

	/**
	 * @param neuespasswortBestaetigen the neuespasswortBestaetigen to set
	 */
	public void setNeuespasswortBestaetigen(String neuespasswortBestaetigen) {
		this.neuespasswortBestaetigen = neuespasswortBestaetigen;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the newMe
	 */
	public User getNewMe() {
		return newMe;
	}

	/**
	 * @param newMe the newMe to set
	 */
	public void setNewMe(User newMe) {
		this.newMe = newMe;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	
	
	
	
	
}
