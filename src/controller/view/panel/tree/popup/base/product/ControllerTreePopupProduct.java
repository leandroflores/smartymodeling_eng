package controller.view.panel.tree.popup.base.product;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.modal.delete.base.evaluation.ViewDeleteMeasure;
import view.modal.delete.base.evaluation.ViewDeleteMetric;
import view.modal.edit.base.evaluation.ViewEditMetric;
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
            this.popup.getDeleteMenuItem().setVisible(false);
        else
            this.popup.getDeleteMenuItem().setVisible(true);
        this.popup.show(event.getComponent(), event.getX(), event.getY());
    }

    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            this.popup.getPanel().getViewMenu().getPanelProject().initPanelEditProject();
        else if (object instanceof Product)
            this.popup.getPanel().getViewMenu().getPanelProject().initPanelEditProduct((Product) object);
        else if (object instanceof Instance)
            this.popup.getPanel().getViewMenu().getPanelProject().initPanelEditInstance((Instance) object);
        else if (object instanceof Artifact)
            this.popup.getPanel().getViewMenu().getPanelProject().initPanelEditArtifact((Artifact) object);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Metric)
            new ViewDeleteMetric(this.getPanelModeling(), (Metric) object).setVisible(true);
        else if (object instanceof Measure)
            new ViewDeleteMeasure(this.getPanelModeling(), (Measure) object).setVisible(true);        
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Metric)
            new ViewEditMetric(this.getPanelModeling(), (Metric) object).setVisible(true);
    }
}