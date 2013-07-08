package model.stichtag;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import klassenDB.Stichtag;

public class StichtagBean {

	public StichtagBean() {
		super();
	}

	@EJB
	StichtagService stichtagService;

	private Stichtag stichtag;
	private String aktStichtag;
	private String selectStichtag = "dd.mm.yyyy";
	private String stichtagStatus;
	private boolean stichtagErfolgreich = false;
	private boolean notStichtagErfolgreich = false;

	boolean vergleich;// datumsvergleich

	/**
	 * initialisiert den aktuellen stichtag
	 * laedt stichtag aus der DB
	 */
	@PostConstruct
	public void init(){
		aktStichtag=stichtagService.getStichtag().getStichtag();
	}
	
	/**
	 * Ueberprüfung und Aenderung des eingegebenen Stichtags
	 * Die Eingabe wird auf Korrektheit geprueft und mit aktuellem Datum verglichen.
	 * Wenn alle Pruefungen bestanden wird die Methode zum setzen des Stichtags aufgerufen.
	 * Ausgabe für Benutzer bei jeder Systemaenderung
	 * 
	 * @return
	 */
	public String updateStichtag() {
		System.out.println("+++++++++++methode update");
		try {
			stichtag = stichtagService.getStichtag(); // Alten stichtag einlesen
			System.out.println("Alter Stichtag: " + stichtag.getStichtag());
		} catch (Exception e) {
			e.printStackTrace();
		}
		stichtagErfolgreich = false;
		// schaut ob der Stichtag nur Zahlen enthält
		String[] splitStichtag = selectStichtag.split("\\.");
		String tmpStichtag = "";
		int[] splitInt = new int[3];
		for (int i = 0; i < splitStichtag.length; i++) {
			try {
				splitInt[i] = Integer.parseInt(splitStichtag[i]);
			} catch (NumberFormatException e) {
				stichtagStatus = "Bitte nur Zahlen nach diesem Format: dd.mm.yyyy eingeben";
				return "stichtagSetzen";
			}
		}
		if (splitInt[0] <= 0 || splitInt[0] > 31) {
			stichtagErfolgreich = false;
			stichtagStatus = "Tag zwischen 1 und 31 eingeben";
			return "stichtagSetzen";
		} else if (splitInt[1] <= 0 || splitInt[1] > 12) {
			stichtagErfolgreich = false;
			stichtagStatus = "Monat zwischen 1 und 12 eingeben";
			return "stichtagSetzen";
		} else { // datum ist richtig eingegeben
			if (splitInt[0] < 10) {
				tmpStichtag += "0" + splitInt[0];
			} else {
				tmpStichtag += splitInt[0];
			}
			if (splitInt[1] < 10) {
				tmpStichtag += ".0" + splitInt[1];
			} else {
				tmpStichtag += "." + splitInt[1];
			}
			tmpStichtag += "." + splitInt[2];
			selectStichtag = tmpStichtag;// korrektes Datumsformat
			stichtagStatus = "Datum ist richtig eingegeben";
			try {
				System.out.println("try block");
				Date stichtagDatum = new Date();
				DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
				stichtagDatum = formatter.parse(selectStichtag) ;
				Calendar cal = Calendar.getInstance();
				cal.setTime(stichtagDatum);
				System.out.println("Datum nicht erhöht: " + cal.getTime());
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1); //Tag des eingegebenen Stichtags +1 damit er mit aktueller Sys Zeit verglichen werden kann
				System.out.println("Datum um eins erhöht: " + cal.getTime());		//sonst wuerde Stichtag wenn er am selben Tag ist, wegen fehlender Zeitinformation nicht akzeptiert werden

				Date akt = new Date(System.currentTimeMillis());
				Calendar calAkt = Calendar.getInstance();
				calAkt.setTime(akt);
				if (vergleich = calAkt.before(cal)) {// Stichtag liegt von aktueller Zeit aus gesehen in der Zukunft
					stichtagErfolgreich = true;
					System.out.println("Stichtag liegt in der Zukunft");
					stichtagStatus = "Stichtag wird hinzugefügt";

					// wenn es noch keinen Stichtag gibt
					if (stichtag == null) {
						Stichtag neuStichtag = new Stichtag();
						neuStichtag.setStichtag(selectStichtag);
						stichtagErfolgreich = stichtagService
								.updateStichtag(neuStichtag);
						if (stichtagErfolgreich) {
							stichtagStatus = "Stichtag wurde erfolgreich hinzugefügt";
							stichtagStatus = "Der Stichtag wurde auf den "
									+ selectStichtag + " erfolgreich gesetzt";
							return "stichtagSetzen";
						} else {
							System.out
									.println("Fehler beim Stichtag setzen (Stichtag==null)");
						}
					} else { // wenn ein Stichtag bereits in der Datenbank existiert
						Stichtag neuStichtag = new Stichtag();
						neuStichtag.setStichtag(selectStichtag);
						stichtagErfolgreich = stichtagService
								.updateStichtag(neuStichtag);
						if (stichtagErfolgreich) {
							stichtagErfolgreich = true;
							stichtagStatus = "Der Stichtag wurde vom "
									+ stichtag.getStichtag() + " auf den "
									+ selectStichtag + " erfolgreich geändert";
							return "stichtagSetzen";
						} else {
							stichtagErfolgreich = false;
							stichtagStatus = "Fehler beim Stichtag setzen: Stichtag wurde nicht gesetzt";
							System.out
									.println("Fehler beim Stichtag setzen: Stichtag wurde nicht aktualisiert");
							return "stichtagSetzen";
						}
					}
				} else {//Stichtag liegt in der Vergangenheit
					stichtagErfolgreich = false;												 
					stichtagStatus = "Das eingegebene Datum liegt in der Vergangenheit";
					System.out
							.println("ACHTUNG! Eingegebenes Datum ist bereits vergeben worden =)");
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
	}

	/**
	 * @param stichtag
	 *            the stichtag to set
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
	 * @param selectStichtag
	 *            the selectStichtag to set
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
	 * @param stichtagStatus
	 *            the stichtagStatus to set
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
	 * @param stichtagErfolgreich
	 *            the stichtagErfolgreich to set
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
	 * @param notStichtagErfolgreich
	 *            the notStichtagErfolgreich to set
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
	 * @param vergleich
	 *            the vergleich to set
	 */
	public void setVergleich(boolean vergleich) {
		this.vergleich = vergleich;
	}

	/**
	 * 
	 * @return stichtagService
	 */
	public StichtagService getStichtagService() {
		return stichtagService;
	}

	/**
	 * 
	 * @param stichtagService
	 */
	public void setStichtagService(StichtagService stichtagService) {
		this.stichtagService = stichtagService;
	}
	
	/**
	 * 
	 * @return aktStichtag
	 */
	public String getAktStichtag() {
		return aktStichtag;
	}

	/**
	 * Setzt übergebenen Stichtag, wenn Stichtag erfolgreich gesetzt wurde. 
	 * Benötigt für Stichtaganzeige auf der Webseite
	 * @param aktStichtag
	 */
	public void setAktStichtag(String aktStichtag) {
		if(stichtagErfolgreich)
			this.aktStichtag = aktStichtag;
	}
}