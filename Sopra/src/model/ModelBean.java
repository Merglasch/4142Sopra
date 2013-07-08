package model;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import klassenDB.User;
import model.modules.FachService;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;

/**
 * Bean, das die Modulsuche implementiert.
 *
 */
public class ModelBean {
	
	@EJB
	ModuleService modulService;
	@EJB
	TreeService treeService;
	@EJB
	FachService fachService;
	@EJB
	ModulhandbuchService modulhandbuchService;
	
	//attribute modulhandbuch
//	private List<String> studienabschluss;
	private List<SelectItem> studienabschluss;
	private String studienabschlussAuswahl="";
	
//	private List<String> studiengang; 
	private List<SelectItem> studiengang; 
	private String studiengangAuswahl=""; 
	
//	private List<String> pruefungsordnung;
	private List<SelectItem> pruefungsordnung;
	private String pruefungsordnungAuswahl="";
	
	//attribute modul
	private String modulName="";
	
	//eingeloggter User
	private User myself;
	
	private List<Modul> suchErg;
	
	private List<HBVWtabellenausgabe> darstellung;
	
	
	/**
	 * Standard-Konstruktor.
	 */
	public ModelBean() {
		super();
	}

	/**
	 * sucht Module nach den in der durch die Bean gesetzten parameter und verweist auf die ausgebende xhtml seite
	 * @return suchergebnis1, tabellenansicht der gefundenen module
	 */
	public String sucheModul(){
		// eventuelle einlesefehler abfangen, gegebenenfalls wird nach allem gesucht
		if(studienabschlussAuswahl.equals("")){
			studienabschlussAuswahl = "alles";
		}
		if(studiengangAuswahl.equals("")){
			studiengangAuswahl = "alles";
		}
		if(pruefungsordnungAuswahl.equals("")){
			pruefungsordnungAuswahl = "alles";
		}
		if(modulName.equals("")){
			modulName = "alles";
		}

		
		// ist die auswahl Alles, wird der entsprechende suchstring fuer SQL in den "%" wildcardoperator geaendert
		if(studienabschlussAuswahl.equals("alles")){
			studienabschlussAuswahl = "%";
		}
		if(studiengangAuswahl.equals("alles")){
			studiengangAuswahl = "%";
		}
		if(pruefungsordnungAuswahl.equals("alles")){
			pruefungsordnungAuswahl = "%";
		}
		if(modulName.equals("alles")){
			modulName = "%";
		}else{
			String s ="%"+modulName+"%";
			modulName = s;
		}
		
//		String s = "%";
//		if(modulName.isEmpty()||modulName.equals("")){
//			//nothing
//		}else{
//			s += modulName +"%";
//		}
		
		
		
		darstellung = new LinkedList<HBVWtabellenausgabe>();
		
		// Module nach eingegebenem namen suchen, keine eingabe heiﬂt, es wird mittels "%" wildcart nach allen gesucht
		if(null == myself){
			// nicht eingeloggte user sehen die aktuell fuer die oeffentlichkeit freigegebenen module
			suchErg = modulService.searchFreiPublicModules(modulName);
		}else{
			// eingeloggte user sehen die zuletzt geaenderten/bearbeiteten module
			suchErg = modulService.aktModulsuche(modulName);
		}
		
		
		// Alle module Durchgehen
		for(Modul m :suchErg){
			// Liste aller handbuchIDs zu einem gegebenen Modul, welche zum Abschluss, Studiengang, und pruefungsordnung
			List<Integer> hbids = modulhandbuchService.findHandbuchid(m.getModulid(), studienabschlussAuswahl,studiengangAuswahl,pruefungsordnungAuswahl);
			// Alle zu einem Modul passenden handbuecher (anhand der ID) durchgehen
			for(int hbid : hbids){
				// Liste aller FachIds zu gegebener HandbuchID und ModulID
				List<Integer> fids = modulhandbuchService.findFachidByHandbuchidAndModulid(hbid, m.getModulid());
				// Alle zu zu einem Modulhandbuch und Modul passenden Faecher (anhand der ID) durchgehen
				for(int fid : fids){
					// An diesem Punkt in den drei For-Schleifen passen Modul(ID), handbuchID und FachID zusammen
					//Modulhandbuch und Fach anhand ihrer ID aus der 'DB lesen
					Modulhandbuch hb = modulhandbuchService.findById(hbid);
					Fach fach = fachService.findById(fid);
					// ein temporaeres Handbuchwerwalter Objekt erzeugen und mit dem passenden Modul, Modulhandbuch und Fach
					HBVWtabellenausgabe tmp = new HBVWtabellenausgabe();
					tmp.setModul(m);
					tmp.setModulhandbuch(hb);
					tmp.setFach(fach);
			
					// tmp Handbuchverwalter zur handbuchverwalter-Liste hinzufuegen
					darstellung.add(tmp);
				}
			}
		}
		modulName="";
		return "suchergebnis";
	}
		
	
	
	
////////////////////////////////////////////////////////
/////////////   Getter und setter   ///////////////////////////
////////////////////////////////////////////////////////

