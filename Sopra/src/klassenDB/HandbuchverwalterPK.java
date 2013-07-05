package klassenDB;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Diese Klasse verwaltet den Primaerschluessel der Handbuchverwaltertabelle.
 * Dabei verwendet sie jeweils die ID eines Moduls, Fachs und Handbuchs, aus denen sie
 * einen neuen Primaerschluessel zusammensetzt.
 * 
 */
@Embeddable
public class HandbuchverwalterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int modulid;

	private int handbuchid;

	private int fid;

	/**
	 * StandardKonstruktor.
	 */
	public HandbuchverwalterPK() {
	}
	
	/**
	 * Get-Methode fuer die ModulID der Handbuchverwaltertabelle.
	 * 
	 * @return Die ModulID der HandbuchverwalterPK-Tabelle
	 */
	public int getModulid() {
		return this.modulid;
	}
	
	/**
	 * Set-Methode fuer die ModulID der Handbuchverwaltertabelle.
	 * 
	 * @param Die ModulID der HandbuchverwalterPK-Tabelle
	 */
	public void setModulid(int modulid) {
		this.modulid = modulid;
	}
	
	/**
	 * Get-Methode fuer die HandbuchID der Handbuchverwaltertabelle.
	 * 
	 * @return Die HandbuchID der HandbuchverwalterPK-Tabelle
	 */
	public int getHandbuchid() {
		return this.handbuchid;
	}
	
	/**
	 * Set-Methode fuer die HandbuchID der Handbuchverwaltertabelle.
	 * 
	 * @param Die HandbuchID der HandbuchverwalterPK-Tabelle
	 */
	public void setHandbuchid(int handbuchid) {
		this.handbuchid = handbuchid;
	}
	
	/**
	 * Get-Methode fuer die FachID der Handbuchverwaltertabelle.
	 * 
	 * @return Die FachID der HandbuchverwalterPK-Tabelle
	 */
	public int getFid() {
		return this.fid;
	}
	
	/**
	 * Set-Methode fuer die HandbuchID der Handbuchverwaltertabelle.
	 * 
	 * @param Die HandbuchID der HandbuchverwalterPK-Tabelle
	 */
	public void setFid(int fid) {
		this.fid = fid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HandbuchverwalterPK)) {
			return false;
		}
		HandbuchverwalterPK castOther = (HandbuchverwalterPK)other;
		return 
			(this.modulid == castOther.modulid)
			&& (this.handbuchid == castOther.handbuchid)
			&& (this.fid == castOther.fid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.modulid;
		hash = hash * prime + this.handbuchid;
		hash = hash * prime + this.fid;
		
		return hash;
	}
}