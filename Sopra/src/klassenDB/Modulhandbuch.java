package klassenDB;
import java.sql.Timestamp;
import java.util.List;


public class Modulhandbuch {
	private int handbuchID;//pk
	private String abschluss;
	private String studiengang;
	private String pruefungsordnung;
	private Timestamp zeitstempel;
	private String dekan;
	private int uID; //uID
	
	
	public Modulhandbuch(int handbuchID, String abschluss, String studiengang,
			String pruefungsordnung, Timestamp zeitstempel, String dekan,
			int uID) {
		super();
		this.handbuchID = handbuchID;
		this.abschluss = abschluss;
		this.studiengang = studiengang;
		this.pruefungsordnung = pruefungsordnung;
		this.zeitstempel = zeitstempel;
		this.dekan = dekan;
		this.uID = uID;
	}


	
	// getter und setter
	public int getHandbuchID() {
		return handbuchID;
	}


	public void setHandbuchID(int handbuchID) {
		this.handbuchID = handbuchID;
	}


	public String getAbschluss() {
		return abschluss;
	}


	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}


	public String getStudiengang() {
		return studiengang;
	}


	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}


	public String getPruefungsordnung() {
		return pruefungsordnung;
	}


	public void setPruefungsordnung(String pruefungsordnung) {
		this.pruefungsordnung = pruefungsordnung;
	}


	public Timestamp getZeitstempel() {
		return zeitstempel;
	}


	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}


	public String getDekan() {
		return dekan;
	}


	public void setDekan(String dekan) {
		this.dekan = dekan;
	}


	public int getuID() {
		return uID;
	}


	public void setuID(int uID) {
		this.uID = uID;
	}		
	
}
