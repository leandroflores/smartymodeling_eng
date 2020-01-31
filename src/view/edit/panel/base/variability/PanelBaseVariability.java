package view.edit.panel.base.variability;

import controller.view.edit.panel.base.variability.ControllerPanelBaseVariability;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.variability.ControllerVariability;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseVariability</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Variability Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/07/2019
 * @see    controller.view.edit.panel.base.variability.ControllerPanelBaseVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.Panel
 */
public final class PanelBaseVariability extends Panel {
    private final ViewMenu viewMenu;
    private final Diagram diagram;
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public PanelBaseVariability(ViewMenu viewMenu, Diagram diagram, Variability variability) {
        this.viewMenu    = viewMenu;
        this.diagram     = diagram;
        this.variability = variability;
        this.controller  = new ControllerPanelBaseVariability(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2));
        this.setMinimumSize(new Dimension(150, 150));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", "", 15));
        
        this.add(this.createLabel("Variation Point*: "));
        this.add(this.createComboBox("variationPointComboBox", new ControllerDiagram(this.diagram).getDefaultElements(), 15, this.getSelectedItem()));

        this.add(this.createLabel("Binding Time*: "));
        this.add(this.createComboBox("bindingTimeComboBox", ControllerVariability.BINDINGS, 15));
    }
    
    /**
     * Method responsible for returning the Selected Item.
     * @return Selected Item.
     */
    private Object getSelectedItem() {
        if (this.variability.getVariationPoint() != null)
            return this.variability.getVariationPoint().getAbstract();
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
     * Method responsible for return the Variability.
     * @return Variability.
     */
    public Variability getVariability() {
        return this.variability;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Variation Point Combo Box.
     * @return Variation Point Combo Box.
     */
    public JComboBox getVariationPointComboBox() {
        return this.comboBoxes.get("variationPointComboBox");
    }
    
    /**
     * Method responsible for returning the Binding Time Combo Box.
     * @return Binding Time Combo Box.
     */
    public JComboBox getBindingTimeComboBox() {
        return this.comboBoxes.get("bindingTimeComboBox");
    }
}