package controller.view.panel.tree.popup.diagram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import model.structural.base.variability.Variability;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.edit.base.ViewEditDiagram;
import view.edit.base.ViewEditElement;
import view.edit.base.ViewEditProject;
import view.edit.base.requirement.ViewEditRequirement;
import view.edit.base.variability.ViewEditVariability;
import view.edit.diagram.classes.ViewEditAttributeUML;
import view.edit.diagram.classes.ViewEditMethodUML;
import view.panel.tree.popup.diagram.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItemEdit</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Menu Item Edit</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    java.awt.event.ActionListener
 * @see    view.panel.tree.popup.diagram.TreePopup
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
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.popup.getPanel().getTree().getLastSelectedPathComponent();
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
            new ViewEditProject(this.popup.getPanel().getViewMenu().getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Requirement)
            new ViewEditRequirement(this.popup.getPanel().getViewMenu().getPanelModeling(), (Requirement) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(this.popup.getPanel().getViewMenu().getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof AttributeUML)
            new ViewEditAttributeUML(this.popup.getPanel().getViewMenu().getPanelModeling(), this.getClassDiagram(node), (AttributeUML) object).setVisible(true);
        else if (object instanceof MethodUML)
            new ViewEditMethodUML(this.popup.getPanel().getViewMenu().getPanelModeling(),    this.getClassDiagram(node), (MethodUML) object).setVisible(true);
        else if (object instanceof Element)
            new ViewEditElement(this.popup.getPanel().getViewMenu().getPanelModeling(),   this.getParentDiagram(node), (Element) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewEditVariability(this.popup.getPanel().getViewMenu().getPanelModeling(),   this.getParentDiagram(node), (Variability) object).setVisible(true);
    }
    
    /**
     * Method responsible for editing the Variability.
     * @param object Object.
     * @param node JTree Node.
     */
    private void editVariability(Object object, DefaultMutableTreeNode node) {
        Diagram     diagram     = this.getDiagram((DefaultMutableTreeNode) node.getParent());
        Variability variability = (Variability) object;
        new ViewEditVariability(this.popup.getPanel().getViewMenu().getPanelModeling(), diagram, variability).setVisible(true);
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
     * Method responsible for returning the Diagram from Element Node.
     * @param  node JTree Node.
     * @return Diagram.
     */
    private Diagram getParentDiagram(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        while ((parent != null) && !(parent.getUserObject() instanceof Diagram))
            parent = (DefaultMutableTreeNode) parent.getParent();
        return parent == null ? null : (Diagram) parent.getUserObject();
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