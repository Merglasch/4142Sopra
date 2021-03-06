package model.account;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

import klassenDB.User;
import model.stichtag.StichtagService;

/**
 * Das UserBean steuert saemtliche Zugriffe auf die User unseres Systems.
 *
 */
public class UserBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5486557840144149740L;
	User myself = null;
	User mySelfSaver = null;
	User misterX = null;
	List<User> zuStellvertretende;
	String email = "";
	String passwort = "";
	Random rnd = new Random();
	boolean failedLogin =false;
	private String stichtag="";
	private String adminMail="";
	
	@EJB
	StichtagService stService;
	
	@EJB
	UserService userService;
	
	@EJB
	StellvertreterService stellvertreterServiceEJB;
	
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
	
	@ManagedProperty(value="#{modulhandbuchErstellenBean}")
	private model.ModulhandbuchErstellenBean modulhandbuchErstellenService;

	@ManagedProperty(value="#{baumstrukturBean}")
	private model.BaumstrukturBean baumstrukturService;
	
	@ManagedProperty(value="#{modelBean}")
	private model.ModelBean suchService;

	/**
	 * Initialisiert die Email-Adresse des Admins.
	 */
	@PostConstruct
	public void init(){
		//TODO: Hier Datenbankmethode zur Suche nach dem Admin aufrufen
		adminMail="admin@admin.de";
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//Stellvertreter Ansicht Methoden
	//////////////////////////////////////////////////////////////////////////////////

	/**
	 * Wechsel zurueck zu meiner Ansicht.
	 * @return "login" Startseite
	 */
	public String changeToMe(){
		myself=mySelfSaver;
		mySelfSaver=null;
		fillErstellungsService();
		fillStellvertreterService();
		fillAenderService();
		fillLoeschService();
		fillBaumService();
		fillStellvertreterList();
		fillMhErstellenService();
		fillSuchService();
		return "login";
	}
	
	/**
	 * Wechsel zur Ansicht des zu Vertretenden.
	 * @return "login" Startseite
	 */
	public String changeToX(){
		mySelfSaver=myself;
		myself=misterX;
		fillErstellungsService();
		fillStellvertreterService();
		fillAenderService();
		fillLoeschService();
		fillBaumService();
		fillStellvertreterList();
		fillMhErstellenService();
		fillSuchService();
		return "login";
	}
	
	/**
	 * Fuellt die Liste der aktuell vertretbaren Personen.
	 */
	public void fillStellvertreterList(){
		zuStellvertretende = new LinkedList<User>();
		List<Integer> stids=stellvertreterServiceEJB.getHauptPers(myself.getUid());
		for(Integer i:stids){
			zuStellvertretende.add(userService.getUserById(i));
		}
	}

	
	//////////////////////////////////////////////////////////////////////////////////
	//fill Bean Methoden
	//////////////////////////////////////////////////////////////////////////////////

	/**
	 * setzt den aktuellen benutzer ( nicht eingeloggter User ist null) im suchservice
	 */
	public void fillSuchService(){
		suchService.setMyself(myself);
	}
	
	/**
	 * Fuellt das MhErstellenBean mit den Daten des angemeldeten Users.
	 */
	public void fillMhErstellenService(){
		modulhandbuchErstellenService.setMyself(myself);
	}
	
	/**
	 * Fuellt das Erstellungsbean mit den Daten des angemeldeten Users.
	 */	public void fillErstellungsService(){
		moderstellungsService.setUid(myself.getUid());
		moderstellungsService.setModulverantwortlicher(myself.getVorname()+" "+myself.getName());	
	}
	
	/**
	 * Fuellt das Stellvertreter mit den Daten des angemeldeten Users.
	 */
	public void fillStellvertreterService(){
		stellvertreterService.setHauptPers(myself);
		List<String> tmp = new LinkedList<String>();
		List<User> stellvertreter = stellvertreterService.svService.getStellvertreter(myself);
		for(User u:stellvertreter){
			tmp.add(u.getEmail());
		}
		stellvertreterService.setSelectedUsers(tmp);
	}
	
	/**
	 * Fuellt die beiden Aenderbeans mit den Daten des aktuell angemeldeten Users.
	 */
	private void fillAenderService(){
		aenderService.setRolle(myself.getRolle());
		aenderService.setAktUserID(myself.getUid());
		aenderService.setModulFreigegeben(false);
		benutzerAendernService.setEmail(myself.getEmail());
		benutzerAendernService.setStatus("Bitte geben Sie Ihre Daten ein");
		benutzerAendernService.setVorname(myself.getVorname());
		benutzerAendernService.setName(myself.getName());
		benutzerAendernService.setNewMe(myself);	
	}
	
	/**
	 * Fuellt das Loeschbean mit den Daten des angemeldeten Users.
	 */
	private void fillLoeschService(){
		loeschService.setAktUser(myself);
		loeschService.setGeloescht(false);
		loeschService.setNichtGeloescht(false);
	}
	
	/**
	 * Fuellt das Baumbstrukturbean mit den Daten des angemeldeten Users.
	 */
	private void fillBaumService(){
		baumstrukturService.setMyself(myself);
		baumstrukturService.fillTree();
	}
	

	//////////////////////////////////////////////////////////////////////////////////
	//login/logout Methoden
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Login-Methode. Setzt die angezeigte Seite das neue Interface auf Grund der Rechte des Nutzers und ruft die Datenbank-Login-Methode auf.
	 * 
	 * @return setzt die naechste aufzurufende Seite auf login
	 */	public String logMeIn(){
		if(!email.isEmpty()&&!passwort.isEmpty()){
			passwort=new Kodierer().code(passwort);
			email=email.toLowerCase();
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
			fillStellvertreterList();			
			fillMhErstellenService();
			fillSuchService();		}
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
		modulhandbuchErstellenService.setMyself(null);		
		baumstrukturService.setRoot(null);
		baumstrukturService.setAktmodul(null);
		baumstrukturService.setAkthb(null);
		baumstrukturService.setMyself(null);
		baumstrukturService.fillTree();
		zuStellvertretende=null;
		suchService.setMyself(null);
		return "login";
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//Getter und Setter
	//////////////////////////////////////////////////////////////////////////////////


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

	/**
	 * @return the mySelfSaver
	 */
	public User getMySelfSaver() {
		return mySelfSaver;
	}

	/**
	 * @param mySelfSaver the mySelfSaver to set
	 */
	public void setMySelfSaver(User mySelfSaver) {
		this.mySelfSaver = mySelfSaver;
	}

	/**
	 * @return the misterX
	 */
	public User getMisterX() {
		return misterX;
	}

	/**
	 * @param misterX the misterX to set
	 */
	public void setMisterX(User misterX) {
		this.misterX = misterX;
	}

	/**
	 * @return the zuStellvertretende
	 */
	public List<User> getZuStellvertretende() {
		return zuStellvertretende;
	}

	/**
	 * @param zuStellvertretende the zuStellvertretende to set
	 */
	public void setZuStellvertretende(List<User> zuStellvertretende) {
		this.zuStellvertretende = zuStellvertretende;
	}

	/**
	 * @return the stellvertreterServiceEJB
	 */
	public StellvertreterService getStellvertreterServiceEJB() {
		return stellvertreterServiceEJB;
	}

	/**
	 * @param stellvertreterServiceEJB the stellvertreterServiceEJB to set
	 */
	public void setStellvertreterServiceEJB(
			StellvertreterService stellvertreterServiceEJB) {
		this.stellvertreterServiceEJB = stellvertreterServiceEJB;
	}
	/**
	 * @return the modulhandbuchErstellenService
	 */
	public model.ModulhandbuchErstellenBean getModulhandbuchErstellenService() {
		return modulhandbuchErstellenService;
	}

	/**
	 * @param modulhandbuchErstellenService the modulhandbuchErstellenService to set
	 */	
	public void setModulhandbuchErstellenService(
			model.ModulhandbuchErstellenBean modulhandbuchErstellenService) {
		this.modulhandbuchErstellenService = modulhandbuchErstellenService;
	}

	/**
	 * @return the stichtag
	 */
	public String getStichtag() {
		return stichtag;
	}

	/**
	 * @param stichtag the stichtag to set
	 */
	public void setStichtag(String stichtag) {
		this.stichtag = stichtag;
	}

	/**
	 * @return the stService
	 */
	public StichtagService getStService() {
		return stService;
	}

	/**
	 * @param stService the stService to set
	 */
	public void setStService(StichtagService stService) {
		this.stService = stService;
	}

	/**
	 * @return the adminMail
	 */
	public String getAdminMail() {
		return adminMail;
	}

	/**
	 * @param adminMail the adminMail to set
	 */
	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}

	public model.ModelBean getSuchService() {
		return suchService;
	}

	public void setSuchService(model.ModelBean suchService) {
		this.suchService = suchService;
	}

}
