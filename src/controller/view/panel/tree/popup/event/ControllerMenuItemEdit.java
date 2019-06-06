package controller.view.panel.tree.popup.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.AttributeUML;
import view.edit.ViewEditDiagram;
import view.edit.ViewEditElement;
import view.edit.ViewEditProject;
import view.edit.classs.ViewEditAttribute;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItemEdit</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Menu Item Edit</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    java.awt.event.ActionListener
 * @see    view.panel.tree.popup.TreePopup
 */
public class ControllerMenuItemEdit implements ActionListener {
    private final TreePopup popup;
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemEdit(TreePopup popup) {
        this.popup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.popup.getPanelTree().getTree().getLastSelectedPathComponent();
        if (node != null) {
            Object object = node.getUserObject();
            this.action(object, node);
        }
    }
    
    /**
     * Method responsible for forwarding Action.
     * @param object Object.
     * @param node JTree Node.
     */
    private void action(Object object, DefaultMutableTreeNode node) {
        if (object instanceof Project)
            new ViewEditProject(this.popup.getPanelTree().getViewMenu().getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(this.popup.getPanelTree().getViewMenu().getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof AttributeUML)
            new ViewEditAttribute(this.popup.getPanelTree().getViewMenu().getPanelModeling(), this.getClassDiagram(node), (AttributeUML) object).setVisible(true);
        else if (object instanceof Element)
            new ViewEditElement(this.popup.getPanelTree().getViewMenu().getPanelModeling(), (Element) object).setVisible(true);
//        else if (object instanceof Variabilidade)
//            this.editarVariabilidade(object, node);
    }
    
    /**
     * Method responsible for editing the Variability.
     * @param object Object.
     * @param node JTree Node.
     */
    private void editVariability(Object object, DefaultMutableTreeNode node) {
//        Diagram       diagrama      = this.getDiagrama((DefaultMutableTreeNode) node.getParent());
//        Variabilidade variabilidade = (Variabilidade) object;
//        new ViewEditarVariabilidade(this.popup.getPainelProjeto().getViewMenu().getPainelModelagem(), diagrama, variabilidade).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Class Diagram from Node.
     * @param  node JTree Node.
     * @return Class Diagram.
     */
    private ClassDiagram getClassDiagram(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        while ((parent != null) && !(parent.getUserObject() instanceof ClassDiagram))
            parent = (DefaultMutableTreeNode) parent.getParent();
        return parent == null ? null : (ClassDiagram) parent.getUserObject();
    }
    
    /**
     * Method responsible for returning Diagram from Node.
     * @param  node JTree Node.
     * @return Diagram.
     */
    private Diagram getDiagram(DefaultMutableTreeNode node) {
        return (Diagram) node.getUserObject();
    }
}