package controller.view.panel.tree.popup.item.diagram;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.variability.Variability;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.modal.edit.base.ViewEditDiagram;
import view.modal.edit.base.ViewEditElement;
import view.modal.edit.base.ViewEditProject;
import view.modal.edit.base.variability.ViewEditVariability;
import view.modal.edit.diagram.classes.base.ViewEditAttributeUML;
import view.modal.edit.diagram.classes.base.ViewEditClassUML;
import view.modal.edit.diagram.classes.base.ViewEditInterfaceUML;
import view.modal.edit.diagram.classes.base.ViewEditMethodUML;
import view.modal.edit.diagram.classes.base.ViewEditPackageUML;
import view.panel.tree.popup.diagram.TreePopupDiagram;

/**
 * <p>Class of Controller <b>ControllerMenuItemEdit</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemEdit</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.diagram.TreePopupDiagram
 */
public class ControllerMenuItemEdit extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Diagram.
     */
    public ControllerMenuItemEdit(TreePopupDiagram popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            new ViewEditProject(this.getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(this.getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof AttributeUML)
            new ViewEditAttributeUML(this.getPanelModeling(), (ClassDiagram) this.getDiagram(node), (AttributeUML) object).setVisible(true);
        else if (object instanceof MethodUML)
            new ViewEditMethodUML(this.getPanelModeling(), (ClassDiagram) this.getDiagram(node), (MethodUML) object).setVisible(true);
        else if (object instanceof PackageUML)
            new ViewEditPackageUML(this.getPanelModeling(), (ClassDiagram) this.getDiagram(node), (PackageUML) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewEditVariability(this.getPanelModeling(), this.getDiagram(node), (Variability) object).setVisible(true);
        else if (object instanceof Element)
            this.edit((Element) object, node);
    }
    
    /**
     * Method responsible for editing the Element by Node.
     * @param element Element.
     * @param node Tree Node.
     */
    private void edit(Element element, DefaultMutableTreeNode node) {
        if (this.getVariability(node) != null)
            System.out.println("Edit Variant: " + element);
        else if (element instanceof ClassUML)
            new ViewEditClassUML(this.getPanelModeling(),     (ClassDiagram) this.getDiagram(node), (ClassUML) element).setVisible(true);
        else if (element instanceof InterfaceUML)
            new ViewEditInterfaceUML(this.getPanelModeling(), (ClassDiagram) this.getDiagram(node), (InterfaceUML) element).setVisible(true);
        else
            new ViewEditElement(this.getPanelModeling(), this.getDiagram(node), element).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Variability Parent Node.
     * @param  node Tree Node.
     * @return Variability Parent.
     */
    protected Variability getVariability(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        if (parent != null && parent.getUserObject() instanceof Variability)
            return (Variability) parent.getUserObject();
        return null;
    }
}