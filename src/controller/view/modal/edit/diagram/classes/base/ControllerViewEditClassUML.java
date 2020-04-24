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
     * @param viewEdit View Edit Class UML.
     */
    public ControllerViewEditClassUML(ViewEditClassUML viewEdit) {
        super(viewEdit);
    }

    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseClassUML().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditClassUML getView() {
        return (ViewEditClassUML) this.viewModal;
    }
}