package view.edit.panel.base;

import controller.view.edit.panel.base.ControllerPanelBaseAssociation;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import model.structural.base.association.Association;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAssociation</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Association Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/11/2019
 * @see    controller.view.edit.panel.base.ControllerPanelBaseAssociation
 * @see    model.structural.base.association.Association
 * @see    view.Panel
 */
public final class PanelBaseAssociation extends Panel {
    private final ViewMenu viewMenu;
    private final Diagram diagram;
    private final Association association;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Diagram.
     * @param association Association.
     */
    public PanelBaseAssociation(ViewMenu viewMenu, Diagram diagram, Association association) {
        this.viewMenu    = viewMenu;
        this.diagram     = diagram;
        this.association = association;
        this.controller  = new ControllerPanelBaseAssociation(this);
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
        
        this.add(this.createLabel("Type: ", 120));
        this.add(this.createTextFieldNoEditable("typeTextField", "", 20));
    }
    
    /**
     * Method responsible for setting the Association Values.
     */
    public void setValues() {
        this.getSourceTextField().setText(this.association.getSource().getName());
        this.getTargetTextField().setText(this.association.getTarget().getName());
        this.getTypeTextField().setText(this.association.getType());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Association.
     * @return Association.
     */
    public Association getAssociation() {
        return this.association;
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return this.textFields.get("sourceTextField");
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return this.textFields.get("targetTextField");
    }
    
    /**
     * Method responsible for returning the Type Text Field.
     * @return Type Text Field.
     */
    public JTextField getTypeTextField() {
        return this.textFields.get("typeTextField");
    }
}