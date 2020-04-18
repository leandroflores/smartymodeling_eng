package view.edit.diagram.classes;

import controller.view.edit.diagram.classes.ControllerViewEditAttributeUML;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import view.edit.ViewEdit;
import view.panel.base.diagram.classes.base.PanelBaseAttributeUML;
import view.edit.panel.stereotype.PanelStereotype;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditAttributeUML</b>.</p>
 * <p>Class responsible for defining the <b>Attribute UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-05
 * @see    controller.view.edit.diagram.classes.ControllerViewEditAttributeUML
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    view.edit.base.ViewEdit
 */
public final class ViewEditAttributeUML extends ViewEdit {
    private final ClassDiagram diagram;
    private final AttributeUML attribute;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Class Diagram.
     * @param attribute Attribute UML.
     */
    public ViewEditAttributeUML(PanelModeling panel, ClassDiagram diagram, AttributeUML attribute) {
        super(panel);
        this.diagram    = diagram;
        this.attribute  = attribute;
        this.controller = new ControllerViewEditAttributeUML(this);
        this.title      = "Edit Attribute Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 445);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 325));        
            this.addPanelBaseAttributeUML();
            this.addPanelStereotype();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Attribute.
     */
    private void addPanelBaseAttributeUML() {
        this.addPanel("panelBaseAttributeUML", new PanelBaseAttributeUML(this.getViewMenu(), this.diagram, this.attribute));
        this.createScrollPane("scrollPanelBaseAttributeUML", this.getPanelBaseAttributeUML());
        this.getScrollPanelBaseAttributeUML().setViewportView(this.getPanelBaseAttributeUML());
        this.tabbedPane.add("Attribute", this.getScrollPanelBaseAttributeUML());
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    protected void addPanelStereotype() {
        this.addPanel("panelStereotype", new PanelStereotype(this.getViewMenu(), this.attribute));
        this.createScrollPane("scrollPanelStereotype",  this.getPanelStereotype());
        this.getScrollPanelStereotype().setViewportView(this.getPanelStereotype());
        this.tabbedPane.add("Stereotype", this.getScrollPanelStereotype());
    }
    
    /**
     * Method responsible for returning the Panel Base Attribute UML.
     * @return Panel Base Attribute UML.
     */
    public PanelBaseAttributeUML getPanelBaseAttributeUML() {
        return (PanelBaseAttributeUML) this.getPanel("panelBaseAttributeUML");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Attribute UML.
     * @return Scroll Panel Base Attribute UML.
     */
    public JScrollPane getScrollPanelBaseAttributeUML() {
        return this.getScrollPane("scrollPanelBaseAttributeUML");
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return (PanelStereotype) this.getPanel("panelStereotype");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Stereotype.
     * @return Scroll Panel Stereotype.
     */
    public JScrollPane getScrollPanelStereotype() {
        return this.getScrollPane("scrollPanelStereotype");
    }
}