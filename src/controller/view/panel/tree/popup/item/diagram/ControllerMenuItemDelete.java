package controller.view.panel.tree.popup.item.diagram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.requirement.Requirement;
import model.structural.base.traceability.Traceability;
import model.structural.base.variability.Variability;
import view.delete.base.ViewDeleteDiagram;
import view.delete.base.ViewDeleteElement;
import view.delete.base.product.ViewDeleteArtifact;
import view.delete.base.product.ViewDeleteInstance;
import view.delete.base.product.ViewDeleteProduct;
import view.delete.base.requirement.ViewDeleteRequirement;
import view.delete.base.traceability.ViewDeleteTraceability;
import view.delete.base.variability.ViewDeleteVariability;
import view.panel.modeling.PanelModeling;
import view.panel.tree.popup.diagram.TreePopupDiagram;

/**
 * <p>Class of Controller <b>ControllerMenuItemDelete</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    java.awt.event.ActionListener
 * @see    view.panel.tree.popup.diagram.TreePopupDiagram
 */
public class ControllerMenuItemDelete implements ActionListener {
    private final TreePopupDiagram popup;
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemDelete(TreePopupDiagram popup) {
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
     * Method responsible for forwarding the Action.
     * @param object Object.
     * @param node JTree Node.
     */
    private void action(Object object, DefaultMutableTreeNode node) {
        if (object instanceof Requirement)
            new ViewDeleteRequirement(this.getPanelModeling(), (Requirement) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewDeleteDiagram(this.getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Element)
            new ViewDeleteElement(this.getPanelModeling(), this.getDiagram(node), (Element) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewDeleteVariability(this.getPanelModeling(), this.getDiagram(node), (Variability) object).setVisible(true);
        else if (object instanceof Traceability)
            new ViewDeleteTraceability(this.getPanelModeling(), (Traceability) object).setVisible(true);
        else if (object instanceof Product)
            new ViewDeleteProduct(this.getPanelModeling(),  (Product) object).setVisible(true);
        else if (object instanceof Instance)
            new ViewDeleteInstance(this.getPanelModeling(), (Instance) object).setVisible(true);
        else if (object instanceof Artifact)
            new ViewDeleteArtifact(this.getPanelModeling(), (Artifact) object).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Diagram from Node.
     * @param  node JTree Node.
     * @return Diagram.
     */
    private Diagram getDiagram(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        while ((parent != null) && !(parent.getUserObject() instanceof Diagram))
               parent = (DefaultMutableTreeNode) parent.getParent();
        return parent == null ? null : (Diagram) parent.getUserObject();
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    private PanelModeling getPanelModeling() {
        return this.popup.getPanel().getViewMenu().getPanelModeling();
    }
}