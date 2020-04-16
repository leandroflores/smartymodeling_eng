package view.edit.panel.base.classes.association;

import controller.view.edit.panel.base.classes.association.ControllerPanelBaseSource;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseSource</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Source Association Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  15/11/2019
 * @see    controller.view.edit.panel.base.classes.association.ControllerPanelBaseSource
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.Panel
 */
public final class PanelBaseSource extends Panel {
    private final ViewMenu viewMenu;
    private final ClassDiagram diagram;
    private final AssociationUML associationUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param association Association UML.
     */
    public PanelBaseSource(ViewMenu viewMenu, ClassDiagram diagram, AssociationUML association) {
        this.viewMenu       = viewMenu;
        this.diagram        = diagram;
        this.associationUML = association;
        this.controller     = new ControllerPanelBaseSource(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(4, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: ", 120));
        this.add(this.createTextFieldNoEditable("sourceTextField", "", 20));
        
        this.add(this.createLabel("Visibility: ", 120));
        this.add(this.createComboBox("visibilityComboBox", this.diagram.getVisibilities(), 30, this.associationUML.getSourceVisibility()));
        this.getVisibilityComboBox().setEnabled(!this.associationUML.isDirection());
        
        this.add(this.createLabel("Name: ", 120));
        this.add(this.createTextField("nameTextField", this.associationUML.getSourceName(), 20));
        this.getNameTextField().setEnabled(!this.associationUML.isDirection());
        
        this.add(this.createLabel("Cardinality: ", 120));
        this.add(this.createTextField("cardinalityTextField", "", 20));
        this.getCardinalityTextField().setEnabled(!this.associationUML.isDirection());
    }
    
     /**
     * Method responsible for updating the Target Values.
     */
    public void updateValues() {
        this.updateEnabled();
        this.setValues();
    }
    
    /**
     * Method responsible for updating the Elements Enabled.
     */
    private void updateEnabled() {
        this.getVisibilityComboBox().setEnabled(!this.associationUML.isDirection());
        this.getNameTextField().setEnabled(!this.associationUML.isDirection());
        this.getCardinalityTextField().setEnabled(!this.associationUML.isDirection());
    }
    
    /**
     * Method responsible for setting the Source Association Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.associationUML.isDirection() ? "" : this.associationUML.getSourceName());
        this.getSourceTextField().setText(this.associationUML.getSource().getName());
        this.getVisibilityComboBox().setSelectedItem(this.associationUML.getSourceVisibility());
        this.getCardinalityTextField().setText(this.associationUML.isDirection() ? "" : this.associationUML.getCardinalitySourceLabel());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Association UML.
     * @return Association UML.
     */
    public AssociationUML getAssociationUML() {
        return this.associationUML;
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return this.getTextField("sourceTextField");
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
}