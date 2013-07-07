package model;

import java.util.List;
import javax.ejb.EJB;
import klassenDB.Fach;
import model.modules.FachService;

/**
 * Bean fuer den Studiendekan in dem er Fachnamen und module aendern kann, die bereits von Modulverantwortlichem und Koordinator freigegeben wurden.
 *
 */
public class AenderungsverwaltungStudiendekanBean {
	public AenderungsverwaltungStudiendekanBean() {
		super();
	}

	@EJB
	FachService fachService;
	
	//Attribute für Name des Faches ändern
	private List<Fach> faecher;
	private String selectFach;
	private String eingabeFach;
	private boolean geaendertFach=false;
	private Fach fach = new Fach();
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
		fach.setFach(eingabeFach);
		fach.setFid(id);
		geaendertFach=fachService.changeFach(fach);
		if(geaendertFach){
			System.out.println("der Name des Fachs wurde geändert");
		}else{
			System.out.println("Fehler bei Fachname ändern");}
		eingabeFach="";
		faecher=null;
		faecher=fachService.getAllFach();
		return "aenderungsverwaltungStudiendekan";
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
}
