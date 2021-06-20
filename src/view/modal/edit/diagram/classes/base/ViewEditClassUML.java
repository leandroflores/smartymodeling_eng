package view.modal.edit.diagram.classes.base;

import controller.view.modal.edit.diagram.classes.base.ControllerViewEditClassUML;
import java.awt.Dimension;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.ClassUML;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.classes.base.PanelBaseClassUML;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.edit.diagram.classes.base.PanelEditClassUML;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditClassUML</b>.</p>
 * <p>Class responsible for defining the <b>Class UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.modal.edit.diagram.classes.base.ControllerViewEditClassUML
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditClassUML extends ViewEdit {
    private final ClassDiagram diagram;
    private final ClassUML class_;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Class Diagram.
     * @param class_ Class UML.
     */
    public ViewEditClassUML(PanelModeling panel, ClassDiagram diagram, ClassUML class_) {
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.class_     = class_;
        this.controller = new ControllerViewEditClassUML(this);
        this.title      = "Edit Class Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditClassUML createPanelEdit() {
        return new PanelEditClassUML(view, diagram, class_);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditClassUML getPanelEdit() {
        return (PanelEditClassUML) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Class UML.
     * @return Panel Base Class UML.
     */
    public PanelBaseClassUML getPanelBaseClassUML() {
        return getPanelEdit().getPanelBaseClassUML();
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return getPanelEdit().getPanelStereotype();
    }
}