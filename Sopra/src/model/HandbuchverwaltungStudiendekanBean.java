package model;

import java.util.List;
import javax.ejb.EJB;
import model.modules.FachService;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;
import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;

public class HandbuchverwaltungStudiendekanBean {
	public HandbuchverwaltungStudiendekanBean() {
		super();
	}
	
	@EJB
	ModuleService modulService;
	ModulhandbuchService modulhandbuchService;
	FachService fachService;
	
	private List<Modul> module;
	private String selectModul; // an modulid
	private List<Fach> faecher;
	private String selectFach;
	private List<Modulhandbuch> modulhandbuecher;
	private String selectModulhandbuch;
	private String eingabeFach;
	private boolean erstellt = false;
	

	public String handbuchAnlegen(){
		int modulid = Integer.parseInt(selectModul);
		int fachid = Integer.parseInt(selectFach);
		int mhdid = Integer.parseInt(selectModulhandbuch);
		erstellt = modulhandbuchService.insertIntoHandbuchverwalter(modulid, fachid, mhdid);
		if(erstellt){
			System.out.println("das Modulhandbuch wurde erfolgreich erstellt");
		}
		else{
			System.out.println("Fehler bei Modulhandbuch erstellen");
		}
		return "handbuchAnlegen";
	}

	/**
	 * @return the module
	 */
	public List<Modul> getModule() {
		module = modulService.getAktModules();
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(List<Modul> module) {
		this.module = module;
	}

	/**
	 * @return the selectModul
	 */
	public String getSelectModul() {
		return selectModul;
	}

	/**
	 * @param selectModul the selectModul to set
	 */
	public void setSelectModul(String selectModul) {
		this.selectModul = selectModul;
	}

	/**
	 * @return the faecher
	 */
	public List<Fach> getFaecher() {
		faecher = fachService.getAllFach();
		return faecher;
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
	 * @return the modulhandbuecher
	 */
	public List<Modulhandbuch> getModulhandbuecher() {
		modulhandbuecher = modulhandbuchService.getModulhandbuch();
		return modulhandbuecher;
	}

	/**
	 * @param modulhandbuecher the modulhandbuecher to set
	 */
	public void setModulhandbuecher(List<Modulhandbuch> modulhandbuecher) {
		this.modulhandbuecher = modulhandbuecher;
	}

	/**
	 * @return the selectModulhandbuch
	 */
	public String getSelectModulhandbuch() {
		return selectModulhandbuch;
	}

	/**
	 * @param selectModulhandbuch the selectModulhandbuch to set
	 */
	public void setSelectModulhandbuch(String selectModulhandbuch) {
		this.selectModulhandbuch = selectModulhandbuch;
	}
	
	/**
	 * @return the erstellt
	 */
	public boolean isErstellt() {
		return erstellt;
	}
	
	/**
	 * @param erstellt the erstellt to set
	 */
	public void setErstellt(boolean erstellt) {
		this.erstellt = erstellt;
	}

	/**
	 * @return the eingabeFach
	 */
	public String getEingabeFach() {
		return eingabeFach;
	}

	/**
	 * @param eingabeFach the eingabeFach to set
	 */
	public void setEingabeFach(String eingabeFach) {
		this.eingabeFach = eingabeFach;
	}
}
