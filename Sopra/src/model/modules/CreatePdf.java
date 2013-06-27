package model.modules;

import java.io.FileOutputStream;
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

	  private static String[][] A = Arraybefuellen();
	  private static int k = 1;
	  //PDF wird aufm Desktop erzeugt
	  private static String FILE = "H:/.win7_profile/Desktop/firstPDF.pdf";
	  
	  //verschiedene Schriftgrößen
	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	      Font.BOLD);
	   
	  public static void main(String[] args) {
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
	  private static void addMetaData(Document document) {
	    document.addTitle("Modul");
	    document.addSubject("Using iText");
	    document.addKeywords("Java, PDF, iText");
	    document.addAuthor("MMS");
	    document.addCreator("MMS");
	  }

	  private static void addTitlePage(Document document)
	      throws DocumentException {
	    Paragraph preface = new Paragraph();
	    addEmptyLine(preface, 7);
	    
	    // Überschrift fürs Modul
	    preface.add(new Paragraph("Informatik", catFont));
	    addEmptyLine(preface, 2);
	    document.add(preface);
	    //Tabelle für Attribute(z.B Modulname, Lernziele, usw...) mit den Ergebnissen aus der Datenbank
	    PdfPTable table = new PdfPTable(2);
	    for(int i=0; i<=4; i++){
	    	for(int j=0; j<=1; j++){
	    		PdfPCell cell1=new PdfPCell(new Phrase(A[i][j]));
	    		PdfPCell cell2=new PdfPCell(new Phrase(A[i][k]));
	    		addEmptyLine(preface, 5);
	    		cell1.setBorder(Rectangle.NO_BORDER);
	    		cell2.setBorder(Rectangle.NO_BORDER);
	    		table.addCell(cell1);
	    		table.addCell(cell2);
	    		
	    	}	
	    	}
//	    PdfPCell cellOne = new PdfPCell(new Phrase("Hello"));
//	    PdfPCell cellTwo = new PdfPCell(new Phrase("World"));
//	    cellOne.setBorder(Rectangle.NO_BORDER);
//	    cellTwo.setBorder(Rectangle.NO_BORDER);
//	    table.addCell(cellOne);
//	    table.addCell(cellTwo);
	    document.add(table);
	    // Start a new page
	    document.newPage();
	  }

	  private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
	  
	  public static String[][] Arraybefuellen(){
		  A= new String[5][2];
		  A[0][0]="Modulname:";
		  A[0][1]="Informatik";
		  A[1][0]="Sprache:";
		  A[1][1]="Deutsch";
		  A[2][0]="Leistungspunkte:";
		  A[2][1]="6 ECTS";
		  A[3][0]="Dozenten:";
		  A[3][1]="Uwe";
		  A[4][0]="Inhalt:";
		  A[4][1]="bla bla";
		  
		  return A;
	  }
	 
	} 	


