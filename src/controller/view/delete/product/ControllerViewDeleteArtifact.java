package controller.view.delete.product;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.product.Artifact;
import view.delete.product.ViewDeleteArtifact;

/**
 * <p>Class of Controller <b>ControllerViewDeleteArtifact</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewDeleteArtifact</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/10/2019
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.product.Artifact
 * @see    view.delete.product.ViewDeleteArtifact
 */
public class ControllerViewDeleteArtifact extends ControllerViewDelete {
    private final ViewDeleteArtifact viewDeleteArtifact;
    private final Artifact artifact;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Artifact.
     */
    public ControllerViewDeleteArtifact(ViewDeleteArtifact viewDelete) {
        super(viewDelete);
        this.viewDeleteArtifact = viewDelete;
        this.artifact           = viewDelete.getArtifact();
    }
    
    @Override
    public void delete() {
        this.viewDeleteArtifact.getInstance().removeArtifact(this.artifact);
        this.viewDeleteArtifact.getInstance().getProduct().updateInstances();
        this.viewDeleteArtifact.getPanelModeling().updateInstancePanels();
        this.viewDeleteArtifact.getPanelModeling().getViewMenu().update();
        this.viewDeleteArtifact.dispose();
    }
}