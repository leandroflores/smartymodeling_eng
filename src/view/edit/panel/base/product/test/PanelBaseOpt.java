package view.edit.panel.base.product.test;

import controller.view.edit.panel.base.product.test.ControllerPanelBaseOpt;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.structural.base.Element;
import view.Panel;
import view.new_.product.test.ViewNewProduct_Final;

/**
 * <p>Class of View <b>PanelBaseOpt</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Optional Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/08/2019
 * @see    controller.view.edit.panel.base.product.test.ControllerPanelBaseOpt
 * @see    model.structural.base.product.Product
 * @see    view.Panel
 */
public class PanelBaseOpt extends Panel {
    private final ViewNewProduct_Final view;
    
    /**
     * Default constructor method of Class.
     * @param view View New Product.
     */
    public PanelBaseOpt(ViewNewProduct_Final view) {
        this.view       = view;
        this.controller = new ControllerPanelBaseOpt(this);
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
        this.add(this.createLabel("Check the Elements for the new Product:"));
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
        for (Element element : this.view.getDiagram().filterOptionalElements())
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
    public ViewNewProduct_Final getViewNewProduct() {
        return this.view;
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