package view.panel.base.product;

import controller.view.panel.base.product.ControllerPanelBaseRelationship;
import java.awt.GridLayout;
import model.structural.base.product.Relationship;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseRelationship</b>.</p> 
 * <p>Class responsible for defining a <b>Relationship Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-14
 * @see    controller.view.panel.base.product.ControllerPanelBaseRelationship
 * @see    model.structural.base.product.Relationship
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseRelationship extends PanelBase {
    private final Relationship relationship;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param relationship Relationship.
     */
    public PanelBaseRelationship(ViewMenu view, Relationship relationship) {
        super(view);
        this.relationship = relationship;
        this.controller   = new ControllerPanelBaseRelationship(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product: "));
        this.add(this.createTextFieldNoEditable("productTextField",  this.relationship.getInstance().getProduct().getName(), 15));
        
        this.add(this.createLabel("Instance: "));
        this.add(this.createTextFieldNoEditable("instanceTextField", this.relationship.getInstance().getName(), 15));
        
        this.add(this.createLabel("Diagram: "));
        this.add(this.createTextFieldNoEditable("diagramTextField",  this.relationship.getInstance().getDiagram().toString(), 15));
        
        this.add(this.createLabel("Source: "));
        this.add(this.createTextFieldNoEditable("sourceTextField",  this.relationship.getAssociation().getSource().getName(), 15));
        
        this.add(this.createLabel("Target: "));
        this.add(this.createTextFieldNoEditable("targetTextField",  this.relationship.getAssociation().getTarget().getName(), 15));
    }
    
    /**
     * Method responsible for returning the Relationship.
     * @return Relationship.
     */
    public Relationship getRelationship() {
        return this.relationship;
    }
}