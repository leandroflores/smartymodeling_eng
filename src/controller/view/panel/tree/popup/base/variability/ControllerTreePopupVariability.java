package controller.view.panel.tree.popup.base.variability;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.variability.Variability;
import view.modal.delete.base.variability.ViewDeleteVariability;
import view.modal.edit.base.ViewEditDiagram;
import view.modal.edit.base.ViewEditProject;
import view.modal.edit.base.variability.ViewEditVariability;
import view.panel.tree.popup.base.variability.TreePopupVariability;

/**
 * <p>Class of Controller <b>ControllerTreePopupVariability</b>.</p>
 * <p>Class responsible for controlling the <b>TreePopupVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.panel.tree.popup.ControllerTreePopup
 * @see    view.panel.tree.popup.base.variability.TreePopupVariability
 */
public class ControllerTreePopupVariability extends ControllerTreePopup {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Variability.
     */
    public ControllerTreePopupVariability(TreePopupVariability popup) {
        super(popup);
    }
    
    @Override
    protected void showPopup(DefaultMutableTreeNode node, MouseEvent event) {
        if (node.getUserObject() instanceof Project)
            setPopupFlag(false, true, false);
        else if (node.getUserObject() instanceof Diagram)
            setPopupFlag(true, true, false);
        else if (node.getUserObject() instanceof Variability)
            setPopupFlag(false, true, true);
        else if (node.getUserObject() instanceof Element)
            setPopupFlag(false, true, true);
        getPopup().show(event.getComponent(), event.getX(), event.getY());
    }

    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        Diagram diagram = getDiagram(node);
        if (object instanceof Project)
            getPanelProject().initPanelEditProject();
        else if (object instanceof Diagram)
            getPanelProject().initPanelEditDiagram((Diagram) object);
        else if (object instanceof Variability)
            getPanelProject().initPanelEditVariability(diagram, (Variability) object, 0);
        else if (object instanceof Element)
            showPanelEdit(diagram, getVariability(node), (Element) object);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param variability Variability.
     * @param element Element.
     */
    private void showPanelEdit(Diagram diagram, Variability variability, Element element) {
        if (variability.isVariant(element))
            getPanelProject().initPanelEditVariability(diagram, variability, 1);
        else
            getPanelProject().initPanelEditVariability(diagram, variability, 0);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Variability)
            new ViewDeleteVariability(getPanelModeling(), getDiagram(node), (Variability) object).setVisible(true);
        else if (object instanceof Element)
            delete(getVariability(node), (Element) object);
    }
    
    /**
     * Method responsible for deleting a Element of a Variability.
     * @param variability Variability.
     * @param element Element.
     */
    private void delete(Variability variability, Element element) {
        variability.removeVariant(element);
        getPanelModeling().getViewMenu().updatePanelTree();
        getPanelModeling().getViewMenu().setSave(false);
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            new ViewEditProject(getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewEditVariability(getPanelModeling(), getDiagram(node), (Variability) object).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Variability Node.
     * @param  node Tree Node.
     * @return Variability Node.
     */
    protected Variability getVariability(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        if (parent != null && parent.getUserObject() instanceof Variability)
            return (Variability) parent.getUserObject();
        return null;
    }
}