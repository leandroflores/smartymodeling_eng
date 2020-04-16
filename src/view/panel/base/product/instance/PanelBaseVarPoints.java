package view.panel.base.product.instance;

import controller.view.edit.panel.base.product.instance.ControllerPanelBaseVarPoints;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.panel.Panel;
import view.new_.product.ViewNewInstance;

/**
 * <p>Class of View <b>PanelBaseVarPoints</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Variation Points Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/10/2019
 * @see    controller.view.edit.panel.base.product.
 * @see    model.structural.base.product.Instance
 * @see    view.panel.Panel
 */
public class PanelBaseVarPoints extends Panel {
    private final ViewNewInstance viewNew;
    
    /**
     * Default constructor method of Class.
     * @param view View New Instance.
     */
    public PanelBaseVarPoints(ViewNewInstance view) {
        this.viewNew    = view;
        this.controller = new ControllerPanelBaseVarPoints(this);
        this.initComponents();
    }
    
    /**
     * Method responsible for initializing the Components.
     */
    private void initComponents() {
        this.setPreferredSize(new Dimension(600, 350));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.updateElements();
    }
    
    /**
     * Method responsible for adding the Header.
     */
    public void addHeader() {
        this.add(this.createLabel("Solve the Variation Points for the New Instance:"));
    }
    
    @Override
    protected void addComponents() {
        this.createScrollPane("variabilitiesScrollPane");
        this.getVariabilitiesScrollPane().setViewportView(this.createVariabilitiesPanel());
        this.getVariabilitiesScrollPane().setPreferredSize(new Dimension(500, 250));
        this.add(this.getVariabilitiesScrollPane());
//        this.addLines(1);
    }
    
    /**
     * Method responsible for returning the Variabilities Panel.
     * @return Variabilities Panel.
     */
    private JPanel createVariabilitiesPanel() {
        JPanel panel = new JPanel();
               panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
               panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (Variability variability : this.viewNew.getInstance().getDiagram().getVariabilitiesList())
               this.addVariability(panel, variability);
        return panel;
    }
    
    /**
     * Method responsible for adding the Variability to Panel.
     * @param panel Panel.
     * @param variability Variability.
     */
    private void addVariability(JPanel panel, Variability variability) {
        JPanel newPanel = new JPanel();
               newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
               newPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
               newPanel.add(this.createVariabilityCheckBox(variability));
                   this.addVariabilityPanel(newPanel, variability);
               newPanel.add(this.createLabel(" "));
               panel.add(newPanel);
    }
    
    /**
     * Method responsible for adding the Variability Panel.
     * @param panel New Panel.
     * @param variability Variability.
     */
    private void addVariabilityPanel(JPanel panel, Variability variability) {
        if (variability.getConstraint().toLowerCase().trim().equals("inclusive"))
            this.addInclusiveVariability(panel, variability);
        else
            this.addExclusiveVariability(panel, variability);
    }
    
    /**
     * Method responsible for returning a new Variability Check Box.
     * @param  variability Variability.
     * @return New Variability Check Box.
     */
    public JCheckBox createVariabilityCheckBox(Variability variability) {
        JCheckBox checkBox = this.createCheckBox("checkBox" + variability.getId(), "", this.viewNew.isVariationPoint(variability.getVariationPoint()));
                  checkBox.setSelected(this.viewNew.isVariationPoint(variability.getVariationPoint()));
                  checkBox.setEnabled(false);
                  checkBox.setName(variability.getId());
                  checkBox.setText(variability.getName() + ": ");
                  checkBox.setFont(new Font("Arial", Font.BOLD, 15));
                  checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        return    checkBox;
    }
    
    /**
     * Method responsible for adding a Inclusive Variability.
     * @param panel Panel.
     * @param variability Inclusive Variability.
     */
    private void addInclusiveVariability(JPanel panel, Variability variability) {
        for (Element variant : variability.getVariants()) {
            JCheckBox checkBoxVariant = this.createVariantCheckBox(variability, variant);
                      panel.add(checkBoxVariant);
        }
        panel.add(this.createLabel(" "));
    }
    
    /**
     * Method responsible for adding a Exclusive Variability.
     * @param panel Panel.
     * @param variability Exclusive Variability.
     */
    private void addExclusiveVariability(JPanel panel, Variability variability) {
        panel.add(this.createVariabilityComboBox(variability));
        panel.add(this.createLabel(" "));
    }
    
    /**
     * Method responsible for returning a New Variant Check Box.
     * @param variability Variability.
     * @param element Variant.
     * @return New Variant Check Box.
     */
    public JCheckBox createVariantCheckBox(Variability variability, Element element) {
        JCheckBox checkBox = this.createCheckBox("checkBox" + variability.getId() + element.getId(), element.getName(), false);
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
        JComboBox comboBox = this.createComboBox("comboBox" + variability.getId(), variability.getVariants().toArray(), 200);
                  comboBox.setName(variability.getId());
                  comboBox.setMaximumSize(new Dimension(450, 30));
                  comboBox.setMinimumSize(new Dimension(450, 30));
        return    comboBox;
    }
    
    /**
     * Method responsible for updating the Elements.
     */
    private void updateElements() {
        List<Variability> variabilities = this.viewNew.getVariabilities();
        for (Variability  variability   : this.viewNew.getInstance().getDiagram().getVariabilitiesList())
            this.getController().setFlag(variability, variabilities.contains(variability));
    }
    
    /**
     * Method responsible for returning the View New Instance.
     * @return View New Instance.
     */
    public ViewNewInstance getViewNewInstance() {
        return this.viewNew;
    }
    
    /**
     * Method responsible for returning the View Controller.
     * @return View Controller.
     */
    public ControllerPanelBaseVarPoints getController() {
        return (ControllerPanelBaseVarPoints) this.controller;
    }
    
    /**
     * Method responsible for returning the Variability Check Box.
     * @param  variability Variability.
     * @return Variability Check Box.
     */
    public JCheckBox getVariabilityCheckBox(Variability variability) {
        return this.getCheckBox("checkBox" + variability.getId());
    }
    
    /**
     * Method responsible for returning the Variant Check Box.
     * @param  variability Variability.
     * @param  element Element.
     * @return Variant Check Box.
     */
    public JCheckBox getVariantCheckBox(Variability variability, Element element) {
        return this.getCheckBox("checkBox" + variability.getId() + element.getId());
    }
    
    /**
     * Method responsible for returning the Variability Combo Box.
     * @param  variability Variability.
     * @return Variability Combo Box.
     */
    public JComboBox getVariabilityComboBox(Variability variability) {
        return this.getComboBox("comboBox" + variability.getId());
    }
    
    /**
     * Method responsible for returning the Variabilities Scroll Pane.
     * @return Variabilities Scroll Pane.
     */
    public JScrollPane getVariabilitiesScrollPane() {
        return this.getScrollPane("variabilitiesScrollPane");
    }
}