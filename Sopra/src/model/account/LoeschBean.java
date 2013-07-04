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
public class LoeschBean  {
	public LoeschBean(){
		super();
	}
	
	private User aktUser;

	
	@EJB
	private UserService userService;
	@EJB
	private ModuleService moduleService;
	
	private List<User> users;
	private List<String> selectedUsers ;
	
	private List<Modul> moduleAktuell;
	private List<String> selectedModuleAktuell; // modulid
	private List<Modul> moduleAlt;
	private List<String> selectedModuleAlt; // modulid
	
	private List<Benachrichtigung> benachrichtigungen;
	private List<String> selectedBenachrichtigungen;
	
	private boolean geloescht;
	private boolean nichtGeloescht;
	
	
	//Methoden für Benutzer löschen
	public String benutzerLoeschen(){
		geloescht =false;
		nichtGeloescht = false;
		//DB Methoden
		for(String s : selectedUsers){
			System.out.println("zu Loeschen benutzer: " + s);
		}
		try {
			userService.deleteUser(selectedUsers);
			geloescht = true;
		} catch (Exception e) {
			nichtGeloescht=true;
			e.printStackTrace();
		}
		
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
	
	
	public List<Modul> getModuleAktuell() {
		moduleAktuell = moduleService.getMyModulesAktuell(aktUser.getUid());
		return moduleAktuell;
	}

	public void setModuleAktuell(List<Modul> moduleAktuell) {
		this.moduleAktuell = moduleAktuell;
	}
	public List<Modul> getModuleAlt() {
		moduleAlt = moduleService.getMyModulesAlt(aktUser.getUid());
		return moduleAlt;
	}
	
	public void setModuleAlt(List<Modul> moduleAlt) {
		this.moduleAlt = moduleAlt;
	}
	
	public String moduleLoeschen(){
		//DBMethodenaufruf
		List<Modul> zuLoeschen = new LinkedList<Modul>();
		for(String s: selectedModuleAktuell){
			System.out.println("Loesche Modul: " +s);
			zuLoeschen.add(moduleService.searchByModulid(Integer.parseInt(s)));
		}
		for(String s: selectedModuleAlt){
			System.out.println("Loesche Modul: " +s);
			zuLoeschen.add(moduleService.searchByModulid(Integer.parseInt(s)));
		}
		moduleService.deleteModule(zuLoeschen);
		return "modulLoeschen";
	}
	
	
	public List<String> getSelectedModuleAlt() {
		return selectedModuleAlt;
	}

	public void setSelectedModuleAlt(List<String> selectedModuleAlt) {
		this.selectedModuleAlt = selectedModuleAlt;
	}
	public List<String> getSelectedModuleAktuell() {
		return selectedModuleAktuell;
	}
	
	public void setSelectedModuleAktuell(List<String> selectedModuleAktuell) {
		this.selectedModuleAktuell = selectedModuleAktuell;
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

	public boolean isGeloescht() {
		return geloescht;
	}

	public void setGeloescht(boolean geloescht) {
		this.geloescht = geloescht;
	}

	public boolean isNichtGeloescht() {
		return nichtGeloescht;
	}

	public void setNichtGeloescht(boolean nichtGeloescht) {
		this.nichtGeloescht = nichtGeloescht;
	}

	public User getAktUser() {
		return aktUser;
	}

	public void setAktUser(User aktUser) {
		this.aktUser = aktUser;
	}
}
