package klassenDB;

import java.io.Serializable;
import javax.persistence.*;



/**
 * Datenbanktabelle fuer die Stichtagtabelle.
 * 
 */
@Entity
public class Stichtag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String stichtag;

	/**
	 * Standard-Konstruktor.
	 */
	public Stichtag() {
	}

	/**
	 * 
	 * @return Stichtag
	 */
	public String getStichtag() {
		return this.stichtag;
	}

	/**
	 * 
	 * @param Stichtag
	 */
	public void setStichtag(String stichtag) {
		this.stichtag = stichtag;
	}
}