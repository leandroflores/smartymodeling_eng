package controller.view.modal.requirement;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.requirement.ViewRequirement;

/**
 * <p>Class of Controller <b>ControllerViewRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.requirement.ViewRequirement
 */
public abstract class ControllerViewRequirement extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Requirement.
     */
    public ControllerViewRequirement(ViewRequirement view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getView().getSaveButton().equals(event.getSource()))
            save();
        else if (getView().getCancelButton().equals(event.getSource()))
            getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
                save();
                break;
            case F2:
                getView().dispose();
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for Saving the View.
     */
    public void save() {
        getView().getViewMenu().setSave(false);
        getView().getViewMenu().updatePanelTree();
        getView().dispose();
    }
    
    /**
     * Method responsible for closing the View New.
     */
    protected void close() {
        getView().dispose();
    }
    
    @Override
    public ViewRequirement getView() {
        return (ViewRequirement) super.getView();
    }
}