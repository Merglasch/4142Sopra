package model;

import java.util.List;
import javax.ejb.EJB;
import klassenDB.Fach;
import klassenDB.Modul;
import model.modules.FachService;
import model.modules.ModuleService;

public class AenderungsverwaltungStudiendekanBean {
	public AenderungsverwaltungStudiendekanBean() {
	}

	@EJB
	FachService fachService;
	ModuleService modulService;
	
	//Attribute für Name des Faches ändern
	private List<Fach> faecher;
	private String selectFach;
	private String eingabeFach;
	private boolean geaendertFach=false;
	private Fach fach = new Fach();
	
	//Attribute für Modul ändern
	private List<Modul> module; 
	private String selectModul;
	private boolean geaendertModul=false;
	
	/**
	 * Ändert den Namen des bestehenden Faches
	 * 
	 * @return
	 */
	public String updateFach(){
		System.out.println("##Methode updateFach");
		int id = Integer.parseInt(selectFach);
		System.out.println("id= "+id);
		System.out.println(eingabeFach);
		fach.setFach(this.eingabeFach);
		fach.setFid(id);
		geaendertFach=fachService.changeFach(fach);
		if(geaendertFach){
			System.out.println("der Name des Fachs wurde geändert");
		}else{
			System.out.println("Fehler bei Fachname ändern");}
		eingabeFach="";
		faecher=fachService.getAllFach();
		return "fachnameAendern";
	}
	
	/**
	 * Ändert das Modul
	 * 
	 */
	public String updateModul(){
		
		return "updateModul";
	}
	
	
	/**
	 * @return the faecher
	 */
	public List<Fach> getFaecher() {
		faecher=fachService.getAllFach();
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
	public boolean isGeaendertFach() {
		return geaendertFach;
	}

	/**
	 * @param geaendert the geaendert to set
	 */
	public void setGeaendertFach(boolean geaendertFach) {
		this.geaendertFach = geaendertFach;
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
	 * @return the geaendertModul
	 */
	public boolean isGeaendertModul() {
		return geaendertModul;
	}

	/**
	 * @param geaendertModul the geaendertModul to set
	 */
	public void setGeaendertModul(boolean geaendertModul) {
		this.geaendertModul = geaendertModul;
	}
}
