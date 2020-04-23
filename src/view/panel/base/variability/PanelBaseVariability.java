package view.panel.base.variability;

import controller.view.panel.base.variability.ControllerPanelBaseVariability;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.variability.ControllerVariability;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseVariability</b>.</p> 
 * <p>Class responsible for defining a <b>Variability Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.panel.base.variability.ControllerPanelBaseVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.panel.base.variability.PanelBase
 */
public final class PanelBaseVariability extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public PanelBaseVariability(ViewMenu view, Diagram diagram, Variability variability) {
        super(view, diagram, variability);
        this.controller = new ControllerPanelBaseVariability(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.variability.getName(), 15));
        
        this.add(this.createLabel("Variation Point*: "));
        this.add(this.createComboBox("variationPointComboBox", new ControllerDiagram(this.diagram).getDefaultElements(), 15, this.getSelectedItem()));

        this.add(this.createLabel("Binding Time*: "));
        this.add(this.createComboBox("bindingTimeComboBox", ControllerVariability.BINDINGS, 15, this.variability.getBindingTime()));
    }
    
    /**
     * Method responsible for returning the Selected Item.
     * @return Selected Item.
     */
    private Object getSelectedItem() {
        if (this.variability.getVariationPoint() != null)
            return this.variability.getVariationPoint();
        return "";
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.variability.getName());
        this.setVariationPoint();
        this.getBindingTimeComboBox().setSelectedItem(this.variability.getBindingTime());
    }
    
    /**
     * Method responsible for setting the Variation Point.
     */
    public void setVariationPoint() {
        Element element = (Element) this.getVariationPointComboBox().getSelectedItem();
        this.variability.setVariationPoint(element);
        this.setVariantes();
        this.diagram.updateElementsStereotype();
    }
    
    /**
     * Method responsible for setting the Variants.
     */
    private void setVariantes() {
        if (this.variability.getVariationPoint() != null)
            this.variability.getVariants().remove(this.variability.getVariationPoint());
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Variation Point Combo Box.
     * @return Variation Point Combo Box.
     */
    public JComboBox getVariationPointComboBox() {
        return this.getComboBox("variationPointComboBox");
    }
    
    /**
     * Method responsible for returning the Binding Time Combo Box.
     * @return Binding Time Combo Box.
     */
    public JComboBox getBindingTimeComboBox() {
        return this.getComboBox("bindingTimeComboBox");
    }
}