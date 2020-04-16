package controller.view.edit.panel.base.product.instance;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.panel.base.product.instance.PanelBaseVarPoints;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVarPoints</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseVarPoints</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/10/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    model.structural.base.product.Instance
 * @see    model.structural.base.product.Product
 * @see    view.panel.base.product.instance.PanelBaseVarPoints
 */
public class ControllerPanelBaseVarPoints extends ControllerPanel {
    private final PanelBaseVarPoints panelBaseVarPoints;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Var Points.
     */
    public ControllerPanelBaseVarPoints(PanelBaseVarPoints panel) {
        super(panel);
        this.panelBaseVarPoints = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JCheckBox)
            this.actionCheckBox((JCheckBox) event.getSource());
        else if (event.getSource() instanceof JComboBox)
            this.actionComboBox((JComboBox) event.getSource());
        else if (this.panelBaseVarPoints.getBackButton().equals(event.getSource()))
            this.panelBaseVarPoints.getViewNewInstance().removePanelBaseVarPoints();
        else if (this.panelBaseVarPoints.getNextButton().equals(event.getSource()))
            this.newInstance();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for create a New Instance.
     */
    public void newInstance() {
        if (this.checkVariabilities()) {
            for (Variability variability : this.panelBaseVarPoints.getViewNewInstance().getInstance().getDiagram().getVariabilitiesList()) {
                if (this.panelBaseVarPoints.getVariabilityCheckBox(variability).isSelected())
                    this.insertVariability(variability);
            }
            this.panelBaseVarPoints.getViewNewInstance().addPanelBaseArtifacts();
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
        this.panelBaseVarPoints.getViewNewInstance().add(variability.getVariationPoint());
        Element element = (Element) this.panelBaseVarPoints.getVariabilityComboBox(variability).getSelectedItem();
        this.panelBaseVarPoints.getViewNewInstance().add(element);
    }
    
    /**
     * Method responsible for insert a Inclusive Variability.
     * @param variability Inclusive Variability.
     */
    private void insertInclusiveVariability(Variability variability) {
        this.panelBaseVarPoints.getViewNewInstance().add(variability.getVariationPoint());
        for (Element variant : this.getVariantsSelected(variability))
            this.panelBaseVarPoints.getViewNewInstance().add(variant);
    }
    
    /**
     * Method responsible for returning the Variants selected by Variability.
     * @param  variability Inclusive Variability.
     * @return Variants selected.
     */
    private List<Element> getVariantsSelected(Variability variability) {
        List<Element> variants = new ArrayList<>();
        for (Element  variant  : variability.getVariants()) {
            if (this.panelBaseVarPoints.getVariantCheckBox(variability, variant).isSelected())
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
        Variability  variability = this.panelBaseVarPoints.getViewNewInstance().getDiagram().getVariability(comboBox.getName());
        Element      variante    = (Element) comboBox.getSelectedItem();
        for (Element element : variability.getVariants()) {
            for (Variability current : this.panelBaseVarPoints.getViewNewInstance().getDiagram().getVariationPoints(element))
                this.setFlag(current, current.getVariationPoint().equals(variante));
        }
    }
    
    /**
     * Method responsible for running the Lock/Unlock Operation to Variant. 
     * @param variabilityId Variability Id.
     * @param variantId Variant Id.
     */
    private void actionVariant(String variabilityId, String variantId) {
        Variability variability = this.panelBaseVarPoints.getViewNewInstance().getDiagram().getVariability(variabilityId);
        Element     element     = this.panelBaseVarPoints.getViewNewInstance().getDiagram().getElement(variantId);
        JCheckBox   checkBox    = this.panelBaseVarPoints.getVariantCheckBox(variability, element);
        for (Variability current : this.panelBaseVarPoints.getViewNewInstance().getDiagram().getVariationPoints(element)) {
            this.panelBaseVarPoints.getVariabilityCheckBox(current).setSelected(checkBox.isSelected());
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
        this.panelBaseVarPoints.getVariabilityCheckBox(variability).setSelected(flag);
        this.panelBaseVarPoints.getVariabilityComboBox(variability).setEnabled(flag);
    }
    
    /**
     * Method responsible for setting the Inclusive Variability Flag.
     * @param variability Inclusive Variability.
     * @param flag Flag.
     */
    private void setInclusiveFlag(Variability variability, boolean flag) {
        this.panelBaseVarPoints.getVariabilityCheckBox(variability).setSelected(flag);
        this.panelBaseVarPoints.getVariabilityCheckBox(variability).setEnabled(false);
        for (Element variant : variability.getVariants()) {
            this.panelBaseVarPoints.getVariantCheckBox(variability, variant).setSelected(false);
            this.panelBaseVarPoints.getVariantCheckBox(variability, variant).setEnabled(flag);
        }
    }
   
    /**
     * Method responsible for returning the Variabilities selected.
     * @return Variabilities selected.
     */
    public List<Variability> getVariabilitiesSelected() {
        List<Variability> filter = new ArrayList<>();
        for (Variability variability : this.panelBaseVarPoints.getViewNewInstance().getDiagram().getVariabilitiesList()) {
            if (this.panelBaseVarPoints.getVariabilityCheckBox(variability).isSelected())
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
                    new ViewError(this.panelBaseVarPoints.getViewNewInstance(), "Select a Variant of " + variability.getName()).setVisible(true);
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
            if (this.panelBaseVarPoints.getVariantCheckBox(variability, variant).isSelected())
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
                    new ViewError(this.panelBaseVarPoints.getViewNewInstance(), "Select a Variant of " + variability.getName()).setVisible(true);
                    this.panelBaseVarPoints.getVariabilityComboBox(variability).requestFocus();
                    this.panelBaseVarPoints.getVariabilityComboBox(variability).setPopupVisible(true);
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
        Element variationPoint = (Element) this.panelBaseVarPoints.getVariabilityComboBox(variability).getSelectedItem();
        for (Variability current : this.panelBaseVarPoints.getViewNewInstance().getDiagram().getVariationPoints(variationPoint)) {
            if (this.panelBaseVarPoints.getVariabilityCheckBox(current).isSelected() == false)
                return false;
        }
        return true;
    }
}