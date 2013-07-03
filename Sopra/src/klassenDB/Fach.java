package klassenDB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FACH database table.
 * 
 */
@Entity
public class Fach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int fid;

	private String fach;

	public Fach() {
	}

	public int getFid() {
		return this.fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFach() {
		return this.fach;
	}

	public void setFach(String fach) {
		this.fach = fach;
	}

}