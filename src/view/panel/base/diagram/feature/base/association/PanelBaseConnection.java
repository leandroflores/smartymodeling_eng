package view.panel.base.diagram.feature.base.association;

import controller.view.panel.base.diagram.feature.base.association.ControllerPanelBaseConnection;
import java.awt.Dimension;
import java.awt.GridLayout;
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
     * @param panelEdit View Edit Connection.
     * @param diagram Feature Diagram.
     * @param connection Connection.
     */
    public PanelBaseConnection(PanelEditConnection panelEdit, FeatureDiagram diagram, Connection connection) {
        super(panelEdit.getViewMenu(), diagram, connection);
        this.panelEdit  = panelEdit;
        this.controller = new ControllerPanelBaseConnection(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(3, 2));
        super.setDefaultProperties();
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: ", 120));
        this.add(this.createTextFieldNoEditable("sourceTextField", this.getAssociation().getSource().getName(), 20));
        
        this.add(this.createLabel("Target: ", 120));
        this.add(this.createTextFieldNoEditable("targetTextField", this.getAssociation().getTarget().getName(), 20));
        
        this.add(this.createLabel("Category: ", 120));
        this.add(this.createComboBox("categoryComboBox", this.getDiagram().getCategories(), 20, this.getAssociation().getCategory()));
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
     * Method responsible for returning the Category Combo Box.
     * @return Category Combo Box.
     */
    public JComboBox getCategoryComboBox() {
        return this.getComboBox("categoryComboBox");
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) this.diagram;
    }
    
    @Override
    public Connection getAssociation() {
        return (Connection) this.association;
    }
}