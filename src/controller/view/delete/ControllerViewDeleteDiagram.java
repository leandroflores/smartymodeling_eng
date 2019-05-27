package controller.view.delete;

import model.structural.base.Diagram;
import view.delete.ViewDeleteDiagram;

/**
 * <p>Class of Controller <b>ControllerViewDeleteDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewDeleteDiagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.Diagram
 * @see    view.delete.ViewDeleteDiagram
 */
public class ControllerViewDeleteDiagram extends ControllerViewDelete {
    private final ViewDeleteDiagram viewDeleteDiagram;
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Diagram.
     */
    public ControllerViewDeleteDiagram(ViewDeleteDiagram viewDelete) {
        super(viewDelete);
        this.viewDeleteDiagram = viewDelete;
        this.diagram           = viewDelete.getDiagram();
    }
    
    @Override
    public void delete() {
        this.viewDeleteDiagram.getPanelModeling().getViewMenu().getProject().removeDiagram(this.diagram);
        this.viewDeleteDiagram.getPanelModeling().removeDiagram(this.diagram);
        this.viewDeleteDiagram.getPanelModeling().getViewMenu().update();
        this.viewDeleteDiagram.dispose();
    }
}