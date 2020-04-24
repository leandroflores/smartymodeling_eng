package controller.view.modal.edit.diagram.classes.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.classes.ViewEditPackageUML;

/**
 * <p>Class of Controller <b>ControllerViewEditPackageUML</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditPackageUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.classes.base.PackageUML
 * @see    view.modal.edit.diagram.classes.ViewEditPackageUML
 */
public class ControllerViewEditPackageUML extends ControllerViewEdit  {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Package UML.
     */
    public ControllerViewEditPackageUML(ViewEditPackageUML viewEdit) {
        super(viewEdit);
    }

    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBasePackageUML().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditPackageUML getView() {
        return (ViewEditPackageUML) this.viewModal;
    }
}