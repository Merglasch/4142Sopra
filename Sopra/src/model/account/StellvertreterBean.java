package model.account;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import klassenDB.User;

public class StellvertreterBean {
	public StellvertreterBean() {	
		super();
	}

	@EJB
	UserService userService;
	
	@EJB
	StellvertreterService svService;
	
	private List<User> users;
	private List<String> selectedUsers ;
	
	private List<User> stellvertreter;
	private User hauptPers;
	private boolean stellvertreterErfolgreich=false;
	

	public String selectStellvertreter(){
		User tmp = null;
		for(String s:selectedUsers){
			System.out.println("Ausgewaehlte Stellvertreter: " +s);
			tmp=userService.getUser(s);
			if(tmp != null){
				stellvertreterErfolgreich=svService.setStellvertreter(hauptPers, tmp);
				if(!stellvertreterErfolgreich){
					System.out.println("Fehler bei User :" + s);
					return "StellvertreterAuswaehlen";
				}
			}
		}
		return "StellvertreterAuswaehlen";
		
		
	}
	
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		users = new LinkedList<User>();
		List<User> tmp = userService.getAllUsers();
		for(User u : tmp){
			if(!u.getEmail().equals(hauptPers.getEmail()) )	{
				if(u.getRolle()!=3)
					users.add(u);
			}
		}
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
	public List<User> getStellvertreter(User u){
		stellvertreter = svService.getStellvertreter(u);
		return stellvertreter;
	}

	/**
	 * @param stellvertreter the stellvertreter to set
	 */
	public void setStellvertreter(List<User> stellvertreter) {
		System.out.println("set stellvertreter");
		this.stellvertreter = stellvertreter;
	}

	/**
	 * @return the hauptPers
	 */
	public User getHauptPers() {
		return hauptPers;
	}

	/**
	 * @param hauptPers the hauptPers to set
	 */
	public void setHauptPers(User hauptPers) {
		this.hauptPers = hauptPers;
	}
	
	
	/**
	 * @return the stellvertreterErfolgreich
	 */
	public boolean isStellvertreterErfolgreich() {
		return stellvertreterErfolgreich;
	}
	
	/**
	 * @param stellvertreterErfolgreich the stellvertreterErfolgreich to set
	 */
	public void setStellvertreterErfolgreich(boolean stellvertreterErfolgreich) {
		this.stellvertreterErfolgreich = stellvertreterErfolgreich;
	}
}
