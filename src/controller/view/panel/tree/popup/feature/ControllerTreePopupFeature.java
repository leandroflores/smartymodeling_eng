package controller.view.panel.tree.popup.feature;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.FeatureDiagram;
import view.delete.base.ViewDeleteDiagram;
import view.delete.base.ViewDeleteElement;
import view.edit.base.ViewEditDiagram;
import view.edit.base.ViewEditElement;
import view.edit.base.ViewEditProject;
import view.panel.tree.popup.feature.TreePopupFeature;

/**
 * <p>Class of Controller <b>ControllerTreePopupFeature</b>.</p>
 * <p>Class responsible for controlling the <b>TreePopupFeature</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.ControllerTreePopup
 * @see    view.panel.tree.popup.feature.TreePopupFeature
 */
public class ControllerTreePopupFeature extends ControllerTreePopup {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Feature.
     */
    public ControllerTreePopupFeature(TreePopupFeature popup) {
        super(popup);
    }
    
    @Override
    protected void showPopup(DefaultMutableTreeNode node, MouseEvent event) {
        if (node.getUserObject() instanceof Project)
            this.treePopup.getDeleteMenuItem().setVisible(false);
        else
            this.treePopup.getDeleteMenuItem().setVisible(true);
        this.treePopup.show(event.getComponent(), event.getX(), event.getY());
    }

    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        Diagram diagram = this.getDiagram(node);
        if (object instanceof Project)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditProject();
        else if (object instanceof Diagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditDiagram((Diagram) object);
        else if (object instanceof Element)
            this.showPanelEdit(diagram, (Element) object);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void showPanelEdit(Diagram diagram, Element element) {
        if (diagram instanceof FeatureDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditElement((FeatureDiagram) diagram, element);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Diagram)
            new ViewDeleteDiagram(this.getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Element)
            new ViewDeleteElement(this.getPanelModeling(), this.getDiagram(node), (Element) object).setVisible(true);
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            new ViewEditProject(this.getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(this.getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Element)
            new ViewEditElement(this.getPanelModeling(), this.getDiagram(node), (Element) object).setVisible(true);
    }
}