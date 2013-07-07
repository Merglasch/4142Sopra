package model.account;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;

import klassenDB.User;

/**
 * Dieses Bean stellt alle Methoden zur Verwaltung der Stellvertreter bereit.
 *
 */
public class StellvertreterBean {
	/**
	 * standartkonstruktor
	 * initialisiert den timer, der die statusmeldung zurueuksetzt
	 */
	public StellvertreterBean() {	
		super();
		timer = new Timer();
		timer.schedule(new MyTimerTask(this), 2000); // 2 sekunden
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
	

	private Timer timer ;
	/**
	 * Liest die ausgewaehlten/ nicht ausgewaehlten User aus und fuegt die entsprechenden Stellvertreterbeziehungen in die Datenbank ein bzw loescht sie heraus.
	 * @return "StellvertreterAuswaehlen" zeigt die gleiche Seite zur Ueberpruefung der Ergebnisse an.
	 */
	public String selectStellvertreter(){
		User tmp = null;
		for(String s:selectedUsers){
			tmp=userService.getUser(s);
			if(tmp != null){
				stellvertreterErfolgreich=svService.setStellvertreter(hauptPers, tmp);
				System.out.println(stellvertreterErfolgreich);
				if(!stellvertreterErfolgreich){
					return "StellvertreterAuswaehlen";
				}
			}
		}
		for(User u:users){
			if(!selectedUsers.contains(u.getEmail())){
				if(svService.isStellvertreter(hauptPers, u)){
					stellvertreterErfolgreich=svService.deleteStellvertreter(hauptPers, u);					
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
	
	/**
	 * 
	 * @author mw59
	 * TimerTask klasse um Statusmeldungen zuruekzusetzten
	 */
	class MyTimerTask extends TimerTask{
		private StellvertreterBean m;
		/**
		 * Konstruktor, erwartet als uebergabeparameter ein Stellvertreterbean
		 * @param m
		 */
		public MyTimerTask(StellvertreterBean m){
			this.m = m;
		}
		/**
		 * Setzt die boolean modulErfolgreich und modulgescheitert auf false zuruek, 
		 * die statusausgabe wird beim erneuten aufrufen der seite wieder ausgeblendet
		 */
		@Override
		public void run(){
			System.out.println("HALLO; ICH BIN EIN TIMER =)");
			m.setStellvertreterErfolgreich(false);
//			timer.cancel();
		}
	}
}
