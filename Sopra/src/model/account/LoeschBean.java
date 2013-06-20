package model.account;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import klassenDB.Benachrichtigung;
import klassenDB.Modul;
import klassenDB.User;

@ManagedBean(name="loesch")
@SessionScoped
public class LoeschBean {
	public LoeschBean(){
		super();
	}
	//Methoden f�r Benutzer l�schen
	public List<User> users;
	public List<User> selectedUsers;
	public List<Modul> module;
	public List<Modul> selectedModule;
	public List<Benachrichtigung> benachrichtigungen;
	public List<Benachrichtigung> selectedBenachrichtigungen;
	
	public String benutzerLoeschen(){
		//DB Fkt zum loeschen
		// deleteUser(selectedUsers); ind DBMethoden
		return "benutzerLoeschen";
	}
	
//	public List<String> getUsersValues(){
//		List<String> val = new LinkedList<String>();
//		
//		for(User u : users){
//			val.add(u.getName()+"&nbsp;&nbsp;"+u.getVorname()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+u.getEmail());
//		}
//		
//		return val;
//	}
	
	public List<User> getUsers() {
		users = new LinkedList<User>();
		users.add(new User("Max","Bauer", "max.bauer@uni.de"));
		users.add(new User("Philipp","Haha","philipp.haha@uni.de"));
		users.add(new User("max","Maier", "hoho.hihi@uni.de"));
		users.add(new User("keiko","was","heiko.was@uni.de"));
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	//Methoden f�r Modul l�schen
//	public List<String> getModuleValues(){
//		module = new LinkedList<String>();
//		module.add("Modul1");
//		module.add("Modul2");
//		module.add("Modul3");
//		module.add("Modul4");
//		
//		return module;
//	}
	public List<Modul> getModule() {
		module = new LinkedList<Modul>();
		module.add(new Modul("modul 1"));
		module.add(new Modul("ich bin ein Modul"));
		module.add(new Modul("cool, ich auch!!"));
		module.add(new Modul("Freaks..."));
		
		return module;
	}

	public void setModule(List<Modul> module) {
		this.module = module;
	}
	
	public String moduleLoeschen(){
		//DBMethodenaufruf
		// deleteModule
		return "modulLoeschen";
	}
	
	//Methoden f�r Benachrichtungen
//	public List<Benachrichtigung> getBenachrichtigungenValues(){
//		benachrichtigungen = new LinkedList<Benachrichtigung>();
//		benachrichtigungen.add(new Benachrichtigung("Spam mail"));
//		benachrichtigungen.add(new Benachrichtigung("noch mehr spam"));
//		benachrichtigungen.add(new Benachrichtigung("etwas wichtiges"));
//		benachrichtigungen.add(new Benachrichtigung("KOSTENLOSERR URLAUB ?"));
//		benachrichtigungen.add(new Benachrichtigung("Hallo Welt =)"));
//		
//		return benachrichtigungen;
//	}
	public List<Benachrichtigung> getBenachrichtigungen() {
		benachrichtigungen = new LinkedList<Benachrichtigung>();
		benachrichtigungen.add(new Benachrichtigung("Spam mail"));
		benachrichtigungen.add(new Benachrichtigung("noch mehr spam"));
		benachrichtigungen.add(new Benachrichtigung("etwas wichtiges"));
		benachrichtigungen.add(new Benachrichtigung("KOSTENLOSERR URLAUB ?"));
		benachrichtigungen.add(new Benachrichtigung("Hallo Welt =)"));
		return benachrichtigungen;
	}
	public String benachrichtigungLoeschen(){
		// DB Methode
		// deleteBenachrichtigung
		
		return "benachrichtigungLoeschen";
	}

	public void setBenachrichtigungen(List<Benachrichtigung> benachrichtigungen) {
		this.benachrichtigungen = benachrichtigungen;
	}

	public List<User> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<User> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public List<Modul> getSelectedModule() {
		return selectedModule;
	}

	public void setSelectedModule(List<Modul> selectedModule) {
		this.selectedModule = selectedModule;
	}

	public List<Benachrichtigung> getSelectedBenachrichtigungen() {
		return selectedBenachrichtigungen;
	}

	public void setSelectedBenachrichtigungen(
			List<Benachrichtigung> selectedBenachrichtigungen) {
		this.selectedBenachrichtigungen = selectedBenachrichtigungen;
	}
}
