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
        controller = new ControllerPanelBaseVariability(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name", variability.getName(), 15));
        
        add(createLabel("Variation Point*: "));
        add(createComboBox("variation_point", new ControllerDiagram(diagram).getDefaultElements(), 15, getSelectedItem()));

        add(createLabel("Binding Time*: "));
        add(createComboBox("binding_time", new ControllerVariability(project).getBindings(), 15, variability.getBindingTime()));
    }
    
    /**
     * Method responsible for returning the Selected Item.
     * @return Selected Item.
     */
    private Object getSelectedItem() {
        if (variability.getVariationPoint() != null)
            return variability.getVariationPoint();
        return "";
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
        getNameTextField().setText(variability.getName());
        setVariationPoint();
        getBindingTimeComboBox().setSelectedItem(variability.getBindingTime());
    }
    
    /**
     * Method responsible for setting the Variation Point.
     */
    public void setVariationPoint() {
        Element element = (Element) getVariationPointComboBox().getSelectedItem();
        variability.setVariationPoint(element);
        setVariants();
        diagram.updateElementsStereotype();
    }
    
    /**
     * Method responsible for setting the Variants.
     */
    private void setVariants() {
        if (variability.getVariationPoint() != null)
            variability.getVariants().remove(variability.getVariationPoint());
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Variation Point Combo Box.
     * @return Variation Point Combo Box.
     */
    public JComboBox getVariationPointComboBox() {
        return getComboBox("variation_point");
    }
    
    /**
     * Method responsible for returning the Binding Time Combo Box.
     * @return Binding Time Combo Box.
     */
    public JComboBox getBindingTimeComboBox() {
        return getComboBox("binding_time");
    }
}