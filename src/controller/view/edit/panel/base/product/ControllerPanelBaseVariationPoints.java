package controller.view.edit.panel.base.product;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import model.structural.base.Element;
import model.structural.base.product.Product;
import model.structural.base.variability.Variability;
import view.edit.panel.base.product.PanelBaseVariationPoints;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariationPoints</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseVariationPoints</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/08/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.product.PanelBaseVariationPoints
 */
public class ControllerPanelBaseVariationPoints extends ControllerPanel {
    private final PanelBaseVariationPoints panelBaseVariationPoints;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Variation Points.
     */
    public ControllerPanelBaseVariationPoints(PanelBaseVariationPoints panel) {
        super(panel);
        this.panelBaseVariationPoints = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseVariationPoints.getNextButton().equals(event.getSource()))
            this.newProduct();
        else if (this.panelBaseVariationPoints.getBackButton().equals(event.getSource()))
            this.panelBaseVariationPoints.getViewNewProduct().addOptionalTabbedPane();
        else if (event.getSource() instanceof JCheckBox)
            this.actionCheckBox((JCheckBox) event.getSource());
        else if (event.getSource() instanceof JComboBox)
            this.actionComboBox((JComboBox) event.getSource());
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for create a New Product.
     */
    public void newProduct() {
        if (this.checkVariabilities()) {
            for (Variability variability : this.panelBaseVariationPoints.getViewNewProduct().getDiagram().getVariabilitiesList()) {
                if (this.panelBaseVariationPoints.getVariabilityCheckBox(variability).isSelected())
                    this.insertVariability(variability);
            }
            this.panelBaseVariationPoints.getViewNewProduct().addProductTabbedPane();
//            this.panelBaseVariationPoints.getViewNewProduct().showNewProduct();
        }
    }
    
    /**
     * Method responsible for insert a Variability.
     * @param variability Variability.
     */
    private void insertVariability(Variability variability) {
        if (variability.getConstraint().toLowerCase().trim().equals("exclusive"))
            this.insertExclusiveVariability(variability);
        else 
            this.insertInclusiveVariability(variability);
    }
    
    /**
     * Method responsible for insert a Exclusive Variability.
     * @param variability Exclusive Variability.
     */
    private void insertExclusiveVariability(Variability variability) {
        this.panelBaseVariationPoints.getViewNewProduct().increment(variability.getVariationPoint());
        Element element = (Element) this.panelBaseVariationPoints.getVariabilityComboBox(variability).getSelectedItem();
        this.panelBaseVariationPoints.getViewNewProduct().increment(element);
    }
    
    /**
     * Method responsible for insert a Inclusive Variability.
     * @param variability Inclusive Variability.
     */
    private void insertInclusiveVariability(Variability variability) {
        this.panelBaseVariationPoints.getViewNewProduct().increment(variability.getVariationPoint());
        for (Element variant : this.getVariantsSelected(variability))
            this.panelBaseVariationPoints.getViewNewProduct().increment(variant);
    }
    
    /**
     * Method responsible for returning the Variants selected by Variability.
     * @param  variability Inclusive Variability.
     * @return Variants selected.
     */
    private List<Element> getVariantsSelected(Variability variability) {
        List<Element> variants = new ArrayList<>();
        for (Element  variant  : variability.getVariants()) {
            if (this.panelBaseVariationPoints.getVariantCheckBox(variability, variant).isSelected())
                variants.add(variant);
        }
        return variants;
    }
    
    /**
     * Method responsible for running the Check Box Action.
     * @param checkBox Check Box.
     */
    private void actionCheckBox(JCheckBox checkBox) {
        String  id     = checkBox.getName().trim();
        boolean action = checkBox.isSelected();
        if (id.contains("|"))
            this.actionVariant(id.substring(0, id.indexOf("|")), id.substring(id.indexOf("|") + 1));
    }
    
    /**
     * Method responsible for running the Combo Box Action.
     * @param comboBox Combo Box.
     */
    private void actionComboBox(JComboBox comboBox) {
        Variability  variability = this.panelBaseVariationPoints.getViewNewProduct().getDiagram().getVariability(comboBox.getName());
        Element      variante    = (Element) comboBox.getSelectedItem();
        for (Element element : variability.getVariants()) {
            for (Variability current : this.panelBaseVariationPoints.getViewNewProduct().getDiagram().getVariationPoints(element))
                this.setFlag(current, current.getVariationPoint().equals(variante));
        }
    }
    
