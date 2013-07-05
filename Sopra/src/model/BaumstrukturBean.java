//Baumstruktur für Startseite//

package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import klassenDB.Fach;
import klassenDB.Modul;
import klassenDB.Modulhandbuch;
import klassenDB.User;
import model.modules.CreateModulhandbuch;
import model.modules.CreatePdf;
import model.modules.ModuleService;
import model.modules.ModulhandbuchService;

import org.primefaces.event.NodeSelectEvent;
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
	private TreeNode selectedNode=root;
	private StreamedContent fileStreamedContent;
	private String mdlfile = "";
	private String hbfile = "";
	private Modul aktmodul;
	private Modulhandbuch akthb;
	private User myself=null;
	
	
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
		mdlfile = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdf_folder/modul.pdf");
		hbfile = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdf_folder/handbuch.pdf");
	}
	
	public void download() throws IOException {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

	    response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
	    response.setContentType("application/pdf"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
	    response.setHeader("Content-disposition", "attachment; filename=\"modul.pdf\""); // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.

	    BufferedInputStream input = null;
	    BufferedOutputStream output = null;
	    
    	ByteArrayOutputStream baos = new CreatePdf(aktmodul).makeDocument();
    	response.setContentLength(baos.size());
    	ServletOutputStream sos = response.getOutputStream();
    	baos.writeTo(sos);
    	sos.flush();

	    facesContext.responseComplete(); // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
	}

	public TreeNode getAktModulTree(Modulhandbuch mh){
		TreeNode thisRoot = new DefaultTreeNode(mh,null);
		List<Fach> faecher = treeService.getFachTree(mh);
		for(Fach f : faecher){
			TreeNode tmp = new DefaultTreeNode(f,thisRoot);
			makeModulNodes(tmp,mh,f);
		}
		return thisRoot;
	}
	
	public TreeNode getModulTree(Modulhandbuch mh){
		TreeNode thisRoot = new DefaultTreeNode(mh,null);
		List<Fach> faecher = treeService.getFachTree(mh);
		for(Fach f : faecher){
			TreeNode tmp = new DefaultTreeNode(f,thisRoot);
			makeModulNodes(tmp,mh,f);
		}
		return thisRoot;
	}

	public void downloadhb() throws IOException {
		TreeNode thisRoot;
		if(myself!=null){
			thisRoot = getModulTree(akthb);			
		}else{
			thisRoot = getAktModulTree(akthb);
		}
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

	    response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
	    response.setContentType("application/pdf"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
	    response.setHeader("Content-disposition", "attachment; filename=\"modul.pdf\""); // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.

	    BufferedInputStream input = null;
	    BufferedOutputStream output = null;
	    
    	ByteArrayOutputStream baos = new CreateModulhandbuch(thisRoot).makeModulhandbuch();
    	response.setContentLength(baos.size());
    	ServletOutputStream sos = response.getOutputStream();
    	baos.writeTo(sos);
    	sos.flush();

	    facesContext.responseComplete(); // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
	}
	
	
	public StreamedContent getFileStreamedContent() {
	    /*try {
	    	Modul tmp = (Modul)selectedNode.getData();
	        InputStream is = new BufferedInputStream(
	           new FileInputStream(mdlfile));
	        return new DefaultStreamedContent(is, mdlfile);
	    } catch (FileNotFoundException e) {
	    	System.out.println("Daaaaaang not found");
	    }*/
	    return fileStreamedContent;
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
			System.out.println(m.getModulname()+" Parent: "+myRoot.getData());
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
/*		if(selectedNode.getData().getClass().equals(Modul.class)){
			System.out.println("Dat laeuft");
			makePdf();
			//Modul tmp = (Modul)selectedNode.getData();
	        //InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(mdlfile);  
	       // fileStreamedContent = new DefaultStreamedContent(stream, mdlfile);  
		} else if(selectedNode.getData().getClass().equals(Modulhandbuch.class)){
			makeHbPdf();
		}
*/	}

	public String getMdlfile() {
		System.out.println("Print dat shit");
		return mdlfile;
	}

	public void setMdlfile(String mdlfile) {
		this.mdlfile = mdlfile;
	}

	public String getHbfile() {
		return hbfile;
	}

	public void setHbfile(String hbfile) {
		this.hbfile = hbfile;
	}

	public Modul getAktmodul() {
		return aktmodul;
	}

	public void setAktmodul(Modul aktmodul) {
		this.aktmodul = aktmodul;
	}

	public Modulhandbuch getAkthb() {
		return akthb;
	}

	public void setAkthb(Modulhandbuch akthb) {
		this.akthb = akthb;
	}

	public User getMyself() {
		return myself;
	}

	public void setMyself(User myself) {
		this.myself = myself;
	}


}
