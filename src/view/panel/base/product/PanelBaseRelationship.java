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
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Product: "));
        add(createTextFieldNoEditable("product", relationship.getInstance().getProduct().getName(), 15));
        
        add(createLabel("Instance: "));
        add(createTextFieldNoEditable("instance", relationship.getInstance().getName(), 15));
        
        add(createLabel("Diagram: "));
        add(createTextFieldNoEditable("diagram", relationship.getInstance().getDiagram().toString(), 15));
        
        add(createLabel("Source: "));
        add(createTextFieldNoEditable("source", relationship.getAssociation().getSource().getName(), 15));
        
        add(createLabel("Target: "));
        add(createTextFieldNoEditable("target", relationship.getAssociation().getTarget().getName(), 15));
    }
    
    /**
     * Method responsible for returning the Relationship.
     * @return Relationship.
     */
    public Relationship getRelationship() {
        return relationship;
    }
}