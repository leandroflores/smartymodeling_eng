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
        if (this.getView().getSaveButton().equals(event.getSource()))
            this.update();
        else if (this.getView().getCancelButton().equals(event.getSource()))
            this.getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            this.update();
        else if (F2 == event.getKeyCode())
            this.getView().dispose();
    }
    
    /**
     * Abstract Method responsible for updating.
     */
    public void update() {
        if (this.check())
            this.save();
    }
    
    /**
     * Abstract method responsible for checking the values.
     * @return Values checked.
     */
    public abstract boolean check();
    
    /**
     * Abstract Method responsible for save the values.
     */
    public abstract void save();
    
    /**
     * Method responsible for closing the View Edit.
     */
    protected void close() {
        this.getView().getViewMenu().setSave(false);
        this.getView().getViewMenu().update();
        this.getView().dispose();
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.getViewMenu().getProject();
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.getView().getViewMenu();
    }
    
    @Override
    public ViewEdit getView() {
        return (ViewEdit) this.viewModal;
    }
}