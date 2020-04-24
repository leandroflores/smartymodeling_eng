package controller.view.modal.edit.diagram.classes.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.classes.ViewEditAttributeUML;

/**
 * <p>Class of Controller <b>ControllerViewEditAttributeUML</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditAttributeUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-06
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    view.modal.edit.diagram.classes.ViewEditAttributeUML
 */
public class ControllerViewEditAttributeUML extends ControllerViewEdit  {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Attribute UML.
     */
    public ControllerViewEditAttributeUML(ViewEditAttributeUML viewEdit) {
        super(viewEdit);
    }

    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseAttributeUML().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditAttributeUML getView() {
        return (ViewEditAttributeUML) this.viewModal;
    }
}