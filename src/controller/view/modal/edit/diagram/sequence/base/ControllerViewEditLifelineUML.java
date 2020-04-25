package controller.view.modal.edit.diagram.sequence.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.sequence.base.ViewEditLifelineUML;

/**
 * <p>Class of Controller <b>ControllerViewEditLifelineUML</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditLifelineUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.sequence.base.LifelineUML
 * @see    view.modal.edit.diagram.sequence.base.ViewEditLifelineUML
 */
public class ControllerViewEditLifelineUML extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Lifeline UML.
     */
    public ControllerViewEditLifelineUML(ViewEditLifelineUML view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseLifelineUML().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditLifelineUML getView() {
        return (ViewEditLifelineUML) this.viewModal;
    }
}