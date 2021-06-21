package controller.view.modal.message;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.message.ViewSave;

/**
 * <p>Class of Controller <b>ControllerViewSave</b>.</p>
 * <p>Class responsible for controlling the <b>ViewSave</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.message.ViewSave
 */
public class ControllerViewSave extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Save.
     */
    public ControllerViewSave(ViewSave view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getView().getButtonYes().equals(event.getSource()))
            save();
        else if (getView().getButtonNo().equals(event.getSource()))
            nextOperation();
        else if (getView().getButtonBack().equals(event.getSource()))
            getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode())
            getView().dispose();
    }
    
    /**
     * Method responsible for Saving the Project.
     */
    private void save() {
        getView().getView().getController().exportProject();
        nextOperation();
    }
    
    /**
     * Method responsible for forwarding the next operation.
     */
    private void nextOperation() {
        if (null != getView().getCode()) { 
            switch (getView().getCode()) {
                case 1:
                    getView().getView().getController().createNewProject();
                    getView().dispose();
                    break;
                case 2:
                    getView().getView().getController().openProject();
                    getView().dispose();
                    break;
                case 3:
                    getView().getView().getController().closeProject();
                    getView().dispose();
                    break;
                case 4:
                    getView().getView().dispose();
                    getView().dispose();
                    break;
                default:
                    break;
            }
        }
    }
    
    @Override
    public ViewSave getView() {
        return (ViewSave) super.getView();
    }
}