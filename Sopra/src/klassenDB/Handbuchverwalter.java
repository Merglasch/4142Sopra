package klassenDB;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


/**
 * The persistent class for the HANDBUCHVERWALTER database table.
 * 
 */
@Entity
public class Handbuchverwalter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HandbuchverwalterPK id;

	public Handbuchverwalter() {
	}

	public HandbuchverwalterPK getId() {
		return this.id;
	}

	public void setId(HandbuchverwalterPK id) {
		this.id = id;
	}

}