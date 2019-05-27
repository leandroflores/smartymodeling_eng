package controller.view.delete;

import model.structural.base.variability.Variability;
import view.delete.ViewDeleteVariability;

/**
 * <p>Class of Controller <b>ControllerViewDeleteVariability</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewDeleteVariability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.variability.Variability
 * @see    view.delete.ViewDeleteVariability
 */
public class ControllerViewDeleteVariability extends ControllerViewDelete {
    private final ViewDeleteVariability viewDeleteVariability;
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Variability.
     */
    public ControllerViewDeleteVariability(ViewDeleteVariability viewDelete) {
        super(viewDelete);
        this.viewDeleteVariability = viewDelete;
        this.variability           = viewDelete.getVariability();
    }
    
    @Override
    public void delete() {
        this.viewDeleteVariability.getDiagram().removeVariability(this.variability);
        this.viewDeleteVariability.getPanelModeling().getViewMenu().update();
        this.viewDeleteVariability.dispose();
    }
}