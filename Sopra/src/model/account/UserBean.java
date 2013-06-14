package model.account;

import klassenDB.User;

public class UserBean {
	String myName="Heinz Dieter";
	User myself = null;
	String email = "Enter Name";
	String passwort = "Enter Passwort";
	private String[] rechtetyp = {"Basic", "Dekan", "Dez2", "blabla"};
	
	public String logMeIn(){
		if(!email.isEmpty()&&!passwort.isEmpty()){
			passwort=new Kodierer().code(passwort);
			//myself = new DBMethoden().login(email, passwort);
			//Fake User
			myself = new User( 1111,"eMail", "name", "vorname", 1, "fakultaet");
		}
		if(myself==null){
			return "";
		}
		//temporaere Welcome Seite
		return "modulsuche";
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
	 * @return the myName
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * @param myName the myName to set
	 */
	public void setMyName(String myName) {
		this.myName = myName;
	}
}
