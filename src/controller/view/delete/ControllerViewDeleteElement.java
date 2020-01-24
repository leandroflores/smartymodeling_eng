package controller.view.delete;

import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.activity.base.FinalUML;
import model.structural.diagram.activity.base.InitialUML;
import model.structural.diagram.activity.base.JoinUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.delete.ViewDeleteElement;

/**
 * <p>Class of Controller <b>ControllerViewDeleteElement</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewDeleteElement</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.Element
 * @see    view.delete.ViewDeleteElement
 */
public class ControllerViewDeleteElement extends ControllerViewDelete {
    private final ViewDeleteElement viewDeleteElement;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Element.
     */
    public ControllerViewDeleteElement(ViewDeleteElement viewDelete) {
        super(viewDelete);
        this.viewDeleteElement = viewDelete;
        this.element           = viewDelete.getElement();
    }
    
    @Override
    public void delete() {
        this.delete(this.viewDeleteElement.getPanelModeling().getPanelDiagram().getDiagram());
        this.close();
    }
    
    /**
     * Method responsible for deleting a Element from Diagram.
     * @param diagram Diagram.
     */
    private void delete(Diagram diagram) {
        if (diagram instanceof ActivityDiagram)
            this.deleteElement((ActivityDiagram) diagram);
        else if (diagram instanceof ClassDiagram)
            this.deleteElement((ClassDiagram) diagram);
        else if (diagram instanceof ComponentDiagram)
            this.deleteElement((ComponentDiagram) diagram);
        else if (diagram instanceof UseCaseDiagram)
            this.deleteElement((UseCaseDiagram) diagram);
        else if (diagram instanceof SequenceDiagram)
            this.deleteElement((SequenceDiagram) diagram);
    }
    
    /**
     * Method responsible for deleting a Element from a Activity Diagram.
     * @param diagram Activity Diagram.
     */
    private void deleteElement(ActivityDiagram diagram) {
        if (this.element instanceof ActivityUML)
            diagram.removeActivity((ActivityUML) this.element);
        else if (this.element instanceof DecisionUML)
            diagram.removeDecision((DecisionUML) this.element);
        else if (this.element instanceof FinalUML)
            diagram.removeFinal((FinalUML) this.element);
        else if (this.element instanceof InitialUML)
            diagram.removeInitial((InitialUML) this.element);
        else if (this.element instanceof JoinUML)
            diagram.removeJoin((JoinUML) this.element);
    }
    
    /**
     * Method responsible for deleting a Element from a Class Diagram.
     * @param diagram Class Diagram.
     */
    private void deleteElement(ClassDiagram diagram) {
        if (this.element instanceof PackageUML)
            diagram.removePackage((PackageUML) this.element);
        else if (this.element instanceof ClassUML)
            diagram.removeClass((ClassUML) this.element);
        else if (this.element instanceof InterfaceUML)
            diagram.removeInterface((InterfaceUML) this.element);
        else if (this.element instanceof AttributeUML)
            diagram.removeAttribute((AttributeUML) this.element);
        else if (this.element instanceof MethodUML)
            diagram.removeMethod((MethodUML) this.element);
    }
    
    /**
     * Method responsible for deleting a Element from a Component Diagram.
     * @param diagram Component Diagram.
     */
    private void deleteElement(ComponentDiagram diagram) {
        if (this.element instanceof ComponentUML)
            diagram.removeComponent((ComponentUML) this.element);
        else if (this.element instanceof model.structural.diagram.component.base.InterfaceUML)
            diagram.removeInterface((model.structural.diagram.component.base.InterfaceUML) this.element);
    }
    
    /**
     * Method responsible for deleting a Element from a Use Cases Diagram.
     * @param diagram Use Cases Diagram.
     */
    private void deleteElement(UseCaseDiagram diagram) {
        if (this.element instanceof ActorUML)
            diagram.removeActor((ActorUML) this.element);
        else if (this.element instanceof UseCaseUML)
            diagram.removeUseCase((UseCaseUML) this.element);
    }
    
    /**
     * Method responsible for deleting a Element from a Sequence Diagram.
     * @param diagram Sequence Diagram.
     */
    private void deleteElement(SequenceDiagram diagram) {
        if (this.element instanceof LifelineUML)
            diagram.removeLifeline((LifelineUML) this.element);
        else if (this.element instanceof InstanceUML)
            diagram.removeInstance((InstanceUML) this.element);
    }
}