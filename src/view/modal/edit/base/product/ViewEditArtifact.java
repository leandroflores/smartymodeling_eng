package view.modal.edit.base.product;

import controller.view.modal.edit.base.product.ControllerViewEditArtifact;
import java.awt.Dimension;
import model.structural.base.product.Artifact;
import view.modal.edit.ViewEdit;
import view.panel.base.product.PanelBaseArtifact;
import view.panel.edit.base.product.PanelEditArtifact;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditArtifact</b>.</p>
 * <p>Class responsible for defining the <b>Artifact Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    controller.view.modal.edit.base.product.ControllerViewEditArtifact
 * @see    model.structural.base.product.Artifact
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditArtifact extends ViewEdit {
    private final Artifact artifact;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param artifact Artifact.
     */
    public ViewEditArtifact(PanelModeling panel, Artifact artifact) {
        super(panel.getViewMenu());
        this.artifact   = artifact;
        this.controller = new ControllerViewEditArtifact(this);
        this.title      = "Edit Artifact Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 350);
    }
    
    @Override
    protected PanelEditArtifact createPanelEdit() {
        return new PanelEditArtifact(view, artifact);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 225);
    }
    
    @Override
    public PanelEditArtifact getPanelEdit() {
        return (PanelEditArtifact) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Artifact.
     * @return Panel Base Artifact.
     */
    public PanelBaseArtifact getPanelBaseArtifact() {
        return getPanelEdit().getPanelBaseArtifact();
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return artifact;
    }
}