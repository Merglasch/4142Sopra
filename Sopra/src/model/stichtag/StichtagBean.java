package model.stichtag;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;

import klassenDB.Stichtag;

public class StichtagBean {

	public StichtagBean() {
		super();
	}
	
	@EJB
	StichtagService stichtagService;
	
	private Stichtag stichtag;
	private String selectStichtag="dd.mm.yyyy";
	private String stichtagStatus;
	private boolean stichtagErfolgreich = false;
	private boolean notStichtagErfolgreich = false;
	

	boolean vergleich;//datumsvergleich
	
	public String updateStichtag(){
		System.out.println("++++++++++++++++++++++++++++++methode update");
		try{
			stichtag =stichtagService.getStichtag(); //Alten stichtag einlesen
		}catch(Exception e){
			e.printStackTrace();
		}
		stichtagErfolgreich=false;
		//schaut ob der Stichtag nur Zahlen enthält
		String[] splitStichtag = selectStichtag.split("\\.");
		String tmpStichtag="";
		int[] splitInt = new int[3];
		for(int i=0; i<splitStichtag.length; i++){
			try {
				splitInt[i] = Integer.parseInt(splitStichtag[i]);
			}
			 catch (NumberFormatException e) {
				 stichtagStatus="Bitte nur Zahlen nach diesem Format: dd.mm.yyyy eingeben";
				 return "stichtagSetzen";
			}	
		}
		if(splitInt[0]<=0 || splitInt[0]>31){
			if(splitInt[0]<10){
				tmpStichtag +="0"+splitInt[0];
			}else{
				tmpStichtag +=splitInt[0];
			}
			
			stichtagErfolgreich=false;
			stichtagStatus="Tag zwischen 1 und 31 eingeben";
			return "stichtagSetzen";
		}else
		if(splitInt[1]<=0 || splitInt[1]>12){
			if(splitInt[1]<10){
				tmpStichtag +=".0"+ splitInt[1];
			}else{
				tmpStichtag +="."+ splitInt[1];
			}
			tmpStichtag += "."+ splitInt[2];
			selectStichtag=tmpStichtag;// korrektes datumsformat
			stichtagErfolgreich=false;
			stichtagStatus="Monat zwischen 1 und 12 eingeben";
			return "stichtagSetzen";
		}else // datum ist richtig eingegeben
		{
			stichtagStatus="Datum ist richtig eingegeben";
			try {
				System.out.println("try block");
				Date stichtagDatum = new Date();
				DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			    stichtagDatum = formatter.parse(selectStichtag);
//				long dateInLong = date.getTime();
				
				Date akt = new Date(System.currentTimeMillis());
				if(vergleich = akt.before(stichtagDatum)){// stichtag liegt von aktueller zeit aus gesehen in d zukunft
							stichtagErfolgreich=true;
							System.out.println("Stichtag liegt in der Zukunft");
							stichtagStatus="Stichtag wird hinzugefügt";
							
							
							//wenn es noch keinen Stichtag gibt
							if(stichtag == null){
									Stichtag neuStichtag = new Stichtag();
									neuStichtag.setStichtag(selectStichtag);
									stichtagErfolgreich=stichtagService.updateStichtag(neuStichtag);
									if(stichtagErfolgreich){
										stichtagStatus="Stichtag wurde erfolgreich hinzugefügt";
										stichtagStatus="Der Stichtag wurde auf den " + selectStichtag + " erfolgreich gesetzt";
										return "stichtagSetzen";
									}
									else{
										System.out.println("Fehler beim Stichtag setzen (Stichtag==null)");
									}
							}else{  //wenn ein Stichtag bereits in der Datenbank existiert
									Stichtag neuStichtag = new Stichtag();
									neuStichtag.setStichtag(selectStichtag);
									stichtagErfolgreich = stichtagService.updateStichtag(neuStichtag);
									if(stichtagErfolgreich){
										stichtagErfolgreich=false;
										stichtagStatus="Der Stichtag wurde vom " + stichtag.getStichtag() + "auf den " + selectStichtag + "erfolgreich geändert";
										return "stichtagSetzen";
									}
									else{
										stichtagErfolgreich=false;
										stichtagStatus="Fehler beim Stichtag setzen: Stichtag wurde nicht gesetzt";
										System.out.println("Fehler beim Stichtag setzen: Stichtag wurde nicht upgedated");
										return "stichtagSetzen";
									}
							}
				}else{//stichtag liegt in d vergangenheit
					stichtagErfolgreich=false; // stichtag liegt in d vergangenheit
					stichtagStatus="Bitte nur Zahlen nach diesem Format: dd.mm.yyyy eingeben";
					System.out.println("ACHTUNG! Eingegebenes Datum ist bereits gelebt worden =)");
					return "stichtagSetzen";
				}
				
			} catch (ParseException e) {
				System.out.println("catch block");
				e.printStackTrace();
			}
		}
		
		return "stichtagSetzen";
	}
	

	/**
	 * @return the stichtag
	 */
	public Stichtag getStichtag() {
		return stichtag = stichtagService.getStichtag();
//		stichtag = new Stichtag();
//		stichtag.setStichtag("22.10.2014");
//		return stichtag ;
	}
	/**
	 * @param stichtag the stichtag to set
	 */
	public void setStichtag(Stichtag stichtag) {
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

	/**
	 * @return the stichtagStatus
	 */
	public String getStichtagStatus() {
		return stichtagStatus;
	}
	
	
	/**
	 * @param stichtagStatus the stichtagStatus to set
	 */
	public void setStichtagStatus(String stichtagStatus) {
		this.stichtagStatus = stichtagStatus;
	}
	
	
	/**
	 * @return the stichtagErfolgreich
	 */
	public boolean isStichtagErfolgreich() {
		return stichtagErfolgreich;
	}
	
	
	/**
	 * @param stichtagErfolgreich the stichtagErfolgreich to set
	 */
	public void setStichtagErfolgreich(boolean stichtagErfolgreich) {
		this.stichtagErfolgreich = stichtagErfolgreich;
	}
	
	

	/**
	 * @return the notStichtagErfolgreich
	 */
	public boolean isNotStichtagErfolgreich() {
		notStichtagErfolgreich = !stichtagErfolgreich;
		return notStichtagErfolgreich;
	}
	
	
	/**
	 * @param notStichtagErfolgreich the notStichtagErfolgreich to set
	 */
	public void setNotStichtagErfolgreich(boolean notStichtagErfolgreich) {
		this.notStichtagErfolgreich = notStichtagErfolgreich;
	}
	
	
	/**
	 * @return the vergleich
	 */
	public boolean isVergleich() {
		return vergleich;
	}
	
	
	/**
	 * @param vergleich the vergleich to set
	 */
	public void setVergleich(boolean vergleich) {
		this.vergleich = vergleich;
	}
}