	/**
	 * @ return the modulName
	 */	
	public String getModulName() {
		return modulName;
	}
	public void setModulName(String modulName) {
		this.modulName = modulName;
	}

	/**
	 * @return the studienabschlussAuswahl
	 */
	public String getStudienabschlussAuswahl() {
		return studienabschlussAuswahl;
	}

	/**
	 * @param studienabschlussAuswahl the studienabschlussAuswahl to set
	 */
	public void setStudienabschlussAuswahl(String studienabschlussAuswahl) {
		this.studienabschlussAuswahl = studienabschlussAuswahl;
	}
	
	/**
	 * @return studiengangAuswahl
	 */
	public String getStudiengangAuswahl() {
		return studiengangAuswahl;
	}

	/**
	 *@param studiengangAuswahl
	 */
	public void setStudiengangAuswahl(String studiengangAuswahl) {
		this.studiengangAuswahl = studiengangAuswahl;
	}

	/**
	 *@return pruefungsordnungsAuswahl
	 */
	public String getPruefungsordnungAuswahl() {
		return pruefungsordnungAuswahl;
	}

	/**
	 *@param pruefungsordnungAuswahl
	 */
	public void setPruefungsordnungAuswahl(String pruefungsordnungAuswahl) {
		this.pruefungsordnungAuswahl = pruefungsordnungAuswahl;
	}

	/**
	 *@param SelectItemList studienabschluss
	 */
	public void setStudienabschluss(List<SelectItem> studienabschluss) {
		this.studienabschluss = studienabschluss;
	}

	/**
	 *@param SelectItemList studiengang
	 */
	public void setStudiengang(List<SelectItem> studiengang) {
		this.studiengang = studiengang;
	}

	/*
	 *@param SelectItemList pruefungsordnung
	 */
	public void setPruefungsordnung(List<SelectItem> pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}

	/**
	 * @return SelectItemList studienabschluss
	 */
	public List<SelectItem> getStudienabschluss() {
		List<String> sl = treeService.getAllAbschluss();
		List<SelectItem> tmp = new LinkedList<SelectItem>();
		for(String s : sl){
			tmp.add(new SelectItem(s,s));
		}
		studienabschluss = tmp;
		return studienabschluss;
	}

	/**
	 * SelectItemList studiengang
	 */
	public List<SelectItem> getStudiengang() {
		List<String> sl = treeService.getAllStudiengang();
		List<SelectItem> tmp = new LinkedList<SelectItem>();
		for(String s : sl){
			tmp.add(new SelectItem(s,s));
		}
		studiengang = tmp;
		return studiengang;
	}

	/**
	 *@return SelectItemList pruefungsordnung
	 */
	public List<SelectItem> getPruefungsordnung() {
		List<String> sl = treeService.getAllPruefungsordnung();
		List<SelectItem> tmp = new LinkedList<SelectItem>();
		for(String s : sl){
			tmp.add(new SelectItem(s,s));
		}
		pruefungsordnung = tmp;
		return pruefungsordnung;
	}

	/**
	 *@return suchErgebnis
	 */
	public List<Modul> getSuchErg() {
		return suchErg;
	}

	/**
	 *@param suchErgebnisListe
	 */
	public void setSuchErg(List<Modul> suchErg) {
		this.suchErg = suchErg;
	}

	/**
	 *@return darstellung
	 */
	public List<HBVWtabellenausgabe> getDarstellung() {
		return darstellung;
	}

	/**
	 *@param darstellungListe
	 */
	public void setDarstellung(List<HBVWtabellenausgabe> darstellung) {
		this.darstellung = darstellung;
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


	
}