    /**
     * Method responsible for running the Lock/Unlock Operation to Variant. 
     * @param variabilityId Variability Id.
     * @param variantId Variant Id.
     */
    private void actionVariant(String variabilityId, String variantId) {
        Variability variability = this.panelBaseVariationPoints.getViewNewProduct().getDiagram().getVariability(variabilityId);
        Element     element     = this.panelBaseVariationPoints.getViewNewProduct().getDiagram().getElement(variantId);
        JCheckBox   checkBox    = this.panelBaseVariationPoints.getVariantCheckBox(variability, element);
        for (Variability current : this.panelBaseVariationPoints.getViewNewProduct().getDiagram().getVariationPoints(element)) {
            this.panelBaseVariationPoints.getVariabilityCheckBox(current).setSelected(checkBox.isSelected());
            this.setFlag(current, checkBox.isSelected());
        }
    }
    
    /**
     * Method responsible for setting the Variability Flag.
     * @param variability Variability.
     * @param flag Flag.
     */
    public void setFlag(Variability variability, boolean flag) {
        if (variability.getConstraint().toLowerCase().trim().equals("exclusive"))
            this.setExclusiveFlag(variability, flag);
        else 
            this.setInclusiveFlag(variability, flag);
    }
    
    /**
     * Method responsible for setting the Exclusive Variability Flag.
     * @param variability Exclusive Variability.
     * @param flag Flag.
     */
    private void setExclusiveFlag(Variability variability, boolean flag) {
        this.panelBaseVariationPoints.getVariabilityCheckBox(variability).setSelected(flag);
        this.panelBaseVariationPoints.getVariabilityComboBox(variability).setEnabled(flag);
    }
    
    /**
     * Method responsible for setting the Inclusive Variability Flag.
     * @param variability Inclusive Variability.
     * @param flag Flag.
     */
    private void setInclusiveFlag(Variability variability, boolean flag) {
        this.panelBaseVariationPoints.getVariabilityCheckBox(variability).setSelected(flag);
        this.panelBaseVariationPoints.getVariabilityCheckBox(variability).setEnabled(false);
        for (Element variant : variability.getVariants()) {
            this.panelBaseVariationPoints.getVariantCheckBox(variability, variant).setSelected(false);
            this.panelBaseVariationPoints.getVariantCheckBox(variability, variant).setEnabled(flag);
        }
    }
   
    /**
     * Method responsible for returning the Variabilities selected.
     * @return Variabilities selected.
     */
    public List<Variability> getVariabilitiesSelected() {
        List<Variability> filter = new ArrayList<>();
        for (Variability variability : this.panelBaseVariationPoints.getViewNewProduct().getDiagram().getVariabilitiesList()) {
            if (this.panelBaseVariationPoints.getVariabilityCheckBox(variability).isSelected())
                filter.add(variability);
        }
        return  filter;
    }
    
    /**
     * Method responsible for checking the Variabilities.
     * @return Variabilities checked.
     */
    public boolean checkVariabilities() {
        return this.checkInclusiveVariabilities()
            && this.checkExclusiveVariabilities();
    }
    
    /**
     * Method responsible for checking the Inclusive Variabilities.
     * @return Inclusive Variabilities checked.
     */
    public boolean checkInclusiveVariabilities() {
        for (Variability variability : this.getVariabilitiesSelected()) {
            if (variability.getConstraint().toLowerCase().trim().equals("inclusive")) {
                if (this.checkInclusiveVariability(variability) == false) {
                    new ViewError(this.panelBaseVariationPoints.getViewNewProduct(), "Select a Variant of " + variability.getName()).setVisible(true);
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Inclusive Variability.
     * @param  variability Inclusive Variability.
     * @return Inclusive Variability checked.
     */
    private boolean checkInclusiveVariability(Variability variability) {
        for (Element variant : variability.getVariants()) {
            if (this.panelBaseVariationPoints.getVariantCheckBox(variability, variant).isSelected())
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for checking the Exclusive Variabilities.
     * @return Exclusive Variabilities checked.
     */
    public boolean checkExclusiveVariabilities() {
        for (Variability variability : this.getVariabilitiesSelected()) {
            if (variability.getConstraint().toLowerCase().trim().equals("exclusive")) {
                if (this.checkExclusiveVariability(variability) == false) {
                    new ViewError(this.panelBaseVariationPoints.getViewNewProduct(), "Select a Variant of " + variability.getName()).setVisible(true);
                    this.panelBaseVariationPoints.getVariabilityComboBox(variability).requestFocus();
                    this.panelBaseVariationPoints.getVariabilityComboBox(variability).setPopupVisible(true);
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Exclusive Variability.
     * @param  variability Exclusive Variability.
     * @return Exclusive Variability checked.
     */
    private boolean checkExclusiveVariability(Variability variability) {
        Element variationPoint = (Element) this.panelBaseVariationPoints.getVariabilityComboBox(variability).getSelectedItem();
        for (Variability current : this.panelBaseVariationPoints.getViewNewProduct().getDiagram().getVariationPoints(variationPoint)) {
            if (this.panelBaseVariationPoints.getVariabilityCheckBox(current).isSelected() == false)
                return false;
        }
        return true;
    }
}