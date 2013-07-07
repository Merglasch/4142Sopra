//Baumstruktur fuer die Bibliothek//

package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.primefaces.model.TreeNode;

/**
 * Das BaumstrukturBean kuemmert sich um die Baumstruktur hinter der Ansicht und um das Erzeugen der Pdfs.
 *
 */
public class BaumstrukturBean {

	private TreeNode root;
	private List<String> abschluesse;
	private TreeNode selectedNode=root;
	private Modul aktmodul;
	private Modulhandbuch akthb;
	private User myself=null;
	private Modul suchModul = null;
	
	
	@EJB
	private TreeService treeService;
	
	@EJB
	private ModulhandbuchService mhService;
	
	@EJB
	private ModuleService modulService;
	
	//////////////////////////////////////////////////////////////////////////////////
	//Konstruktor und post Konstruktor
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Konstruktor.
	 * fuellt die Wurzel des Baums
	 */
	public BaumstrukturBean() {
		root = new DefaultTreeNode("Root", null);
	}
	
	/**
	 * Post Constructor.
	 * loescht den alten Baum.
	 * Prueft ob ein User angemeldet ist und fuellt den Baum dementsprechend.
	 */
	@PostConstruct
	public void init(){
		root=null;
		root = new DefaultTreeNode("Root",null);
		if(myself!=null){
			makeAbschlussNodes();			
		}else{
			makeAktAbschlussNodes();
		}
	}
	
	/**
	 * loescht den alten Baum und fuellt ihn neu.
	 */
	public void fillTree(){
		root=null;
		root = new DefaultTreeNode("Root",null);
		if(myself!=null){
			makeAbschlussNodes();			
		}else{
			makeAktAbschlussNodes();
		}		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//Methoden fuer den Pdf Download
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Methode fuer den Download eines Moduls als Pdf Datei.<p>
	 * Holt sich aus dem Faces Context ein Servlet Response Object und arbeitet auf diesem mit Byte Streams.<p>
	 * Ruft die Methode CreatePdf.makeDocument() fuer die Erstellung einer Pdf auf.<p>
	 * @throws IOException
	 */
	public void download() throws IOException {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

	    response.reset(); 
	    response.setContentType("application/pdf"); 
	    response.setHeader("Content-disposition", "attachment; filename=\"modul.pdf\""); 
	    
    	ByteArrayOutputStream baos = new CreatePdf(aktmodul).makeDocument();
    	response.setContentLength(baos.size());
    	ServletOutputStream sos = response.getOutputStream();
    	baos.writeTo(sos);
    	sos.flush();
    	
    	//Response fertig, JSF muss/darf sich nicht mehr um die Navigation hierfuer kuemmern
	    facesContext.responseComplete(); 
	}
	
	/**
	 * Methode fuer den Download eines Modulhandbuchs als Pdf Datei.<p>
	 * Holt sich den Baum des Modulhandbuchs je nach eingeloggtem/anonymen User mit getModulTree bzw. getAktModulTree.<p>
	 * Uebergibt die Wurzel des geholten Baums an die CreateModulhandbuch.makeModulhandbuch() zum erstellen einer Pdf.<p>
	 * @throws IOException
	 */
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

	    response.reset(); 
	    response.setContentType("application/pdf"); 
	    response.setHeader("Content-disposition", "attachment; filename=\"modul.pdf\"");
	    
    	ByteArrayOutputStream baos = new CreateModulhandbuch(thisRoot).makeModulhandbuch();
    	response.setContentLength(baos.size());
    	ServletOutputStream sos = response.getOutputStream();
    	baos.writeTo(sos);
    	sos.flush();
    	
    	//Response fertig, JSF muss/darf sich nicht mehr um die Navigation hierfuer kuemmern
	    facesContext.responseComplete(); 
	}
	
	/**
	 * Bestimmt den aktuellsten Baum ab dem gewaehlten Modul.
	 * @param klassenDB.Modulhandbuch
	 * @return
	 */
	public TreeNode getAktModulTree(Modulhandbuch mh){
		TreeNode thisRoot = new DefaultTreeNode(mh,null);
		List<Fach> faecher = treeService.getFachTree(mh);
		for(Fach f : faecher){
			TreeNode tmp = new DefaultTreeNode(f,thisRoot);
			makeAktModulNodes(tmp,mh,f);
		}
		return thisRoot;
	}
	
