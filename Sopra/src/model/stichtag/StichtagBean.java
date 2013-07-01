package model.stichtag;

import javax.ejb.EJB;
import java.util.Date;

import klassenDB.Stichtag;

public class StichtagBean {

	public StichtagBean() {
		super();
	}
	
	@EJB
	StichtagService stichtagService;
	
	private String stichtag;
	private String selectStichtag = "dd.mm.yyyy";
	private String stichtagStatus = "";
	private boolean stichtagErfolgreich = false;
	
	public String updateStichtag(){
		stichtagErfolgreich = stichtagService.updateStichtag(selectStichtag);

		if(stichtag==null && stichtagErfolgreich){
			stichtagService.updateStichtag(selectStichtag);
			stichtagStatus="Stichtag wurde erfolgreich hinzugefügt";
			return "stichtagSetzen";
		}
		
		if(stichtagErfolgreich){
			stichtagStatus="Der Stichtag wurde vom " + stichtag + "auf den " + selectStichtag + "erfolgreich geändert";
			return "stichtagSetzen";
		}
		else{
			System.out.println("Fehler bei Stichtag setzen");
			return "stichtagSetzen";
		}
	
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
