package controller.view.panel.tree.popup.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.traceability.Traceability;
import model.structural.base.variability.Variability;
import view.delete.ViewDeleteDiagram;
import view.delete.ViewDeleteElement;
import view.delete.product.ViewDeleteInstance;
import view.delete.product.ViewDeleteProduct;
import view.delete.traceability.ViewDeleteTraceability;
import view.delete.variability.ViewDeleteVariability;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItemDelete</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Menu Item Delete</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    java.awt.event.ActionListener
 * @see    view.panel.tree.popup.TreePopup
 */
public class ControllerMenuItemDelete implements ActionListener {
    private final TreePopup popup;
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemDelete(TreePopup popup) {
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
     * Method responsible for forwarding the Action.
     * @param object Object.
     * @param node JTree Node.
     */
    private void action(Object object, DefaultMutableTreeNode node) {
        if (object instanceof Diagram)
            new ViewDeleteDiagram(this.popup.getPanelTree().getViewMenu().getPanelModeling(), ((Diagram) object)).setVisible(true);
        else if (object instanceof Element)
            new ViewDeleteElement(this.popup.getPanelTree().getViewMenu().getPanelModeling(), ((Element) object)).setVisible(true);
        else if (object instanceof Variability)
            new ViewDeleteVariability(this.popup.getPanelTree().getViewMenu().getPanelModeling(), this.getDiagram(node), (Variability) object).setVisible(true);
        else if (object instanceof Traceability)
            new ViewDeleteTraceability(this.popup.getPanelTree().getViewMenu().getPanelModeling(), (Traceability) object).setVisible(true);
        else if (object instanceof Product)
            new ViewDeleteProduct(this.popup.getPanelTree().getViewMenu().getPanelModeling(),  (Product) object).setVisible(true);
        else if (object instanceof Instance)
            new ViewDeleteInstance(this.popup.getPanelTree().getViewMenu().getPanelModeling(), (Instance) object).setVisible(true);
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
}