package view.panel.new_.base.product.instance;

import controller.view.panel.new_.base.product.instance.ControllerPanelBaseVarPoints;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import model.structural.base.Element;
import model.structural.base.product.Instance;
import model.structural.base.variability.Variability;
import view.panel.new_.base.product.PanelNewInstance;

/**
 * <p>Class of View <b>PanelBaseVarPoints</b>.</p> 
 * <p>Class responsible for defining a <b>Variation Points Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBaseVarPoints
 * @see    model.structural.base.product.Instance
 * @see    view.panel.new_.base.product.instance.PanelBase
 */
public final class PanelBaseVarPoints extends PanelBase {
    private Integer index;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Instance.
     * @param instance Instance.
     */
    public PanelBaseVarPoints(PanelNewInstance panel, Instance instance) {
        super(panel, instance);
        controller = new ControllerPanelBaseVarPoints(this);
        setDefaultProperties();
        addHeader();
        addComponents();
        addFooter();
        updateFlag();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    /**
     * Method responsible for adding the Header.
     */
    public void addHeader() {
        add(createLabel("Solve the Variation Points:"), createConstraints(2, 1, 0, 0));
    }
    
    @Override
    protected void addComponents() {
        index = 2;
        for (Variability variability : getInstance().getDiagram().getVariabilitiesList())
               addVariability(variability);
    }
    
    /**
     * Method responsible for adding the Variability.
     * @param variability Variability.
     */
    private void addVariability(Variability variability) {
        index++;
        add(createVariabilityCheckBox(variability), createConstraints(1, 1, 0, index));
        if (variability.getConstraint().toLowerCase().trim().equals("inclusive"))
            addInclusiveVariability(variability);
        else
            addExclusiveVariability(variability);
    }
    
    /**
     * Method responsible for adding a Inclusive Variability.
     * @param variability Inclusive Variability.
     */
    private void addInclusiveVariability(Variability variability) {
        for (Element variant : variability.getVariants()) {
            add(createVariantCheckBox(variability, variant), createConstraints(1, 1, 1, index));
            index++;
        }
    }
    
    /**
     * Method responsible for adding a Exclusive Variability.
     * @param variability Exclusive Variability.
     */
    private void addExclusiveVariability(Variability variability) {
        add(createVariabilityComboBox(variability), createConstraints(1, 1, 1, index));
        index++;
    }
    
    /**
     * Method responsible for returning a new Variability Check Box.
     * @param  variability Variability.
     * @return New Variability Check Box.
     */
    public JCheckBox createVariabilityCheckBox(Variability variability) {
        JCheckBox checkBox = createCheckBox("checkBox" + variability.getId(), "", getPanelNew().isVariationPoint(variability.getVariationPoint()));
                  checkBox.setSelected(getPanelNew().isVariationPoint(variability.getVariationPoint()));
                  checkBox.setEnabled(false);
                  checkBox.setName(variability.getId());
                  checkBox.setText(variability.getName() + ": ");
                  checkBox.setFont(new Font("Arial", Font.BOLD, 15));
                  checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        return    checkBox;
    }
    
    /**
     * Method responsible for returning a New Variant Check Box.
     * @param variability Variability.
     * @param element Variant.
     * @return New Variant Check Box.
     */
    public JCheckBox createVariantCheckBox(Variability variability, Element element) {
        JCheckBox checkBox = createCheckBox("checkBox" + variability.getId() + element.getId(), element.getName(), false);
                  checkBox.setName(variability.getId() + "|" + element.getId());
                  checkBox.setSelected(false);
                  checkBox.setEnabled(true);
        return    checkBox;
    }
    
    /**
     * Method responsible for returning a New Variability Combo Box.
     * @param  variability Variability.
     * @return New Variability Combo Box.
     */
    public JComboBox createVariabilityComboBox(Variability variability) {
        JComboBox comboBox = createComboBox("comboBox" + variability.getId(), variability.getVariants().toArray(), 200);
                  comboBox.setName(variability.getId());
                  comboBox.setMaximumSize(new Dimension(450, 30));
                  comboBox.setMinimumSize(new Dimension(450, 30));
        return    comboBox;
    }
    
    @Override
    public void addFooter() {
        index++;
        add(getFooter(), createConstraints(2, 1, 0, index));
    }
    
    /**
     * Method responsible for updating the Flags.
     */
    private void updateFlag() {
        List<Variability> list = getPanelNew().getVariabilities();
        for (Variability  variability : instance.getDiagram().getVariabilitiesList())
            getController().setFlag(variability, list.contains(variability));
    }
    
    /**
     * Method responsible for returning the Variability Check Box.
     * @param  variability Variability.
     * @return Variability Check Box.
     */
    public JCheckBox getVariabilityCheckBox(Variability variability) {
        return getCheckBox("checkBox" + variability.getId());
    }
    
    /**
     * Method responsible for returning the Variant Check Box.
     * @param  variability Variability.
     * @param  element Element.
     * @return Variant Check Box.
     */
    public JCheckBox getVariantCheckBox(Variability variability, Element element) {
        return getCheckBox("checkBox" + variability.getId() + element.getId());
    }
    
    /**
     * Method responsible for returning the Variability Combo Box.
     * @param  variability Variability.
     * @return Variability Combo Box.
     */
    public JComboBox getVariabilityComboBox(Variability variability) {
        return getComboBox("comboBox" + variability.getId());
    }
    
    @Override
    public ControllerPanelBaseVarPoints getController() {
        return (ControllerPanelBaseVarPoints) controller;
    }
}