package controller.view.modal.new_;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.new_.ViewNew;
import view.panel.project.tree.PanelTree;
import view.structural.ViewMenu;

/**
 * <p>Class of Controller <b>ControllerViewNew</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNew</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.new_.ViewNew
 */
public abstract class ControllerViewNew extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View New.
     */
    public ControllerViewNew(ViewNew view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.getView().getInsertButton().equals(event.getSource()))
            this.insert();
        else if (this.getView().getBackButton().equals(event.getSource()))
            this.getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
                this.insert();
                break;
            case F2:
                this.getView().dispose();
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for Insert a New Object.
     */
    private void insert() {
        if (this.check()) {
            this.new_();
            this.close();
        }
    }
    
    /**
     * Abstract method responsible for Checking Values.
     * @return Values checked.
     */
    public abstract boolean check();
    
    /**
     * Abstract Method responsible for creating a New Object.
     */
    public abstract void new_();
    
    /**
     * Method responsible for closing the View New.
     */
    protected void close() {
        this.getView().getViewMenu().setSave(false);
        this.getView().getViewMenu().update();
        this.getView().dispose();
    }
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    protected PanelTree getPanelTree() {
        return this.getViewMenu().getPanelProject().getPanelTree();
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    protected ViewMenu getViewMenu() {
        return this.getView().getViewMenu();
    }
    
    @Override
    public ViewNew getView() {
        return (ViewNew) this.viewModal;
    }
}