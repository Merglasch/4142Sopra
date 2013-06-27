package model.account;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import klassenDB.Benachrichtigung;
import klassenDB.Modul;
import klassenDB.User;
import model.modules.ModuleService;

@ManagedBean(name="loesch")
@SessionScoped
public class LoeschBean {
	public LoeschBean(){
		super();
		
		users = userService.getAllUsers();
		module = moduleService.getAllModules();
		
		benachrichtigungen = new LinkedList<Benachrichtigung>();
		benachrichtigungen.add(new Benachrichtigung("Spam mail"));
		benachrichtigungen.add(new Benachrichtigung("noch mehr spam"));
		benachrichtigungen.add(new Benachrichtigung("etwas wichtiges"));
		benachrichtigungen.add(new Benachrichtigung("KOSTENLOSERR URLAUB ?"));
		benachrichtigungen.add(new Benachrichtigung("Hallo Welt =)"));
	}
	
	@EJB
	private UserService userService;
	@EJB
	private ModuleService moduleService;
	
	public List<User> users;
	public List<User> selectedUsers;
	public List<Modul> module;
	public List<Modul> selectedModule;
	public List<Benachrichtigung> benachrichtigungen;
	public List<Benachrichtigung> selectedBenachrichtigungen;
	
	//Methoden für Benutzer löschen
	public String benutzerLoeschen(){
		//DB Methoden
		userService.deleteUser(selectedUsers);
		return "benutzerLoeschen";
	}
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
 
	public List<Modul> getModule() {
		return moduleService.getAllModules();
	}

	public void setModule(List<Modul> module) {
		this.module = module;
	}
	
	public String moduleLoeschen(){
		//DBMethodenaufruf
		// deleteModule
		moduleService.deleteModule(selectedModule);
		return "modulLoeschen";
	}
	
	
	public List<Benachrichtigung> getBenachrichtigungen() {
		return benachrichtigungen;
	}
	public String benachrichtigungLoeschen(){
		// DB Methode
		// deleteBenachrichtigung
		
		// TODO Auto-generated method stub
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
