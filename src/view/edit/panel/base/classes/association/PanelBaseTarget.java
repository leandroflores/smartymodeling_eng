package view.edit.panel.base.classes.association;

import controller.view.edit.panel.base.classes.association.ControllerPanelBaseTarget;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseTarget</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Target Association Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  15/11/2019
 * @see    controller.view.edit.panel.base.classes.association.ControllerPanelBaseTarget
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.Panel
 */
public final class PanelBaseTarget extends Panel {
    private final ViewMenu viewMenu;
    private final ClassDiagram diagram;
    private final AssociationUML associationUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param association Association UML.
     */
    public PanelBaseTarget(ViewMenu viewMenu, ClassDiagram diagram, AssociationUML association) {
        this.viewMenu       = viewMenu;
        this.diagram        = diagram;
        this.associationUML = association;
        this.controller     = new ControllerPanelBaseTarget(this);
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
        this.add(this.createLabel("Target: ", 120));
        this.add(this.createTextFieldNoEditable("targetTextField", "", 20));
        
        this.add(this.createLabel("Name: ", 120));
        this.add(this.createTextField("nameTextField", "", 20));
        this.getNameTextField().setEnabled(this.associationUML.isDirection());
        
        this.add(this.createLabel("Cardinality: ", 120));
        this.add(this.createTextField("cardinalityTextField", "", 20));
        this.getCardinalityTextField().setEnabled(this.associationUML.isDirection());
    }
    
    /**
     * Method responsible for setting the Target Association Values.
     */
    public void setValues() {
        this.getTargetTextField().setText(this.associationUML.getTarget().getName());
        this.getNameTextField().setText(this.associationUML.getTargetName());
        this.getCardinalityTextField().setText(this.associationUML.getTargetLabel());
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
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return this.textFields.get("targetTextField");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Cardinality Text Field.
     * @return Cardinality Text Field.
     */
    public JTextField getCardinalityTextField() {
        return this.textFields.get("cardinalityTextField");
    }
}