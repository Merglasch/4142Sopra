package model.account;

public class BenutzerAendernBean {

	private String altespasswort="";
	private String neuespasswort="";
	private String neuespasswortBestaetigen="";
	private String email="";
	private String status="";
	
	public BenutzerAendernBean() {
		super();
		status="bitte daten eingeben";
		//email einlesen, fled fuer namen anzeigen lassen -> namen initialisieren
	}

	public String datenAendern(){
		
//		if( altespasswort.equals(_____aktuelles user passwort___________) ){
		if(true){
			if(neuespasswort.equals(neuespasswortBestaetigen)){
				if(email.equals("") || !email.contains("@")  ){
					status="Bitte korrekte email eingeben";
				}else{
					
					//DB Methode
					// user abspeichern, bzw daten aendern
					
					status="Benutzerdaten geändert";
					
					
				}
			}else{
				status="neues passwort und neues passwort bestaetigen sind nicht identisch";
			}
		}else{
			status="Sry, altes PW passt nicht!";
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
