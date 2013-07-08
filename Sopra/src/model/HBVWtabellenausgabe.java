package model;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;
/**
 * klasse repraesentiert  einen eintrag der Handbuchverwalter tabelle der DB
 * @author mw59
 *
 */
public class HBVWtabellenausgabe {

	/**
	 * standart Konstruktor
	 */
	public HBVWtabellenausgabe() {
		super();
	}
	
	private Modul modul;
	private Modulhandbuch modulhandbuch;
	private Fach fach;
	/**
	 * gibt Modul
	 * 
	 * @return modul
	 */
	public Modul getModul() {
		return modul;
	}
	/**
	 * setzt Modul
	 * 
	 * @param modul
	 */
	public void setModul(Modul modul) {
		this.modul = modul;
	}
	/**
	 * gibt Modulhandbuch
	 * 
	 * @return modulhandbuch
	 */
	public Modulhandbuch getModulhandbuch() {
		return modulhandbuch;
	}
	
	/**
	 * setzt modulhandbuch
	 * @param modulhandbuch
	 */
	public void setModulhandbuch(Modulhandbuch modulhandbuch) {
		this.modulhandbuch = modulhandbuch;
	}
	
	/**
	 * gibt Fach
	 * 
	 * @return fach
	 */
	public Fach getFach() {
		return fach;
	}
	
	/**
	 * setzt Fach
	 * 
	 * @param fach
	 */
	public void setFach(Fach fach) {
		this.fach = fach;
	}

}
