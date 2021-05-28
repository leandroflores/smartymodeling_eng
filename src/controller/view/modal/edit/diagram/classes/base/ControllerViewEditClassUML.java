package controller.view.modal.edit.diagram.classes.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.classes.base.ViewEditClassUML;

/**
 * <p>Class of Controller <b>ControllerViewEditClassUML</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditClassUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    view.modal.edit.diagram.classes.base.ViewEditClassUML
 */
public class ControllerViewEditClassUML extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Class UML.
     */
    public ControllerViewEditClassUML(ViewEditClassUML view) {
        super(view);
    }

    @Override
    public boolean check() {
        return check(getView().getPanelBaseClassUML().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditClassUML getView() {
        return (ViewEditClassUML) super.getView();
    }
}