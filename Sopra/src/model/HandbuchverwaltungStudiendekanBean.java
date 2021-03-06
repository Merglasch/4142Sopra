package model;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import model.modules.FachService;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;

import java.util.Timer;


/**
 * Bean fuer den Studiendekan, in dem er Eintraege zum Handbuchverwalter hinzufuegen und wieder entfernen kann.
 *
 */
public class HandbuchverwaltungStudiendekanBean {
	public HandbuchverwaltungStudiendekanBean() {
		super();
		timer = new Timer();
		timer.schedule(new MyTimerTask(this), 2000); // 2 sekunden
	}
	
	@EJB
	FachService fachService;
	@EJB
	ModuleService modulService;
	@EJB
	ModulhandbuchService modulhandbuchService;
	
	private Timer timer;
	private List<Modul> module;
	private String selectModul; // an modulid
	private List<SelectItem> faecher;
	private String selectFach;
	private List<Modulhandbuch> modulhandbuecher;
	private String selectModulhandbuch;
	private String eingabeFach;
	private Fach fach = new Fach();
	private boolean erstellt = false;
	private boolean fachExistiert=true;
	private String statusGreen="";
	private String statusRed="";
	private Modulhandbuch aktHb;
	private boolean freigabeStatus=false;
	

	
	/**
	 * Gibt das gewaehlte Handbuch frei oder nimmt die Freigabe zurueck.
	 * @return String gleiche Seite fuer Feedback
	 */
	public String handbuchFreigabe(){
		List<Modulhandbuch> tmplist = modulhandbuchService.getModulhandbuch();
		for(Modulhandbuch mh : tmplist){
			if(mh.getHandbuchid()==Short.parseShort(selectModulhandbuch)){
				aktHb=mh;
				break;
			}
		}
		if(aktHb.getFreigegeben()==(short)0)
			aktHb.setFreigegeben((short)1);
		else
			aktHb.setFreigegeben((short)0);
		freigabeStatus=true;
		modulhandbuchService.updateModulhandbuch(aktHb);
		timer.schedule(new MyTimerTask(this), 2000); // 2 sekunden
		return "handbuchverwaltungStudiendekan";
	}
	
	/**
	 * Methode um Beziehungen zum Modulhandbuch festzulegen.
	 * 
	 */
	public String handbuchAnlegen(){
		int mhdid = Integer.parseInt(selectModulhandbuch);
		int modulid = Integer.parseInt(selectModul);
		int fachid = Integer.parseInt(selectFach);
		//Ueberpruefe ob fach schon vorhanden
		if(fachid ==-1){
			for(SelectItem f : faecher){
				if(f.getLabel().equals(eingabeFach)){
					statusRed="Das Fach existiert bereits schon";
					timer.schedule(new MyTimerTask(this), 2000); // 2 sekunden
					return "handbuchAnlegen";
				}
			}	
		System.out.println("eingabeFach= "+eingabeFach);
		fach.setFach(eingabeFach);
		int id = fachService.createFach(fach);
//		fach.setFid(id);
		System.out.println("id von neuem Fach="+id);
		}
		if(modulhandbuchService.searchModulhandbuchByIds(mhdid, fachid, modulid) == true){
			statusRed="Diese Kombination existiert bereits schon";
			timer.schedule(new MyTimerTask(this), 2000); // 2 sekunden
			return "handbuchAnlegen";
		}
		else{
			erstellt = modulhandbuchService.insertIntoHandbuchverwalter(modulid, fachid, mhdid);
			if(erstellt){
				statusGreen ="Das Modulhandbuch wurde erfolgreich erstellt";
				System.out.println("das Modulhandbuch wurde erfolgreich erstellt");
			}
			else{
				System.out.println("Fehler bei Modulhandbuch erstellen");
			}
		}
		timer.schedule(new MyTimerTask(this), 2000); // 2 sekunden
		return "handbuchAnlegen";
	}

