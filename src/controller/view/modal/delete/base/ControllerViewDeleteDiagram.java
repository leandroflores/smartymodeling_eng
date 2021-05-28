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
     * @param view View Delete Diagram.
     */
    public ControllerViewDeleteDiagram(ViewDeleteDiagram view) {
        super(view);
        diagram = view.getDiagram();
    }
    
    @Override
    public void delete() {
        getView().getProject().removeDiagram(diagram);
        getView().getPanelModeling().removeDiagram(diagram);
        close();
    }
    
    @Override
    public ViewDeleteDiagram getView() {
        return (ViewDeleteDiagram) super.getView();
    }
}