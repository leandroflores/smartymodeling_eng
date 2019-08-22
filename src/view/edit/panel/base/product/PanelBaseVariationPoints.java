package view.edit.panel.base.product;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.Panel;
import view.new_.product.ViewNewProduct;

/**
 * <p>Class of View <b>PanelBaseVariationPoints</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Variation Points Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/08/2019
 * @see    controller.view.edit.panel.base.product.
 * @see    model.structural.base.product.Product
 * @see    view.Panel
 */
public class PanelBaseVariationPoints extends Panel {
    private final ViewNewProduct view;
    
    /**
     * Default constructor method of Class.
     * @param view View New Product.
     */
    public PanelBaseVariationPoints(ViewNewProduct view) {
        this.view       = view;
//        this.controller = new ControllerPainelPontosDeVariacao(this);
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
        this.updateComponentes();
    }
    
    /**
     * Method responsible for adding the Header.
     */
    public void addHeader() {
        this.add(this.createLabel("Solve the Variation Points for the new Product:"));
    }
    
    @Override
    protected void addComponents() {
        this.createScrollPane("variabilitiesScrollPane");
        this.getVariabilitiesScrollPane().setViewportView(this.createVariabilitiesPanel());
        this.getVariabilitiesScrollPane().setPreferredSize(new Dimension(500, 200));
        this.add(this.getVariabilitiesScrollPane());
        this.addLines(1);
    }
    
    /**
     * Method responsible for returning the Variabilities Panel.
     * @return Variabilities Panel.
     */
    private JPanel createVariabilitiesPanel() {
        JPanel panel = new JPanel();
//        List<Variabilidade> variabilidades = this.view.getDiagrama().getListaVariabilidades();
        for (Variability variability : this.view.getDiagram().getVariabilitiesList())
               this.addVariability(panel, variability);
//               panel.setPreferredSize(new Dimension(480, variabilidades.size() * 70));
        return panel;
    }
    
    /**
     * Method responsible for adding the Variability to Panel.
     * @param panel Panel.
     * @param variability Variability.
     */
    private void addVariability(JPanel panel, Variability variability) {
        panel.add(this.createVariabilityCheckBox(variability));
        panel.add(this.createLabel(variability.getName() + ": "));
        if (variability.getConstraint().toLowerCase().trim().equals("inclusive"))
            this.addInclusiveVariability(panel, variability);
        else
            this.addExclusiveVariability(panel, variability);
        panel.add(this.createLabel("", 500));
    }
    
    /**
     * Method responsible for returning ta new Variability Check Box.
     * @param  variability Variability.
     * @return New Variability Check Box.
     */
    public JCheckBox createVariabilityCheckBox(Variability variability) {
        JCheckBox checkBox = this.createCheckBox("checkBox" + variability.getId(), "", this.view.isVariationPoint(variability.getVariationPoint()));
                  checkBox.setSelected(this.view.isVariationPoint(variability.getVariationPoint()));
                  checkBox.setEnabled(false);
                  checkBox.setName(variability.getId());
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
    }
    
    /**
     * Method responsible for adding a Exclusive Variability.
     * @param panel Panel.
     * @param variability Exclusive Variability.
     */
    private void addExclusiveVariability(JPanel panel, Variability variability) {
        panel.add(this.createVariabilityComboBox(variability));
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
        return    comboBox;
    }
    
    /**
     * Method responsible for updating the Elements.
     */
    private void updateComponentes() {
        List<Variability> variabilities = this.view.filterVariabilities();
//        for (Variability  variability   : this.view.getDiagram().getVariabilitiesList())
//            ((ControllerPainelPontosDeVariacao) controller).setFlag(variability, variabilities.contains(variability));
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    public void addFooter() {
        this.add(this.createButton("backButton", "  Back  ", "Back", "back.png"));
        this.add(this.createButton("nextButton", "  Next  ", "Next", "next.png"));
        
        this.getBackButton().setPreferredSize(new Dimension(150, 30));
        this.getNextButton().setPreferredSize(new Dimension(150, 30));
    }
    
    /**
     * Method responsible for returning the View New Product.
     * @return View New Product.
     */
    public ViewNewProduct getViewNewProduct() {
        return this.view;
    }
    
    /**
     * Method responsible for returning the Variability Check Box.
     * @param  variability Variability.
     * @return Variability Check Box.
     */
    public JCheckBox getVariabilityCheckBox(Variability variability) {
        return this.checkBoxes.get("checkBox" + variability.getId());
    }
    
    /**
     * Method responsible for returning the Variant Check Box.
     * @param  variability Variability.
     * @param  element Element.
     * @return Variant Check Box.
     */
    public JCheckBox getVariantCheckBox(Variability variability, Element element) {
        return this.checkBoxes.get("checkBox" + variability.getId() + element.getId());
    }
    
    /**
     * Method responsible for returning the Variability Combo Box.
     * @param  variability Variability.
     * @return Variability Combo Box.
     */
    public JComboBox getVariabilityComboBox(Variability variability) {
        return this.comboBoxes.get("comboBox" + variability.getId());
    }
    
    /**
     * Method responsible for returning the Variabilities Scroll Pane.
     * @return Variabilities Scroll Pane.
     */
    public JScrollPane getVariabilitiesScrollPane() {
        return this.scrollPanes.get("variabilitiesScrollPane");
    }
    
    /**
     * Method responsible for returning the Back Button.
     * @return Back Button.
     */
    public JButton getBackButton() {
        return this.buttons.get("backButton");
    }
    
    /**
     * Method responsible for returning the Next Button.
     * @return Next Button.
     */
    public JButton getNextButton() {
        return this.buttons.get("nextButton");
    }
}