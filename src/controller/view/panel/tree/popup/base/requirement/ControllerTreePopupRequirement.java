package controller.view.panel.tree.popup.base.requirement;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.modal.delete.base.requirement.ViewDeleteRequirement;
import view.modal.edit.base.requirement.ViewEditRequirement;
import view.panel.tree.popup.base.requirement.TreePopupRequirement;

/**
 * <p>Class of Controller <b>ControllerTreePopupRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>TreePopupRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.ControllerTreePopup
 * @see    view.panel.tree.popup.base.requirement.TreePopupRequirement
 */
public class ControllerTreePopupRequirement extends ControllerTreePopup {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Requirement.
     */
    public ControllerTreePopupRequirement(TreePopupRequirement popup) {
        super(popup);
    }
    
    @Override
    protected void showPopup(DefaultMutableTreeNode node, MouseEvent event) {
        if (node.getUserObject() instanceof Project)
            setPopupFlag(true, true, false);
        else if (node.getUserObject() instanceof Requirement)
            setPopupFlag(true, true, true);
        else if (node.getUserObject() instanceof Element)
            setPopupFlag(false, true, true);
        getPopup().show(event.getComponent(), event.getX(), event.getY());
    }
    
    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            getPanelProject().initPanelEditProject();
        else if (object instanceof Requirement)
            getPanelProject().initPanelEditRequirement((Requirement) object, 0);
        else if (object instanceof Element)
            showPanelEditElement(node, (Element) object);
    }
    
    /**
     * Method responsible for showing the Panel Edit Element.
     * @param node Tree Node.
     * @param element Element.
     */
    private void showPanelEditElement(DefaultMutableTreeNode node, Element element) {
        Requirement requirement = getRequirement(node);
        getPanelProject().initPanelEditRequirement(requirement, element.getDiagram().getIndex());
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Requirement)
            new ViewDeleteRequirement(getPanelModeling(), (Requirement) object).setVisible(true);
        else if (object instanceof Element)
            delete(getRequirement(node), (Element) object);      
    }
    
    /**
     * Method responsible for deleting a Element if Requirement.
     * @param requirement Requirement.
     * @param element Element.
     */
    private void delete(Requirement requirement, Element element) {
        requirement.removeElement(element);
        getPanelModeling().getViewMenu().updatePanelTree();
        getPanelModeling().getViewMenu().setSave(false);
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Requirement)
            new ViewEditRequirement(getPanelModeling(), (Requirement) object, 0).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Requirement Node.
     * @param  node Tree Node.
     * @return Requirement Node.
     */
    protected Requirement getRequirement(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        if (parent != null && parent.getUserObject() instanceof Requirement)
            return (Requirement) parent.getUserObject();
        return null;
    }
    
    @Override
    protected TreePopupRequirement getPopup() {
        return (TreePopupRequirement) popup;
    }
}