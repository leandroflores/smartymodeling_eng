package controller.view.delete;

import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.UseCaseDiagram;
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
        this.element            = viewDelete.getElement();
    }
    
    @Override
    public void delete() {
        this.deleteElement(this.viewDeleteElement.getPanelModeling().getPanelDiagram().getDiagram());
        this.viewDeleteElement.getPanelModeling().getViewMenu().update();
        this.viewDeleteElement.dispose();
    }
    
    /**
     * Method responsible for deleting a Element from Diagram.
     * @param diagram Diagram.
     */
    private void deleteElement(Diagram diagram) {
        if (diagram instanceof UseCaseDiagram)
            this.deleteUseCasesDiagram((UseCaseDiagram) diagram);
//        else if (diagram instanceof DiagramaClasses)
//            this.removeDiagramaClasses((DiagramaClasses) diagram);
//        else if (diagram instanceof DiagramaComponentes)
//            this.removeDiagramaComponentes((DiagramaComponentes) diagram);
    }
    
    /**
     * Method responsible for deleting a Element from a Use Cases Diagram.
     * @param diagram Use Cases Diagram.
     */
    private void deleteUseCasesDiagram(UseCaseDiagram diagram) {
        if (this.element instanceof ActorUML)
            diagram.removeActor((ActorUML) this.element);
        else if (this.element instanceof UseCaseUML)
            diagram.removeUseCase((UseCaseUML) this.element);
    }
    
    /**
     * Metodo responsavel por remover um Elemento do Diagrama de Classes.
     * @param diagrama Diagrama de Classes.
     */
//    private void removeDiagramaClasses(DiagramaClasses diagrama) {
//        if (this.element instanceof PacoteUML)
//            diagrama.removePacote((PacoteUML) this.element);
//        else if (this.element instanceof ClasseUML)
//            diagrama.removeClasse((ClasseUML) this.element);
//        else if (this.element instanceof InterfaceUML)
//            diagrama.removeInterface((InterfaceUML) this.element);
//    }
    
    /**
     * Metodo responsavel por remover um Elemento do Diagrama de Componentes.
     * @param diagrama Diagrama de Componentes.
     */
//    private void removeDiagramaComponentes(DiagramaComponentes diagrama) {
//        if (this.element instanceof ComponenteUML)
//            diagrama.removeComponente((ComponenteUML) this.element);
//        else if (this.element instanceof modelo.estruturais.diagrama.componentes.InterfaceUML)
//            diagrama.removeInterface((modelo.estruturais.diagrama.componentes.InterfaceUML) this.element);
//    }
}