	/**
	 * Bestimmt den kompletten Baum ab dem gewaehlten Modul.
	 * @param klassenDB.Modulhandbuch
	 * @return
	 */
	public TreeNode getModulTree(Modulhandbuch mh){
		TreeNode thisRoot = new DefaultTreeNode(mh,null);
		List<Fach> faecher = treeService.getFachTree(mh);
		for(Fach f : faecher){
			TreeNode tmp = new DefaultTreeNode(f,thisRoot);
			makeModulNodes(tmp,mh,f);
		}
		return thisRoot;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//Baumaufbau fuer eingeloggte User
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Sucht Abschluesse und macht TreeNodes daraus.
	 * Parents: root
	 * Kinder: makeStudiengangNodes()
	 */
	public void makeAbschlussNodes(){
		abschluesse=treeService.getAllAbschluss();
		for(String s : abschluesse){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,root);
				makeStudiengangNodes(tmp);				
			}
		}
	}
	
	/**
	 * Sucht Studiengaenge und macht TreeNodes daraus.
	 * Parents: makeAbschlussNodes()
	 * Kinder: makePruefungsOrdnungNodes()
	 * @param myRoot Parent TreeNode
	 */
	public void makeStudiengangNodes(TreeNode myRoot){
		List<String> studiengaenge=treeService.getAllStudiengang(myRoot.getData().toString());
		for(String s: studiengaenge){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,myRoot);
				makePruefungsOrdnungNodes(tmp,myRoot);
			}
		}
	}
	
	/**
	 * Sucht Pruefungsordnungen und macht TreeNodes daraus.
	 * Parents: makeStudiengangNodes()
	 * Kinder: makeModulHandbuchNodes()
	 * @param myRoot Parent TreeNode
	 * @param myParent Parent vom Parent
	 */
	public void makePruefungsOrdnungNodes(TreeNode myRoot, TreeNode myParent){
		List<String> pruefungsordnungen=treeService.getAllPruefungsordnung(myParent.getData().toString(),myRoot.getData().toString());
		for(String s: pruefungsordnungen){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,myRoot);
				makeModulHandbuchNodes(tmp,myRoot,myParent);
			}
		}
	}
	
	/**
	 * Sucht Modulhandbuecher und macht TreeNodes daraus.
	 * Parents: makePruefungsOrdnungNodes()
	 * Kinder: makeFachNodes()
	 * @param myRoot Parent TreeNode
	 * @param myParent Parent vom Parent
	 * @param myGrandPa Parent vom Parent vom Parent
	 */
	public void makeModulHandbuchNodes(TreeNode myRoot, TreeNode myParent, TreeNode myGrandPa){

		List<Modulhandbuch> handbuecher  = mhService.search(myRoot.getData().toString(), myParent.getData().toString(), myGrandPa.getData().toString());
		for(Modulhandbuch mh : handbuecher){
			if(mh!=null){
				TreeNode tmp = new DefaultTreeNode("hb_type",mh, myRoot);
				makeFachNodes(tmp, mh);
			}
		}
	}
	
	/**
	 * Sucht Faecher und macht TreeNodes daraus.
	 * Parents: makeModulHandbuchNodes()
	 * Kinder: makeModulNodes()
	 * @param myRoot Parent TreeNode
	 * @param mh Modulhandbuch
	 */
	public void makeFachNodes(TreeNode myRoot, Modulhandbuch mh){
		List<Fach> faecher = treeService.getFachTree(mh);
		for(Fach f : faecher){
			if(f!=null){
				TreeNode tmp = new DefaultTreeNode("fach_type", f, myRoot);
				makeModulNodes(tmp, mh, f);
			}
		}
	}
	
	/**
	 * Sucht Module und macht TreeNodes daraus.
	 * Parents: makeFachNodes()
	 * @param myRoot Parent TreeNode
	 * @param mh Modulhandbuch
	 * @param f Fach
	 */
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

	
	//////////////////////////////////////////////////////////////////////////////////
	//Baumaufbau fuer anonyme User
	//////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Sucht aktuelle Abschluesse und macht TreeNodes daraus.
	 * Parents: root
	 * Kinder: makeStudiengangNodes()
	 */
	public void makeAktAbschlussNodes(){
		abschluesse=treeService.getAllAktAbschluss();
		for(String s : abschluesse){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,root);
				makeAktStudiengangNodes(tmp);				
			}
		}
	}
	
	/**
	 * Sucht aktuelle Studiengaenge und macht TreeNodes daraus.
	 * Parents: makeAbschlussNodes()
	 * Kinder: makePruefungsOrdnungNodes()
	 * @param myRoot Parent TreeNode
	 */
	public void makeAktStudiengangNodes(TreeNode myRoot){
		List<String> studiengaenge=treeService.getAllAktStudiengang(myRoot.getData().toString());
		for(String s: studiengaenge){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,myRoot);
				makeAktPruefungsOrdnungNodes(tmp,myRoot);
			}
		}
	}
	
	/**
	 * Sucht aktuelle Pruefungsordnungen und macht TreeNodes daraus.
	 * Parents: makeStudiengangNodes()
	 * Kinder: makeModulHandbuchNodes()
	 * @param myRoot Parent TreeNode
	 * @param myParent Parent vom Parent
	 */
	public void makeAktPruefungsOrdnungNodes(TreeNode myRoot, TreeNode myParent){
		List<String> pruefungsordnungen=treeService.getAllAktPruefungsordnung(myParent.getData().toString(),myRoot.getData().toString());
		for(String s: pruefungsordnungen){
			if(s!=null){
				TreeNode tmp = new DefaultTreeNode("branch_type",s,myRoot);
				makeAktModulHandbuchNodes(tmp,myRoot,myParent);
			}
		}
	}
	
	/**
	 * Sucht aktuelle Modulhandbuecher und macht TreeNodes daraus.
	 * Parents: makePruefungsOrdnungNodes()
	 * Kinder: makeFachNodes()
	 * @param myRoot Parent TreeNode
	 * @param myParent Parent vom Parent
	 * @param myGrandPa Parent vom Parent vom Parent
	 */
	public void makeAktModulHandbuchNodes(TreeNode myRoot, TreeNode myParent, TreeNode myGrandPa){

		List<Modulhandbuch> handbuecher  = treeService.getAllAktModulhandbuch(myRoot.getData().toString(), myParent.getData().toString(), myGrandPa.getData().toString());
		for(Modulhandbuch mh : handbuecher){
			if(mh!=null){
				TreeNode tmp = new DefaultTreeNode("hb_type",mh, myRoot);
				makeAktFachNodes(tmp, mh);
			}
		}
	}
	
	/**
	 * Sucht aktuelle Faecher und macht TreeNodes daraus.
	 * Parents: makeModulHandbuchNodes()
	 * Kinder: makeModulNodes()
	 * @param myRoot Parent TreeNode
	 * @param mh Modulhandbuch
	 */
	public void makeAktFachNodes(TreeNode myRoot, Modulhandbuch mh){
		List<Fach> faecher = treeService.getFachTree(mh);
		for(Fach f : faecher){
			if(f!=null){
				TreeNode tmp = new DefaultTreeNode("fach_type", f, myRoot);
				makeAktModulNodes(tmp, mh, f);
			}
		}
	}
	
	/**
	 * Sucht aktuelle Module und macht TreeNodes daraus.
	 * Parents: makeFachNodes()
	 * @param myRoot Parent TreeNode
	 * @param mh Modulhandbuch
	 * @param f Fach
	 */
	public void makeAktModulNodes(TreeNode myRoot, Modulhandbuch mh, Fach f){
//		ModulNodes einfuegen
		List<Modul> module = treeService.getAllAktModules(f,mh);
		for(Modul m : module){
			System.out.println(m.getModulname()+" Parent: "+myRoot.getData());
			if(m!=null){
				TreeNode tmp = new DefaultTreeNode("modul_type",m,myRoot);
			}
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////
	//Modulsuche Ergebnis
	//////////////////////////////////////////////////////////////////////////////////
	/**
	 * setzt die selektierte Node auf das bei der Modulsuche gefundene Modul.
	 * @return Modulansicht als Zielseite.
	 */
	public String mySuchModul(){
		selectedNode=new DefaultTreeNode(suchModul,null);
		return "modulansicht";
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//Getter und Setter und die onNodeSelect
	//////////////////////////////////////////////////////////////////////////////////

	public void onNodeSelect(NodeSelectEvent e){
		selectedNode=e.getTreeNode();
	}

	/**
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * @return the abschluesse
	 */
	public List<String> getAbschluesse() {
		return abschluesse;
	}

	/**
	 * @param abschluesse the abschluesse to set
	 */
	public void setAbschluesse(List<String> abschluesse) {
		this.abschluesse = abschluesse;
	}

	/**
	 * @return the selectedNode
	 */
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	/**
	 * @param selectedNode the selectedNode to set
	 */
	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	/**
	 * @return the aktmodul
	 */
	public Modul getAktmodul() {
		return aktmodul;
	}

	/**
	 * @param aktmodul the aktmodul to set
	 */
	public void setAktmodul(Modul aktmodul) {
		this.aktmodul = aktmodul;
	}

	/**
	 * @return the akthb
	 */
	public Modulhandbuch getAkthb() {
		return akthb;
	}

	/**
	 * @param akthb the akthb to set
	 */
	public void setAkthb(Modulhandbuch akthb) {
		this.akthb = akthb;
	}

	/**
	 * @return the myself
	 */
	public User getMyself() {
		return myself;
	}

	/**
	 * @param myself the myself to set
	 */
	public void setMyself(User myself) {
		this.myself = myself;
	}

	/**
	 * @return the treeService
	 */
	public TreeService getTreeService() {
		return treeService;
	}

	/**
	 * @param treeService the treeService to set
	 */
	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	/**
	 * @return the mhService
	 */
	public ModulhandbuchService getMhService() {
		return mhService;
	}

	/**
	 * @param mhService the mhService to set
	 */
	public void setMhService(ModulhandbuchService mhService) {
		this.mhService = mhService;
	}

	/**
	 * @return the modulService
	 */
	public ModuleService getModulService() {
		return modulService;
	}

	/**
	 * @param modulService the modulService to set
	 */
	public void setModulService(ModuleService modulService) {
		this.modulService = modulService;
	}

	public Modul getSuchModul() {
		return suchModul;
	}

	public void setSuchModul(Modul suchModul) {
		this.suchModul = suchModul;
	}	


}
