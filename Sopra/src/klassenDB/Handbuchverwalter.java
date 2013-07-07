package klassenDB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Datenbanktabelle fuer die Hanbuchverwalter Tabelle.
 * 
 */
@Entity
public class Handbuchverwalter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HandbuchverwalterPK id;

	/**
	 * Standard-Konstruktor.
	 */
	public Handbuchverwalter() {
	}

	/**
	 * Get-Methode fuer das HandbuchverwalterPK-Objekt, das den Primaerschluessel
	 * der Handbuchverwaltertabelle verwaltet.
	 * 
	 * @return Das HandbuchverwalterPK-Objekt das den Primaerschluessel verwaltet.
	 * 
	 */
	public HandbuchverwalterPK getId() {
		return this.id;
	}

	/**
	 * Diese Methode legt das HandbuchverwalterPK-Objekt fest, das den Primaerschluessel
	 * der Handbuchverwaltertabelle verwaltet.
	 * 
	 * @param HandbuchverwalterPK-Objekt
	 */
	public void setId(HandbuchverwalterPK id) {
		this.id = id;
	}

}