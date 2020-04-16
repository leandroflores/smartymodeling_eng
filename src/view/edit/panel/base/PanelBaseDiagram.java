package view.edit.panel.base;

import controller.view.edit.panel.base.ControllerPanelBaseDiagram;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseDiagram</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Diagram Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    controller.view.edit.panel.base.ControllerPanelBaseDiagram
 * @see    model.structural.base.Diagram
 * @see    view.panel.Panel
 */
public final class PanelBaseDiagram extends Panel {
    private final ViewMenu viewMenu;
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Diagram.
     */
    public PanelBaseDiagram(ViewMenu viewMenu, Diagram diagram) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.controller = new ControllerPanelBaseDiagram(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", "", 15));
        
        this.add(this.createLabel("Type: "));
        this.add(this.createTextFieldNoEditable("typeTextField", "", 15));
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.diagram.getName());
        this.getTypeTextField().setText(this.diagram.getType());
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
     * Method responsible for returning Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning Type Text Field.
     * @return Type Text Field.
     */
    public JTextField getTypeTextField() {
        return this.getTextField("typeTextField");
    }
}