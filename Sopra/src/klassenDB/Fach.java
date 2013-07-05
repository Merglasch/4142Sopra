package klassenDB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Datenbankklasse fuer die Fachtabelle.
 * 
 */
@Entity
public class Fach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int fid;

	private String fach;

	/**
	 * Standard-Konstruktor.
	 */
	public Fach() {
	}
	
	/**
	 * Get-Methode fuer die ID eines Fachs.
	 * 
	 * @return Die ID des Fachs
	 */
	public int getFid() {
		return this.fid;
	}

	/**
	 * Set-Methode fuer die ID eines Fachs.
	 * 
	 * @param Die ID die fuer das Fach gesetzt werden soll
	 */
	public void setFid(int fid) {
		this.fid = fid;
	}

	
	/**
	 * Get-Methode fuer den Namen eines Fachs.
	 * 
	 * @return Der Name des Fachs
	 */
	public String getFach() {
		return this.fach;
	}

	/**
	 * Set-Methode fuer den Namen eines Fachs.
	 * 
	 * @param Der Name der fuer das Fach gesetzt werden soll
	 */
	public void setFach(String fach) {
		this.fach = fach;
	}

}