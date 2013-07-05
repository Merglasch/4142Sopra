package model;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import klassenDB.Modul;
import model.modules.FachService;
import model.modules.ModuleService;

public class ModelBean {
	
	@EJB
	ModuleService modulService;
	@EJB
	TreeService treeService;
	@EJB
	FachService fachService;
	
	
	//attribute modulhandbuch
//	private List<String> studienabschluss;
	private List<SelectItem> studienabschluss;
	private String studienabschlussAuswahl;
	
//	private List<String> studiengang; 
	private List<SelectItem> studiengang; 
	private String studiengangAuswahl; 
	
//	private List<String> pruefungsordnung;
	private List<SelectItem> pruefungsordnung;
	private String pruefungsordnungAuswahl;
	
	//attribute modul
	private String modulName;
	
	private List<Modul> suchErg;
	
	
	public ModelBean() {
		super();
	}

	//liefert liste von modulhandbuechern, modulhandbuecher haben listen von ihren modulen
	
	public String sucheModul(){
		System.out.println("##METHODE sucheModul");
		System.out.println("abschl: "+studienabschlussAuswahl+"  gang: "+studiengangAuswahl+"  ordn: "+pruefungsordnungAuswahl);

		suchErg = modulService.aktModulsuche(studienabschlussAuswahl, studiengangAuswahl, pruefungsordnungAuswahl, modulName);
		
		for(Modul m :suchErg){
			System.out.println("**********Suchergebnis:"+m.getModulname()+" "+m.getModulid());
		}
		
		return "suchergebnis";
	}
		
	
	
	
////////////////////////////////////////////////////////
/////////////   Getter und setter   ///////////////////////////
////////////////////////////////////////////////////////
	public String getModulName() {
		return modulName;
	}
	public void setModulName(String modulName) {
		this.modulName = modulName;
	}

	public String getStudienabschlussAuswahl() {
		return studienabschlussAuswahl;
	}

	public void setStudienabschlussAuswahl(String studienabschlussAuswahl) {
		this.studienabschlussAuswahl = studienabschlussAuswahl;
	}

	public String getStudiengangAuswahl() {
		return studiengangAuswahl;
	}

	public void setStudiengangAuswahl(String studiengangAuswahl) {
		this.studiengangAuswahl = studiengangAuswahl;
	}

	public String getPruefungsordnungAuswahl() {
		return pruefungsordnungAuswahl;
	}

	public void setPruefungsordnungAuswahl(String pruefungsordnungAuswahl) {
		this.pruefungsordnungAuswahl = pruefungsordnungAuswahl;
	}

	public void setStudienabschluss(List<SelectItem> studienabschluss) {
		this.studienabschluss = studienabschluss;
	}

	public void setStudiengang(List<SelectItem> studiengang) {
		this.studiengang = studiengang;
	}

	public void setPruefungsordnung(List<SelectItem> pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}

	public List<SelectItem> getStudienabschluss() {
		List<String> sl = treeService.getAllAbschluss();
		List<SelectItem> tmp = new LinkedList<SelectItem>();
		for(String s : sl){
			tmp.add(new SelectItem(s,s));
		}
		studienabschluss = tmp;
		return studienabschluss;
	}

	public List<SelectItem> getStudiengang() {
		List<String> sl = treeService.getAllStudiengang();
		List<SelectItem> tmp = new LinkedList<SelectItem>();
		for(String s : sl){
			tmp.add(new SelectItem(s,s));
		}
		studiengang = tmp;
		return studiengang;
	}

	public List<SelectItem> getPruefungsordnung() {
		List<String> sl = treeService.getAllPruefungsordnung();
		List<SelectItem> tmp = new LinkedList<SelectItem>();
		for(String s : sl){
			tmp.add(new SelectItem(s,s));
		}
		pruefungsordnung = tmp;
		return pruefungsordnung;
	}

	public List<Modul> getSuchErg() {
		return suchErg;
	}

	public void setSuchErg(List<Modul> suchErg) {
		this.suchErg = suchErg;
	}

	
}
