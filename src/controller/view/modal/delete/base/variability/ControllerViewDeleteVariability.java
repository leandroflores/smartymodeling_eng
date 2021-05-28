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
public final class ControllerViewDeleteVariability extends ControllerViewDelete {
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Variability.
     */
    public ControllerViewDeleteVariability(ViewDeleteVariability view) {
        super(view);
        variability = getView().getVariability();
    }
    
    @Override
    public void delete() {
        getView().getDiagram().removeVariability(variability);
        getView().getDiagram().updateElementsStereotype();
        close();
    }
    
    @Override
    public ViewDeleteVariability getView() {
        return (ViewDeleteVariability) super.getView();
    }
}