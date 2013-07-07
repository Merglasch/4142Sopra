package model;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;

public class HBVWtabellenausgabe {

	public HBVWtabellenausgabe() {
		super();
	}
	
	private Modul modul;
	private Modulhandbuch modulhandbuch;
	private Fach fach;
	
	public Modul getModul() {
		return modul;
	}
	
	public void setModul(Modul modul) {
		this.modul = modul;
	}
	public Modulhandbuch getModulhandbuch() {
		return modulhandbuch;
	}
	public void setModulhandbuch(Modulhandbuch modulhandbuch) {
		this.modulhandbuch = modulhandbuch;
	}
	public Fach getFach() {
		return fach;
	}
	public void setFach(Fach fach) {
		this.fach = fach;
	}

}
