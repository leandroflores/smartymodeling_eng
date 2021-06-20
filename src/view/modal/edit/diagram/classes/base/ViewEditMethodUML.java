package view.modal.edit.diagram.classes.base;

import controller.view.modal.edit.diagram.classes.base.ControllerViewEditMethodUML;
import java.awt.Dimension;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.classes.base.PanelBaseMethodUML;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.edit.diagram.classes.base.PanelEditMethodUML;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditMethodUML</b>.</p>
 * <p>Class responsible for defining the <b>Method UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-06
 * @see    controller.view.modal.edit.diagram.classes.base.ControllerViewEditMethodUML
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditMethodUML extends ViewEdit {
    private final ClassDiagram diagram;
    private final MethodUML method;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Class Diagram.
     * @param method Method UML.
     */
    public ViewEditMethodUML(PanelModeling panel, ClassDiagram diagram, MethodUML method) {
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.method     = method;
        this.controller = new ControllerViewEditMethodUML(this);
        this.title      = "Edit Method Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditMethodUML createPanelEdit() {
        return new PanelEditMethodUML(view, diagram, method);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditMethodUML getPanelEdit() {
        return (PanelEditMethodUML) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Method UML.
     * @return Panel Base Method UML.
     */
    public PanelBaseMethodUML getPanelBaseMethodUML() {
        return getPanelEdit().getPanelBaseMethodUML();
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return getPanelEdit().getPanelStereotype();
    }
}