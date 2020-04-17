package controller.view.panel.base.product.instance;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import model.structural.base.Element;
import model.structural.base.product.Instance;
import model.structural.base.variability.Variability;
import view.panel.base.product.instance.PanelBaseVarPoints;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVarPoints</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseVarPoints</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.panel.base.product.instance.ControllerPanelBase
 * @see    view.panel.base.product.instance.PanelBaseVarPoints
 */
public class ControllerPanelBaseVarPoints extends ControllerPanelBase {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Var Points.
     */
    public ControllerPanelBaseVarPoints(PanelBaseVarPoints panel) {
        super(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (event.getSource() instanceof JCheckBox)
            this.actionCheckBox((JCheckBox) event.getSource());
        else if (event.getSource() instanceof JComboBox)
            this.actionComboBox((JComboBox) event.getSource());
    }
    
    @Override
    protected void return_() {
        this.getViewNew().removePanelBaseVarPoints();
    }
    
    @Override
    public boolean check() {
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
                if (!this.checkInclusiveVariability(variability)) {
                    new ViewError(this.getViewNew(), "Select a Variant of " + variability.getName()).setVisible(true);
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
            if (this.getPanel().getVariantCheckBox(variability, variant).isSelected())
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
                if (!this.checkExclusiveVariability(variability)) {
                    new ViewError(this.getViewNew(), "Select a Variant of " + variability.getName()).setVisible(true);
                    this.getPanel().getVariabilityComboBox(variability).requestFocus();
                    this.getPanel().getVariabilityComboBox(variability).setPopupVisible(true);
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
        Element variationPoint = (Element) this.getPanel().getVariabilityComboBox(variability).getSelectedItem();
        for (Variability current : this.getViewNew().getDiagram().getVariationPoints(variationPoint)) {
            if (!this.getPanel().getVariabilityCheckBox(current).isSelected())
                return false;
        }
        return true;
    }
    
    /**
     * Method responsible for returning the Variabilities selected.
     * @return Variabilities selected.
     */
    public List<Variability> getVariabilitiesSelected() {
        List<Variability> filter = new ArrayList<>();
        for (Variability variability : this.getViewNew().getDiagram().getVariabilitiesList()) {
            if (this.getPanel().getVariabilityCheckBox(variability).isSelected())
                filter.add(variability);
        }
        return  filter;
    }
    
    @Override
    public void next() {
        this.update();
        this.getViewNew().addPanelBaseArtifacts();
    }
    
    @Override
    protected void update() {
        for (Variability variability : this.getInstance().getDiagram().getVariabilitiesList()) {
            if (this.getPanel().getVariabilityCheckBox(variability).isSelected())
                this.insertVariability(variability);
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
        this.getViewNew().add(variability.getVariationPoint());
        Element element = (Element) this.getPanel().getVariabilityComboBox(variability).getSelectedItem();
        this.getViewNew().add(element);
    }
    
    /**
     * Method responsible for insert a Inclusive Variability.
     * @param variability Inclusive Variability.
     */
    private void insertInclusiveVariability(Variability variability) {
        this.getViewNew().add(variability.getVariationPoint());
        for (Element variant : this.getVariantsSelected(variability))
            this.getViewNew().add(variant);
    }
    
    /**
     * Method responsible for returning the Variants selected by Variability.
     * @param  variability Variability.
     * @return Variants selected.
     */
    private List<Element> getVariantsSelected(Variability variability) {
        List<Element> variants = new ArrayList<>();
        for (Element  variant  : variability.getVariants()) {
            if (this.getPanel().getVariantCheckBox(variability, variant).isSelected())
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
        Variability  variability = this.getViewNew().getDiagram().getVariability(comboBox.getName());
        Element      variant     = (Element) comboBox.getSelectedItem();
        for (Element element : variability.getVariants()) {
            for (Variability current : this.getViewNew().getDiagram().getVariationPoints(element))
                this.setFlag(current, current.getVariationPoint().equals(variant));
        }
    }
    
    /**
     * Method responsible for running the Lock/Unlock Operation to Variant. 
     * @param variabilityId Variability Id.
     * @param variantId Variant Id.
     */
    private void actionVariant(String variabilityId, String variantId) {
        Variability variability = this.getViewNew().getDiagram().getVariability(variabilityId);
        Element     element     = this.getViewNew().getDiagram().getElement(variantId);
        JCheckBox   checkBox    = this.getPanel().getVariantCheckBox(variability, element);
        for (Variability current : this.getViewNew().getDiagram().getVariationPoints(element)) {
            this.getPanel().getVariabilityCheckBox(current).setSelected(checkBox.isSelected());
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
        this.getPanel().getVariabilityCheckBox(variability).setSelected(flag);
        this.getPanel().getVariabilityComboBox(variability).setEnabled(flag);
    }
    
    /**
     * Method responsible for setting the Inclusive Variability Flag.
     * @param variability Inclusive Variability.
     * @param flag Flag.
     */
    private void setInclusiveFlag(Variability variability, boolean flag) {
        this.getPanel().getVariabilityCheckBox(variability).setSelected(flag);
        this.getPanel().getVariabilityCheckBox(variability).setEnabled(false);
        for (Element variant : variability.getVariants()) {
            this.getPanel().getVariantCheckBox(variability, variant).setSelected(false);
            this.getPanel().getVariantCheckBox(variability, variant).setEnabled(flag);
        }
    }
    
    @Override
    protected Instance getInstance() {
        return this.getPanel().getInstance();
    }
    
    @Override
    public PanelBaseVarPoints getPanel() {
        return (PanelBaseVarPoints) this.panel;
    }
}