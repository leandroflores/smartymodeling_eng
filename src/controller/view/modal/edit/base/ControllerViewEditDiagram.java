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
    private final ViewEditDiagram viewEditDiagram;

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Diagram.
     */
    public ControllerViewEditDiagram(ViewEditDiagram viewEdit) {
        super(viewEdit);
        this.viewEditDiagram = viewEdit;
    }
    
    @Override
    public boolean check() {
        return this.check(this.viewEditDiagram.getPanelBaseDiagram().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
//        this.getView().getDiagram().setName(this.getString(this.getView().getPanelBaseDiagram().getNameTextField()));
        this.close();
    }
    
    @Override
    public ViewEditDiagram getView() {
        return (ViewEditDiagram) this.viewModal;
    }
}