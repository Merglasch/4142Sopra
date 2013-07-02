package model.stichtag;

import javax.ejb.EJB;
import java.util.Date;

import klassenDB.Stichtag;

public class StichtagBean {

	public StichtagBean() {
		super();
		stichtagStatus = "Bitte geben Sie ein Datum ein";
	}
	
	@EJB
	StichtagService stichtagService;
	
	private String stichtag;
	private String selectStichtag = "dd.mm.yyyy";
	private String stichtagStatus = "";
	private boolean stichtagErfolgreich = false;
	
	/**
	 * @return Stichtag hinzufügen
	 */
	public String updateStichtag(){
		int laengeStichtag = selectStichtag.length();
		
		//schaut ob der Stichtag nur Zahlen enthält
		String[] splitStichtag = selectStichtag.split("\\.");
		for(int i=0; i<splitStichtag.length; i++){
			try {
				int split = Integer.parseInt(splitStichtag[i]);
			}
			 catch (NumberFormatException e) {
				 stichtagStatus="Bitte nur Zahlen nach diesem Format: dd.mm.yyyy eingeben";
			}
		}
		
		//schaut ob das Format des Datums eingehalten wurde
		if(laengeStichtag!=10){
			stichtagStatus="Bitte das Datum nach diesem Format: dd.mm.yyyy eingeben";
		}
		
		//wenn es noch keinen Stichtag gibt
		else if(stichtag == null){
			stichtagErfolgreich=stichtagService.updateStichtag(selectStichtag);
			if(stichtagErfolgreich)
				stichtagStatus="Stichtag wurde erfolgreich hinzugefügt";
			else
				System.out.println("Fehler beim Stichtag setzen (Stichtag==null)");
		}
		
		//wenn ein Stichtag bereits in der Datenbank existiert
		else if(stichtag != null){
			stichtagErfolgreich = stichtagService.updateStichtag(selectStichtag);
			if(stichtagErfolgreich)
				stichtagStatus="Der Stichtag wurde vom " + stichtag + "auf den " + selectStichtag + "erfolgreich geändert";
			else
				System.out.println("Fehler beim Stichtag setzen: Stichtag wurde nicht upgedated");
		}
		
		else{
			System.out.println("Fehler bei Stichtag setzen (Allgemein)");
		}
	
		return "stichtagSetzen";
	}
	

	/**
	 * @return the stichtag
	 */
	public String getStichtag() {
		return stichtag = stichtagService.getStichtag();
	}
	/**
	 * @param stichtag the stichtag to set
	 */
	public void setStichtag(String stichtag) {
		this.stichtag = stichtag;
	}
	/**
	 * @return the selectStichtag
	 */
	public String getSelectStichtag() {
		return selectStichtag;
	}
	/**
	 * @param selectStichtag the selectStichtag to set
	 */
	public void setSelectStichtag(String selectStichtag) {
		this.selectStichtag = selectStichtag;
	}	

}
