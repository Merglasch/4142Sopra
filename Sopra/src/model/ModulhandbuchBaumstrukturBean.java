package model;

import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

public class ModulhandbuchBaumstrukturBean {

	private TreeNode root;

	public ModulhandbuchBaumstrukturBean() {
		root = new DefaultTreeNode("Root", null);

		// baum mit dingern aus DB fuellen
		//
		// fake fuellung
		
//Pruefungsordnung 2012//		
		TreeNode node0 = new DefaultTreeNode("Pruefungsordnung 2012", root);
		//Aufklappen Pruefungsordnung 2012//
		TreeNode node00 = new DefaultTreeNode("Bachelor Informatik", node0);
		
		TreeNode node01 = new DefaultTreeNode("Bachelor Medieninformatik", node0);
		TreeNode node02 = new DefaultTreeNode("Bachelor Chemie", node0);
		
//Pruefungsordnung 2011//	
		TreeNode node1 = new DefaultTreeNode("Pruefungsordnung 2011", root);
		//Aufklappen Pruefungsordnung 2011//
		TreeNode node10 = new DefaultTreeNode("Bachelor Informatik", node1);
		TreeNode node11 = new DefaultTreeNode("Bachelor Medieninformatik", node1);
		TreeNode node12 = new DefaultTreeNode("Bachelor Chemie", node1);
		
//Pruefungsordnung 2010//	
		TreeNode node2 = new DefaultTreeNode("Pruefungsordnung 2010", root);
		//Aufklappen Pruefungsordnung 2010//
		TreeNode node20 = new DefaultTreeNode("Bachelor Informatik", node2);
		TreeNode node21 = new DefaultTreeNode("Bachelor Medieninformatik", node2);
		TreeNode node22 = new DefaultTreeNode("Bachelor Chemie", node2);
		
		

		
				
//zweitesmal aufklappen (weiterer punkt) //
//		TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);
	}

	public TreeNode getRoot() {
		return root;
	}
}
