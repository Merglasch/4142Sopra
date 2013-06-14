package klassenDB;
import java.util.List;


public class Modulhandbuch {
	private String name;
	private String abschluss;
	private String studiengang;
	private String pruefungsordnung;
	private String version;		
	private List<Modul> modul;  
	
	public Modulhandbuch() {
	}
	public Modulhandbuch(String name, String abschluss, String studiengang, 
					String pruefungsordnung, String version, List<Modul> modul) {
		this.name = name;
		this.abschluss = abschluss;
		this.studiengang = studiengang;
		this.pruefungsordnung = pruefungsordnung;
		this.version = version;
		this.modul = modul;
	}
	
	
	
	
	// getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<Modul> getModul() {
		return modul;
	}
	public void setModul(List<Modul> modul) {
		this.modul = modul;
	}
}
