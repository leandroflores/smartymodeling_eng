package view.panel.base.diagram.classes.base.association;

import controller.view.panel.base.diagram.classes.base.association.ControllerPanelBaseSource;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.base.PanelBaseAssociation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseSource</b>.</p> 
 * <p>Class responsible for defining a <b>Association UML Source Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-15
 * @see    controller.view.panel.base.diagram.classes.base.association.ControllerPanelBaseSource
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.panel.base.PanelBaseAssociation
 */
public final class PanelBaseSource extends PanelBaseAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param association Association UML.
     */
    public PanelBaseSource(ViewMenu view, ClassDiagram diagram, AssociationUML association) {
        super(view, diagram, association);
        controller = new ControllerPanelBaseSource(this);
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
        add(createLabel("Source: ", 120));
        add(createTextFieldNoEditable("source", "", 20));
        
        add(createLabel("Visibility: ", 120));
        add(createComboBox("visibility", getDiagram().getVisibilities(), 30));
        getVisibilityComboBox().setEnabled(!getAssociation().isDirection());
        
        add(createLabel("Name: ", 120));
        add(createTextField("name", "", 20));
        getNameTextField().setEnabled(!getAssociation().isDirection());
        
        add(createLabel("Cardinality: ", 120));
        add(createTextField("cardinality", "", 20));
        getCardinalityTextField().setEnabled(!getAssociation().isDirection());
    }
    
     /**
     * Method responsible for updating the Target Values.
     */
    public void updateValues() {
        updateEnabled();
        setValues();
    }
    
    /**
     * Method responsible for updating the Elements Enabled.
     */
    private void updateEnabled() {
        getVisibilityComboBox().setEnabled(!getAssociation().isDirection());
        getNameTextField().setEnabled(!getAssociation().isDirection());
        getCardinalityTextField().setEnabled(!getAssociation().isDirection());
    }
    
    /**
     * Method responsible for setting the Source Association Values.
     */
    public void setValues() {
        getNameTextField().setText(getAssociation().isDirection() ? "" : getAssociation().getSourceName());
        getSourceTextField().setText(getAssociation().getSource().getName());
        getVisibilityComboBox().setSelectedItem(getAssociation().getSourceVisibility());
        getCardinalityTextField().setText(getAssociation().isDirection() ? "" : getAssociation().getCardinalitySourceLabel());
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return getTextField("source");
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