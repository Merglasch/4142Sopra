package model.account;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;

import klassenDB.User;

public class RechteverwaltungsBean   {

	private List<User> users;
	private String userAuswahl;//email des ausgewaehlten users
	private User selectedUser;
	private String rechtetyp;
	private String fakultaet;
	
	private String name;
	private String vorname;
	private String email;
	
	private boolean geaendert;
	
	@EJB
	UserService userService;
	
	
	
	
	
	
	
	
	
	public RechteverwaltungsBean() {
		super();
	}
	
	//von rechteverwaltung 1 auf 2
	public  String auswahl(ValueChangeEvent e){
		System.out.println("Methode auswahl");
		geaendert=false;
		userAuswahl=e.getNewValue().toString();
		
			selectedUser = userService.getUser(userAuswahl);// user anhand der auswahl
			name = selectedUser.getName();
			vorname = selectedUser.getVorname();
			
		System.out.println(userAuswahl);
		return "rechteverwaltung1";
	}

	//von rechteverwaltung 2 auf 1
	public String rechteAendern(){
		
		System.out.println("rechte aendern");
		selectedUser.setEmail(email);
		selectedUser.setName(name);
		selectedUser.setVorname(vorname);
		selectedUser.setFakultaet(fakultaet);
		selectedUser.setRolle(Integer.parseInt(rechtetyp));
		
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


}
