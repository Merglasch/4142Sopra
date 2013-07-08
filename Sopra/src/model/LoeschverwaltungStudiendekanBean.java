package model;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import model.modules.FachService;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;

/**
 * Bean fuer den Studiendekan, in dem er Module und Modulhandbuecher loeschen kann.
 *
 */
public class LoeschverwaltungStudiendekanBean {

	/**
	 * Standard-Konstruktor.
	 */
	public LoeschverwaltungStudiendekanBean() {
		super();
	}
	
	@EJB
	ModuleService modulService;
	@EJB
	ModulhandbuchService modulhandbuchService;
	@EJB
	FachService fachService;
	
	private List<Modul> module;
	private List<Fach> faecher;
	private List<Modulhandbuch> modulhandbuecher;
	private List<HBVWtabellenausgabe> handbuchverwalter;
	private List<String> handbuchverwalterAuswahliste;
	
	private String modulAuswahl;
	private String fachAuswahl;
	private String modulhandbuchAuswahl;
	private boolean erfolgreich = false;
	private boolean nichtsLoeschenAuswahl = false;
	private boolean loeschenAuswahl = false;
	private String status="";
	private String[] splitResult;
	
	/**
	 * 
	 * @return loeschverwaltungStudiendekan
	 */
	public String loeschcheckbox(){
		for(String s : handbuchverwalterAuswahliste){
			splitResult = s.split(" ");
			System.out.println("Modulid: " + splitResult[0]);
			System.out.println("Fachid: " + splitResult[1]);
			System.out.println("modulhandbuchid: " + splitResult[2]);
			erfolgreich = modulhandbuchService.deleteHandbuchverwalter(Integer.parseInt(splitResult[0]), Integer.parseInt(splitResult[1]), Integer.parseInt(splitResult[2]));
		}
		if(erfolgreich){
			status="Die Kombination wurde erfolgreich gelöscht";
		}
		else{
			System.out.println("Fehler bei Auswahl löschen");
		}
		return "loeschverwaltungStudiendekan";
	}
	
	
	
	
	/**
	 * 
	 * @return loeschverwaltungStudiendekan
	 */
	public String loescheAuswahl(){
		if(!modulAuswahl.equals("-1")){
			modulService.deleteModule(Integer.parseInt(modulAuswahl));
			modulhandbuchService.deleteByModuleID(Integer.parseInt(modulAuswahl));
			nichtsLoeschenAuswahl=false;
			loeschenAuswahl = true;
			nichtsLoeschenAuswahl=false;
		}
		
		if(!fachAuswahl.equals("-1")){
			fachService.deleteFach(Integer.parseInt(fachAuswahl));
			modulhandbuchService.deleteByFachID(Integer.parseInt(fachAuswahl));
			nichtsLoeschenAuswahl=false;
			loeschenAuswahl = true;
			nichtsLoeschenAuswahl=false;		
		}
		
		if(!modulhandbuchAuswahl.equals("-1")){
			modulhandbuchService.deleteModulhandbuch(Integer.parseInt(modulhandbuchAuswahl));
			modulhandbuchService.deleteByHandbuchID(Integer.parseInt(modulhandbuchAuswahl));
			loeschenAuswahl = true;
			nichtsLoeschenAuswahl=false;
		}
		
		else{
			nichtsLoeschenAuswahl = true;
			loeschenAuswahl = false;
		}
		
		return "loeschverwaltungStudiendekan";
	}





	/**
	 * @return the module
	 */
	public List<Modul> getModule() {
		module=modulService.searchPublicModules();
		return module;
	}





