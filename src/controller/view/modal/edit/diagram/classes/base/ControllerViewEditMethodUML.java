package controller.view.modal.edit.diagram.classes.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.classes.base.ViewEditMethodUML;

/**
 * <p>Class of Controller <b>ControllerViewEditMethodUML</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditMethodUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-06
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.modal.edit.diagram.classes.base.ViewEditMethodUML
 */
public class ControllerViewEditMethodUML extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Method UML.
     */
    public ControllerViewEditMethodUML(ViewEditMethodUML view) {
        super(view);
    }

    @Override
    public boolean check() {
        return check(getView().getPanelBaseMethodUML().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditMethodUML getView() {
        return (ViewEditMethodUML) super.getView();
    }
}