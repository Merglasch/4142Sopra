package klassenDB;

public class Benachrichtigung {
	private String id;
	private String text;
	private String betreff;
	private String eMail;
	private String modulname;
	
	public Benachrichtigung() {
	}
	public Benachrichtigung(String id, String text, 
			String betreff, String eMail,String modulname ) {
		this.id = id;
		this.text = text;
		this.betreff = betreff;
		this.eMail = eMail;
		this.modulname= modulname;

	}
	
	
	//getters and setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getModulname() {
		return modulname;
	}
	public void setModulname(String modulname) {
		this.modulname = modulname;
	}
	
	
}
