package controller.view.modal.edit.diagram.sequence.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.sequence.base.ViewEditInstanceUML;

/**
 * <p>Class of Controller <b>ControllerViewEditInstanceUML</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditInstanceUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.sequence.base.InstanceUML
 * @see    view.modal.edit.diagram.sequence.base.ViewEditInstanceUML
 */
public class ControllerViewEditInstanceUML extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Instance UML.
     */
    public ControllerViewEditInstanceUML(ViewEditInstanceUML view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseInstanceUML().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditInstanceUML getView() {
        return (ViewEditInstanceUML) super.getView();
    }
}