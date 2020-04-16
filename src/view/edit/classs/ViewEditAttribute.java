package view.edit.classs;

import controller.view.edit.classes.ControllerViewEditAttribute;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import view.edit.ViewEdit;
import view.edit.panel.base.classes.PanelBaseAttributeUML;
import view.edit.panel.stereotype.PanelStereotype;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditAttribute</b>.</p>
 * <p>Class responsible for defining the <b>Attribute Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/06/2019
 * @see    controller.view.edit.classes.ControllerViewEditAttribute
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    view.edit.ViewEdit
 */
public final class ViewEditAttribute extends ViewEdit {
    private final ClassDiagram diagram;
    private final AttributeUML attribute;
    private PanelBaseAttributeUML panelBaseAttributeUML;
    private PanelStereotype    panelStereotype;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Class Diagram.
     * @param attribute Attribute UML.
     */
    public ViewEditAttribute(PanelModeling panel, ClassDiagram diagram, AttributeUML attribute) {
        super(panel);
        this.diagram    = diagram;
        this.attribute  = attribute;
        this.controller = new ControllerViewEditAttribute(this);
        this.title      = "Edit Attribute Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 445);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 325));
        
        this.addPanelBaseAttribute();
        this.addPanelStereotype();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Attribute.
     */
    private void addPanelBaseAttribute() {
        this.panelBaseAttributeUML = new PanelBaseAttributeUML(this.getViewMenu(), this.diagram, this.attribute);
        this.createScrollPane("scrollPanelBaseAttribute", this.panelBaseAttributeUML);
        this.getScrollPanelBaseAttribute().setViewportView(this.panelBaseAttributeUML);
        this.tabbedPane.add("Attribute", this.getScrollPanelBaseAttribute());
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    private void addPanelStereotype() {
        this.panelStereotype  = new PanelStereotype(this.getViewMenu(), this.attribute);
        this.createScrollPane("scrollPanelStereotype",  this.panelStereotype);
        this.getScrollPanelStereotype().setViewportView(this.panelStereotype);
        this.tabbedPane.add("Stereotype", this.getScrollPanelStereotype());
    }
    
    @Override
    public void setValues() {
        this.panelBaseAttributeUML.setValues();
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Attribute.
     * @return Scroll Panel Base Attribute.
     */
    public JScrollPane getScrollPanelBaseAttribute() {
        return this.getScrollPane("scrollPanelBaseAttribute");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Stereotype.
     * @return Scroll Panel Stereotype.
     */
    public JScrollPane getScrollPanelStereotype() {
        return this.getScrollPane("scrollPanelStereotype");
    }
}