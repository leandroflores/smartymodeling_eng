package view.panel.base;

import controller.view.panel.base.ControllerPanelBaseStereotype;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.base.Stereotype;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseStereotype</b>.</p> 
 * <p>Class responsible for defining a <b>Stereotype Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-17
 * @see    controller.view.panel.base.ControllerPanelBaseStereotype
 * @see    model.structural.base.Stereotype
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseStereotype extends PanelBase {
    private final Stereotype stereotype;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param stereotype Stereotype.
     */
    public PanelBaseStereotype(ViewMenu view, Stereotype stereotype) {
        super(view);
        this.stereotype = stereotype;
        this.controller = new ControllerPanelBaseStereotype(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(2, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name",  stereotype.getName(), 20));
        
        add(createLabel("Primitive: "));
        add(createCheckBox("primitive", "", stereotype.isPrimitive()));
        getPrimitiveCheckBox().setEnabled(false);
    }
    
    /**
     * Method responsible for returning the Stereotype.
     * @return Stereotype.
     */
    public Stereotype getStereotype() {
        return stereotype;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Primitive Check Box.
     * @return Primitive Check Box.
     */
    public JCheckBox getPrimitiveCheckBox() {
        return getCheckBox("primitive");
    }
}