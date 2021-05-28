package controller.view.modal.delete.base;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.activity.base.FinalUML;
import model.structural.diagram.activity.base.InitialUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.Variability;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.modal.delete.base.ViewDeleteElement;

/**
 * <p>Class of Controller <b>ControllerViewDeleteElement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteElement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.Element
 * @see    view.modal.delete.base.ViewDeleteElement
 */
public class ControllerViewDeleteElement extends ControllerViewDelete {
    private final Diagram diagram;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Element.
     */
    public ControllerViewDeleteElement(ViewDeleteElement view) {
        super(view);
        diagram = view.getDiagram();
        element = view.getElement();
    }
    
    @Override
    public void delete() {
        if (diagram instanceof FeatureDiagram)
            deleteElement((FeatureDiagram) diagram);
        else if (diagram instanceof ActivityDiagram)
            deleteElement((ActivityDiagram) diagram);
        else if (diagram instanceof ClassDiagram)
            deleteElement((ClassDiagram) diagram);
        else if (diagram instanceof ComponentDiagram)
            deleteElement((ComponentDiagram) diagram);
        else if (diagram instanceof UseCaseDiagram)
            deleteElement((UseCaseDiagram) diagram);
        else if (diagram instanceof SequenceDiagram)
            deleteElement((SequenceDiagram) diagram);
        close();
    }
    
    /**
     * Method responsible for deleting a Element from a Feature Diagram.
     * @param diagram Feature Diagram.
     */
    private void deleteElement(FeatureDiagram diagram) {
        if (element instanceof Feature)
            diagram.removeFeature((Feature) element);
        else if (element instanceof Variability)
            diagram.removeVariability((Variability) element);
    }
    
    /**
     * Method responsible for deleting a Element from a Activity Diagram.
     * @param diagram Activity Diagram.
     */
    private void deleteElement(ActivityDiagram diagram) {
        if (element instanceof ActivityUML)
            diagram.removeActivity((ActivityUML) element);
        else if (element instanceof DecisionUML)
            diagram.removeDecision((DecisionUML) element);
        else if (element instanceof FinalUML)
            diagram.removeFinal((FinalUML) element);
        else if (element instanceof InitialUML)
            diagram.removeInitial((InitialUML) element);
        diagram.updateElementsStereotype();
    }
    
    /**
     * Method responsible for deleting a Element from a Class Diagram.
     * @param diagram Class Diagram.
     */
    private void deleteElement(ClassDiagram diagram) {
        if (element instanceof PackageUML)
            diagram.removePackage((PackageUML) element);
        else if (element instanceof ClassUML)
            diagram.removeClass((ClassUML) element);
        else if (element instanceof InterfaceUML)
            diagram.removeInterface((InterfaceUML) element);
        else if (element instanceof AttributeUML)
            diagram.removeAttribute((AttributeUML) element);
        else if (element instanceof MethodUML)
            diagram.removeMethod((MethodUML) element);
        diagram.updateElementsStereotype();
    }
    
    /**
     * Method responsible for deleting a Element from a Component Diagram.
     * @param diagram Component Diagram.
     */
    private void deleteElement(ComponentDiagram diagram) {
        if (element instanceof ComponentUML)
            diagram.removeComponent((ComponentUML) element);
        else if (element instanceof  model.structural.diagram.component.base.InterfaceUML)
            diagram.removeInterface((model.structural.diagram.component.base.InterfaceUML) element);
        diagram.updateElementsStereotype();
    }
    
    /**
     * Method responsible for deleting a Element from a Use Cases Diagram.
     * @param diagram Use Cases Diagram.
     */
    private void deleteElement(UseCaseDiagram diagram) {
        if (element instanceof ActorUML)
            diagram.removeActor((ActorUML) element);
        else if (element instanceof UseCaseUML)
            diagram.removeUseCase((UseCaseUML) element);
        diagram.updateElementsStereotype();
    }
    
    /**
     * Method responsible for deleting a Element from a Sequence Diagram.
     * @param diagram Sequence Diagram.
     */
    private void deleteElement(SequenceDiagram diagram) {
        if (element instanceof LifelineUML)
            diagram.removeLifeline((LifelineUML) element);
        else if (element instanceof InstanceUML)
            diagram.removeInstance((InstanceUML) element);
        diagram.updateElementsStereotype();
    }
    
    @Override
    public ViewDeleteElement getView() {
        return (ViewDeleteElement) super.getView();
    }
}