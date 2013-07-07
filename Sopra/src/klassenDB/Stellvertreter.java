package klassenDB;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


/**
 * Datenbanktabelle fuer die Stellvertretertabelle.
 * 
 */
@Entity
public class Stellvertreter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StellvertreterPK id;

	/**
	 * Standard-Konstruktor.
	 */
	public Stellvertreter() {
	}

	/**
	 * Get-Methode fuer das StellvertreterPK-Objekt, das den Primaerschluessel
	 * der Stellvertretertabelle verwaltet.
	 * 
	 * @return Das StellvertreterPK-Objekt das den Primaerschluessel verwaltet.
	 * 
	 */
	public StellvertreterPK getId() {
		return this.id;
	}

	/**
	 * Diese Methode legt das StellvertreterPK-Objekt fest, das den Primaerschluessel
	 * der Stellvertretertabelle verwaltet.
	 * 
	 * @param StellvertreterPK-Objekt
	 */
	public void setId(StellvertreterPK id) {
		this.id = id;
	}

}