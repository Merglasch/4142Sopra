package klassenDB;

public class Benachrichtigung {
	private String nachrichtID; // PK
	private String text;
	private String betreff;
	private int uID;  // FK
	private int modulID; // FK
	
	public Benachrichtigung() {
	}
	public Benachrichtigung(String nachrichtID, String text, 
			String betreff, int uID, int modulID ) {
		this.nachrichtID = nachrichtID;
		this.text = text;
		this.betreff = betreff;
		this.uID=uID;
		this.modulID = modulID;

	}
	
	
	//getters and setters
	public String getId() {
		return nachrichtID;
	}
	public void setId(String id) {
		this.nachrichtID = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getBetreff() {
		return betreff;
	}
	public void setBetreff(String betreff) {
		this.betreff = betreff;
	}
		
}
