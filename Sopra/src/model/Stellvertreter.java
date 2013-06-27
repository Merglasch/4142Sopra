package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STELLVERTRETER database table.
 * 
 */
@Entity
public class Stellvertreter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StellvertreterPK id;

	public Stellvertreter() {
	}

	public StellvertreterPK getId() {
		return this.id;
	}

	public void setId(StellvertreterPK id) {
		this.id = id;
	}

}