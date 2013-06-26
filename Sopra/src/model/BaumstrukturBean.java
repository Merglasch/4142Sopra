//Baumstruktur für Startseite//

package model;

import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

public class BaumstrukturBean {

	private TreeNode root;

	public BaumstrukturBean() {

		root = new DefaultTreeNode("Root", null);

		// Modul für Abschluss: Staatsexamen Lehramt//
		TreeNode node0 = new DefaultTreeNode(
				"Modul für Abschluss: Staatsexamen Lehramt", root);
		TreeNode node00 = new DefaultTreeNode("Studiengang:Informatik", node0);
		TreeNode node000 = new DefaultTreeNode("Pruefungsordnung 2012", node00);
		TreeNode node0000 = new DefaultTreeNode("Hier stehen die Listen an Modulen", node000);

		// Modul für Abschluss: Bachelor/
		TreeNode node1 = new DefaultTreeNode("Modul für Abschluss: Bachelor",
				root);
		TreeNode node10 = new DefaultTreeNode("Studiengang:Informatik", node1);
		TreeNode node100 = new DefaultTreeNode("Pruefungsordnung 2012", node10);
		TreeNode node1000 = new DefaultTreeNode("Hier steht die Liste von Modulen", node100);

		// Modul für Abschluss: Master/
		TreeNode node2 = new DefaultTreeNode("Modul für Abschluss: Master", root);
		TreeNode node20 = new DefaultTreeNode("Studiengang:Informatik", node2);
		TreeNode node200 = new DefaultTreeNode("Pruefungsordnung 2012", node20);
		TreeNode node2000 = new DefaultTreeNode("Hier steht die Liste von Modulen", node200);
		}

	public TreeNode getRoot() {
		return root;
	}
}
