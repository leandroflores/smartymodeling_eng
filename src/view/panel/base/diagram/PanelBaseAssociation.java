package view.panel.base.diagram;

import controller.view.panel.base.diagram.ControllerPanelBaseAssociation;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import model.structural.base.association.Association;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAssociation</b>.</p>
 * <p>Class responsible for defining a <b>Association Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    controller.view.panel.base.diagram.ControllerPanelBaseAssociation
 * @see    model.structural.base.association.Association
 * @see    view.panel.base.PanelBaseAssociation
 */
public final class PanelBaseAssociation extends view.panel.base.PanelBaseAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param association Association.
     */
    public PanelBaseAssociation(ViewMenu view, Diagram diagram, Association association) {
        super(view, diagram, association);
        this.controller = new ControllerPanelBaseAssociation(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(3, 2));
        super.setDefaultProperties();
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: ", 120));
        this.add(this.createTextFieldNoEditable("sourceTextField", this.getAssociation().getSource().getName(), 20));
        
        this.add(this.createLabel("Target: ", 120));
        this.add(this.createTextFieldNoEditable("targetTextField", this.getAssociation().getTarget().getName(), 20));
        
        this.add(this.createLabel("Type: ", 120));
        this.add(this.createTextFieldNoEditable("typeTextField", this.getAssociation().getType(), 20));
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
    
    /**
     * Method responsible for returning the Type Text Field.
     * @return Type Text Field.
     */
    public JTextField getTypeTextField() {
        return this.getTextField("typeTextField");
    }
}