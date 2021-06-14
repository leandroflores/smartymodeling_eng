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
            setProjectPopup();
        else if (node.getUserObject() instanceof Diagram)
            setDiagramPopup();
        else if (node.getUserObject() instanceof Variability)
            setVariabilityPopup();
        else if (node.getUserObject() instanceof Entity)
            setEntityPopup();
        else if (node.getUserObject() instanceof Element)
            setPopupFlag(false, true, true);
        getPopup().show(event.getComponent(), event.getX(), event.getY());
    }

    /**
     * Method responsible for setting the Project Popup.
     */
    private void setProjectPopup() {
        setPopupFlag(true, true, false);
        setNewDiagram(true);
        setNewVariability(false);
        setNewElement(false);
    }
    
    /**
     * Method responsible for setting the Diagram Popup.
     */
    private void setDiagramPopup() {
        setPopupFlag(true, true, true);
        setNewDiagram(false);
        setNewVariability(true);
        setNewElement(false);
    }
    
    /**
     * Method responsible for setting the Variability Popup.
     */
    private void setVariabilityPopup() {
        setPopupFlag(false, true, true);
    }
    
    /**
     * Method responsible for setting the Entity Popup.
     */
    private void setEntityPopup() {
        setPopupFlag(true, true, true);
        setNewDiagram(false);
        setNewVariability(false);
        setNewElement(true);
    }
    
    /**
     * Method responsible for setting the New Diagram.
     * @param flag New Diagram Flag.
     */
    private void setNewDiagram(boolean flag) {
        getPopup().getUseCaseDiagramMenuItem().setVisible(flag);
        getPopup().getClassDiagramMenuItem().setVisible(flag);
        getPopup().getComponentDiagramMenuItem().setVisible(flag);
        getPopup().getSequenceDiagramMenuItem().setVisible(flag);
        getPopup().getActivityDiagramMenuItem().setVisible(flag);
    }
    
    /**
     * Method responsible for setting the New Variability.
     * @param flag New Variability Flag.
     */
    private void setNewVariability(boolean flag) {
        getPopup().getVariabilityMenuItem().setVisible(flag);
    }
    
    /**
     * Method responsible for setting the New Element.
     * @param flag New Element Flag.
     */
    private void setNewElement(boolean flag) {
        getPopup().getAttributeMenuItem().setVisible(flag);
        getPopup().getMethodMenuItem().setVisible(flag);
    }
    
    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        Diagram diagram = getDiagram(node);
        if (object instanceof Project)
            getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditProject();
        else if (object instanceof Diagram)
            getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditDiagram((Diagram) object);
        else if (object instanceof Variability)
            getPopup().getPanel().getViewMenu().getPanelProject().initPanelEditVariability(diagram, (Variability) object, 0);
        else if (object instanceof Element)
            showPanelEdit(diagram, (Element) object);
        else if (object instanceof Association)
            showPanelEdit(diagram, (Association) object);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void showPanelEdit(Diagram diagram, Element element) {
        if (diagram instanceof ActivityDiagram)
            getPanelProject().initPanelEditElement((ActivityDiagram) diagram, element);
        else if (diagram instanceof ClassDiagram)
            getPanelProject().initPanelEditElement((ClassDiagram) diagram, element);
        else if (diagram instanceof ComponentDiagram)
            getPanelProject().initPanelEditElement((ComponentDiagram) diagram, element);
        else if (diagram instanceof UseCaseDiagram)
            getPanelProject().initPanelEditElement((UseCaseDiagram)  diagram, element);
        else if (diagram instanceof SequenceDiagram)
            getPanelProject().initPanelEditElement((SequenceDiagram) diagram, element);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param association Association.
     */
    private void showPanelEdit(Diagram diagram, Association association) {
        if (diagram instanceof ActivityDiagram)
            getPanelProject().initPanelEditAssociation((ActivityDiagram) diagram, association);
        else if (diagram instanceof ClassDiagram)
            getPanelProject().initPanelEditAssociation((ClassDiagram)    diagram, association);
        else if (diagram instanceof SequenceDiagram)
            getPanelProject().initPanelEditAssociation((SequenceDiagram) diagram, association);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Diagram)
            new ViewDeleteDiagram(getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Element)
            new ViewDeleteElement(getPanelModeling(), getDiagram(node), (Element) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewDeleteVariability(getPanelModeling(), getDiagram(node), (Variability) object).setVisible(true);
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            new ViewEditProject(getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewEditVariability(getPanelModeling(), getDiagram(node), (Variability) object).setVisible(true);
        else if (object instanceof PackageUML)
            new ViewEditPackageUML(getPanelModeling(),   (ClassDiagram) getDiagram(node), (PackageUML) object).setVisible(true);
        else if (object instanceof InterfaceUML)
            new ViewEditInterfaceUML(getPanelModeling(), (ClassDiagram) getDiagram(node), (InterfaceUML) object).setVisible(true);
        else if (object instanceof ClassUML)
            new ViewEditClassUML(getPanelModeling(),     (ClassDiagram) getDiagram(node), (ClassUML) object).setVisible(true);
        else if (object instanceof AttributeUML)
            new ViewEditAttributeUML(getPanelModeling(), (ClassDiagram) getDiagram(node), (AttributeUML) object).setVisible(true);
        else if (object instanceof MethodUML)
            new ViewEditMethodUML(getPanelModeling(),    (ClassDiagram) getDiagram(node), (MethodUML) object).setVisible(true);
        else if (object instanceof LifelineUML)
            new ViewEditLifelineUML(getPanelModeling(),  (SequenceDiagram) getDiagram(node), (LifelineUML) object).setVisible(true);
        else if (object instanceof InstanceUML)
            new ViewEditInstanceUML(getPanelModeling(),  (SequenceDiagram) getDiagram(node), (InstanceUML) object).setVisible(true);
        else if (object instanceof Element)
            new ViewEditElement(getPanelModeling(), getDiagram(node), (Element) object).setVisible(true);
    }
    
    @Override
    protected TreePopupDiagram getPopup() {
        return (TreePopupDiagram) popup;
    }
}