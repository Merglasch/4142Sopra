package model;

import java.util.ArrayList;

import klassenDB.Modul;

public class ModulHandbuchBean {
	private String modulName;
	private int modulID;
	private String pruefungsordnung;
	private String studiengang;

	private ArrayList<Modul> module;
	
	public ModulHandbuchBean(){
		super();
	}
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
	
	
	
	public String getModulName() {
		return modulName;
	}
	public void setModulName(String modulName) {
		this.modulName = modulName;
	}
	public int getModulID() {
		return modulID;
	}
	public void setModulID(int modulID) {
		this.modulID = modulID;
	}
	public String getPruefungsordnung() {
		return pruefungsordnung;
	}
	public void setPruefungsordnung(String pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}
	public String getStudiengang() {
		return studiengang;
	}
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}
}
