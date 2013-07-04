package model;

import java.util.List;

import javax.ejb.EJB;

import klassenDB.Fach;
import model.modules.ModuleService;

public class AenderungsverwaltungStudiendekanBean {
	public AenderungsverwaltungStudiendekanBean() {
	}

	@EJB
	ModuleService modulService;
	
	private List<Fach> faecher;
	private String selectFach;
	private String eingabeFach;
	private boolean geaendert=false;
	
	public String updateFach(){
		geaendert=modulService.updateFach(eingabeFach);
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
		return faecher=modulService.getAktFaecher();
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
	
	
}
