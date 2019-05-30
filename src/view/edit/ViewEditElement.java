package view.edit;

import controller.view.edit.ControllerViewEditElement;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Element;
import view.edit.panel.base.PanelBaseElement;
import view.edit.panel.stereotype.PanelStereotype;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditElement</b>.</p>
 * <p>Class responsible for defining the <b>Element Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  30/05/2019
 * @see    controller.view.edit.ControllerViewEditElement
 * @see    model.structural.base.Element
 * @see    view.edit.ViewEdit
 */
public final class ViewEditElement extends ViewEdit {
    private final Element element;
    private PanelBaseElement panelBaseElement;
    private PanelStereotype  panelStereotype;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param element Element.
     */
    public ViewEditElement(PanelModeling panel, Element element) {
        super(panel);
        this.element    = element;
        this.controller = new ControllerViewEditElement(this);
        this.title      = "Edit Element";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 320);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
        
        this.addPanelBaseElement();
        this.addPanelStereotype();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Element.
     */
    private void addPanelBaseElement() {
        this.panelBaseElement = new PanelBaseElement();
        this.createScrollPane("scrollPanelBaseElement", this.panelBaseElement);
        this.getScrollPanelBaseElement().setViewportView(this.panelBaseElement);
        this.tabbedPane.add("Element", this.getScrollPanelBaseElement());
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    private void addPanelStereotype() {
        System.out.println("Element: " + this.element);
        this.panelStereotype  = new PanelStereotype(this.getViewMenu().getProject(), this.element);
        this.createScrollPane("scrollPanelStereotype",  this.panelStereotype);
        this.getScrollPanelStereotype().setViewportView(this.panelStereotype);
        this.tabbedPane.add("Stereotype", this.getScrollPanelStereotype());
    }
    
    @Override
    public void setValues() {
        this.panelBaseElement.getNameTextField().setText(this.element.getName());
        this.panelBaseElement.getMandatoryCheckBox().setSelected(this.element.isMandatory());
        
        this.panelBaseElement.getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return this.element;
    }
    
    /**
     * Method responsible for returning Scroll Panel Base Element.
     * @return Scroll Panel Base Element.
     */
    public JScrollPane getScrollPanelBaseElement() {
        return this.scrollPanes.get("scrollPanelBaseElement");
    }
    
    /**
     * Method responsible for returning Panel Data Element.
     * @return Panel Data Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return this.panelBaseElement;
    }
    
    /**
     * Method responsible for returning Scroll Panel Stereotype.
     * @return Scroll Panel Stereotype.
     */
    public JScrollPane getScrollPanelStereotype() {
        return this.scrollPanes.get("scrollPanelStereotype");
    }
    
    /**
     * Method responsible for returning Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return this.panelStereotype;
    }
}