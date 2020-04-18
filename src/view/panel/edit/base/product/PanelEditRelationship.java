package view.panel.edit.base.product;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.product.Relationship;
import view.panel.base.product.PanelBaseRelationship;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

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
     * @param viewMenu View Menu.
     * @param relationship Relationship.
     */
    public PanelEditRelationship(ViewMenu viewMenu, Relationship relationship) {
        super(viewMenu);
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
        this.addPanel("panelBaseRelationship", new PanelBaseRelationship(this.viewMenu, this.relationship));
        this.createScrollPane("scrollPanelBaseRelationship",  this.getPanelBaseRelationship());
        this.getScrollPanelBaseRelationship().setViewportView(this.getPanelBaseRelationship());
        this.tabbedPane.add("Relationship", this.getScrollPanelBaseRelationship());
    }
    
    /**
     * Method responsible for returning the Panel Base Relationship.
     * @return Panel Base Relationship.
     */
    public PanelBaseRelationship getPanelBaseRelationship() {
        return (PanelBaseRelationship) this.getPanel("panelBaseRelationship");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Relationship.
     * @return Scroll Panel Base Relationship.
     */
    public JScrollPane getScrollPanelBaseRelationship() {
        return this.getScrollPane("scrollPanelBaseRelationship");
    }
    
    /**
     * Method responsible for returning the Relationship.
     * @return Relationship.
     */
    public Relationship getRelationship() {
        return this.relationship;
    }
}