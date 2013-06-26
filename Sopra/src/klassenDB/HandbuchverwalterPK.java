package klassenDB;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HANDBUCHVERWALTER database table.
 * 
 */
@Embeddable
public class HandbuchverwalterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int modulid;

	private int handbuchid;

	public HandbuchverwalterPK() {
	}
	public int getModulid() {
		return this.modulid;
	}
	public void setModulid(int modulid) {
		this.modulid = modulid;
	}
	public int getHandbuchid() {
		return this.handbuchid;
	}
	public void setHandbuchid(int handbuchid) {
		this.handbuchid = handbuchid;
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
			&& (this.handbuchid == castOther.handbuchid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.modulid;
		hash = hash * prime + this.handbuchid;
		
		return hash;
	}
}