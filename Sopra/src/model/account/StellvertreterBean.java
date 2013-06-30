package model.account;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import klassenDB.User;

@ManagedBean(name="hinzufuegen")
@SessionScoped
public class StellvertreterBean {
	public StellvertreterBean() {	
		super();
	}

	@EJB
	UserService userService;
	
	private List<User> users;
	private List<String> selectedUsers ;
	
	private List<Stellvertreter> stellvertreter;
	
	
	public String selectStellvertreter(){
		for(String s:selectedModule){
			System.out.println("Loesche Modul: " +s);
		}
		return "StellvertreterAuswaehlen";
	}
	
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		users = userService.getAllUsers();
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		System.out.println("set users");
		this.users = users;
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
	 * @return the stellvertreter
	 */
	public List<Stellvertreter> getStellvertreter() {
		stellvertreter = userService.getAllStellvertreter;
		return stellvertreter;
	}

	/**
	 * @param stellvertreter the stellvertreter to set
	 */
	public void setStellvertreter(List<Stellvertreter> stellvertreter) {
		System.out.println("set stellvertreter");
		this.stellvertreter = stellvertreter;
	}
	
	
}
