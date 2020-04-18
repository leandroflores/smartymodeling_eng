package controller.view.edit.base;

import view.edit.base.ViewEditElement;

/**
 * <p>Class of Controller <b>ControllerViewEditElement</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEditElement</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  30/05/2019
 * @see    controller.view.edit.base.ControllerViewEdit
 * @see    view.edit.base.ViewEditElement
 */
public class ControllerViewEditElement extends ControllerViewEdit  {
    private final ViewEditElement viewEditElement;

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Element.
     */
    public ControllerViewEditElement(ViewEditElement viewEdit) {
        super(viewEdit);
        this.viewEditElement = viewEdit;
    }

    /**
     * Method responsible for checking the Element Name.
     * @return Name checked.
     */
    private boolean checkName() {
        return this.check(this.viewEditElement.getPanelBaseElement().getNameTextField(), "Name is a required field!");
    }
    
    @Override
    public boolean check() {
        return this.checkName();
    }

    @Override
    public void save() {
        this.viewEditElement.getElement().setName(this.viewEditElement.getPanelBaseElement().getNameTextField().getText());
        this.viewEditElement.getElement().setMandatory(this.viewEditElement.getPanelBaseElement().getMandatoryCheckBox().isSelected());
        this.viewEditElement.getDiagram().updateStereotype(this.viewEditElement.getElement());
        this.close();
    }
}