package controller.view.panel.tree.popup.diagram;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.base.variability.Variability;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.delete.base.ViewDeleteDiagram;
import view.delete.base.ViewDeleteElement;
import view.delete.base.variability.ViewDeleteVariability;
import view.edit.base.ViewEditDiagram;
import view.edit.base.ViewEditElement;
import view.edit.base.ViewEditProject;
import view.edit.diagram.classes.ViewEditAttributeUML;
import view.edit.diagram.classes.ViewEditMethodUML;
import view.panel.tree.popup.diagram.TreePopupDiagram;

/**
 * <p>Class of Controller <b>ControllerTreePopupDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>TreePoput</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.panel.tree.popup.ControllerTreePopup
 * @see    view.panel.tree.popup.diagram.TreePopupDiagram
 */
public class ControllerTreePopupDiagram extends ControllerTreePopup {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Diagram.
     */
    public ControllerTreePopupDiagram(TreePopupDiagram popup) {
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
    protected void showPanelEdit(DefaultMutableTreeNode node) {
        if (node != null && node.getUserObject() != null) {
            Diagram diagram = this.getDiagram(node);
            if (node.getUserObject() instanceof Project)
                this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditProject();
            else if (node.getUserObject() instanceof Diagram)
                this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditDiagram((Diagram) node.getUserObject());
            else if (node.getUserObject() instanceof Variability)
                this.showPanelEditVariability(diagram, (Variability) node.getUserObject());
            else if (node.getUserObject() instanceof Element)
                this.showPanelEdit(diagram, (Element) node.getUserObject());
            else if (node.getUserObject() instanceof Association)
                this.showPanelEdit(diagram, (Association) node.getUserObject());
        }
    }
    
    /**
     * Method responsible for showing the Panel Edit Variability.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    private void showPanelEditVariability(Diagram diagram, Variability variability) {
        this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditVariability(diagram, variability);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void showPanelEdit(Diagram diagram, Element element) {
        if (diagram instanceof FeatureDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditElement((FeatureDiagram) diagram, element);
        else if (diagram instanceof ActivityDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditElement((ActivityDiagram) diagram, element);
        else if (diagram instanceof ClassDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditElement((ClassDiagram) diagram, element);
        else if (diagram instanceof ComponentDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditElement((ComponentDiagram) diagram, element);
        else if (diagram instanceof UseCaseDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditElement((UseCaseDiagram)  diagram, element);
        else if (diagram instanceof SequenceDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditElement((SequenceDiagram) diagram, element);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param association Association.
     */
    private void showPanelEdit(Diagram diagram, Association association) {
        if (diagram instanceof ActivityDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditAssociation((ActivityDiagram) diagram, association);
        else if (diagram instanceof SequenceDiagram)
            this.treePopup.getPanel().getViewMenu().getPanelProject().initPanelEditAssociation((SequenceDiagram) diagram, association);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Diagram)
            new ViewDeleteDiagram(this.treePopup.getPanel().getViewMenu().getPanelModeling(), (Diagram) node.getUserObject()).setVisible(true);
        else if (node.getUserObject() instanceof Element)
            new ViewDeleteElement(this.treePopup.getPanel().getViewMenu().getPanelModeling(), 
                                  this.getDiagram(node),
                                  (Element) node.getUserObject()).setVisible(true);
        else if (node.getUserObject() instanceof Variability)
            new ViewDeleteVariability(this.treePopup.getPanel().getViewMenu().getPanelModeling(), this.getDiagram(node), (Variability) node.getUserObject()).setVisible(true);
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Project)
            new ViewEditProject(this.treePopup.getPanel().getViewMenu().getPanelModeling(), ((Project) node.getUserObject())).setVisible(true);
        else if (node.getUserObject() instanceof Diagram)
            new ViewEditDiagram(this.treePopup.getPanel().getViewMenu().getPanelModeling(), ((Diagram) node.getUserObject())).setVisible(true);
        else if (node.getUserObject() instanceof Variability)
            this.editVariability(node.getUserObject(), node);
        else if (node.getUserObject() instanceof AttributeUML)
            new ViewEditAttributeUML(this.treePopup.getPanel().getViewMenu().getPanelModeling(), ((ClassDiagram) this.getDiagram(node)), ((AttributeUML) node.getUserObject())).setVisible(true);
        else if (node.getUserObject() instanceof MethodUML)
            new ViewEditMethodUML(this.treePopup.getPanel().getViewMenu().getPanelModeling(), ((ClassDiagram) this.getDiagram(node)), ((MethodUML) node.getUserObject())).setVisible(true);
        else if (node.getUserObject() instanceof Element)
            new ViewEditElement(this.treePopup.getPanel().getViewMenu().getPanelModeling(), this.getDiagram(node), ((Element) node.getUserObject())).setVisible(true);
    }
    
    /**
     * Method responsible for editing Variability.
     * @param object Selected Object.
     * @param node JTree Node.
     */
    private void editVariability(Object object, DefaultMutableTreeNode node) {
        Diagram     diagram     = this.getDiagram((DefaultMutableTreeNode) node.getParent());
        Variability variability = (Variability) object;
        
    }
}