package controller.view.modal.requirement;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.new_.ViewNew;
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
    public ControllerViewRequirement(ViewNew view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.getView().getRefreshButton().equals(event.getSource()))
            this.refresh();
        else if (this.getView().getCancelButton().equals(event.getSource()))
            this.getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
                this.refresh();
                break;
            case F2:
                this.getView().dispose();
                break;
            default:
                break;
        }
    }
    
    /**
     * Abstract Method responsible for refreshing the View.
     */
    public abstract void refresh();
    
    /**
     * Method responsible for closing the View New.
     */
    protected void close() {
        this.getView().getViewMenu().setSave(false);
//        this.getView().getViewMenu().getPanelProject().getPanelTree().updateNode(requirement);
        this.getView().dispose();
    }
    
    @Override
    public ViewRequirement getView() {
        return (ViewRequirement) this.viewModal;
    }
}