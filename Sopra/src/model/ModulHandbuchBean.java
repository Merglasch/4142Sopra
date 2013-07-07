package model;

import java.util.ArrayList;

import klassenDB.Modul;

public class ModulHandbuchBean {
	private String modulName;
	private int modulID;
	private String pruefungsordnung;
	private String studiengang;

	private ArrayList<Modul> module;
	
	/**
	 * Standard-Konstruktor.
	 */
	public ModulHandbuchBean(){
		super();
	}
	/**
	 * Konstruktor der das Bean gleich mit Werten versorgt.
	 * 
	 * @param modulName
	 * @param modulID
	 * @param studiengang
	 * @param pruefungsordnung
	 */
	public ModulHandbuchBean(String modulName, int modulID, String studiengang, String pruefungsordnung){
		super();
		this.modulName = modulName;
		this.modulID = modulID;
		this.studiengang =studiengang;
		this.pruefungsordnung=pruefungsordnung;
	}
	
	public String sucheModul(){
		//DB Methode die Modul mit gegebenem namen sucht
		//test dummy
		Modul mod = new Modul();
		mod.setInhalt("hallo welt");
		
		module = new ArrayList<Modul>();
		module.add(mod);
		
		return "ErgebnisModulSeite";
	}
	
	/**
	 * @return the modulName
	 */
	public String getModulName() {
		return modulName;
	}
	/**
	 * @param modulName the modulName to set
	 */
	public void setModulName(String modulName) {
		this.modulName = modulName;
	}
	/**
	 * @return the modulID
	 */
	public int getModulID() {
		return modulID;
	}
	/**
	 * @param modulID the modulID to set
	 */
	public void setModulID(int modulID) {
		this.modulID = modulID;
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
	 * @return the module
	 */
	public ArrayList<Modul> getModule() {
		return module;
	}
	/**
	 * @param module the module to set
	 */
	public void setModule(ArrayList<Modul> module) {
		this.module = module;
	}
	
	

}
