package view.edit.panel.base.variability;

import controller.view.edit.panel.base.variability.ControllerPanelBaseVariants;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.variability.ControllerVariability;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseVariants</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Variants Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/07/2019
 * @see    controller.view.edit.panel.base.
 * @see    model.structural.base.variability.Variability
 * @see    view.Panel
 */
public final class PanelBaseVariants extends Panel {
    private final ViewMenu viewMenu;
    private final Diagram diagram;
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public PanelBaseVariants(ViewMenu viewMenu, Diagram diagram, Variability variability) {
        this.viewMenu    = viewMenu;
        this.diagram     = diagram;
        this.variability = variability;
        this.controller  = new ControllerPanelBaseVariants(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(150, 150));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Constraint: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("constraintComboBox", ControllerVariability.TYPES, 100), this.createConstraints(2, 1, 1, 0));
        this.add(this.createTextFieldNoEditable("minimumTextField", "0", 3), this.createConstraints(1, 1, 3, 0));
        this.add(this.createTextFieldNoEditable("maximumTextField", "0", 3), this.createConstraints(1, 1, 4, 0));        
        
        this.add(this.createLabel("Variant: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("variantComboBox", new ControllerDiagram(this.diagram).getDefaultElements(), 175), this.createConstraints(2, 1, 1, 1));
        this.add(this.createButton("addVariantButton", "", "add.png"), this.createConstraints(1, 1, 3, 1));
        this.add(this.createButton("delVariantButton", "", "not.png"), this.createConstraints(1, 1, 4, 1));
        
        this.createList("variantsList");
        this.add(this.getVariantsScrollPane(), this.createConstraints(5, 10, 0, 2));
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
        this.getConstraintComboBox().setSelectedItem(this.variability.getConstraint());
        this.updateValues();
        this.updateVariantsList();
    }
    
    /**
     * Method responsible for adding a Variant.
     */
    public void addVariant() {
        Element variant = this.getVariant();
        if    ((!this.variability.getVariants().contains(variant)) 
            && (!this.variability.getVariationPoint().equals(variant))) {
            this.variability.getVariants().add(variant);
            this.updateValues();
            this.updateVariantsList();
        }
    }
    
    /**
     * Method responsible for deleting a Variant.
     */
    public void delVariant() {
        this.variability.getVariants().remove((Element) this.getVariantsList().getSelectedValue());
        this.updateValues();
        this.updateVariantsList();
    }
    
    /**
     * Method responsible for updating the Variabilty Values.
     */
    public void updateValues() {
        String constraint = this.getConstraintComboBox().getSelectedItem().toString().trim();
        this.getMinimumTextField().setText(this.variability.getVariants().isEmpty() ? "0" : "1");
        this.getMaximumTextField().setText(constraint.toLowerCase().equals("inclusive") ? Integer.toString(this.variability.getVariants().size()) : "1");
    }
    
    /**
     * Method responsible for updating the Variants List.
     */
    public void updateVariantsList() {
        this.getVariantsList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (Element element :  this.variability.getVariants())
            model.addElement(element);
        this.getVariantsList().setModel(model);
    }
    
    /**
     * Method responsible for returning the Variant.
     * @return Variant Element.
     */
    public Element getVariant() {
//        String item = this.getVariantComboBox().getSelectedItem().toString();
//        String id   = item.substring(item.indexOf("[") + 1, item.indexOf("]")).trim();
//        return this.diagram.getElement(id);
        return (Element) this.getVariantComboBox().getSelectedItem();
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
     * Method responsible for returning the Constraint Combo Box.
     * @return Constraint Combo Box.
     */
    public JComboBox getConstraintComboBox() {
        return this.getComboBox("constraintComboBox");
    }
    
    /**
     * Method responsible for returning the Minimum Text Field.
     * @return Minimum Text Field.
     */
    public JTextField getMinimumTextField() {
        return this.getTextField("minimumTextField");
    }
    
    /**
     * Method responsible for returning the Maximum Text Field.
     * @return Maximum Text Field.
     */
    public JTextField getMaximumTextField() {
        return this.getTextField("maximumTextField");
    }
    
    /**
     * Method responsible for returning the Variant Combo Box.
     * @return Variant Combo Box.
     */
    public JComboBox getVariantComboBox() {
        return this.getComboBox("variantComboBox");
    }
    
    /**
     * Method responsible for returning the Add Variant Button.
     * @return Add Variant Button.
     */
    public JButton getAddVariantButton() {
        return this.getButton("addVariantButton");
    }
    
    /**
     * Method responsible for returning the Del Variant Button.
     * @return Del Variant Button.
     */
    public JButton getDelVariantButton() {
        return this.getButton("delVariantButton");
    }
    
    /**
     * Method responsible for return the Variants List.
     * @return Variants List.
     */
    public JList getVariantsList() {
        return this.getList("variantsList");
    }
    
    /**
     * Method responsible for return the Variants Scroll Pane.
     * @return Variants Scroll Pane.
     */
    public JScrollPane getVariantsScrollPane() {
        return this.getScrollPane("variantsList");
    }
}