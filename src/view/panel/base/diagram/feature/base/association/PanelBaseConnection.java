package view.panel.base.diagram.feature.base.association;

import controller.view.panel.base.diagram.feature.base.association.ControllerPanelBaseConnection;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.association.Connection;
import view.panel.base.PanelBaseAssociation;
import view.panel.edit.diagram.feature.base.association.PanelEditConnection;

/**
 * <p>Class of View <b>PanelBaseConnection</b>.</p> 
 * <p>Class responsible for defining a <b>Connection Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-15
 * @see    controller.view.panel.base.diagram.feature.base.association.ControllerPanelBaseConnection
 * @see    model.structural.diagram.feature.base.association.Connection
 * @see    view.panel.base.PanelBaseAssociation
 */
public final class PanelBaseConnection extends PanelBaseAssociation {
    private final PanelEditConnection panelEdit;
    
    /**
     * Default constructor method of Class.
     * @param panel View Edit Connection.
     * @param diagram Feature Diagram.
     * @param connection Connection.
     */
    public PanelBaseConnection(PanelEditConnection panel, FeatureDiagram diagram, Connection connection) {
        super(panel.getViewMenu(), diagram, connection);
        panelEdit  = panel;
        controller = new ControllerPanelBaseConnection(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        super.setDefaultProperties();
        setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Source: ", 120));
        add(createTextFieldNoEditable("source", getAssociation().getSource().getName(), 20));
        
        add(createLabel("Target: ", 120));
        add(createTextFieldNoEditable("target", getAssociation().getTarget().getName(), 20));
        
        add(createLabel("Category: ", 120));
        add(createComboBox("category", getDiagram().getCategories(), 20, getAssociation().getCategory()));
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return getTextField("source");
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return getTextField("target");
    }
    
    /**
     * Method responsible for returning the Category Combo Box.
     * @return Category Combo Box.
     */
    public JComboBox getCategoryComboBox() {
        return getComboBox("category");
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) diagram;
    }
    
    @Override
    public Connection getAssociation() {
        return (Connection) association;
    }
}