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
	public List<User> users;
	public List<User> selectedUsers;
	public List<Modul> module;
	public List<Modul> selectedModule;
	public List<Benachrichtigung> benachrichtigungen;
	public List<Benachrichtigung> selectedBenachrichtigungen;
	
	//Methoden für Benutzer löschen
	public String benutzerLoeschen(){
		//DB Methoden
		// deleteUser(selectedUsers); ind DBMethoden
		model.account.DBMethoden.deleteUser(selectedUsers);
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
		//test
		users = new LinkedList<User>();
		users.add(new User("Max","Bauer", "max.bauer@uni.de"));
		users.add(new User("Philipp","Haha","philipp.haha@uni.de"));
		users.add(new User("max","Maier", "hoho.hihi@uni.de"));
		users.add(new User("keiko","was","heiko.was@uni.de"));
		
		//DBMethode, liste von user
		
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	//Methoden für Modul löschen
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
		//test
		module = new LinkedList<Modul>();
		module.add(new Modul("modul 1"));
		module.add(new Modul("ich bin ein Modul"));
		module.add(new Modul("cool, ich auch!!"));
		module.add(new Modul("Freaks..."));
		
		
		//DB Methode, liste von modulen
		
		return module;
	}

	public void setModule(List<Modul> module) {
		this.module = module;
	}
	
	public String moduleLoeschen(){
		//DBMethodenaufruf
		// deleteModule
		model.account.DBMethoden.deleteModul(selectedModule);
		return "modulLoeschen";
	}
	
	//Methoden für Benachrichtungen
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
		//test
		benachrichtigungen = new LinkedList<Benachrichtigung>();
		benachrichtigungen.add(new Benachrichtigung("Spam mail"));
		benachrichtigungen.add(new Benachrichtigung("noch mehr spam"));
		benachrichtigungen.add(new Benachrichtigung("etwas wichtiges"));
		benachrichtigungen.add(new Benachrichtigung("KOSTENLOSERR URLAUB ?"));
		benachrichtigungen.add(new Benachrichtigung("Hallo Welt =)"));
		
		//DBMethode liste benachrichtigungen
		
		return benachrichtigungen;
	}
	public String benachrichtigungLoeschen(){
		// DB Methode
		// deleteBenachrichtigung
		
		model.account.DBMethoden.deleteBenachrichtigung(selectedBenachrichtigungen);
		
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
