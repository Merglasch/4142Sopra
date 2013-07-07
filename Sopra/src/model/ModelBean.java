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
		//liefert liste von modulhandbuechern, modulhandbuecher haben listen von ihren modulen
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

	public List<HBVWtabellenausgabe> getDarstellung() {
		return darstellung;
	}

	public void setDarstellung(List<HBVWtabellenausgabe> darstellung) {
		this.darstellung = darstellung;
	}


	
}

