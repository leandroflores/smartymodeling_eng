package controller.view.modal.edit;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Project;
import view.modal.edit.ViewEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of Controller <b>ControllerViewEdit</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEdit</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.edit.ViewEdit
 */
public abstract class ControllerViewEdit extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Edit.
     */
    public ControllerViewEdit(ViewEdit view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getView().getSaveButton().equals(event.getSource()))
            update();
        else if (getView().getCancelButton().equals(event.getSource()))
            getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            update();
        else if (F2 == event.getKeyCode())
            getView().dispose();
    }
    
    /**
     * Abstract Method responsible for updating the values for Edit.
     */
    public void update() {
        if (check())
            save();
    }
    
    /**
     * Abstract method responsible for checking the values.
     * @return Values checked.
     */
    public abstract boolean check();
    
    /**
     * Abstract Method responsible for saving the values.
     */
    public abstract void save();
    
    /**
     * Method responsible for closing the View Edit.
     */
    protected void close() {
        getView().getViewMenu().setSave(false);
        getView().getViewMenu().update();
        getView().dispose();
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return getViewMenu().getProject();
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return getView().getViewMenu();
    }
    
    @Override
    public ViewEdit getView() {
        return (ViewEdit) super.getView();
    }
}