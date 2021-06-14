package view.panel.base.variability;

import controller.view.panel.base.variability.ControllerPanelBaseVariants;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.variability.ControllerVariability;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseVariants</b>.</p>
 * <p>Class responsible for defining a <b>Variants Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.panel.base.variability.ControllerPanelBaseVariants
 * @see    model.structural.base.variability.Variability
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseVariants extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public PanelBaseVariants(ViewMenu view, Diagram diagram, Variability variability) {
        super(view, diagram, variability);
        this.controller = new ControllerPanelBaseVariants(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setValues();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(150, 150));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Constraint: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("constraintComboBox", new ControllerVariability(project).getConstraints(), 100), this.createConstraints(2, 1, 1, 0));
        this.add(this.createTextFieldNoEditable("minimumTextField", "0", 3), this.createConstraints(1, 1, 3, 0));
        this.add(this.createTextFieldNoEditable("maximumTextField", "0", 3), this.createConstraints(1, 1, 4, 0));
        
        this.add(this.createLabel("Variant: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("variantComboBox", new ControllerDiagram(this.diagram).getDefaultElements(), 175), this.createConstraints(4, 1, 1, 1));
        
        this.addButtons();
        
        this.createList("variantsList");
        this.add(this.getVariantsScrollPane(), this.createConstraints(5, 10, 0, 3));
    }
    
    /**
     * Method responsible for adding the Buttons.
     */
    private void addButtons() {
        JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(1, 2));
               panel.add(this.createButton("addVariantButton", "", "add.png"));
               panel.add(this.createButton("delVariantButton", "", "not.png"));
        this.add(panel, this.createConstraints(5, 1, 0, 2));
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
        return (Element) this.getVariantComboBox().getSelectedItem();
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