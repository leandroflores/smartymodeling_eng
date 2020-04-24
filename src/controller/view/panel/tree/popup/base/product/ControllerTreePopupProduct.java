package controller.view.panel.tree.popup.base.product;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.modal.delete.base.product.ViewDeleteArtifact;
import view.modal.delete.base.product.ViewDeleteInstance;
import view.modal.delete.base.product.ViewDeleteProduct;
import view.modal.edit.base.product.ViewEditInstance;
import view.modal.edit.base.product.ViewEditProduct;
import view.panel.tree.popup.base.product.TreePopupProduct;

/**
 * <p>Class of Controller <b>ControllerTreePopupProduct</b>.</p>
 * <p>Class responsible for controlling the <b>TreePopupProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.ControllerTreePopup
 * @see    view.panel.tree.popup.base.product.TreePopupProduct
 */
public class ControllerTreePopupProduct extends ControllerTreePopup {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Product.
     */
    public ControllerTreePopupProduct(TreePopupProduct popup) {
        super(popup);
    }
    
    @Override
    protected void showPopup(DefaultMutableTreeNode node, MouseEvent event) {
        if (node.getUserObject() instanceof Project)
            this.setProjectPopup();
        else if (node.getUserObject() instanceof Product)
            this.setProductPopup();
        else if (node.getUserObject() instanceof Instance)
            super.setPopupFlag(false, true, true);
        else if (node.getUserObject() instanceof Artifact)
            super.setPopupFlag(false, false, true);
        this.getPopup().show(event.getComponent(), event.getX(), event.getY());
    }

    /**
     * Method responsible for setting the Project Popup.
     */
    private void setProjectPopup() {
        super.setPopupFlag(true, true, false);
        this.getPopup().getProductMenuItem().setVisible(true);
        this.getPopup().getInstanceMenuItem().setVisible(false);
    }
    
    /**
     * Method responsible for setting the Product Popup.
     */
    private void setProductPopup() {
        super.setPopupFlag(true, true, true);
        this.getPopup().getProductMenuItem().setVisible(false);
        this.getPopup().getInstanceMenuItem().setVisible(true);
    }
    
    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            this.getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditProject();
        else if (object instanceof Product)
            this.getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditProduct((Product) object);
        else if (object instanceof Instance)
            this.getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditInstance((Instance) object);
        else if (object instanceof Artifact)
            this.getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditArtifact((Artifact) object);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Product)
            new ViewDeleteProduct(this.getPanelModeling(),  (Product) object).setVisible(true);
        else if (object instanceof Instance)
            new ViewDeleteInstance(this.getPanelModeling(), (Instance) object).setVisible(true);        
        else if (object instanceof Artifact)
            new ViewDeleteArtifact(this.getPanelModeling(), (Artifact) object).setVisible(true);        
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Product)
            new ViewEditProduct(this.getPanelModeling(),  (Product) object).setVisible(true);
        else if (object instanceof Instance)
            new ViewEditInstance(this.getPanelModeling(), (Instance) object).setVisible(true);
    }
    
    @Override
    protected TreePopupProduct getPopup() {
        return (TreePopupProduct) this.popup;
    }
}