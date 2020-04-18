package controller.view.new_.base.traceability;

import controller.view.new_.ControllerViewNew;
import view.message.ViewError;
import view.new_.base.traceability.ViewNewTraceability;

/**
 * <p>Class of Controller <b>ControllerViewNewTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewTraceability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-05
 * @see    controller.view.new_.ControllerViewNew
 * @see    model.structural.base.traceability.Traceability
 * @see    view.new_.base.traceability.ViewNewTraceability
 */
public class ControllerViewNewTraceability extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param viewNew View New Traceability.
     */
    public ControllerViewNewTraceability(ViewNewTraceability viewNew) {
        super(viewNew);
    }

    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseTraceability().getNameTextField(), "Name is required!")
            && this.check(this.getView().getPanelBaseTraceability().getDescriptionTextField(), "Description is required!")
            && this.checkElements();
    }
    
    /**
     * Method responsible for checking the Elements.
     * @return Elements are checkeds.
     */
    public boolean checkElements() {
        if (this.getView().getTraceability().getElements().isEmpty()) {
            new ViewError(this.getView(), "Add some Element!").setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    public void new_() {
        this.getView().getProject().addTraceability(this.getView().getTraceability());
    }
    
    @Override
    public ViewNewTraceability getView() {
        return (ViewNewTraceability) this.viewModal;
    }
}