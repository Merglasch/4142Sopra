package model.modules;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;

import org.primefaces.model.TreeNode;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;


public class CreateModulhandbuch {
  //private List<Modul> module = Listebefuellen();
  //PDF wird aufm Desktop erzeugt
  private String FILE = "/WebContent/resources/pdf_folder/";
  //verschiedene Schriftgrößen
  private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD);
  private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 25,
	      Font.BOLD);
  private CreatePdf modulPdf;
  private TreeNode handbuchNode;
  private List<TreeNode> faecher=new LinkedList<TreeNode>();
  private Modulhandbuch handbuch;
  
  
  public CreateModulhandbuch(TreeNode handbuchNode){
	  this.handbuchNode=handbuchNode;
	  handbuch=(Modulhandbuch)handbuchNode.getData();
  }
  
  public void makeModulhandbuch() {
	  for(TreeNode mNode : handbuchNode.getChildren()){
		  for(TreeNode fNode : mNode.getChildren()){
			  new CreatePdf((Modul)fNode.getData()).makeDocument();			  
		  }
	  }
	  
	    // A File object to represent the filename
	    File f = new File(FILE);
	    if(f.exists()) {
	    	f.delete();
	    }
	  
	try {
      Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(FILE+handbuch.getStudiengang()+handbuch.getAbschluss()+handbuch.getPruefungsordnung()+".pdf"));
      document.open();
      Image image = Image.getInstance("img/logo1.png");
      image.setAbsolutePosition(35, 760);
      Image image2 = Image.getInstance("img/logo2.png");
      image2.setAbsolutePosition(340, 760);
      document.add(image);
      document.add(image2);
      addTitlePage(document);
      addMetaData(document);
      addDataPage(document);
   
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Metadaten
  /**
 * @param document
 */
  private static void addMetaData(Document document) {
    document.addTitle("Modulhandbuch");
    document.addSubject("Using iText");
    document.addKeywords("Java, PDF, iText");
    document.addAuthor("MMS");
    document.addCreator("MMS");
  }

  private void addTitlePage(Document document) throws DocumentException{	  
	  Paragraph paragraph1 = new Paragraph();
	  paragraph1.setSpacingAfter(70);
      paragraph1.setSpacingBefore(250);
      paragraph1.setAlignment(Element.ALIGN_CENTER);
      
      Paragraph paragraph2 = new Paragraph();
      paragraph2.setSpacingAfter(25);
      paragraph2.setAlignment(Element.ALIGN_CENTER);
      
      Paragraph paragraph3 = new Paragraph();
      paragraph3.setSpacingAfter(25);
      paragraph3.setAlignment(Element.ALIGN_CENTER);

      Paragraph paragraph4 = new Paragraph();
      paragraph4.setSpacingAfter(25);
      paragraph4.setAlignment(Element.ALIGN_CENTER);
      
      
      paragraph1.add(new Phrase("Modulhandbuch", bigFont));
      paragraph2.add(new Phrase(handbuch.getAbschluss(), catFont));
      paragraph3.add(new Phrase(handbuch.getStudiengang(), catFont));
      paragraph4.add(new Phrase(handbuch.getPruefungsordnung(), catFont));
      document.add(paragraph1);
      document.add(paragraph2);
      document.add(paragraph3);
      document.add(paragraph4);
	  document.newPage();
  }

  private void addDataPage(Document document)
      throws DocumentException {
    Paragraph preface = new Paragraph();
    Chunk chunk;
    //Tabelle für Attribute(z.B Modulname, Lernziele, usw...) mit den Ergebnissen aus der Datenbank
    /*for(int i=0; i<module.size(); i++){
    		document.add("");
    		document.newPage();*/
    }
  
 }  
 
 