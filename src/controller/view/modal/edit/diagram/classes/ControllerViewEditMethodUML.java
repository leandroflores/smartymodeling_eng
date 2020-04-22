package controller.view.modal.edit.diagram.classes;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.classes.ViewEditMethodUML;

/**
 * <p>Class of Controller <b>ControllerViewEditMethodUML</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditMethodUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-06
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.modal.edit.diagram.classes.ViewEditMethodUML
 */
public class ControllerViewEditMethodUML extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Method UML.
     */
    public ControllerViewEditMethodUML(ViewEditMethodUML viewEdit) {
        super(viewEdit);
    }

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void save() {
        this.close();
    }
}