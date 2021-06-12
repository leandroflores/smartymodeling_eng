package controller.view.panel.new_.base.product.instance;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import model.structural.base.Element;
import model.structural.base.product.Instance;
import model.structural.base.variability.Variability;
import view.panel.new_.base.product.instance.PanelBaseVarPoints;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVarPoints</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseVarPoints</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBase
 * @see    view.panel.new_.base.product.instance.PanelBaseVarPoints
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
            actionCheckBox((JCheckBox) event.getSource());
        else if (event.getSource() instanceof JComboBox)
            actionComboBox((JComboBox) event.getSource());
    }
    
    @Override
    protected void return_() {
        getPanel().getPanelNew().removePanelBaseVarPoints();
    }
    
    @Override
    public boolean check() {
        return checkInclusiveVariabilities()
            && checkExclusiveVariabilities();
    }
    
    /**
     * Method responsible for checking the Inclusive Variabilities.
     * @return Inclusive Variabilities checked.
     */
    public boolean checkInclusiveVariabilities() {
        for (Variability variability : getVariabilitiesSelected()) {
            if (variability.getConstraint().toLowerCase().trim().equals("inclusive")) {
                if (!checkInclusiveVariability(variability)) {
                    new ViewError(getViewNew(), "Select a Variant of " + variability.getName()).setVisible(true);
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
            if (getPanel().getVariantCheckBox(variability, variant).isSelected())
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for checking the Exclusive Variabilities.
     * @return Exclusive Variabilities checked.
     */
    public boolean checkExclusiveVariabilities() {
        for (Variability variability : getVariabilitiesSelected()) {
            if (variability.getConstraint().toLowerCase().trim().equals("exclusive")) {
                if (!checkExclusiveVariability(variability)) {
                    new ViewError(getViewNew(), "Select a Variant of " + variability.getName()).setVisible(true);
                    getPanel().getVariabilityComboBox(variability).requestFocus();
                    getPanel().getVariabilityComboBox(variability).setPopupVisible(true);
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
        Element variationPoint = (Element) getPanel().getVariabilityComboBox(variability).getSelectedItem();
        for (Variability current : getPanel().getPanelNew().getDiagram().getVariationPoints(variationPoint)) {
            if (!getPanel().getVariabilityCheckBox(current).isSelected())
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
        for (Variability variability : getPanel().getPanelNew().getDiagram().getVariabilitiesList()) {
            if (getPanel().getVariabilityCheckBox(variability).isSelected())
                filter.add(variability);
        }
        return  filter;
    }
    
    @Override
    public void next() {
        update();
        getPanel().getPanelNew().addPanelBaseArtifacts();
    }
    
    @Override
    protected void update() {
        for (Variability variability : getInstance().getDiagram().getVariabilitiesList()) {
            if (getPanel().getVariabilityCheckBox(variability).isSelected())
                insertVariability(variability);
        }
    }

    /**
     * Method responsible for insert a Variability.
     * @param variability Variability.
     */
    private void insertVariability(Variability variability) {
        if (variability.getConstraint().toLowerCase().trim().equals("exclusive"))
            insertExclusiveVariability(variability);
        else 
            insertInclusiveVariability(variability);
    }
    
    /**
     * Method responsible for insert a Exclusive Variability.
     * @param variability Exclusive Variability.
     */
    private void insertExclusiveVariability(Variability variability) {
        getPanel().getPanelNew().add(variability.getVariationPoint());
        Element element = (Element) getPanel().getVariabilityComboBox(variability).getSelectedItem();
        getPanel().getPanelNew().add(element);
    }
    
    /**
     * Method responsible for insert a Inclusive Variability.
     * @param variability Inclusive Variability.
     */
    private void insertInclusiveVariability(Variability variability) {
        getPanel().getPanelNew().add(variability.getVariationPoint());
        for (Element variant : getVariantsSelected(variability))
            getPanel().getPanelNew().add(variant);
    }
    
    /**
     * Method responsible for returning the Variants selected by Variability.
     * @param  variability Variability.
     * @return Variants selected.
     */
    private List<Element> getVariantsSelected(Variability variability) {
        List<Element> variants = new ArrayList<>();
        for (Element  variant  : variability.getVariants()) {
            if (getPanel().getVariantCheckBox(variability, variant).isSelected())
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
            actionVariant(id.substring(0, id.indexOf("|")), id.substring(id.indexOf("|") + 1));
    }
    
    /**
     * Method responsible for running the Combo Box Action.
     * @param comboBox Combo Box.
     */
    private void actionComboBox(JComboBox comboBox) {
        Variability  variability = getPanel().getPanelNew().getDiagram().getVariability(comboBox.getName());
        Element      variant     = (Element) comboBox.getSelectedItem();
        for (Element element : variability.getVariants()) {
            for (Variability current : getPanel().getPanelNew().getDiagram().getVariationPoints(element))
                setFlag(current, current.getVariationPoint().equals(variant));
        }
    }
    
    /**
     * Method responsible for running the Lock/Unlock Operation to Variant. 
     * @param variabilityId Variability Id.
     * @param variantId Variant Id.
     */
    private void actionVariant(String variabilityId, String variantId) {
        Variability variability  = getPanel().getPanelNew().getDiagram().getVariability(variabilityId);
        Element     element      = getPanel().getPanelNew().getDiagram().getElement(variantId);
        JCheckBox   checkBox     = getPanel().getVariantCheckBox(variability, element);
        for (Variability current : getPanel().getPanelNew().getDiagram().getVariationPoints(element)) {
            getPanel().getVariabilityCheckBox(current).setSelected(checkBox.isSelected());
            setFlag(current, checkBox.isSelected());
        }
    }
    
    /**
     * Method responsible for setting the Variability Flag.
     * @param variability Variability.
     * @param flag Flag.
     */
    public void setFlag(Variability variability, boolean flag) {
        if (variability.getConstraint().toLowerCase().trim().equals("exclusive"))
            setExclusiveFlag(variability, flag);
        else 
            setInclusiveFlag(variability, flag);
    }
    
    /**
     * Method responsible for setting the Exclusive Variability Flag.
     * @param variability Exclusive Variability.
     * @param flag Flag.
     */
    private void setExclusiveFlag(Variability variability, boolean flag) {
        getPanel().getVariabilityCheckBox(variability).setSelected(flag);
        getPanel().getVariabilityComboBox(variability).setEnabled(flag);
    }
    
    /**
     * Method responsible for setting the Inclusive Variability Flag.
     * @param variability Inclusive Variability.
     * @param flag Flag.
     */
    private void setInclusiveFlag(Variability variability, boolean flag) {
        getPanel().getVariabilityCheckBox(variability).setSelected(flag);
        getPanel().getVariabilityCheckBox(variability).setEnabled(false);
        for (Element variant : variability.getVariants()) {
            getPanel().getVariantCheckBox(variability, variant).setSelected(false);
            getPanel().getVariantCheckBox(variability, variant).setEnabled(flag);
        }
    }
    
    @Override
    protected Instance getInstance() {
        return getPanel().getInstance();
    }
    
    @Override
    public PanelBaseVarPoints getPanel() {
        return (PanelBaseVarPoints) panel;
    }
}