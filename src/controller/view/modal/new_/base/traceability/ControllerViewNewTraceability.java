package controller.view.modal.new_.base.traceability;

import controller.view.modal.new_.ControllerViewNew;
import view.modal.message.ViewError;
import view.modal.new_.base.traceability.ViewNewTraceability;

/**
 * <p>Class of Controller <b>ControllerViewNewTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewTraceability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-05
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    model.structural.base.traceability.Traceability
 * @see    view.modal.new_.base.traceability.ViewNewTraceability
 */
public class ControllerViewNewTraceability extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param view View New Traceability.
     */
    public ControllerViewNewTraceability(ViewNewTraceability view) {
        super(view);
    }

    @Override
    public boolean check() {
        return check(getView().getPanelBaseTraceability().getNameTextField(), "Name is required!")
            && check(getView().getPanelBaseTraceability().getDescriptionTextField(), "Description is required!")
            && checkElements();
    }
    
    /**
     * Method responsible for checking the Elements.
     * @return Elements are checkeds.
     */
    public boolean checkElements() {
        if (getView().getTraceability().getElements().isEmpty()) {
            new ViewError(getView(), "Add some Element!").setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    public void new_() {
        getView().getProject().addTraceability(getView().getTraceability());
        getView().getViewMenu().setTabIndex(0);
    }
    
    @Override
    public ViewNewTraceability getView() {
        return (ViewNewTraceability) super.getView();
    }
}