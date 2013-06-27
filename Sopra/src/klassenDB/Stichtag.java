package klassenDB;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the STICHTAG database table.
 * 
 */
@Entity
public class Stichtag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.DATE)
	private Date stichtag;

	public Stichtag() {
	}

	public Date getStichtag() {
		return this.stichtag;
	}

	public void setStichtag(Date stichtag) {
		this.stichtag = stichtag;
	}
}