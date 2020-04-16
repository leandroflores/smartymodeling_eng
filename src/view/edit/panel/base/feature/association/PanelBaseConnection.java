package view.edit.panel.base.feature.association;

import controller.view.edit.panel.base.feature.association.ControllerPanelBaseConnection;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.association.Connection;
import view.panel.Panel;
import view.panel.edit.base.feature.association.PanelEditConnection;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAssociationUML</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Association UML Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  15/11/2019
 * @see    controller.view.edit.panel.base.classes.association.ControllerPanelBaseAssociationUML
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.panel.Panel
 */
public final class PanelBaseConnection extends Panel {
    private final ViewMenu viewMenu;
    private final PanelEditConnection panelEdit;
    private final FeatureDiagram diagram;
    private final Connection connection;
    
    /**
     * Default constructor method of Class.
     * @param panelEdit View Edit Connection.
     * @param diagram Feature Diagram.
     * @param connection Connection.
     */
    public PanelBaseConnection(PanelEditConnection panelEdit, FeatureDiagram diagram, Connection connection) {
        this.viewMenu   = panelEdit.getViewMenu();
        this.panelEdit  = panelEdit;
        this.diagram    = diagram;
        this.connection = connection;
        this.controller = new ControllerPanelBaseConnection(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(3, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: ", 120));
        this.add(this.createTextFieldNoEditable("sourceTextField", "", 20));
        
        this.add(this.createLabel("Target: ", 120));
        this.add(this.createTextFieldNoEditable("targetTextField", "", 20));
        
        this.add(this.createLabel("Category: ", 120));
        this.add(this.createComboBox("categoryComboBox", this.getCategories(), 20, this.connection.getCategory()));
//        this.add(this.createTextFieldNoEditable("categoryTextField", "", 20));
    }
    
    private String[] getCategories() {
        return new String[]{"mandatory", "optional", "inclusive", "exclusive"};
    }
    
    /**
     * Method responsible for setting the Association UML Values.
     */
    public void setValues() {
        this.getSourceTextField().setText(this.connection.getSource().getName());
        this.getTargetTextField().setText(this.connection.getTarget().getName());
        this.getCategoryComboBox().setSelectedItem(this.connection.getCategory());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Feature Diagram.
     * @return Feature Diagram.
     */
    public FeatureDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Connection.
     * @return Connection.
     */
    public Connection getConnection() {
        return this.connection;
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
}