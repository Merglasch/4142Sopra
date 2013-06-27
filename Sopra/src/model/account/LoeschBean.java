package model.account;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import klassenDB.Benachrichtigung;
import klassenDB.Modul;
import klassenDB.User;
import model.modules.ModuleService;

@ManagedBean(name="loesch")
@SessionScoped
public class LoeschBean  {
	public LoeschBean(){
		super();
	}
	
	@EJB
	UserService userService;
	@EJB
	ModuleService moduleService;
	
	private List<User> users;
	private List<String> selectedUsers ;
	
	private List<Modul> module;
	private List<String> selectedModule;
	
	private List<Benachrichtigung> benachrichtigungen;
	private List<String> selectedBenachrichtigungen;
	
	
	
	//Methoden für Benutzer löschen
	public String benutzerLoeschen(){
		//DB Methoden
		for(String s : selectedUsers){
			System.out.println("zu Loeschen benutzer: " + s);
		}
		// TODO Kommentare entfernen wenn delete methode steht 
//		userService.deleteUser(auswahl);
		
		return "benutzerLoeschen";
	}
	
	public List<User> getUsers() {
//		System.out.println("get users");
		users = userService.getAllUsers();
		return users;
	}

	public void setUsers(List<User> users) {
		System.out.println("set users");
		this.users = users;
	}
	
	
	public List<Modul> getModule() {
		module = moduleService.getAllModules();
		return module;
	}

	public void setModule(List<Modul> module) {
		this.module = module;
	}
	
	public String moduleLoeschen(){
		//DBMethodenaufruf
		for(String s:selectedModule){
			System.out.println("Loesche Modul: " +s);
		}
		//TODO 
		//moduleService.deleteModule(selectedModule);
		return "modulLoeschen";
	}
	
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
		for(String s : selectedBenachrichtigungen){
			System.out.println("Loesche Benachrichtigung ID :" + s);
		}
		// TODO Auto-generated method stub
		// benachrichtigungsservice delete nachrichtid
		
		return "benachrichtigungLoeschen";
	}

	public void setBenachrichtigungen(List<Benachrichtigung> benachrichtigungen) {
		this.benachrichtigungen = benachrichtigungen;
	}

	public List<String> getSelectedModule() {
		return selectedModule;
	}

	public void setSelectedModule(List<String> selectedModule) {
		this.selectedModule = selectedModule;
	}

	public List<String> getSelectedBenachrichtigungen() {
		return selectedBenachrichtigungen;
	}

	public void setSelectedBenachrichtigungen(
			List<String> selectedBenachrichtigungen) {
		this.selectedBenachrichtigungen = selectedBenachrichtigungen;
	}

	public List<String> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<String> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
}
