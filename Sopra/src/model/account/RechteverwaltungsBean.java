package model.account;

import java.util.List;

import javax.ejb.EJB;

import klassenDB.User;

public class RechteverwaltungsBean   {

	private List<User> users;
	private String userAuswahl;//email des ausgewaehlten users
	private User selectedUser;
	private User selectedUserBackup; // backup urspr. user daten
	
	private String rechtetyp;
	private String fakultaet;
	private String name;
	private String vorname;
	private String email;
	
	private String rechtetypNeu="0";
	private String fakultaetNeu="alt";
	private String nameNeu="alt";
	private String vornameNeu="alt";
	private String emailNeu="alt";
	
	private boolean geaendert;
	
	@EJB
	UserService userService;
	
	
	/**
	 * Standard-Konstruktor.
	 */
	public RechteverwaltungsBean() {
		super();
	}
	
	/**
	 * Leitet den Benutzer mit den eingegebenen Daten auf die Seite rechteverwaltung2 weiter.
	 * 
	 * @return Setzt die als naechstes angezeigte Seite auf rechteverwaltung2
	 */
	//von rechteverwaltung 1 auf 2
	public  String auswahl(){
		System.out.println("Methode auswahl");
		geaendert=false;
		
		
			selectedUser = userService.getUser(userAuswahl);// user anhand der auswahl
			selectedUserBackup = userService.getUser(userAuswahl);// user anhand der auswahl
			
			name = selectedUser.getName();
			vorname = selectedUser.getVorname();
			email =selectedUser.getEmail();
			fakultaet=selectedUser.getFakultaet();
			
			rechtetyp=rolleToType( selectedUser.getRolle() );
			
		return "rechteverwaltung2";
	}
	
	/**
	 * Gibt die Rolle des aufrufenden Benutzers als String zurueck.
	 * 
	 * @param Rolle als int
	 * @return Rolle als String
	 */
	private String rolleToType(int i){
		String s;
		switch (i) {
		case 0:
			s="Autor/Modulverantwortlicher";
			break;
		case 1:
			s="Koordinatoren";
			break;
		case 2:
			s="Freigabeberechtigter/Studiendekan";
			break;

		default:
			s="Admin";
			break;
		}
		return s;
	}

	/**
	 * Leitet den Benutzer mit den eingegebenen Daten auf die Seite rechteverwaltung1 weiter.
	 * 
	 * @return Setzt die als naechstes angezeigte Seite auf rechteverwaltung1
	 */
	//von rechteverwaltung 2 auf 1
	public String rechteAendern(){
		
		System.out.println("rechte aendern");
		System.out.println("######   pseudo akt werte1  #########");
		System.out.println(email);
		System.out.println(name);
		System.out.println(vorname);
		System.out.println(fakultaet);
		System.out.println(rechtetyp); // rechtetyp 3 geht..
		System.out.println("######   NEU  #########");
		System.out.println(emailNeu);
		System.out.println(nameNeu);
		System.out.println(vornameNeu);
		System.out.println(fakultaetNeu);
		System.out.println(rechtetypNeu);
		
//		selectedUserBackup = userService.getUser(userAuswahl);// user anhand der auswahl
		
		//keine aenderung, nimm backup
		if(!emailNeu.equals("alt")){
			selectedUser.setEmail(emailNeu);
		}else{
			selectedUser.setEmail(email);
		}
		if(!nameNeu.equals("alt")){
			selectedUser.setName(nameNeu);
		}else{
			selectedUser.setName(name);
		}
		if(!vornameNeu.equals("alt")){
			selectedUser.setVorname(vornameNeu);
		}else{
			selectedUser.setName(vorname);
		}
		if(!fakultaetNeu.equals("alt")){
			selectedUser.setFakultaet(fakultaetNeu);
		}else{
			selectedUser.setFakultaet(fakultaet);
		}
		//rechtetyp muss gewaehlt werden
		selectedUser.setRolle(Integer.parseInt(rechtetypNeu));
		
		System.out.println("######   pseudo akt werte 2 #########");
		System.out.println(email);
		System.out.println(name);
		System.out.println(vorname);
		System.out.println(fakultaet);
		System.out.println(rechtetyp);
		
		
		name = selectedUser.getName();
		vorname = selectedUser.getVorname();
		email =selectedUser.getEmail();
		fakultaet=selectedUser.getFakultaet();
		rechtetyp=rolleToType( selectedUser.getRolle() );
		
		System.out.println("######## geaenderte werte ######");
		System.out.println(email);
		System.out.println(name);
		System.out.println(vorname);
		System.out.println(fakultaet);
		System.out.println(rechtetyp);
		
		
		userService.updateUser(selectedUser);
		geaendert=true;
		
		return "rechteverwaltung1";
	}
	
	
	
