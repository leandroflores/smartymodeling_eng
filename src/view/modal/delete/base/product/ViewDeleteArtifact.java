package view.modal.delete.base.product;

import controller.view.modal.delete.base.product.ControllerViewDeleteArtifact;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import view.modal.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteArtifact</b>.</p>
 * <p>Class responsible for defining the <b>Artifact Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-14
 * @see    controller.view.modal.delete.base.product.ControllerViewDeleteArtifact
 * @see    model.structural.base.product.Artifact
 * @see    view.modal.delete.ViewDelete
 */
public final class ViewDeleteArtifact extends ViewDelete {
    private final Instance instance;
    private final Artifact artifact;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param artifact Artifact.
     */
    public ViewDeleteArtifact(PanelModeling panel, Artifact artifact) {
        super(panel);
        this.instance   = artifact.getInstance();
        this.artifact   = artifact;
        this.controller = new ControllerViewDeleteArtifact(this);
        this.title      = "Delete Artifact";
        initComponents();
        addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(artifact.getElement().getName());
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return artifact;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return instance;
    }
}