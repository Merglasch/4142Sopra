package model.modules;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import klassenDB.Modul;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePdf {
	
	  private Modul modul;
	  private List<String> modulwerte = modulListeWerte();
	  private List<String> modulattribute = modulListeAttribute();
	  //PDF wird aufm Desktop erzeugt
	  private String FILE = "/resources/pdf_folder/";
	  
	  //Schriftgröße
	  private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	      Font.BOLD);
	  
	  public CreatePdf(Modul modul){
		  this.modul = modul;
	  }
	  
	  public void makeDocument() {
		  File f = new File(FILE+"modul.pdf");		  
		  //löscht die Pdf, wenn diese bereits schon existiert
		  if(f.exists()) {
			  f.delete();
		  }
		  try {
		      Document document = new Document();
		      PdfWriter.getInstance(document, new FileOutputStream(FILE));
		      document.open();
		      Image image1 = Image.getInstance("img/logo1.png");
		      image1.setAbsolutePosition(35, 760);
		      Image image2 = Image.getInstance("img/logo2.png");
		      image2.setAbsolutePosition(340, 760);
		      document.add(image1);
		      document.add(image2);
		      addMetaData(document);
		      addTitlePage(document);
		   
		      document.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }

	  //Metadaten
	  public void addMetaData(Document document) {
	    document.addTitle("Modul");
	    document.addSubject("Using iText");
	    document.addKeywords("Java, PDF, iText");
	    document.addAuthor("MMS");
	    document.addCreator("MMS");
	  }

	  public void addTitlePage(Document document) throws DocumentException {
		    Paragraph preface = new Paragraph();
		    addEmptyLine(preface, 7);
		    
		    // Überschrift fürs Modul
		    preface.add(new Paragraph(modul.getModulname(), catFont));
		    addEmptyLine(preface, 2);
		    document.add(preface);
		    //Tabelle für Attribute(z.B Modulname, Lernziele, usw...) mit den Ergebnissen aus der Datenbank
		    PdfPTable table = new PdfPTable(2);
		    for(int i = 0; i < modulattribute.size(); i++) {
		    	addEmptyLine(preface, 2);
			    document.add(preface);
		    	PdfPCell cell1=new PdfPCell(new Phrase(modulattribute.get(i)));
	    		PdfPCell cell2=new PdfPCell(new Phrase(modulwerte.get(i)));
	    		cell1.setBorder(Rectangle.NO_BORDER);
	    		cell2.setBorder(Rectangle.NO_BORDER);
	    		table.addCell(cell1);
	    		table.addCell(cell2);
		    }		
		    
		    document.add(table);
		    
		    // Start a new page
		    document.newPage();
	  }

	  public void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
	  
	  public List<String> modulListeWerte(){
		  modul = new Modul();
		  List<String> modulwerte = new LinkedList<String>();
		  modulwerte.add(modul.getCode());
		  modulwerte.add(""+modul.getDauer());
		  modulwerte.add(modul.getLeistungspunkte());
		  modulwerte.add(modul.getTurnus());
		  modulwerte.add(modul.getInhalt());
		  modulwerte.add(modul.getLernziele());
		  modulwerte.add(modul.getLiteratur());
		  modulwerte.add(modul.getSprache());
		  modulwerte.add(modul.getNotenbildung());
		  
		  //Wahlpflicht: ja=1; nein=0
		  if(modul.getWahlpflicht() == 1)
			  modulwerte.add("ja");
		  else
			  modulwerte.add("nein");
		  
		  return modulwerte;
	  }
	  
	  public List<String> modulListeAttribute(){
		  List<String> modul = new LinkedList<String>();
		  modul.add("Kürzel:");
		  modul.add("Dauer:");
		  modul.add("Leistungspunkte:");
		  modul.add("Turnus:");
		  modul.add("Inhalt:");
		  modul.add("Lernziele:");
		  modul.add("Literatur");
		  modul.add("Sprache:");
		  //modul.add("Prüfungsform");
		  modul.add("Notenbildung:");
		  modul.add("Wahlpflicht:");
		  
		  return modul;
	  }
	 
	} 	


