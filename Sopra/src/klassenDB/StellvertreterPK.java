package klassenDB;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Diese Klasse verwaltet den Primaerschluessel der Stellvertretertabelle.
 * Dabei verwendet sie jeweils die ID eines Stellvertreters und einer Hauptperson, um
 * einen neuen Primaerschluessel zu erzeugen.
 * 
 */
@Embeddable
public class StellvertreterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int stv;

	private int hauptpers;

	/**
	 * Standard-Konstruktor.
	 */
	public StellvertreterPK() {
	}
	
	/**
	 * 
	 * @return Stellvertreter
	 */
	public int getStv() {
		return this.stv;
	}
	
	/**
	 * 
	 * @param Stellvertreter
	 */
	public void setStv(int stv) {
		this.stv = stv;
	}
	
	/**
	 * 
	 * @return Hauptperson
	 */
	public int getHauptpers() {
		return this.hauptpers;
	}
	
	/**
	 * 
	 * @param Hauptperson
	 */
	public void setHauptpers(int hauptpers) {
		this.hauptpers = hauptpers;
	}

	/**
	 * ueberprueft ob ein uebergebenen Object other mit der aktuellen instanz uebereinstimmt
	 * @param other
	 * @return true, wenn Object other mit der aktuellen instanz uebereinstimmt
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StellvertreterPK)) {
			return false;
		}
		StellvertreterPK castOther = (StellvertreterPK)other;
		return 
			(this.stv == castOther.stv)
			&& (this.hauptpers == castOther.hauptpers);
	}

	/**
	 * erzeugt hashcode mithilfe von stellvertreter und hauptperson
	 * @return hashcode
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.stv;
		hash = hash * prime + this.hauptpers;
		
		return hash;
	}
}