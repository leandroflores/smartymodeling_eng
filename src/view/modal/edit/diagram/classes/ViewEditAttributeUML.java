package view.modal.edit.diagram.classes;

import controller.view.modal.edit.diagram.classes.base.ControllerViewEditAttributeUML;
import java.awt.Dimension;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.classes.base.PanelBaseAttributeUML;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.edit.diagram.classes.base.PanelEditAttributeUML;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditAttributeUML</b>.</p>
 * <p>Class responsible for defining the <b>Attribute UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-05
 * @see    controller.view.modal.edit.diagram.classes.base.ControllerViewEditAttributeUML
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    view.modal.edit.ViewEdit
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
        this.addPanelEditAttributeUML();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Attribute UML.
     */
    private void addPanelEditAttributeUML() {
        this.addPanel("panelEditAttribute", new PanelEditAttributeUML(this.view, this.diagram, this.attribute));
        this.getPanel("panelEditAttribute").setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanel("panelEditAttribute"));
    }
    
    /**
     * Method responsible for returning the Panel Base Attribute UML.
     * @return Panel Base Attribute UML.
     */
    public PanelBaseAttributeUML getPanelBaseAttributeUML() {
        return ((PanelEditAttributeUML) this.getPanel("panelEditAttribute")).getPanelBaseAttributeUML();
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return ((PanelEditAttributeUML) this.getPanel("panelEditAttribute")).getPanelStereotype();
    }
}