	/**
	 * @param module the module to set
	 */
	public void setModule(List<Modul> module) {
		this.module = module;
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
	 * @return the handbuchverwalter
	 */
	public List<HBVWtabellenausgabe> getHandbuchverwalter() {
		handbuchverwalter = new LinkedList<HBVWtabellenausgabe>();
		List<Modul> suchErg = modulService.searchPublicModules();
		for(Modul m :suchErg){
			System.out.print("Modul: "+m.getModulname());
			List<Integer> hbids = modulhandbuchService.findHandbuchid(m.getModulid(), "%","%","%");
			for(int hbid : hbids){
				List<Integer> fids = modulhandbuchService.findFachidByHandbuchidAndModulid(hbid, m.getModulid());
				for(int fid : fids){
					Modulhandbuch hb = modulhandbuchService.findById(hbid);
					Fach fach = fachService.findById(fid);
					System.out.print(" Modulhandbuch: "+hb.getAbschluss()+" "+hb.getPruefungsordnung()+" "+hb.getStudiengang());
					System.out.print(" Fach: "+fach.getFach()+"\n");
		
					HBVWtabellenausgabe tmp = new HBVWtabellenausgabe();
					tmp.setModul(m);
					tmp.setModulhandbuch(hb);
					tmp.setFach(fach);
		
					handbuchverwalter.add(tmp);
				}
			}
		}
		for(HBVWtabellenausgabe m : handbuchverwalter){
			System.out.println(m.getFach() + " " + m.getModul().getModulname() + " " + m.getModulhandbuch().getStudiengang());
		}
		return handbuchverwalter;
	}





	/**
	 * @param handbuchverwalter the handbuchverwalter to set
	 */
	public void setHandbuchverwalter(List<HBVWtabellenausgabe> handbuchverwalter) {
		this.handbuchverwalter = handbuchverwalter;
	}





	/**
	 * @return the handbuchverwalterAuswahliste
	 */
	public List<String> getHandbuchverwalterAuswahliste() {
		return handbuchverwalterAuswahliste;
	}





	/**
	 * @param handbuchverwalterAuswahliste the handbuchverwalterAuswahliste to set
	 */
	public void setHandbuchverwalterAuswahliste(
			List<String> handbuchverwalterAuswahliste) {
		this.handbuchverwalterAuswahliste = handbuchverwalterAuswahliste;
	}





	/**
	 * @return the modulAuswahl
	 */
	public String getModulAuswahl() {
		return modulAuswahl;
	}





	/**
	 * @param modulAuswahl the modulAuswahl to set
	 */
	public void setModulAuswahl(String modulAuswahl) {
		this.modulAuswahl = modulAuswahl;
	}





	/**
	 * @return the fachAuswahl
	 */
	public String getFachAuswahl() {
		return fachAuswahl;
	}





	/**
	 * @param fachAuswahl the fachAuswahl to set
	 */
	public void setFachAuswahl(String fachAuswahl) {
		this.fachAuswahl = fachAuswahl;
	}





	/**
	 * @return the modulhandbuchAuswahl
	 */
	public String getModulhandbuchAuswahl() {
		return modulhandbuchAuswahl;
	}





	/**
	 * @param modulhandbuchAuswahl the modulhandbuchAuswahl to set
	 */
	public void setModulhandbuchAuswahl(String modulhandbuchAuswahl) {
		this.modulhandbuchAuswahl = modulhandbuchAuswahl;
	}




	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}




	/**
	 * @return the splitResult
	 */
	public String[] getSplitResult() {
		return splitResult;
	}




	/**
	 * @param splitResult the splitResult to set
	 */
	public void setSplitResult(String[] splitResult) {
		this.splitResult = splitResult;
	}




	/**
	 * @return the erfolgreich
	 */
	public boolean isErfolgreich() {
		return erfolgreich;
	}




	/**
	 * @param erfolgreich the erfolgreich to set
	 */
	public void setErfolgreich(boolean erfolgreich) {
		this.erfolgreich = erfolgreich;
	}




	/**
	 * @return the nichtsLoeschenAuswahl
	 */
	public boolean isNichtsLoeschenAuswahl() {
		return nichtsLoeschenAuswahl;
	}




	/**
	 * @param nichtsLoeschenAuswahl the nichtsLoeschenAuswahl to set
	 */
	public void setNichtsLoeschenAuswahl(boolean nichtsLoeschenAuswahl) {
		this.nichtsLoeschenAuswahl = nichtsLoeschenAuswahl;
	}




	/**
	 * @return the loeschenAuswahl
	 */
	public boolean isLoeschenAuswahl() {
		return loeschenAuswahl;
	}




	/**
	 * @param loeschenAuswahl the loeschenAuswahl to set
	 */
	public void setLoeschenAuswahl(boolean loeschenAuswahl) {
		this.loeschenAuswahl = loeschenAuswahl;
	}

}
