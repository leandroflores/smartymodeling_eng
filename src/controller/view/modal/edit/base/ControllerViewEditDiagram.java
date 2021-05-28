package controller.view.modal.edit.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.ViewEditDiagram;

/**
 * <p>Class of Controller <b>ControllerViewEditDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-29
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.Diagram
 * @see    view.modal.edit.base.ViewEditDiagram
 */
public class ControllerViewEditDiagram extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Diagram.
     */
    public ControllerViewEditDiagram(ViewEditDiagram view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseDiagram().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditDiagram getView() {
        return (ViewEditDiagram) super.getView();
    }
}