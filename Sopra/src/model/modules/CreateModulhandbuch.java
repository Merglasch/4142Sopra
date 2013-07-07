package model.modules;

import java.io.ByteArrayOutputStream;

import javax.faces.context.FacesContext;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;

import org.primefaces.model.TreeNode;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Klasse zum Erstellen von Handbuechern im Pdf Format.
 * @author Inna Düster und David Klein
 *
 */
public class CreateModulhandbuch {
  //verschiedene Schriftgrößen
  private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD);
  private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 25,
	      Font.BOLD);
  private TreeNode handbuchNode;
  private Modulhandbuch handbuch;
  
  /**
   * Konstruktor. Setzt die uebergebene TreeNode des Modulhandbuchs.
   * @param handbuchNode
   */
  public CreateModulhandbuch(TreeNode handbuchNode){
	  this.handbuchNode=handbuchNode;
	  handbuch=(Modulhandbuch)handbuchNode.getData();
  }
  
  /**
   * Liefert die Modulhandbuch Pdf im ByteArrayOutputStream.
   * Wird im BaumstrukturBean aufgerufen
   * @return baos Modulhandbuch als ByteArrayOutputStream.
   */
  public ByteArrayOutputStream makeModulhandbuch() { 
	ByteArrayOutputStream baos =null;
	  
	try {
      Document document = new Document();
      baos = new ByteArrayOutputStream();
      PdfWriter docWriter;
      docWriter = PdfWriter.getInstance(document, baos);
      document.open();
      Image image = Image.getInstance(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/images/logo1.png"));
      image.setAbsolutePosition(35, 760);
      Image image2 = Image.getInstance(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/images/logo2.png"));
      image2.setAbsolutePosition(340, 760);
      document.add(image);
      document.add(image2);
      addTitlePage(document);
      addMetaData(document);
      addDataPage(document);
   
      document.close();
      docWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
	
	return baos;
  }


  /**
   * Setzt die Metadaten fuer die erstellte datei.
   * @param document Pdf Dokument
   */
  private static void addMetaData(Document document) {
    document.addTitle("Modulhandbuch");
    document.addSubject("Using iText");
    document.addKeywords("Java, PDF, iText");
    document.addAuthor("MMS");
    document.addCreator("MMS");
  }

  /**
   * Erstellt die Titelseite des Handbuchs.
   * @param document Pdf Dokument
   * @throws DocumentException
   */
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

  /**
   * erstellt den Inhalt des Modulhandbuchs mit  Faechern und Modulen.
   * Iteriert durch Faecher (Kapitel) und Module (Sektionen) und schreibt diese in das Dokument.
   * @param document Pdf Dokument
   * @throws DocumentException
   */
  private void addDataPage(Document document)
      throws DocumentException {
    //Tabelle für Attribute(z.B Modulname, Lernziele, usw...) mit den Ergebnissen aus der Datenbank
	  int i=1;
	  for(TreeNode fNode : handbuchNode.getChildren()){
		  Fach f = (Fach)fNode.getData();
		  Anchor anchor = new Anchor(f.getFach(),bigFont);
		  Chapter chapter = new Chapter(new Paragraph(anchor),i);
		  Section subChapter = null;
		  for(TreeNode mNode : fNode.getChildren()){
			  // Überschrift fürs Modul
			  Modul m = (Modul)mNode.getData();
			  CreatePdf creator = new CreatePdf(m);
			  Paragraph preface = new Paragraph(m.getModulname(), catFont);	
   		   	  creator.addEmptyLine(preface, 2);
			  subChapter = chapter.addSection(preface);
			  creator.addSectionPage(subChapter);
		  }
		  document.add(chapter);
		  i++;
	  }
    }
 }  
 
 