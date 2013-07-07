package model;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import model.modules.FachService;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;

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
	
	private List<Modul> suchErg;
	
	private List<HBVWtabellenausgabe> darstellung;
	
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
		System.out.println("##METHODE sucheModul");
		if(studienabschlussAuswahl.equals("")){
			studienabschlussAuswahl = "alles";
		}
		if(studiengangAuswahl.equals("")){
			studiengangAuswahl = "alles";
		}
		if(pruefungsordnungAuswahl.equals("")){
			pruefungsordnungAuswahl = "alles";
		}
		System.out.println("abschl: "+studienabschlussAuswahl+"  gang: "+studiengangAuswahl+"  ordn: "+pruefungsordnungAuswahl);

		
		
		
		darstellung = new LinkedList<HBVWtabellenausgabe>();
		//alle Module durchgehen
		suchErg = modulService.aktModulsuche(studienabschlussAuswahl, studiengangAuswahl, pruefungsordnungAuswahl, modulName);
		
		
		
		
		if(studienabschlussAuswahl.equals("alles")){
			studienabschlussAuswahl = "%";
		}
		if(studiengangAuswahl.equals("alles")){
			studiengangAuswahl = "%";
		}
		if(pruefungsordnungAuswahl.equals("alles")){
			pruefungsordnungAuswahl = "%";
		}
		
		String s = "%";
		if(modulName.isEmpty()||modulName.equals("")){
			//nothing
		}else{
			s += modulName +"%";
		}
		
		System.out.println("****************** Ausgabe suchergebnis");
		for(Modul m :suchErg){
			System.out.print("Modul: "+m.getModulname());
			List<Integer> hbids = modulhandbuchService.findHandbuchid(m.getModulid(), studienabschlussAuswahl,studiengangAuswahl,pruefungsordnungAuswahl);
			for(int hbid : hbids){
				List<Integer> fids = modulhandbuchService.findFachidByHandbuchidAndModulid(hbid, m.getModulid());
				for(int fid : fids){
					Modulhandbuch hb = modulhandbuchService.findById(hbid);
					Fach fach = fachService.findById(fid);
					System.out.print("   Modulhandbuch: "+hb.getAbschluss()+"  "+hb.getPruefungsordnung()+"  "+hb.getStudiengang());
					System.out.print("   Fach: "+fach.getFach()+"\n");
					
					HBVWtabellenausgabe tmp = new HBVWtabellenausgabe();
					tmp.setModul(m);
					tmp.setModulhandbuch(hb);
					tmp.setFach(fach);
			
					darstellung.add(tmp);
				}
			}
		}
		
		
		
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

	**
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


	
}

