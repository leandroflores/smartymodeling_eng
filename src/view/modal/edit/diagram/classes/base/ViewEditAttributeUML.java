package view.modal.edit.diagram.classes.base;

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
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.attribute  = attribute;
        this.controller = new ControllerViewEditAttributeUML(this);
        this.title      = "Edit Attribute Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditAttributeUML createPanelEdit() {
        return new PanelEditAttributeUML(view, diagram, attribute);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditAttributeUML getPanelEdit() {
        return (PanelEditAttributeUML) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Attribute UML.
     * @return Panel Base Attribute UML.
     */
    public PanelBaseAttributeUML getPanelBaseAttributeUML() {
        return getPanelEdit().getPanelBaseAttributeUML();
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return getPanelEdit().getPanelStereotype();
    }
}