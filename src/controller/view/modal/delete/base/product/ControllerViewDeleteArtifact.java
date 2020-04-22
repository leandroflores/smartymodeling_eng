package controller.view.modal.delete.base.product;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.product.Artifact;
import view.modal.delete.base.product.ViewDeleteArtifact;

/**
 * <p>Class of Controller <b>ControllerViewDeleteArtifact</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteArtifact</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-14
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.product.Artifact
 * @see    view.modal.delete.base.product.ViewDeleteArtifact
 */
public class ControllerViewDeleteArtifact extends ControllerViewDelete {
    private final Artifact artifact;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Artifact.
     */
    public ControllerViewDeleteArtifact(ViewDeleteArtifact viewDelete) {
        super(viewDelete);
        this.artifact = viewDelete.getArtifact();
    }
    
    @Override
    public void delete() {
        this.getView().getInstance().removeArtifact(this.artifact);
        this.getView().getInstance().getProduct().updateInstances();
        this.getView().getPanelModeling().updateInstance(this.getView().getInstance());
        this.close();
    }
    
    @Override
    public ViewDeleteArtifact getView() {
        return (ViewDeleteArtifact) this.viewModal;
    }
}