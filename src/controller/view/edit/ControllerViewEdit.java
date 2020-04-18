package controller.view.edit;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Project;
import view.edit.ViewEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of Controller <b>ControllerViewEdit</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEdit</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.ControllerViewModal
 * @see    view.edit.ViewEdit
 */
public abstract class ControllerViewEdit extends ControllerViewModal {
    protected ViewEdit viewEdit;
    
    /**
     * Default constructor method of Class.
     * @param view View Edit.
     */
    public ControllerViewEdit(ViewEdit view) {
        super(view);
        this.viewEdit = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewEdit.getSaveButton().equals(event.getSource()))
            this.save();
        else if (this.viewEdit.getCancelButton().equals(event.getSource()))
            this.viewEdit.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            this.save();
        else if (F2 == event.getKeyCode())
            this.viewEdit.dispose();
    }
    
    /**
     * Abstract Method responsible for saving the Edit.
     */
    public void save() {
        if (this.check())
            this.update();
    }
    
    /**
     * Abstract method responsible for checking the values.
     * @return Values checked.
     */
    public abstract boolean check();
    
    /**
     * Abstract Method responsible for updating the values.
     */
    public abstract void update();
    
    /**
     * Method responsible for closing the View Edit.
     */
    protected void close() {
        this.viewEdit.getViewMenu().setSave(false);
        this.viewEdit.getViewMenu().update();
        this.viewEdit.dispose();
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