package controller.view.edit.base.traceability;

import controller.view.edit.ControllerViewEdit;
import view.edit.base.traceability.ViewEditTraceability;

/**
 * <p>Class of Controller <b>ControllerViewEditTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditTraceability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.edit.ControllerViewEdit
 * @see    model.structural.base.traceability.Traceability
 * @see    view.edit.base.traceability.ViewEditTraceability
 */
public class ControllerViewEditTraceability extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Traceability.
     */
    public ControllerViewEditTraceability(ViewEditTraceability viewEdit) {
        super(viewEdit);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseTraceability().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditTraceability getView() {
        return (ViewEditTraceability) this.viewModal;
    }
}