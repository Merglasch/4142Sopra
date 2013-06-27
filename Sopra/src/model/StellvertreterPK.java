package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the STELLVERTRETER database table.
 * 
 */
@Embeddable
public class StellvertreterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int stv;

	private int hauptpers;

	public StellvertreterPK() {
	}
	public int getStv() {
		return this.stv;
	}
	public void setStv(int stv) {
		this.stv = stv;
	}
	public int getHauptpers() {
		return this.hauptpers;
	}
	public void setHauptpers(int hauptpers) {
		this.hauptpers = hauptpers;
	}

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

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.stv;
		hash = hash * prime + this.hauptpers;
		
		return hash;
	}
}