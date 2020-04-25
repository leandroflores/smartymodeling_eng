package view.panel.new_.base.product.instance;

import controller.view.panel.new_.base.product.instance.ControllerPanelBaseOptional;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import model.structural.base.Element;
import model.structural.base.product.Instance;
import view.panel.new_.base.product.PanelNewInstance;

/**
 * <p>Class of View <b>PanelBaseOptional</b>.</p> 
 * <p>Class responsible for defining a <b>Optional Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-08
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBaseOptional
 * @see    model.structural.base.product.Instance
 * @see    view.panel.new_.base.product.instance.PanelBase
 */
public final class PanelBaseOptional extends PanelBase {
    private Integer index;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Instance.
     * @param instance Instance.
     */
    public PanelBaseOptional(PanelNewInstance panel, Instance instance) {
        super(panel, instance);
        this.controller = new ControllerPanelBaseOptional(this);
        this.setDefaultProperties();
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setPreferredSize(new Dimension(600, 350));
        this.setLayout(new GridBagLayout());
    }

    /**
     * Method responsible for adding the Header.
     */
    public void addHeader() {
        this.add(this.createLabel("Select the Elements:"), this.createConstraints(2, 1, 0, 0));
    }
    
    @Override
    protected void addComponents() {
        this.index = 2;
        for (Element element : this.getInstance().getDiagram().filterOptionalElements()) {
            this.add(this.createLabel(element.getName() + ":"), this.createConstraints(1, 1, 0, this.index));
            this.add(this.createElementCheckBox(element),       this.createConstraints(1, 1, 1, this.index));
            this.index++;
        }
//        this.addElements();
//        this.createElementsPanel();
//        this.createScrollPane("elementsScrollPane");
//        this.getElementsScrollPane().setViewportView(this.createElementsPanel());
//        this.getElementsScrollPane().setPreferredSize(new Dimension(500, 250));
//        this.add(this.getElementsScrollPane());
    }
    
    /**
     * Method responsible for returning the Elements Panel.
     * @return Elements Panel.
     */
    private void addElements() {
        
//        JPanel panel = new JPanel();
//               panel.setLayout(new GridLayout(0, 4));
//        for (Element element : this.getInstance().getDiagram().filterOptionalElements())
//               this.getElementCheckBox(panel, element);
//        return panel;
    }
    
    /**
     * Method responsible for returning the Element Check Box.
     * @param  element Element.
     * @return Element Check Box.
     */
    private JCheckBox createElementCheckBox(Element element) {
        JCheckBox checkBox = this.createCheckBox("checkBox" + element.getId(), "Yes", element.isMandatory());
                  checkBox.setSelected(element.isMandatory());
                  checkBox.setEnabled(!element.isMandatory());
        return    checkBox;
    }
    
    /**
     * Method responsible for returning the Check Box by Element.
     * @param  element Element.
     * @return Check Box.
     */
    public JCheckBox getCheckBox(Element element) {
        return this.getCheckBox("checkBox" + element.getId());
    }
    
    @Override
    public void addFooter() {
        this.add(this.getFooter(), this.createConstraints(4, 1, 0, this.index));
    }
}