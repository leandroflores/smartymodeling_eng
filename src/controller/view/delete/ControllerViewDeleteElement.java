package controller.view.delete;

import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.classs.base.ClassUML;
import model.structural.diagram.classs.base.InterfaceUML;
import model.structural.diagram.classs.base.PackageUML;
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
        this.viewDeleteElement.getPanelModeling().getViewMenu().update();
        this.viewDeleteElement.dispose();
    }
    
    /**
     * Method responsible for deleting a Element from Diagram.
     * @param diagram Diagram.
     */
    private void delete(Diagram diagram) {
        if (diagram instanceof UseCaseDiagram)
            this.deleteElement((UseCaseDiagram) diagram);
        else if (diagram instanceof ClassDiagram)
            this.deleteElement((ClassDiagram) diagram);
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
    }
}