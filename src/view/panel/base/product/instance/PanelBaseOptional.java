package view.panel.base.product.instance;

import controller.view.panel.base.product.instance.ControllerPanelBaseOptional;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.structural.base.Element;
import view.new_.product.ViewNewInstance;

/**
 * <p>Class of View <b>PanelBaseOptional</b>.</p> 
 * <p>Class responsible for defining a <b>Optional Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-08
 * @see    controller.view.panel.base.product.instance.ControllerPanelBaseOptional
 * @see    model.structural.base.product.Instance
 * @see    view.panel.base.product.instance.PanelBase
 */
public final class PanelBaseOptional extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View New Instance.
     */
    public PanelBaseOptional(ViewNewInstance view) {
        super(view);
        this.controller = new ControllerPanelBaseOptional(this);
        this.setDefaultProperties();
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setPreferredSize(new Dimension(600, 350));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    /**
     * Method responsible for adding the Header.
     */
    public void addHeader() {
        this.add(this.createLabel("Check the Elements for the New Instance:"));
    }
    
    @Override
    protected void addComponents() {
        this.createScrollPane("elementsScrollPane");
        this.getElementsScrollPane().setViewportView(this.createElementsPanel());
        this.getElementsScrollPane().setPreferredSize(new Dimension(500, 250));
        this.add(this.getElementsScrollPane());
    }
    
    /**
     * Method responsible for returning the Elements Panel.
     * @return Elements Panel.
     */
    private JPanel createElementsPanel() {
        JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(0, 4));
        for (Element element : this.getInstance().getDiagram().filterOptionalElements())
               this.addElementCheckBox(panel, element);
        return panel;
    }
    
    /**
     * Method responsible for adding the Element Check Box.
     * @param panel Panel.
     * @param element Element.
     */
    private void addElementCheckBox(JPanel panel, Element element) {
        JCheckBox checkBox = this.createElementCheckBox(element);
                  checkBox.setSelected(element.isMandatory());
                  checkBox.setEnabled(!element.isMandatory());
                  panel.add(this.createLabel(""));
                  panel.add(this.createLabel(element.getName() + ": "));
                  panel.add(checkBox);
                  panel.add(this.createLabel(""));
    }
    
    /**
     * Method responsible for returning a New Element Check Box.
     * @param  element Element.
     * @return New Element Check Box.
     */
    public JCheckBox createElementCheckBox(Element element) {
        return this.createCheckBox("checkBox" + element.getId(), "Yes", element.isMandatory());
    }
    
    /**
     * Method responsible for returning the Check Box by Element.
     * @param  element Element.
     * @return Check Box.
     */
    public JCheckBox getCheckBox(Element element) {
        return this.getCheckBox("checkBox" + element.getId());
    }
    
    /**
     * Method responsible for returning the Elements Scroll Pane.
     * @return Elements Scroll Pane.
     */
    public JScrollPane getElementsScrollPane() {
        return this.getScrollPane("elementsScrollPane");
    }
}