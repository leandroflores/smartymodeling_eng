package controller.view.modal.delete.base;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.Diagram;
import view.modal.delete.base.ViewDeleteDiagram;

/**
 * <p>Class of Controller <b>ControllerViewDeleteDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.Diagram
 * @see    view.modal.delete.base.ViewDeleteDiagram
 */
public class ControllerViewDeleteDiagram extends ControllerViewDelete {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Diagram.
     */
    public ControllerViewDeleteDiagram(ViewDeleteDiagram viewDelete) {
        super(viewDelete);
        this.diagram = viewDelete.getDiagram();
    }
    
    @Override
    public void delete() {
        this.getView().getProject().removeDiagram(this.diagram);
        this.getView().getPanelModeling().removeDiagram(this.diagram);
        this.close();
    }
    
    @Override
    public ViewDeleteDiagram getView() {
        return (ViewDeleteDiagram) this.viewModal;
    }
}