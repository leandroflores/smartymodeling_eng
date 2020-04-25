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
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 350);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditArtifact();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Artifact.
     */
    private void addPanelEditArtifact() {
        this.addPanel("panelEditArtifact", new PanelEditArtifact(this.view, this.artifact));
        this.getPanelEditArtifact().setPreferredSize(new Dimension(500, 225));
        this.add(this.getPanelEditArtifact());
    }
    
    /**
     * Method responsible for returning the Panel Edit Artifact.
     * @return Panel Edit Artifact.
     */
    private PanelEditArtifact getPanelEditArtifact() {
        return (PanelEditArtifact) this.getPanel("panelEditArtifact");
    }
    
    /**
     * Method responsible for returning the Panel Base Artifact.
     * @return Panel Base Artifact.
     */
    public PanelBaseArtifact getPanelBaseArtifact() {
        return this.getPanelEditArtifact().getPanelBaseArtifact();
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return this.artifact;
    }
}