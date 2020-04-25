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
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import view.modal.delete.base.ViewDeleteDiagram;
import view.modal.delete.base.ViewDeleteElement;
import view.modal.delete.base.variability.ViewDeleteVariability;
import view.modal.edit.base.ViewEditDiagram;
import view.modal.edit.base.ViewEditElement;
import view.modal.edit.base.ViewEditProject;
import view.modal.edit.base.variability.ViewEditVariability;
import view.modal.edit.diagram.classes.base.ViewEditAttributeUML;
import view.modal.edit.diagram.classes.base.ViewEditClassUML;
import view.modal.edit.diagram.classes.base.ViewEditInterfaceUML;
import view.modal.edit.diagram.classes.base.ViewEditMethodUML;
import view.modal.edit.diagram.classes.base.ViewEditPackageUML;
import view.modal.edit.diagram.sequence.base.ViewEditInstanceUML;
import view.modal.edit.diagram.sequence.base.ViewEditLifelineUML;
import view.panel.tree.popup.diagram.TreePopupDiagram;

/**
 * <p>Class of Controller <b>ControllerTreePopupDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>TreePopupDiagram</b> Events of SMartyModeling.</p>
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
            this.setProjectPopup();
        else if (node.getUserObject() instanceof Diagram)
            this.setDiagramPopup();
        else if (node.getUserObject() instanceof Variability)
            this.setVariabilityPopup();
        else if (node.getUserObject() instanceof Entity)
            this.setEntityPopup();
        else if (node.getUserObject() instanceof Element)
            super.setPopupFlag(false, true, true);
        this.getPopup().show(event.getComponent(), event.getX(), event.getY());
    }

    /**
     * Method responsible for setting the Project Popup.
     */
    private void setProjectPopup() {
        super.setPopupFlag(true, true, false);
        this.setNewDiagram(true);
        this.setNewVariability(false);
        this.setNewElement(false);
    }
    
    /**
     * Method responsible for setting the Diagram Popup.
     */
    private void setDiagramPopup() {
        super.setPopupFlag(true, true, true);
        this.setNewDiagram(false);
        this.setNewVariability(true);
        this.setNewElement(false);
    }
    
    /**
     * Method responsible for setting the Variability Popup.
     */
    private void setVariabilityPopup() {
        super.setPopupFlag(false, true, true);
    }
    
    /**
     * Method responsible for setting the Entity Popup.
     */
    private void setEntityPopup() {
        super.setPopupFlag(true, true, true);
        this.setNewDiagram(false);
        this.setNewVariability(false);
        this.setNewElement(true);
    }
    
    /**
     * Method responsible for setting the New Diagram.
     * @param flag New Diagram Flag.
     */
    private void setNewDiagram(boolean flag) {
        this.getPopup().getUseCaseDiagramMenuItem().setVisible(flag);
        this.getPopup().getClassDiagramMenuItem().setVisible(flag);
        this.getPopup().getComponentDiagramMenuItem().setVisible(flag);
        this.getPopup().getSequenceDiagramMenuItem().setVisible(flag);
        this.getPopup().getActivityDiagramMenuItem().setVisible(flag);
    }
    
    /**
     * Method responsible for setting the New Variability.
     * @param flag New Variability Flag.
     */
    private void setNewVariability(boolean flag) {
        this.getPopup().getVariabilityMenuItem().setVisible(flag);
    }
    
    /**
     * Method responsible for setting the New Element.
     * @param flag New Element Flag.
     */
    private void setNewElement(boolean flag) {
        this.getPopup().getAttributeMenuItem().setVisible(flag);
        this.getPopup().getMethodMenuItem().setVisible(flag);
    }
    
    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        Diagram diagram = this.getDiagram(node);
        if (object instanceof Project)
            this.getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditProject();
        else if (object instanceof Diagram)
            this.getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditDiagram((Diagram) object);
        else if (object instanceof Variability)
            this.getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditVariability(diagram, (Variability) object, 0);
        else if (object instanceof Element)
            this.showPanelEdit(diagram, (Element) object);
        else if (object instanceof Association)
            this.showPanelEdit(diagram, (Association) object);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void showPanelEdit(Diagram diagram, Element element) {
        if (diagram instanceof ActivityDiagram)
            this.getPanelProject().initPanelEditElement((ActivityDiagram) diagram, element);
        else if (diagram instanceof ClassDiagram)
            this.getPanelProject().initPanelEditElement((ClassDiagram) diagram, element);
        else if (diagram instanceof ComponentDiagram)
            this.getPanelProject().initPanelEditElement((ComponentDiagram) diagram, element);
        else if (diagram instanceof UseCaseDiagram)
            this.getPanelProject().initPanelEditElement((UseCaseDiagram)  diagram, element);
        else if (diagram instanceof SequenceDiagram)
            this.getPanelProject().initPanelEditElement((SequenceDiagram) diagram, element);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param association Association.
     */
    private void showPanelEdit(Diagram diagram, Association association) {
        if (diagram instanceof ActivityDiagram)
            this.getPanelProject().initPanelEditAssociation((ActivityDiagram) diagram, association);
        else if (diagram instanceof ClassDiagram)
            this.getPanelProject().initPanelEditAssociation((ClassDiagram)    diagram, association);
        else if (diagram instanceof SequenceDiagram)
            this.getPanelProject().initPanelEditAssociation((SequenceDiagram) diagram, association);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Diagram)
            new ViewDeleteDiagram(this.getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Element)
            new ViewDeleteElement(this.getPanelModeling(), this.getDiagram(node), (Element) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewDeleteVariability(this.getPanelModeling(), this.getDiagram(node), (Variability) object).setVisible(true);
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            new ViewEditProject(this.getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(this.getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewEditVariability(this.getPanelModeling(), this.getDiagram(node), (Variability) object).setVisible(true);
        else if (object instanceof PackageUML)
            new ViewEditPackageUML(this.getPanelModeling(),   (ClassDiagram) this.getDiagram(node), (PackageUML) object).setVisible(true);
        else if (object instanceof InterfaceUML)
            new ViewEditInterfaceUML(this.getPanelModeling(), (ClassDiagram) this.getDiagram(node), (InterfaceUML) object).setVisible(true);
        else if (object instanceof ClassUML)
            new ViewEditClassUML(this.getPanelModeling(),     (ClassDiagram) this.getDiagram(node), (ClassUML) object).setVisible(true);
        else if (object instanceof AttributeUML)
            new ViewEditAttributeUML(this.getPanelModeling(), (ClassDiagram) this.getDiagram(node), (AttributeUML) object).setVisible(true);
        else if (object instanceof MethodUML)
            new ViewEditMethodUML(this.getPanelModeling(),    (ClassDiagram) this.getDiagram(node), (MethodUML) object).setVisible(true);
        else if (object instanceof LifelineUML)
            new ViewEditLifelineUML(this.getPanelModeling(),  (SequenceDiagram) this.getDiagram(node), (LifelineUML) object).setVisible(true);
        else if (object instanceof InstanceUML)
            new ViewEditInstanceUML(this.getPanelModeling(),  (SequenceDiagram) this.getDiagram(node), (InstanceUML) object).setVisible(true);
        else if (object instanceof Element)
            new ViewEditElement(this.getPanelModeling(), this.getDiagram(node), (Element) object).setVisible(true);
    }
    
    @Override
    protected TreePopupDiagram getPopup() {
        return (TreePopupDiagram) this.popup;
    }
}