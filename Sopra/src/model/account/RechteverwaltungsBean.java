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
	
	
	public RechteverwaltungsBean() {
		super();
	}
	
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
			
			

			System.out.println(userAuswahl);
			
		return "rechteverwaltung2";
	}
	
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
	
	
	
	
	public List<User> getUsers() {
		users = userService.getAllUsers();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUserAuswahl() {
		return userAuswahl;
	}

	public void setUserAuswahl(String userAuswahl) {
		this.userAuswahl = userAuswahl;
	}

	public String getRechtetyp() {
		return rechtetyp;
	}

	public void setRechtetyp(String rechtetyp) {
		this.rechtetyp = rechtetyp;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public String getName() {
		name=selectedUser.getName();
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		vorname=selectedUser.getVorname();
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getEmail() {
		email=selectedUser.getEmail();
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFakultaet() {
		selectedUser.getFakultaet();
		return fakultaet;
	}

	public void setFakultaet(String fakultaet) {
		this.fakultaet = fakultaet;
	}

	public boolean isGeaendert() {
		return geaendert;
	}

	public void setGeaendert(boolean geaendert) {
		this.geaendert = geaendert;
	}

	public String getRechtetypNeu() {
		return rechtetypNeu;
	}

	public void setRechtetypNeu(String rechtetypNeu) {
		this.rechtetypNeu = rechtetypNeu;
	}

	public String getFakultaetNeu() {
		return fakultaetNeu;
	}

	public void setFakultaetNeu(String fakultaetNeu) {
		this.fakultaetNeu = fakultaetNeu;
	}

	public String getNameNeu() {
		return nameNeu;
	}

	public void setNameNeu(String nameNeu) {
		this.nameNeu = nameNeu;
	}

	public void setVornameNeu(String vornameNeu) {
		this.vornameNeu = vornameNeu;
	}
	
	public String getVornameNeu() {
		return vornameNeu;
	}

	public String getEmailNeu() {
		return emailNeu;
	}

	public void setEmailNeu(String emailNeu) {
		this.emailNeu = emailNeu;
	}


}
