package model.account;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

import klassenDB.User;
import model.ModulErstellenBean;

public class UserBean implements Serializable{
	User myself = null;
	String email = "";
	String passwort = "";
	private String[] rechtetyp = {"Basic", "Dekan", "Dez2", "blabla"};
	Random rnd = new Random();
	boolean failedLogin =false;
	
	@EJB
	UserService userService;
	
	@ManagedProperty(value="#{modulErstellenBean}")
	private model.ModulErstellenBean moderstellungsService;
	
	@ManagedProperty(value="#{stellvertreterBean}")
	private model.account.StellvertreterBean stellvertreterService;
	
	@ManagedProperty(value="#{modulAendernBean}")
	private model.ModulAendernBean aenderService;

	@ManagedProperty(value="#{benutzerAendernBean}")
	private model.account.BenutzerAendernBean benutzerAendernService;
	
	@ManagedProperty(value="#{loesch}")
	private model.account.LoeschBean loeschService;

	@ManagedProperty(value="#{baumstrukturBean}")
	private model.BaumstrukturBean baumstrukturService;
	
	/**
	 * Fuellt das Erstellungsbean mit den Daten des angemeldeten Users.
	 */
	public void fillErstellungsService(){
		moderstellungsService.setUid(myself.getUid());
		moderstellungsService.setModulverantwortlicher(myself.getVorname()+" "+myself.getName());	
	}
	
	/**
	 * Fuellt das Stellvertreter mit den Daten des angemeldeten Users.
	 */
	public void fillStellvertreterService(){
		stellvertreterService.setHauptPers(myself);
	}
	
	private void fillAenderService(){
		aenderService.setRolle(myself.getRolle());
		aenderService.setAktUserID(myself.getUid());
		benutzerAendernService.setEmail(myself.getEmail());
		benutzerAendernService.setStatus("");
		benutzerAendernService.setVorname(myself.getVorname());
		benutzerAendernService.setName(myself.getName());
		benutzerAendernService.setNewMe(myself);	
	}
	
	/**
	 * Fuellt das Loeschbean mit den Daten des angemeldeten Users.
	 */
	private void fillLoeschService(){
		loeschService.setAktUser(myself);
	}
	
	/**
	 * Fuellt das Baumbstrukturbean mit den Daten des angemeldeten Users.
	 */
	private void fillBaumService(){
		baumstrukturService.setMyself(myself);
		baumstrukturService.fillTree();
	}
	
	/**
	 * Login-Methode. Setzt die angezeigte Seite das neue Interface auf Grund der Rechte des Nutzers und ruft die Datenbank-Login-Methode auf.
	 * 
	 * @return setzt die naechste aufzurufende Seite auf login
	 */
	public String logMeIn(){
		if(!email.isEmpty()&&!passwort.isEmpty()){
			passwort=new Kodierer().code(passwort);
			myself = userService.login(email, passwort);
			failedLogin=false;
			
		}
		if(myself==null){
			failedLogin=true;
			return "";
		}
		else{
			fillErstellungsService();
			fillStellvertreterService();
			fillAenderService();
			fillLoeschService();
			fillBaumService();
		}
		//zur Welcome Seite
		return "login";
	}
	
	/**
	 * Logout-Methode. Loggt den Benutzer aus und beendet die Session.
	 * 
	 * @return setzt die als naechstes aufzurufende Seite auf login
	 */
	public String logout(){
		myself=null;
		email="";
		passwort="";
		return "login";
	}

	/**
	 * @return the myself
	 */
	public User getMyself() {
		return myself;
	}

	/**
	 * @param myself the myself to set
	 */
	public void setMyself(User myself) {
		this.myself = myself;
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
	 * @return the passwort
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * @param passwort the passwort to set
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * @return the failedLogin
	 */
	public boolean isFailedLogin() {
		return failedLogin;
	}

	/**
	 * @param failedLogin the failedLogin to set
	 */
	public void setFailedLogin(boolean failedLogin) {
		this.failedLogin = failedLogin;
	}

	/**
	 * 
	 * @return modulerstellungsService
	 */
	public model.ModulErstellenBean getModerstellungsService() {
		return moderstellungsService;
	}

	/**
	 * 
	 * @param moderstellungsService
	 */
	public void setModerstellungsService(
			model.ModulErstellenBean moderstellungsService) {
		this.moderstellungsService = moderstellungsService;
	}

	/**
	 * 
	 * @return stellvertreterService
	 */
	public model.account.StellvertreterBean getStellvertreterService() {
		return stellvertreterService;
	}

	/**
	 * 
	 * @param stellvertreterService
	 */
	public void setStellvertreterService(
			model.account.StellvertreterBean stellvertreterService) {
		this.stellvertreterService = stellvertreterService;
	}

	/**
	 * 
	 * @return aenderService
	 */
	public model.ModulAendernBean getAenderService() {
		return aenderService;
	}

	/**
	 * 
	 * @param aenderService
	 */
	public void setAenderService(model.ModulAendernBean aenderService) {
		this.aenderService = aenderService;
	}

	/**
	 * 
	 * @return userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * 
	 * @param userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @return benutzerAendernService
	 */
	public model.account.BenutzerAendernBean getBenutzerAendernService() {
		return benutzerAendernService;
	}

	/**
	 * 
	 * @param benutzerAendernService
	 */
	public void setBenutzerAendernService(
			model.account.BenutzerAendernBean benutzerAendernService) {
		this.benutzerAendernService = benutzerAendernService;
	}

	/**
	 * 
	 * @return loeschService
	 */
	public model.account.LoeschBean getLoeschService() {
		return loeschService;
	}

	/**
	 * 
	 * @param loeschService
	 */
	public void setLoeschService(model.account.LoeschBean loeschService) {
		this.loeschService = loeschService;
	}

	/**
	 * 
	 * @return baumstrukturService
	 */
	public model.BaumstrukturBean getBaumstrukturService() {
		return baumstrukturService;
	}

	/**
	 * 
	 * @param baumstrukturService
	 */
	public void setBaumstrukturService(model.BaumstrukturBean baumstrukturService) {
		this.baumstrukturService = baumstrukturService;
	}

}
