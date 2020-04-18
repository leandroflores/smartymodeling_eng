package controller.view.edit.base;

import view.edit.base.ViewEditDiagram;

/**
 * <p>Class of Controller <b>ControllerViewEditDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEditDiagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    controller.view.edit.base.ControllerViewEdit
 * @see    view.edit.base.ViewEditDiagram
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

    /**
     * Method responsible for checking the Diagram Name.
     * @return Name checked.
     */
    private boolean checkName() {
        return this.check(this.viewEditDiagram.getPanelBaseDiagram().getNameTextField(), "Name is a required field!");
    }
    
    @Override
    public boolean check() {
        return this.checkName();
    }

    @Override
    public void save() {
        this.viewEditDiagram.getDiagram().setName(this.viewEditDiagram.getPanelBaseDiagram().getNameTextField().getText());
        this.close();
    }
}