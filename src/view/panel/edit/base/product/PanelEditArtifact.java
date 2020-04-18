package view.panel.edit.base.product;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.product.Artifact;
import view.panel.base.product.PanelBaseArtifact;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

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
     * @param viewMenu View Menu.
     * @param artifact Artifact.
     */
    public PanelEditArtifact(ViewMenu viewMenu, Artifact artifact) {
        super(viewMenu);
        this.artifact = artifact;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
            this.addPanelBaseArtifact();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Artifact.
     */
    private void addPanelBaseArtifact() {
        this.addPanel("panelBaseArtifact", new PanelBaseArtifact(this.viewMenu, this.artifact));
        this.createScrollPane("scrollPanelBaseArtifact",  this.getPanelBaseArtifact());
        this.getScrollPanelBaseArtifact().setViewportView(this.getPanelBaseArtifact());
        this.tabbedPane.add("Artifact", this.getScrollPanelBaseArtifact());
    }
    
    /**
     * Method responsible for returning the Panel Base Artifact.
     * @return Panel Base Artifact.
     */
    public PanelBaseArtifact getPanelBaseArtifact() {
        return (PanelBaseArtifact) this.getPanel("panelBaseArtifact");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Artifact.
     * @return Scroll Panel Base Artifact.
     */
    public JScrollPane getScrollPanelBaseArtifact() {
        return this.getScrollPane("scrollPanelBaseArtifact");
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return this.artifact;
    }
}