package view.panel.edit.base.product;

import java.awt.Dimension;
import model.structural.base.product.Relationship;
import view.panel.base.product.PanelBaseRelationship;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditRelationship</b>.</p> 
 * <p>Class responsible for defining a <b>Relationship Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-14
 * @see    model.structural.base.product.Relationship
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditRelationship extends PanelEdit {
    private final Relationship relationship;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param relationship Relationship.
     */
    public PanelEditRelationship(ViewMenu view, Relationship relationship) {
        super(view);
        this.relationship = relationship;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseRelationship();
    }
    
    /**
     * Method responsible for adding the Panel Base Relationship.
     */
    private void addPanelBaseRelationship() {
        this.addPanel("panelBaseRelationship", new PanelBaseRelationship(this.viewMenu, this.relationship));
        this.createScrollPane("scrollPanelBaseRelationship",  this.getPanelBaseRelationship());
        this.getScrollPane("scrollPanelBaseRelationship").setViewportView(this.getPanelBaseRelationship());
        this.tabbedPane.add("Relationship", this.getScrollPane("scrollPanelBaseRelationship"));
    }
    
    /**
     * Method responsible for returning the Panel Base Relationship.
     * @return Panel Base Relationship.
     */
    public PanelBaseRelationship getPanelBaseRelationship() {
        return (PanelBaseRelationship) this.getPanel("panelBaseRelationship");
    }
    
    /**
     * Method responsible for returning the Relationship.
     * @return Relationship.
     */
    public Relationship getRelationship() {
        return this.relationship;
    }
}