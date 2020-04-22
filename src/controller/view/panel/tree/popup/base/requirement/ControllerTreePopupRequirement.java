package controller.view.panel.tree.popup.base.requirement;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.delete.base.requirement.ViewDeleteRequirement;
import view.edit.base.requirement.ViewEditRequirement;
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
            this.setPopupFlag(false, true, false);
        else if (node.getUserObject() instanceof Requirement)
            this.setPopupFlag(true, true, true);
        else if (node.getUserObject() instanceof Element)
            this.setPopupFlag(false, true, true);
        this.getPopup().show(event.getComponent(), event.getX(), event.getY());
    }

    /**
     * Method responsible for setting the Popup Flag.
     * @param add Add Element Flag.
     * @param edit Edit Flag.
     * @param delete Delete Flag.
     */
    private void setPopupFlag(boolean add, boolean edit, boolean delete) {
        this.getPopup().getAddElementMenuItem().setVisible(add);
        this.getPopup().getEditMenuItem().setVisible(edit);
        this.getPopup().getDeleteMenuItem().setVisible(delete);
    }
    
    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditProject();
        else if (object instanceof Requirement)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditRequirement((Requirement) object);
//        else if (object instanceof Element)
//            this.getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditElement((Requirement) object);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Requirement)
            new ViewDeleteRequirement(this.getPanelModeling(), (Requirement) object).setVisible(true);
//        else if (object instanceof Measure)
//            new ViewDeleteMeasure(this.getPanelModeling(), (Measure) object).setVisible(true);        
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Requirement)
            new ViewEditRequirement(this.getPanelModeling(), (Requirement) object).setVisible(true);
    }
    
    @Override
    protected TreePopupRequirement getPopup() {
        return (TreePopupRequirement) this.treePopup;
    }
}