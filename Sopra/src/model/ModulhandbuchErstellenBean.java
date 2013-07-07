package model;

import java.sql.Timestamp;
import javax.ejb.EJB;
import klassenDB.Modulhandbuch;
import klassenDB.User;
import model.modules.ModulhandbuchService;


public class ModulhandbuchErstellenBean {
	public ModulhandbuchErstellenBean() {
	}
	
	@EJB
	ModulhandbuchService modulhandbuchService;
	
	private String abschluss;
	private String studiengang;
	private String pruefungsordnung;
	private Modulhandbuch modulhandbuch=new Modulhandbuch();
	private User myself;
	private boolean erfolgreich = false;
	private boolean gescheitert = false;
	
	public String modulhandbuchErstellen(){
		modulhandbuch.setAbschluss(abschluss);
		modulhandbuch.setPruefungsordnung(pruefungsordnung);
		modulhandbuch.setStudiengang(studiengang);
		modulhandbuch.setDekan(myself.getVorname() + " " + myself.getName());
		modulhandbuch.setUid(myself.getUid());
		modulhandbuch.setZeitstempel(new Timestamp(System.currentTimeMillis()));
		if(modulhandbuchService.searchModulhandbuch(pruefungsordnung, studiengang, abschluss) == false){
			modulhandbuchService.createModulhandbuch(modulhandbuch);
			erfolgreich = true;
			gescheitert = false;
			abschluss = "";
			studiengang = "";
			pruefungsordnung = "";
		}
		else{
			gescheitert = true;
			erfolgreich = false;
		}
		return "modulhandbuchErstellen";
	}

	/**
	 * @return the abschluss
	 */
	public String getAbschluss() {
		return abschluss;
	}

	/**
	 * @param abschluss the abschluss to set
	 */
	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}

	/**
	 * @return the pruefungsordnung
	 */
	public String getPruefungsordnung() {
		return pruefungsordnung;
	}

	/**
	 * @param pruefungsordnung the pruefungsordnung to set
	 */
	public void setPruefungsordnung(String pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}

	/**
	 * @return the modulhandbuch
	 */
	public Modulhandbuch getModulhandbuch() {
		return modulhandbuch;
	}

	/**
	 * @param modulhandbuch the modulhandbuch to set
	 */
	public void setModulhandbuch(Modulhandbuch modulhandbuch) {
		this.modulhandbuch = modulhandbuch;
	}
	
	/**
	 * @return the studiengang
	 */
	public String getStudiengang() {
		return studiengang;
	}
	
	/**
	 * @param studiengang the studiengang to set
	 */
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	/**
	 * @return the modulhandbuchService
	 */
	public ModulhandbuchService getModulhandbuchService() {
		return modulhandbuchService;
	}

	/**
	 * @param modulhandbuchService the modulhandbuchService to set
	 */
	public void setModulhandbuchService(ModulhandbuchService modulhandbuchService) {
		this.modulhandbuchService = modulhandbuchService;
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
	 * @return the erfolgreich
	 */
	public boolean isErfolgreich() {
		return erfolgreich;
	}

	/**
	 * @param erfolgreich the erfolgreich to set
	 */
	public void setErfolgreich(boolean erfolgreich) {
		this.erfolgreich = erfolgreich;
	}

	/**
	 * @return the gescheitert
	 */
	public boolean isGescheitert() {
		return gescheitert;
	}

	/**
	 * @param gescheitert the gescheitert to set
	 */
	public void setGescheitert(boolean gescheitert) {
		this.gescheitert = gescheitert;
	}
}
