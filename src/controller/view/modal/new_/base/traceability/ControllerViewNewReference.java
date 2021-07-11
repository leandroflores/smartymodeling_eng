package controller.view.modal.new_.base.traceability;

import controller.view.modal.new_.ControllerViewNew;
import view.modal.message.ViewError;
import view.modal.new_.base.traceability.ViewNewReference;

/**
 * <p>Class of Controller <b>ControllerViewNewReference</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewReference</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-05
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    model.structural.base.traceability.Reference
 * @see    view.modal.new_.base.traceability.ViewNewReference
 */
public class ControllerViewNewReference extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param view View New Reference.
     */
    public ControllerViewNewReference(ViewNewReference view) {
        super(view);
    }

    @Override
    public boolean check() {
        return check(getView().getPanelBaseReference().getNameTextField(), "Name is required!")
            && check(getView().getPanelBaseReference().getDescriptionTextField(), "Description is required!")
            && checkElements();
    }
    
    /**
     * Method responsible for checking the Elements.
     * @return Elements are checkeds.
     */
    public boolean checkElements() {
        if (getView().getReference().getElements().isEmpty()) {
            new ViewError(getView(), "Add some Element!").setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    public void new_() {
        getView().getProject().addReference(getView().getReference());
        getView().getViewMenu().setTabIndex(0);
    }
    
    @Override
    public ViewNewReference getView() {
        return (ViewNewReference) super.getView();
    }
}