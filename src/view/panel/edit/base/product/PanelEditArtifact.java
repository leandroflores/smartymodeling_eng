package view.panel.edit.base.product;

import java.awt.Dimension;
import model.structural.base.product.Artifact;
import view.panel.base.product.PanelBaseArtifact;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditArtifact</b>.</p> 
 * <p>Class responsible for defining a <b>Artifact Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-25
 * @see    model.structural.base.product.Artifact
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditArtifact extends PanelEdit {
    private final Artifact artifact;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param artifact Artifact.
     */
    public PanelEditArtifact(ViewMenu view, Artifact artifact) {
        super(view);
        this.artifact = artifact;
        setPreferredSize(new Dimension(200, 100));
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseArtifact();
    }
    
    /**
     * Method responsible for adding the Panel Base Artifact.
     */
    private void addPanelBaseArtifact() {
        addPanel("base_artifact", new PanelBaseArtifact(viewMenu, artifact));
        createScrollPane("base_artifact", getPanelBaseArtifact());
        getScrollPane("base_artifact").setViewportView(getPanelBaseArtifact());
        tabbedPane.add("Artifact", getScrollPane("base_artifact"));
    }
    
    /**
     * Method responsible for returning the Panel Base Artifact.
     * @return Panel Base Artifact.
     */
    public PanelBaseArtifact getPanelBaseArtifact() {
        return (PanelBaseArtifact) getPanel("base_artifact");
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return artifact;
    }
}