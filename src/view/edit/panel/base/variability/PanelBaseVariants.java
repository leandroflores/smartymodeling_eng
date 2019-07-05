package view.edit.panel.base.variability;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.variability.ControllerVariability;
import model.structural.base.Diagram;
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
//        this.controller  = new ControllerPanelBaseDiagram(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(300, 300));
        this.setSize(new Dimension(300, 300));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Constraint: "), this.getConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("constraintComboBox", ControllerVariability.TYPES, 150), this.getConstraints(2, 1, 1, 0));
        this.add(this.createTextFieldNoEditable("minimumTextField", "0", 3), this.getConstraints(1, 1, 3, 0));
        this.add(this.createTextFieldNoEditable("maximumTextField", "0", 3), this.getConstraints(1, 1, 4, 0));        
        
        this.add(this.createLabel("Variant: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("variantComboBox", new ControllerDiagram(this.diagram).getElements(), 150), this.getConstraints(2, 1, 1, 1));
        this.add(this.createButton("addVariantButton", "", "add.png"), this.getConstraints(1, 1, 3, 1));
        this.add(this.createButton("delVariantButton", "", "not.png"), this.getConstraints(1, 1, 4, 1));
        
        this.createList("variantsList");
        this.add(this.getVariantsScrollPane(), this.getConstraints(5, 10, 0, 2));
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
//        this.getNameTextField().setText(this.variability.getName());
//        this.getVariationPointComboBox().setText(this.diagram.getType());
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
        return this.comboBoxes.get("constraintComboBox");
    }
    
    /**
     * Method responsible for returning the Minimum Text Field.
     * @return Minimum Text Field.
     */
    public JTextField getMinimumTextField() {
        return this.textFields.get("minimumTextField");
    }
    
    /**
     * Method responsible for returning the Maximum Text Field.
     * @return Maximum Text Field.
     */
    public JTextField getMaximumTextField() {
        return this.textFields.get("maximumTextField");
    }
    
    /**
     * Method responsible for returning the Variant Combo Box.
     * @return Variant Combo Box.
     */
    public JComboBox getVariantComboBox() {
        return this.comboBoxes.get("variantComboBox");
    }
    
    /**
     * Method responsible for returning the Add Variant Button.
     * @return Add Variant Button.
     */
    public JButton getAddVariantButton() {
        return this.buttons.get("addVariantButton");
    }
    
    /**
     * Method responsible for returning the Del Variant Button.
     * @return Del Variant Button.
     */
    public JButton getDelVariantButton() {
        return this.buttons.get("delVariantButton");
    }
    
    /**
     * Method responsible for return the Variants List.
     * @return Variants List.
     */
    public JList getVariantsList() {
        return this.lists.get("variantsList");
    }
    
    /**
     * Method responsible for return the Variants Scroll Pane.
     * @return Variants Scroll Pane.
     */
    public JScrollPane getVariantsScrollPane() {
        return this.scrollPanes.get("variantsList");
    }
}