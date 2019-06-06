package view.edit.classs;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.AttributeUML;
import view.edit.ViewEdit;
import view.edit.panel.base.classs.PanelBaseAttribute;
import view.edit.panel.stereotype.PanelStereotype;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditAttribute</b>.</p>
 * <p>Class responsible for defining the <b>Attribute Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/06/2019
 * @see    controller.view.edit.c
 * @see    model.structural.diagram.classs.base.AttributeUML
 * @see    view.edit.ViewEdit
 */
public final class ViewEditAttribute extends ViewEdit {
    private final ClassDiagram diagram;
    private final AttributeUML attribute;
    private PanelBaseAttribute panelBaseAttribute;
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
//        this.controller = new ControllerViewEditarAtributo(this);
        this.title      = "Edit Attribute";
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
        this.panelBaseAttribute = new PanelBaseAttribute(this.diagram, this.attribute);
        this.createScrollPane("scrollPanelBaseAttribute", this.panelBaseAttribute);
        this.getScrollPanelBaseAttribute().setViewportView(this.panelBaseAttribute);
        this.tabbedPane.add("Attribute", this.getScrollPanelBaseAttribute());
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    private void addPanelStereotype() {
        this.panelStereotype  = new PanelStereotype(this.getViewMenu().getProject(), this.attribute);
        this.createScrollPane("scrollPanelStereotype",  this.panelStereotype);
        this.getScrollPanelStereotype().setViewportView(this.panelStereotype);
        this.tabbedPane.add("Stereotype", this.getScrollPanelStereotype());
    }
    
    @Override
    public void setValues() {
        this.panelBaseAttribute.getVisibilityComboBox().setSelectedItem(this.attribute.getVisibility());
        this.panelBaseAttribute.getNameTextField().setText(this.attribute.getName());
        this.panelBaseAttribute.getTypeComboBox().setSelectedItem(this.attribute.getTypeUML());
        this.panelBaseAttribute.getStaticCheckBox().setSelected(this.attribute.isStatic());
        this.panelBaseAttribute.getFinalCheckBox().setSelected(this.attribute.isFinal());
        
        this.panelBaseAttribute.getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Attribute.
     * @return Attribute.
     */
    public AttributeUML getAttribute() {
        return this.attribute;
    }
    
    /**
     * Method responsible for returning the Panel Base Attribute.
     * @return Panel Base Attribute.
     */
    public PanelBaseAttribute getPanelBaseAttribute() {
        return this.panelBaseAttribute;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Attribute.
     * @return Scroll Panel Base Attribute.
     */
    public JScrollPane getScrollPanelBaseAttribute() {
        return this.scrollPanes.get("scrollPanelBaseAttribute");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Stereotype.
     * @return Scroll Panel Stereotype.
     */
    public JScrollPane getScrollPanelStereotype() {
        return this.scrollPanes.get("scrollPanelStereotype");
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return this.panelStereotype;
    }
}