	/**
	 * @return the module
	 */
	public List<Modul> getModule() {
		module = modulService.searchPublicModules();
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
	 * @return the faecher
	 */
	public List<SelectItem> getFaecher() {
		faecher = new LinkedList<SelectItem>();
		List<Fach> tmp =fachService.getAllFach();
		for(Fach f : tmp){
			System.out.println("##### getFaecher: "+f.getFach());
			SelectItem s = new SelectItem(f.getFid(), f.getFach());
			System.out.println("##### getFaecher2: "+s.getValue()+"  "+s.getLabel());
			faecher.add(s); 
			
		}
		return faecher;
	}

	/**
	 * @param faecher the faecher to set
	 */
	public void setFaecher(List<SelectItem> faecher) {
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
	 * @return the selectModulhandbuch
	 */
	public String getSelectModulhandbuch() {
		return selectModulhandbuch;
	}

	/**
	 * @param selectModulhandbuch the selectModulhandbuch to set
	 */
	public void setSelectModulhandbuch(String selectModulhandbuch) {
		this.selectModulhandbuch = selectModulhandbuch;
	}
	
	/**
	 * @return the erstellt
	 */
	public boolean isErstellt() {
		return erstellt;
	}
	
	/**
	 * @param erstellt the erstellt to set
	 */
	public void setErstellt(boolean erstellt) {
		this.erstellt = erstellt;
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
	 * @return the fachExistiert
	 */
	public boolean isFachExistiert() {
		return fachExistiert;
	}

	/**
	 * @param fachExistiert the fachExistiert to set
	 */
	public void setFachExistiert(boolean fachExistiert) {
		this.fachExistiert = fachExistiert;
	}

	/**
	 * @return the fachService
	 */
	public FachService getFachService() {
		return fachService;
	}

	/**
	 * @param fachService the fachService to set
	 */
	public void setFachService(FachService fachService) {
		this.fachService = fachService;
	}

	/**
	 * @return the modulService
	 */
	public ModuleService getModulService() {
		return modulService;
	}

	/**
	 * @param modulService the modulService to set
	 */
	public void setModulService(ModuleService modulService) {
		this.modulService = modulService;
	}

	/**
	 * @return the modulhandbuchService
	 */
	public ModulhandbuchService getModulhandbuchService() {
		return modulhandbuchService;
	}

	/**
	 * @param modulhandbuchService the modulhandbuchService to set
	 */
	public void setModulhandbuchService(ModulhandbuchService modulhandbuchService) {
		this.modulhandbuchService = modulhandbuchService;
	}

	/**
	 * @return the statusGreen
	 */
	public String getStatusGreen() {
		return statusGreen;
	}

	/**
	 * @param statusGreen the statusGreen to set
	 */
	public void setStatusGreen(String statusGreen) {
		this.statusGreen = statusGreen;
	}

	/**
	 * @return the statusRed
	 */
	public String getStatusRed() {
		return statusRed;
	}

	/**
	 * @param statusRed the statusRed to set
	 */
	public void setStatusRed(String statusRed) {
		this.statusRed = statusRed;
	}

	/**
	 * @return the aktHb
	 */
	public Modulhandbuch getAktHb() {
		return aktHb;
	}

	/**
	 * @param aktHb the aktHb to set
	 */
	public void setAktHb(Modulhandbuch aktHb) {
		this.aktHb = aktHb;
	}

	/**
	 * @return the freigabeStatus
	 */
	public boolean isFreigabeStatus() {
		return freigabeStatus;
	}

	/**
	 * @param freigabeStatus the freigabeStatus to set
	 */
	public void setFreigabeStatus(boolean freigabeStatus) {
		this.freigabeStatus = freigabeStatus;
	}
	/**
	 * 
	 * @author mw59
	 * TimerTask klasse um Statusmeldungen zuruekzusetzten
	 */
	class MyTimerTask extends TimerTask{
		private HandbuchverwaltungStudiendekanBean m;
		/**
		 * Konstruktor, erwartet als uebergabeparameter ein HandbuchverwaltungStudiendekanBean
		 * @param m
		 */
		public MyTimerTask(HandbuchverwaltungStudiendekanBean m){
			this.m = m;
		}
		/**
		 * Setzt die boolean modulErfolgreich und modulgescheitert auf false zuruek, 
		 * die statusausgabe wird beim erneuten aufrufen der seite wieder ausgeblendet
		 */
		@Override
		public void run(){
			System.out.println("HALLO; ICH BIN EIN TIMER =)");
			m.setFreigabeStatus(false);
//			timer.cancel();
		}
	}
}
