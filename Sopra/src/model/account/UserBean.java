package model.account;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import klassenDB.User;

public class UserBean {
	User myself = null;
	String email = "Enter Name";
	String passwort = "Enter Passwort";
	private String[] rechtetyp = {"Basic", "Dekan", "Dez2", "blabla"};

	
	public String logMeIn(){
		if(!email.isEmpty()&&!passwort.isEmpty()){
			passwort=new Kodierer().code(passwort);
			//myself = new DBMethoden().login(email, passwort);
			//Fake User
			myself = new User(1111, "email", "fakultaet", "name",
					"passwort", 0, "vorname");
		}
		if(myself==null){
			return "";
		}
		//temporaere Welcome Seite
		return "modulsuche";
	}
	
	public String logout(){
		myself=null;
		email="Enter email";
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

}
