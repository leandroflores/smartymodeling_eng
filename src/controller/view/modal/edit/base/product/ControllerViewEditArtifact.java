package controller.view.modal.edit.base.product;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.product.ViewEditArtifact;

/**
 * <p>Class of Controller <b>ControllerViewEditArtifact</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditArtifact</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.product.Artifact
 * @see    view.modal.edit.base.product.ViewEditArtifact
 */
public class ControllerViewEditArtifact extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Artifact.
     */
    public ControllerViewEditArtifact(ViewEditArtifact view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditArtifact getView() {
        return (ViewEditArtifact) this.viewModal;
    }
}