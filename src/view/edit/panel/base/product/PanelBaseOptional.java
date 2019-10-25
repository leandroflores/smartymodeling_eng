package view.edit.panel.base.product;

import controller.view.edit.panel.base.product.ControllerPanelBaseOptional;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.structural.base.Element;
import view.Panel;
import view.new_.product.ViewNewInstance;

/**
 * <p>Class of View <b>PanelBaseOptional</b>.</p> 
 * <p>Class responsible for defining the for showing the <b>Optional Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  08/10/2019
 * @see    controller.view.edit.panel.base.product.ControllerPanelBaseOptional
 * @see    model.structural.base.product.Instance
 * @see    view.Panel
 */
public class PanelBaseOptional extends Panel {
    private final ViewNewInstance viewNew;
    
    /**
     * Default constructor method of Class.
     * @param view View New Instance.
     */
    public PanelBaseOptional(ViewNewInstance view) {
        this.viewNew    = view;
        this.controller = new ControllerPanelBaseOptional(this);
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
        this.addLines(1);
    }
    
    /**
     * Method responsible for returning the Elements Panel.
     * @return Elements Panel.
     */
    private JPanel createElementsPanel() {
        JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(0, 4));
        for (Element element : this.viewNew.getInstance().getDiagram().filterOptionalElements())
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
     * Method responsible for returning the View New Instance.
     * @return View New Instance.
     */
    public ViewNewInstance getViewNewInstance() {
        return this.viewNew;
    }
    
    /**
     * Method responsible for returning the Check Box by Element.
     * @param  element Element.
     * @return Check Box.
     */
    public JCheckBox getCheckBox(Element element) {
        return this.checkBoxes.get("checkBox" + element.getId());
    }
    
    /**
     * Method responsible for returning the Elements Scroll Pane.
     * @return Elements Scroll Pane.
     */
    public JScrollPane getElementsScrollPane() {
        return this.scrollPanes.get("elementsScrollPane");
    }
}