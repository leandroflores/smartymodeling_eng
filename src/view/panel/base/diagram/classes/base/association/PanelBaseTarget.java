package view.panel.base.diagram.classes.base.association;

import controller.view.panel.base.diagram.classes.base.association.ControllerPanelBaseTarget;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.base.PanelBaseAssociation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseTarget</b>.</p> 
 * <p>Class responsible for defining a <b>Association UML Target Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-15
 * @see    controller.view.panel.base.diagram.classes.base.association.ControllerPanelBaseTarget
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.panel.base.PanelBaseAssociation
 */
public final class PanelBaseTarget extends PanelBaseAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param association Association UML.
     */
    public PanelBaseTarget(ViewMenu view, ClassDiagram diagram, AssociationUML association) {
        super(view, diagram, association);
        this.controller = new ControllerPanelBaseTarget(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setValues();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(4, 2));
        super.setDefaultProperties();
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Target: ", 120));
        this.add(this.createTextFieldNoEditable("targetTextField", "", 20));
        
        this.add(this.createLabel("Visibility: ", 120));
        this.add(this.createComboBox("visibilityComboBox", this.getDiagram().getVisibilities(), 30, this.getAssociation().getTargetVisibility()));
        
        this.add(this.createLabel("Name: ", 120));
        this.add(this.createTextField("nameTextField", this.getAssociation().getTargetName(), 20));
        
        this.add(this.createLabel("Cardinality: ", 120));
        this.add(this.createTextField("cardinalityTextField", "", 20));
    }
    
    /**
     * Method responsible for setting the Target Association Values.
     */
    public void setValues() {
        this.getTargetTextField().setText(this.getAssociation().getTarget().getName());
        this.getVisibilityComboBox().setSelectedItem(this.getAssociation().getTargetVisibility());
        this.getNameTextField().setText(this.getAssociation().getTargetName());
        this.getCardinalityTextField().setText(this.getAssociation().getCardinalityTargetLabel());
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return this.getTextField("targetTextField");
    }
    
    /**
     * Method responsible for returning the Visibility Combo Box.
     * @return Visibility Combo Box.
     */
    public JComboBox getVisibilityComboBox() {
        return this.getComboBox("visibilityComboBox");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Cardinality Text Field.
     * @return Cardinality Text Field.
     */
    public JTextField getCardinalityTextField() {
        return this.getTextField("cardinalityTextField");
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    @Override
    public AssociationUML getAssociation() {
        return (AssociationUML) this.association;
    }
}