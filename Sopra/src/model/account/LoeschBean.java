package model.account;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	private boolean aktuelleModuleVorhanden;
	private boolean alteModuleVorhanden;
	
	
	private List<String> selectedBenachrichtigungen;
	
	private boolean geloescht=true;
	private boolean nichtGeloescht=true;
	
	/**
	 * Uebergibt die ausgewaehlten Benutzer an die Datenbankmethode, die diese dann loescht.
	 * 
	 * @return setzt die als naechstes angezeigte Seite auf benutzerloeschen
	 */
	//Methoden f�r Benutzer l�schen
	public String benutzerLoeschen(){
		geloescht =false;
		nichtGeloescht = false;
		//DB Methoden
		try {
			userService.deleteUser(selectedUsers);
			geloescht = true;
		} catch (Exception e) {
			nichtGeloescht=true;
			e.printStackTrace();
		}
		
		return "benutzerLoeschen";
	}
	
	/**
	 * Gibt alle Benutzer aus der Datenbank zurueck und fuellt die Klassenvariable users damit.
	 * 
	 * @return users
	 */
	public List<User> getUsers() {
		users = userService.getAllUsers();
		return users;
	}

	/**
	 * Setzt die Klassenvariable users.
	 * 
	 * @param users
	 */
	public void setUsers(List<User> users) {
		System.out.println("set users");
		this.users = users;
	}
	
	/**
	 * Diese Methode gibt fuer die unterscheidlichen Rollen jeweils alle aktuellen Module zurueck, die diese sehen duerfen.
	 * 
	 * @return moduleAktuell
	 */
	public List<Modul> getModuleAktuell() {
		System.out.println("### Methode: getModuleAktuell");
		System.out.println("### Aktueller User : "+aktUser.getName()+" "+aktUser.getUid()+ " " +aktUser.getRolle());
		if(aktUser.getRolle() == 0){ //Mod verantwortlicher kann seine und die die er stellvertritt aendern
			moduleAktuell = moduleService.getMyModulesAktuell(aktUser.getUid()); // aktuelle uID des bearbeitenden
		}else{ //Koordinator oder dekan  kann alle aendern 
			moduleAktuell = moduleService.getAllModules();
		}
//		aktuelleModuleVorhanden= !moduleAktuell.isEmpty()	;
		aktuelleModuleVorhanden= true	;
		return moduleAktuell;
	}

	/**
	 * Setzt die moduleAktuell Variable 
	 * 
	 * @param moduleAktuell
	 */
	public void setModuleAktuell(List<Modul> moduleAktuell) {
		this.moduleAktuell = moduleAktuell;
	}
	
	/**
	 * Diese Methode gibt fuer die unterscheidlichen Rollen jeweils alle alten Module zurueck, die diese sehen duerfen.
	 * 
	 * @return moduleAlt
	 */
	public List<Modul> getModuleAlt() {
		if(aktUser.getRolle() == 0){ //Mod verantwortlicher kann seine und die die er stellvertritt aendern
			moduleAlt = moduleService.getMyModulesAlt(aktUser.getUid()); // aktuelle uID des bearbeitenden
		}else{ //Koordinator oder dekan  kann alle aendern 
			moduleAlt = moduleService.getAllModules();
		}
		alteModuleVorhanden =!moduleAlt.isEmpty();
		return moduleAlt;
	}
	
	/**
	 * setzt die moduleAlt Variable
	 * 
	 * @param moduleAlt
	 */
	public void setModuleAlt(List<Modul> moduleAlt) {
		this.moduleAlt = moduleAlt;
	}
	
	/**
	 * Loescht alle ausgewaehlten Module aus der Datenbank.
	 * 
	 * @return setzt die als naechstes anzuzeigende Seite auf modulLoeschen
	 */
	public String moduleLoeschen(){
		//DBMethodenaufruf
		System.out.println("##Methode moduleLoeschen");
		List<Modul> zuLoeschen = new LinkedList<Modul>();
		for(String s: selectedModuleAktuell){
			System.out.println("Loesche Modul: " +s);
			zuLoeschen.add(moduleService.searchByModulid(Integer.parseInt(s)));
		}
		for(String s: selectedModuleAlt){
			System.out.println("Loesche Modul: " +s);
			zuLoeschen.add(moduleService.searchByModulid(Integer.parseInt(s)));
		}
		if(!zuLoeschen.isEmpty()){
			moduleService.deleteModule(zuLoeschen);
		}
		
		return "modulLoeschen";
	}

	
	//////////////////////////
	//Getters and Setters
	//////////////////////////
	
	
	
	/**
	 * @return the aktUser
	 */
	public User getAktUser() {
		return aktUser;
	}

	/**
	 * @param aktUser the aktUser to set
	 */
	public void setAktUser(User aktUser) {
		this.aktUser = aktUser;
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
	 * @return the moduleService
	 */
	public ModuleService getModuleService() {
		return moduleService;
	}

	/**
	 * @param moduleService the moduleService to set
	 */
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	/**
	 * @return the selectedUsers
	 */
	public List<String> getSelectedUsers() {
		return selectedUsers;
	}

	/**
	 * @param selectedUsers the selectedUsers to set
	 */
	public void setSelectedUsers(List<String> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	/**
	 * @return the selectedModuleAktuell
	 */
	public List<String> getSelectedModuleAktuell() {
		return selectedModuleAktuell;
	}

	/**
	 * @param selectedModuleAktuell the selectedModuleAktuell to set
	 */
	public void setSelectedModuleAktuell(List<String> selectedModuleAktuell) {
		this.selectedModuleAktuell = selectedModuleAktuell;
	}

	/**
	 * @return the selectedModuleAlt
	 */
	public List<String> getSelectedModuleAlt() {
		return selectedModuleAlt;
	}

	/**
	 * @param selectedModuleAlt the selectedModuleAlt to set
	 */
	public void setSelectedModuleAlt(List<String> selectedModuleAlt) {
		this.selectedModuleAlt = selectedModuleAlt;
	}

	/**
	 * @return the aktuelleModuleVorhanden
	 */
	public boolean isAktuelleModuleVorhanden() {
		return aktuelleModuleVorhanden;
	}

	/**
	 * @param aktuelleModuleVorhanden the aktuelleModuleVorhanden to set
	 */
	public void setAktuelleModuleVorhanden(boolean aktuelleModuleVorhanden) {
		this.aktuelleModuleVorhanden = aktuelleModuleVorhanden;
	}

	/**
	 * @return the alteModuleVorhanden
	 */
	public boolean isAlteModuleVorhanden() {
		return alteModuleVorhanden;
	}

	/**
	 * @param alteModuleVorhanden the alteModuleVorhanden to set
	 */
	public void setAlteModuleVorhanden(boolean alteModuleVorhanden) {
		this.alteModuleVorhanden = alteModuleVorhanden;
	}

	/**
	 * @return the selectedBenachrichtigungen
	 */
	public List<String> getSelectedBenachrichtigungen() {
		return selectedBenachrichtigungen;
	}

	/**
	 * @param selectedBenachrichtigungen the selectedBenachrichtigungen to set
	 */
	public void setSelectedBenachrichtigungen(
			List<String> selectedBenachrichtigungen) {
		this.selectedBenachrichtigungen = selectedBenachrichtigungen;
	}

	/**
	 * @return the geloescht
	 */
	public boolean isGeloescht() {
		return geloescht;
	}

	/**
	 * @param geloescht the geloescht to set
	 */
	public void setGeloescht(boolean geloescht) {
		this.geloescht = geloescht;
	}

	/**
	 * @return the nichtGeloescht
	 */
	public boolean isNichtGeloescht() {
		return nichtGeloescht;
	}

	/**
	 * @param nichtGeloescht the nichtGeloescht to set
	 */
	public void setNichtGeloescht(boolean nichtGeloescht) {
		this.nichtGeloescht = nichtGeloescht;
	}
	
	

}
