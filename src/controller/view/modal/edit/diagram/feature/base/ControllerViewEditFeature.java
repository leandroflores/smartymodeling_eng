package controller.view.modal.edit.diagram.feature.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.diagram.feature.base.ViewEditFeature;

/**
 * <p>Class of Controller <b>ControllerViewEditFeature</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditFeature</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.diagram.feature.base.Feature
 * @see    view.modal.edit.diagram.feature.base.ViewEditFeature
 */
public class ControllerViewEditFeature extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Feature.
     */
    public ControllerViewEditFeature(ViewEditFeature view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseFeature().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditFeature getView() {
        return (ViewEditFeature) super.getView();
    }
}