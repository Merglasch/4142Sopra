package model.account;

public class BenutzerAendernBean {

	private String altespasswort="";
	private String neuespasswort="";
	private String neuespasswortBestaetigen="";
	private String email="";
	private String status="";
	
	public BenutzerAendernBean() {
		super();
		status="bitte Daten eingeben";
		//email einlesen, fled fuer namen anzeigen lassen -> namen initialisieren
	}

	public String datenAendern(){
		//TODO
//		if( altespasswort.equals(_____aktuelles user passwort___________) ){
		if(true){
			if(neuespasswort.equals(neuespasswortBestaetigen)){
				if(email.equals("") || !email.contains("@")  ){
					status="Bitte korrekte E-Mail eingeben";
				}else{
					
					//DB Methode
					// user abspeichern, bzw daten aendern
					
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

}
