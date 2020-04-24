package controller.view.modal.edit.diagram.classes.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.classes.base.ViewEditInterfaceUML;

/**
 * <p>Class of Controller <b>ControllerViewEditInterfaceUML</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditInterfaceUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.classes.base.InterfaceUML
 * @see    view.modal.edit.diagram.classes.base.ViewEditInterfaceUML
 */
public class ControllerViewEditInterfaceUML extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Interface UML.
     */
    public ControllerViewEditInterfaceUML(ViewEditInterfaceUML viewEdit) {
        super(viewEdit);
    }

    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseInterfaceUML().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditInterfaceUML getView() {
        return (ViewEditInterfaceUML) this.viewModal;
    }
}