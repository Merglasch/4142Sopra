package klassenDB;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the STICHTAG database table.
 * 
 */
@Entity
public class Stichtag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String stichtag;

	public Stichtag() {
	}

	public String getStichtag() {
		return this.stichtag;
	}

	public void setStichtag(String stichtag) {
		this.stichtag = stichtag;
	}
}