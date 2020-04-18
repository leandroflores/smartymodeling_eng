package controller.view.edit.base;

import controller.view.edit.ControllerViewEdit;
import view.edit.base.ViewEditElement;

/**
 * <p>Class of Controller <b>ControllerViewEditElement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditElement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-30
 * @see    controller.view.edit.ControllerViewEdit
 * @see    model.structural.base.Element
 * @see    view.edit.base.ViewEditElement
 */
public class ControllerViewEditElement extends ControllerViewEdit  {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Element.
     */
    public ControllerViewEditElement(ViewEditElement viewEdit) {
        super(viewEdit);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseElement().getNameTextField(), "Name is a required!");
    }

    @Override
    public void update() {
//        this.getView().getElement().setName(this.getString(this.getView().getPanelBaseElement().getNameTextField()));
//        this.getView().getElement().setMandatory(this.getView().getPanelBaseElement().getMandatoryCheckBox().isSelected());
//        this.getView().getDiagram().updateStereotype(this.getView().getElement());
        this.close();
    }
    
    @Override
    public ViewEditElement getView() {
        return (ViewEditElement) this.viewModal;
    }
}