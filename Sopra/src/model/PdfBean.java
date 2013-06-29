package model;

import java.io.Serializable;

import javax.ejb.EJB;

import model.modules.CreatePdf;
import model.modules.ModuleService;
import klassenDB.Modul;


public class PdfBean implements Serializable{

	private Modul modul = getModul();
	private String modulname;
	private CreatePdf pdf;

	@EJB
	private ModuleService moduleService;
	
	public Modul getModul() {
		return modul=moduleService.searchByName(modulname);
	}
	
	public String createPdf(){
		pdf= new CreatePdf(modul);
		pdf.makeDocument();
		
		return "pdfSeite";
	}
	//getter- und setter Methoden
	/**
	 * @return the modul
	 */
	public Modul getPdf() {
		return modul;
	}
	
	/**
	 * @param modul the modul to set
	 */
	public void setModul(Modul modul) {
		this.modul = modul;
	}
	
	/**
	 * @return the modulname
	 */
	public String getModulname() {
		return modulname;
	}
	
	
	/**
	 * @param modulname the modulname to set
	 */
	public void setModulname(String modulname) {
		this.modulname = modulname;
	}
}
