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
	
	public void fillErstellungsService(){
		moderstellungsService.setUid(myself.getUid());
		moderstellungsService.setModulverantwortlicher(myself.getVorname()+" "+myself.getName());	
	}
	
	public void fillStellvertreterService(){
		stellvertreterService.setHauptPers(myself);
	}
	
	private void fillAenderService(){
		aenderService.setRolle(myself.getRolle());
		aenderService.setAktUserID(myself.getUid());
	}
	
	public String logMeIn(){
		if(!email.isEmpty()&&!passwort.isEmpty()){
			//passwort=new Kodierer().code(passwort);
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
		}
		//temporaere Welcome Seite
		return "login";
	}
	
//	public String makeDaUsa(){
//		userService.createUser();
//		return "modulLoeschen";
//	}
	
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



	public model.ModulErstellenBean getModerstellungsService() {
		return moderstellungsService;
	}

	public void setModerstellungsService(
			model.ModulErstellenBean moderstellungsService) {
		this.moderstellungsService = moderstellungsService;
	}

	public model.account.StellvertreterBean getStellvertreterService() {
		return stellvertreterService;
	}

	public void setStellvertreterService(
			model.account.StellvertreterBean stellvertreterService) {
		this.stellvertreterService = stellvertreterService;
	}

	public model.ModulAendernBean getAenderService() {
		return aenderService;
	}

	public void setAenderService(model.ModulAendernBean aenderService) {
		this.aenderService = aenderService;
	}

}
