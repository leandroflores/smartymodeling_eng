package view.edit.panel.base;

import controller.view.edit.panel.base.ControllerPanelBaseStereotype;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.base.Stereotype;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseStereotype</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Stereotype Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/01/2020
 * @see    controller.view.edit.panel.base.ControllerPanelBaseStereotype
 * @see    model.structural.base.Stereotype
 * @see    view.Panel
 */
public final class PanelBaseStereotype extends Panel {
    private final ViewMenu viewMenu;
    private final Stereotype stereotype;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param stereotype Stereotype.
     */
    public PanelBaseStereotype(ViewMenu viewMenu, Stereotype stereotype) {
        this.viewMenu   = viewMenu;
        this.stereotype = stereotype;
        this.controller = new ControllerPanelBaseStereotype(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(2, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField",  "", 20));
        
        this.add(this.createLabel("Primitive: "));
        this.add(this.createCheckBox("primitiveCheckBox", "", this.stereotype.isPrimitive()));
        this.getPrimitiveCheckBox().setEnabled(false);
    }
    
    /**
     * Method responsible for setting the Stereotype Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.stereotype.getName());
        this.getPrimitiveCheckBox().setSelected(this.stereotype.isPrimitive());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Stereotype.
     * @return Stereotype.
     */
    public Stereotype getStereotype() {
        return this.stereotype;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Primitive Check Box.
     * @return Primitive Check Box.
     */
    public JCheckBox getPrimitiveCheckBox() {
        return this.checkBoxes.get("primitiveCheckBox");
    }
}