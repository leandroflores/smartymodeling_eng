package view.panel.edit.base.product;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.product.Artifact;
import view.edit.panel.base.product.PanelBaseArtifact;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditArtifact</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Artifact</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/10/2019
 * @see    model.structural.base.product.Artifact
 * @see    view.edit.panel.base.product.PanelBaseArtifact
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditArtifact extends PanelEdit {
    private final Project  project;
    private final Artifact artifact;
    private PanelBaseArtifact panelBaseArtifact;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param artifact Artifact.
     */
    public PanelEditArtifact(ViewMenu viewMenu, Artifact artifact) {
        super(viewMenu);
        this.project  = this.viewMenu.getProject();
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
        this.panelBaseArtifact = new PanelBaseArtifact(this.viewMenu, this.artifact);
        this.createScrollPane("scrollPanelBaseArtifact",  this.panelBaseArtifact);
        this.getScrollPanelBaseArtifact().setViewportView(this.panelBaseArtifact);
        this.tabbedPane.add("Artifact", this.getScrollPanelBaseArtifact());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return this.artifact;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Artifact.
     * @return Scroll Panel Base Artifact.
     */
    public JScrollPane getScrollPanelBaseArtifact() {
        return this.getScrollPane("scrollPanelBaseArtifact");
    }
}