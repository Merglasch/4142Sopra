package model;

import java.util.List;

import javax.ejb.EJB;

import klassenDB.Fach;
import model.modules.FachService;
import model.modules.ModuleService;

public class AenderungsverwaltungStudiendekanBean {
	public AenderungsverwaltungStudiendekanBean() {
	}

	@EJB
	FachService fachService;
	
	private List<Fach> faecher;
	private String selectFach;
	private String eingabeFach;
	private boolean geaendert=false;
	private Fach fach;
	
	public String updateFach(){
		int id = Integer.parseInt(selectFach);
		fach.setFach(eingabeFach);
		fach.setFid(id);
		geaendert=fachService.changeFach(fach);
		if(geaendert)
			System.out.println("der Name des Fachs wurde geändert");
		else
			System.out.println("Fehler bei Fachname ändern");
		return "fachnameAendern";
	}
	
	
	
	/**
	 * @return the faecher
	 */
	public List<Fach> getFaecher() {
		return faecher=fachService.getAllFach();
	}
	/**
	 * @param faecher the faecher to set
	 */
	public void setFaecher(List<Fach> faecher) {
		this.faecher = faecher;
	}
	/**
	 * @return the selectFach
	 */
	public String getSelectFach() {
		return selectFach;
	}
	/**
	 * @param selectFach the selectFach to set
	 */
	public void setSelectFach(String selectFach) {
		this.selectFach = selectFach;
	}
	
	
	/**
	 * @return the fach
	 */
	public Fach getFach() {
		return fach;
	}
	
	
	
	/**
	 * @param fach the fach to set
	 */
	public void setFach(Fach fach) {
		this.fach = fach;
	}



	/**
	 * @return the eingabeFach
	 */
	public String getEingabeFach() {
		int id = Integer.parseInt(selectFach);
		Fach f = fachService.getFach(id);
		eingabeFach = f.getFach();
		return eingabeFach;
	}



	/**
	 * @param eingabeFach the eingabeFach to set
	 */
	public void setEingabeFach(String eingabeFach) {
		this.eingabeFach = eingabeFach;
	}



	/**
	 * @return the geaendert
	 */
	public boolean isGeaendert() {
		return geaendert;
	}



	/**
	 * @param geaendert the geaendert to set
	 */
	public void setGeaendert(boolean geaendert) {
		this.geaendert = geaendert;
	}
}
