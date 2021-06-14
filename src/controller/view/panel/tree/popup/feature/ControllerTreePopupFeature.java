package controller.view.panel.tree.popup.feature;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.modal.delete.base.ViewDeleteDiagram;
import view.modal.delete.base.ViewDeleteElement;
import view.modal.edit.base.ViewEditDiagram;
import view.modal.edit.base.ViewEditProject;
import view.modal.edit.diagram.feature.base.ViewEditFeature;
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
            setPopupFlag(true, true, false);
        else if (node.getUserObject() instanceof Diagram)
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
        else if (object instanceof Element)
            showPanelEdit(diagram, (Element) object);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void showPanelEdit(Diagram diagram, Element element) {
        if (diagram instanceof FeatureDiagram)
            getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditElement((FeatureDiagram) diagram, element);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Diagram)
            new ViewDeleteDiagram(getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Element)
            new ViewDeleteElement(getPanelModeling(), getDiagram(node), (Element) object).setVisible(true);
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            new ViewEditProject(getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Feature)
            new ViewEditFeature(getPanelModeling(), (FeatureDiagram) getDiagram(node), (Feature) object).setVisible(true);
    }
    
    @Override
    protected TreePopupFeature getPopup() {
        return (TreePopupFeature) popup;
    }
}