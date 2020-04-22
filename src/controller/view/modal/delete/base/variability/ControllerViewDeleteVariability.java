package controller.view.modal.delete.base.variability;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.variability.Variability;
import view.modal.delete.base.variability.ViewDeleteVariability;

/**
 * <p>Class of Controller <b>ControllerViewDeleteVariability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.variability.Variability
 * @see    view.modal.delete.base.variability.ViewDeleteVariability
 */
public class ControllerViewDeleteVariability extends ControllerViewDelete {
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Variability.
     */
    public ControllerViewDeleteVariability(ViewDeleteVariability viewDelete) {
        super(viewDelete);
        this.variability = viewDelete.getVariability();
    }
    
    @Override
    public void delete() {
        this.getView().getDiagram().removeVariability(this.variability);
        this.getView().getDiagram().updateElementsStereotype();
        this.close();
    }
    
    @Override
    public ViewDeleteVariability getView() {
        return (ViewDeleteVariability) this.viewModal;
    }
}