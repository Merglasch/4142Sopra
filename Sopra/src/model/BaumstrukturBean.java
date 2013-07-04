//Baumstruktur für Startseite//

package model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import model.modules.CreateModulhandbuch;
import model.modules.CreatePdf;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

public class BaumstrukturBean {

	private TreeNode root;
	private List<String> abschluesse;
	private List<String> studiengaenge;
	private List<String> pruefungsordnungen;
	private boolean neither;
	private boolean modul;
	private boolean hb;
	private TreeNode selectedNode;
	
	
	@EJB
	private TreeService treeService;
	
	@EJB
	private ModulhandbuchService mhService;
	
	@EJB
	private ModuleService modulService;
	

	
	public BaumstrukturBean() {
		root = new DefaultTreeNode("Root", null);
	}
	
	@PostConstruct
	public void init(){
		makeAbschlussNodes();
	}
	
	public StreamedContent getFileStreamedContent() {
	    try {
	    	Modul tmp = (Modul)selectedNode.getData();
	        InputStream is = new BufferedInputStream(
	           new FileInputStream("/WebContent/resources/pdf_folder/"+tmp.getModulname()+".pdf"));
	        return new DefaultStreamedContent(is, "/WebContent/resources/pdf_folder/", tmp.getModulname()+".pdf");
	    } catch (FileNotFoundException e) {
	    }
	    return null;
	}
	
	public String makePdf(){
		new CreatePdf((Modul)selectedNode.getData()).makeDocument();
		return "modulansicht";
	}
	
	public String makeHtml(){
		//pdfCreator.makeDocument();
		return "modulansicht";
	}

	public String makeHbPdf(){
		new CreateModulhandbuch(selectedNode).makeModulhandbuch();
		return "modulansicht";
	}
	
	public String fillTree(){
		makeAbschlussNodes();
		return "login";
	}
	
	
	public void makeAbschlussNodes(){
		abschluesse=treeService.getAllAbschluss();
		for(String s : abschluesse){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,root);
				makeStudiengangNodes(tmp);				
			}
		}
	}
	
	public void makeStudiengangNodes(TreeNode myRoot){
		List<String> studiengaenge=treeService.getAllStudiengang(myRoot.getData().toString());
		for(String s: studiengaenge){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,myRoot);
				makePruefungsOrdnungNodes(tmp,myRoot);
			}
		}
	}
	
	public void makePruefungsOrdnungNodes(TreeNode myRoot, TreeNode myParent){
		List<String> pruefungsordnungen=treeService.getAllPruefungsordnung(myParent.getData().toString(),myRoot.getData().toString());
		for(String s: pruefungsordnungen){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,myRoot);
				makeModulHandbuchNodes(tmp,myRoot,myParent);
			}
		}
	}
	
	public void makeModulHandbuchNodes(TreeNode myRoot, TreeNode myParent, TreeNode myGrandPa){

		List<Modulhandbuch> handbuecher  = mhService.search(myRoot.getData().toString(), myParent.getData().toString(), myGrandPa.getData().toString());
		for(Modulhandbuch mh : handbuecher){
			if(mh!=null){
				TreeNode tmp = new DefaultTreeNode("hb_type",mh, myRoot);
				makeFachNodes(tmp, mh);
			}
		}
	}
	
	public void makeFachNodes(TreeNode myRoot, Modulhandbuch mh){
		List<Fach> faecher = treeService.getFachTree(mh);
		for(Fach f : faecher){
			if(f!=null){
				TreeNode tmp = new DefaultTreeNode("fach_type", f, myRoot);
				makeModulNodes(tmp, mh, f);
			}
		}
	}
	
	public void makeModulNodes(TreeNode myRoot, Modulhandbuch mh, Fach f){
//		ModulNodes einfuegen
		List<Modul> module = treeService.getModulTree(mh, f);
		for(Modul m : module){
			if(m!=null){
				TreeNode tmp = new DefaultTreeNode("modul_type",m,myRoot);
			}
		}
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void onNodeSelect(NodeSelectEvent e){
		selectedNode=e.getTreeNode();
		if(selectedNode.getData().getClass().equals(Modul.class)){
			makePdf();			
		} else if(selectedNode.getData().getClass().equals(Modulhandbuch.class)){
			makeHbPdf();
		}
	}


}
