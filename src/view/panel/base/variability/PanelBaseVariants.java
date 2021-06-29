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
 * @see    view.panel.base.variability.PanelBase
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
        controller = new ControllerPanelBaseVariants(this);
        setDefaultProperties();
        addComponents();
        setValues();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(150, 150));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Constraint: "), createConstraints(1, 1, 0, 0));
        add(createComboBox("constraint", new ControllerVariability(project).getConstraints(), 100), createConstraints(2, 1, 1, 0));
        add(createTextFieldNoEditable("min", "0", 3), createConstraints(1, 1, 3, 0));
        add(createTextFieldNoEditable("max", "0", 3), createConstraints(1, 1, 4, 0));
        
        add(createLabel("Variant: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("variant", new ControllerDiagram(diagram).getDefaultElements(), 175), createConstraints(4, 1, 1, 1));
        
        addButtons();
        
        createList("variants");
        add(getVariantsScrollPane(), createConstraints(5, 10, 0, 3));
    }
    
    /**
     * Method responsible for adding the Buttons.
     */
    private void addButtons() {
        JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(1, 2));
               panel.add(createButton("add_variant", "", "add.png"));
               panel.add(createButton("del_variant", "", "not.png"));
        add(panel, createConstraints(5, 1, 0, 2));
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
        getConstraintComboBox().setSelectedItem(variability.getConstraint());
        updateValues();
        updateVariantsList();
    }
    
    /**
     * Method responsible for adding a Variant.
     */
    public void addVariant() {
        Element variant = getVariant();
        if (!variability.getVariants().contains(variant)
         && !variability.getVariationPoint().equals(variant)) {
            variability.getVariants().add(variant);
            updateValues();
            updateVariantsList();
        }
    }
    
    /**
     * Method responsible for deleting a Variant.
     */
    public void delVariant() {
        variability.getVariants().remove((Element) getVariantsList().getSelectedValue());
        updateValues();
        updateVariantsList();
    }
    
    /**
     * Method responsible for updating the Variabilty Values.
     */
    public void updateValues() {
        String constraint = getConstraintComboBox().getSelectedItem().toString().trim();
        getMinimumTextField().setText(variability.getVariants().isEmpty() ? "0" : "1");
        getMaximumTextField().setText(constraint.toLowerCase().equals("inclusive") ? Integer.toString(variability.getVariants().size()) : "1");
    }
    
    /**
     * Method responsible for updating the Variants List.
     */
    public void updateVariantsList() {
        getVariantsList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (Element element :  variability.getVariants())
            model.addElement(element);
        getVariantsList().setModel(model);
    }
    
    /**
     * Method responsible for returning the Variant.
     * @return Variant Element.
     */
    public Element getVariant() {
        return (Element) getVariantComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for returning the Constraint Combo Box.
     * @return Constraint Combo Box.
     */
    public JComboBox getConstraintComboBox() {
        return getComboBox("constraint");
    }
    
    /**
     * Method responsible for returning the Minimum Text Field.
     * @return Minimum Text Field.
     */
    public JTextField getMinimumTextField() {
        return getTextField("min");
    }
    
    /**
     * Method responsible for returning the Maximum Text Field.
     * @return Maximum Text Field.
     */
    public JTextField getMaximumTextField() {
        return getTextField("max");
    }
    
    /**
     * Method responsible for returning the Variant Combo Box.
     * @return Variant Combo Box.
     */
    public JComboBox getVariantComboBox() {
        return getComboBox("variant");
    }
    
    /**
     * Method responsible for returning the Add Variant Button.
     * @return Add Variant Button.
     */
    public JButton getAddVariantButton() {
        return getButton("add_variant");
    }
    
    /**
     * Method responsible for returning the Del Variant Button.
     * @return Del Variant Button.
     */
    public JButton getDelVariantButton() {
        return getButton("del_variant");
    }
    
    /**
     * Method responsible for return the Variants List.
     * @return Variants List.
     */
    public JList getVariantsList() {
        return getList("variants");
    }
    
    /**
     * Method responsible for return the Variants Scroll Pane.
     * @return Variants Scroll Pane.
     */
    public JScrollPane getVariantsScrollPane() {
        return getScrollPane("variants");
    }
}