package view.panel.edit.base.product;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.product.Relationship;
import view.edit.panel.base.product.PanelBaseRelationship;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditRelationship</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Relationship</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/11/2019
 * @see    model.structural.base.product.Relationship
 * @see    view.edit.panel.base.product.PanelBaseRelationship
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditRelationship extends PanelEdit {
    private final Project  project;
    private final Relationship relationship;
    private PanelBaseRelationship panelBaseRelationship;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param relationship Relationship.
     */
    public PanelEditRelationship(ViewMenu viewMenu, Relationship relationship) {
        super(viewMenu);
        this.project      = this.viewMenu.getProject();
        this.relationship = relationship;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseRelationship();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Relationship.
     */
    private void addPanelBaseRelationship() {
        this.panelBaseRelationship = new PanelBaseRelationship(this.viewMenu, this.relationship);
        this.createScrollPane("scrollPanelBaseRelationship",  this.panelBaseRelationship);
        this.getScrollPanelBaseRelationship().setViewportView(this.panelBaseRelationship);
        this.tabbedPane.add("Relationship", this.getScrollPanelBaseRelationship());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Relationship.
     * @return Relationship.
     */
    public Relationship getRelationship() {
        return this.relationship;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Relationship.
     * @return Scroll Panel Base Relationship.
     */
    public JScrollPane getScrollPanelBaseRelationship() {
        return this.scrollPanes.get("scrollPanelBaseRelationship");
    }
}