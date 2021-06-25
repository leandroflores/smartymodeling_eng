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
        controller = new ControllerPanelBaseTarget(this);
        setDefaultProperties();
        addComponents();
        setValues();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        super.setDefaultProperties();
        setLayout(new GridLayout(4, 2));
        setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Target: ", 120));
        add(createTextFieldNoEditable("target", "", 20));
        
        add(createLabel("Visibility: ", 120));
        add(createComboBox("visibility", getDiagram().getVisibilities(), 30, getAssociation().getTargetVisibility()));
        
        add(createLabel("Name: ", 120));
        add(createTextField("name", getAssociation().getTargetName(), 20));
        
        add(createLabel("Cardinality: ", 120));
        add(createTextField("cardinality", "", 20));
    }
    
    /**
     * Method responsible for setting the Target Association Values.
     */
    public void setValues() {
        getTargetTextField().setText(getAssociation().getTarget().getName());
        getVisibilityComboBox().setSelectedItem(getAssociation().getTargetVisibility());
        getNameTextField().setText(getAssociation().getTargetName());
        getCardinalityTextField().setText(getAssociation().getCardinalityTargetLabel());
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return getTextField("target");
    }
    
    /**
     * Method responsible for returning the Visibility Combo Box.
     * @return Visibility Combo Box.
     */
    public JComboBox getVisibilityComboBox() {
        return getComboBox("visibility");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Cardinality Text Field.
     * @return Cardinality Text Field.
     */
    public JTextField getCardinalityTextField() {
        return getTextField("cardinality");
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
    
    @Override
    public AssociationUML getAssociation() {
        return (AssociationUML) association;
    }
}