	/**
	 * 
	 * @return the users
	 */
	public List<User> getUsers() {
		users = userService.getAllUsers();
		return users;
	}

	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		name=selectedUser.getName();
		return name;
	}

	/**
	 * 
	 * @return the vorname
	 */
	public String getVorname() {
		vorname=selectedUser.getVorname();
		return vorname;
	}

	/**
	 * 
	 * @return the email
	 */
	public String getEmail() {
		email=selectedUser.getEmail();
		return email;
	}

	/**
	 * 
	 * @return the fakultaet
	 */
	public String getFakultaet() {
		fakultaet = selectedUser.getFakultaet();
		return fakultaet;
	}

	/**
	 * @return the userAuswahl
	 */
	public String getUserAuswahl() {
		return userAuswahl;
	}

	/**
	 * @param userAuswahl the userAuswahl to set
	 */
	public void setUserAuswahl(String userAuswahl) {
		this.userAuswahl = userAuswahl;
	}

	/**
	 * @return the selectedUser
	 */
	public User getSelectedUser() {
		return selectedUser;
	}

	/**
	 * @param selectedUser the selectedUser to set
	 */
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	/**
	 * @return the selectedUserBackup
	 */
	public User getSelectedUserBackup() {
		return selectedUserBackup;
	}

	/**
	 * @param selectedUserBackup the selectedUserBackup to set
	 */
	public void setSelectedUserBackup(User selectedUserBackup) {
		this.selectedUserBackup = selectedUserBackup;
	}

	/**
	 * @return the rechtetyp
	 */
	public String getRechtetyp() {
		return rechtetyp;
	}

	/**
	 * @param rechtetyp the rechtetyp to set
	 */
	public void setRechtetyp(String rechtetyp) {
		this.rechtetyp = rechtetyp;
	}

	/**
	 * @return the rechtetypNeu
	 */
	public String getRechtetypNeu() {
		return rechtetypNeu;
	}

	/**
	 * @param rechtetypNeu the rechtetypNeu to set
	 */
	public void setRechtetypNeu(String rechtetypNeu) {
		this.rechtetypNeu = rechtetypNeu;
	}

	/**
	 * @return the fakultaetNeu
	 */
	public String getFakultaetNeu() {
		return fakultaetNeu;
	}

	/**
	 * @param fakultaetNeu the fakultaetNeu to set
	 */
	public void setFakultaetNeu(String fakultaetNeu) {
		this.fakultaetNeu = fakultaetNeu;
	}

	/**
	 * @return the nameNeu
	 */
	public String getNameNeu() {
		return nameNeu;
	}

	/**
	 * @param nameNeu the nameNeu to set
	 */
	public void setNameNeu(String nameNeu) {
		this.nameNeu = nameNeu;
	}

	/**
	 * @return the vornameNeu
	 */
	public String getVornameNeu() {
		return vornameNeu;
	}

	/**
	 * @param vornameNeu the vornameNeu to set
	 */
	public void setVornameNeu(String vornameNeu) {
		this.vornameNeu = vornameNeu;
	}

	/**
	 * @return the emailNeu
	 */
	public String getEmailNeu() {
		return emailNeu;
	}

	/**
	 * @param emailNeu the emailNeu to set
	 */
	public void setEmailNeu(String emailNeu) {
		this.emailNeu = emailNeu;
	}

	/**
	 * @return the geaendert
	 */
	public boolean isGeaendert() {
		return geaendert;
	}

	/**
	 * @param geaendert the geaendert to set
	 */
	public void setGeaendert(boolean geaendert) {
		this.geaendert = geaendert;
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

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @param fakultaet the fakultaet to set
	 */
	public void setFakultaet(String fakultaet) {
		this.fakultaet = fakultaet;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	


}
