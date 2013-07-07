package model;

import java.util.ArrayList;
import java.util.List;

import klassenDB.Modul;
import klassenDB.Modulhandbuch;

public class ModelBean {
	//attribute modulhandbuch
	private String studienabschluss;
	private String studiengang; 
	private String pruefungsordnung;
	
	//attribute modul
	private String modulName;
	
	String[] handbuchverwalter; //[handbuchID, MODULNAME, abschluss, studiengang, pruefungordnung]
	private List<Modul> modul;
	
//	private List<Modulhandbuch> modulHandbuchListe;
//	private List<Modul> modul;
//	private List<Object[][]> handbuchverwalter;
	
	/**
	 * Konstruktor Abschluss, Studiengang , Pruefungsordnung und Modulnamen setzt.
	 * 
	 * @param Studienabschluss
	 * @param Studiengang
	 * @param Pruefungsordnung
	 * @param ModulName
	 */
	public ModelBean(String studienabschluss, String studiengang,
			String pruefungsordnung, String modulName) {
		super();
		this.studienabschluss = studienabschluss;
		this.studiengang = studiengang;
		this.pruefungsordnung = pruefungsordnung;
		this.modulName = modulName;
	}
	
	/**
	 * Standard-Konstruktor.
	 */
	public ModelBean() {
		super();
	}

	//liefert liste von modulhandbuechern, modulhandbuecher haben listen von ihren modulen
	
	public String sucheModul(){
		//DB Methode aufrufen, die passende modulnamen zur suchanfrage liefert, als String[]
		
		// antwortseite zeigt nur daten aus handbuchverwalter, 
		// auf antwortseite neue suche zu dem ausgewaehlten modulnamen
		
		//test
		handbuchverwalter[0] = "NR;Studienabschluss;Studiengang;Pruefungsordnung";
		handbuchverwalter[1] = "132 ;Studienabschluss;Studiengang;2323";
		
		return"Antwortseite1";
	}
		
	
	
	
	
	
/*	public String sucheModul(){
		modulHandbuchListe = new ArrayList<Modulhandbuch>();
//		modul = new ArrayList<Modul>();
//		handbuchverwalter = new ArrayList<Object[][]>();
		handbuchverwalter = new ArrayList<Object[][]>();
		
		// DB Methode, die Handbuecher liefert und in modulHandbuchListe speichert
		//methodenaufruf
		Modulhandbuch mhb1 = new Modulhandbuch();
		Modulhandbuch mhb2 = new Modulhandbuch();
		modulHandbuchListe.add(mhb1);
		modulHandbuchListe.add(mhb2);
		
		
		Object[][] objects;
		if(modulHandbuchListe.size() < 0){
			//nichts
		}else{
			
			for(int i =0; i < modulHandbuchListe.size(); i++){
				
				//DB Methode, die module zu uebergebenem handbuch liefert
				//methodenaufruf liefert liste von modulen
				// modul = ...Methode...
				
				//hier: test
				modul = new ArrayList<Modul>();
				Modul bspMod = new Modul();
				bspMod.setInhalt("Inhalt modul: "+i);
				modul.add(bspMod);
				
				objects = new Object [][];
				
				handbuchverwalter;
			}
			
		}
		
		return "Antwortseite";
	}*/
	
	public String sucheModul(String studienabschluss, String studiengang,
			String pruefungsordnung, int nummer, String modulName){
		
		this.studienabschluss = studienabschluss;
		this.studiengang = studiengang;
		this.pruefungsordnung = pruefungsordnung;
		this.modulName = modulName;
		
		
		return sucheModul();
		
	}
	
	
	
////////////////////////////////////////////////////////
/////////////   Getter und setter   ///////////////////////////
////////////////////////////////////////////////////////

	
	
	/**
	 * @return the studienabschluss
	 */
	public String getStudienabschluss() {
		return studienabschluss;
	}

	/**
	 * @param studienabschluss the studienabschluss to set
	 */
	public void setStudienabschluss(String studienabschluss) {
		this.studienabschluss = studienabschluss;
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
	 * @return the handbuchverwalter
	 */
	public String[] getHandbuchverwalter() {
		return handbuchverwalter;
	}

	/**
	 * @param handbuchverwalter the handbuchverwalter to set
	 */
	public void setHandbuchverwalter(String[] handbuchverwalter) {
		this.handbuchverwalter = handbuchverwalter;
	}

	/**
	 * @return the modul
	 */
	public List<Modul> getModul() {
		return modul;
	}

	/**
	 * @param modul the modul to set
	 */
	public void setModul(List<Modul> modul) {
		this.modul = modul;
	}
	
}
