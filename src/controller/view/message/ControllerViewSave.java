package controller.view.message;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.message.ViewSave;

/**
 * <p>Class of Controller <b>ControllerViewSave</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from <b>ViewSave</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.view.ControllerViewModal
 * @see    view.message.ViewSave
 */
public class ControllerViewSave extends ControllerViewModal {
    private final ViewSave viewSave;
    
    /**
     * Default constructor method of Class.
     * @param view View Save.
     */
    public ControllerViewSave(ViewSave view) {
        super(view);
        this.viewSave = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewSave.getButtonYes().equals(event.getSource()))
            this.save();
        else if (this.viewSave.getButtonNo().equals(event.getSource()))
            this.nextOperation();
        else if (this.viewSave.getButtonBack().equals(event.getSource()))
            this.viewSave.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode())
            this.viewSave.dispose();
    }
    
    /**
     * Method responsible for Saving the Project.
     */
    private void save() {
        this.viewSave.getViewMenu().getController().exportProject();
        this.nextOperation();
    }
    
    /**
     * Method responsible for forwarding the next operation.
     */
    private void nextOperation() {
        if (null != this.viewSave.getCode())
            switch (this.viewSave.getCode()) {
            case 1:
                this.viewSave.getViewMenu().getController().createNewProject();
                this.viewSave.dispose();
                break;
            case 2:
                this.viewSave.getViewMenu().getController().openProject();
                this.viewSave.dispose();
                break;
            case 3:
                this.viewSave.getViewMenu().getController().closeProject();
                this.viewSave.dispose();
                break;
            case 4:
                this.viewSave.getViewMenu().dispose();
                this.viewSave.dispose();
                break;
            default:
                break;
        }
    }
}