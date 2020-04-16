package view.edit.panel.base.product;

import controller.view.edit.panel.base.product.ControllerPanelBaseRelationship;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.product.Relationship;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseRelationship</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Relationship Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/11/2019
 * @see    controller.view.edit.panel.base.product.ControllerPanelBaseRelationship
 * @see    model.structural.base.product.Relationship
 * @see    view.panel.Panel
 */
public final class PanelBaseRelationship extends Panel {
    private final ViewMenu viewMenu;
    private final Relationship relationship;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param relationship Relationship.
     */
    public PanelBaseRelationship(ViewMenu view, Relationship relationship) {
        this.viewMenu     = view;
        this.relationship = relationship;
        this.controller   = new ControllerPanelBaseRelationship(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product: "));
        this.add(this.createTextFieldNoEditable("productTextField",  "", 15));
        
        this.add(this.createLabel("Instance: "));
        this.add(this.createTextFieldNoEditable("instanceTextField", "", 15));
        
        this.add(this.createLabel("Diagram: "));
        this.add(this.createTextFieldNoEditable("diagramTextField",  "", 15));
        
        this.add(this.createLabel("Source: "));
        this.add(this.createTextFieldNoEditable("sourceTextField",  "", 15));
        
        this.add(this.createLabel("Target: "));
        this.add(this.createTextFieldNoEditable("targetTextField",  "", 15));
    }
    
    /**
     * Method responsible for setting the Relationship Values.
     */
    public void setValues() {
        this.getProductTextField().setText(this.relationship.getInstance().getProduct().getName());
        this.getInstanceTextField().setText(this.relationship.getInstance().getName());
        this.getDiagramTextField().setText(this.relationship.getInstance().getDiagram().toString());
        this.getSourceTextField().setText(this.relationship.getAssociation().getSource().getName());
        this.getTargetTextField().setText(this.relationship.getAssociation().getTarget().getName());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Relationship.
     * @return Relationship.
     */
    public Relationship getRelationship() {
        return this.relationship;
    }
    
    /**
     * Method responsible for returning the Product Text Field.
     * @return Product Text Field.
     */
    public JTextField getProductTextField() {
        return this.getTextField("productTextField");
    }
    
    /**
     * Method responsible for returning the Instance Text Field.
     * @return Instance Text Field.
     */
    public JTextField getInstanceTextField() {
        return this.getTextField("instanceTextField");
    }
    
    /**
     * Method responsible for returning the Diagram Text Field.
     * @return Diagram Text Field.
     */
    public JTextField getDiagramTextField() {
        return this.getTextField("diagramTextField");
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return this.getTextField("sourceTextField");
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return this.getTextField("targetTextField");
    }
}