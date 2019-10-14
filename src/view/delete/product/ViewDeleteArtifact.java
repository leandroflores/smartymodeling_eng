package view.delete.product;

import controller.view.delete.product.ControllerViewDeleteArtifact;
import model.structural.base.Project;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import view.ViewStyle;
import view.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteArtifact</b>.</p>
 * <p>Class responsible for defining the <b>Artifact Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/10/2019
 * @see    controller.view.delete.product.ControllerViewDeleteArtifact
 * @see    model.structural.base.product.Artifact
 * @see    view.delete.ViewDelete
 */
public final class ViewDeleteArtifact extends ViewDelete {
    private final Project  project;
    private final Instance instance;
    private final Artifact artifact;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param artifact Artifact.
     */
    public ViewDeleteArtifact(PanelModeling panel, Artifact artifact) {
        super(panel);
        this.project    = this.view.getProject();
        this.instance   = artifact.getInstance();
        this.artifact   = artifact;
        this.controller = new ControllerViewDeleteArtifact(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Delete Artifact");
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.artifact.getElement().getName());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return this.artifact;
    }
}