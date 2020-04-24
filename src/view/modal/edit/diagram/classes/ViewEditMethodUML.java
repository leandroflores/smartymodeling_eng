package view.modal.edit.diagram.classes;

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
        super(panel);
        this.diagram    = diagram;
        this.method     = method;
        this.controller = new ControllerViewEditMethodUML(this);
        this.title      = "Edit Method Data";
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
        this.addPanelEditMethodUML();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Method UML.
     */
    private void addPanelEditMethodUML() {
        this.addPanel("panelEditMethod", new PanelEditMethodUML(this.view, this.diagram, this.method));
        this.getPanel("panelEditMethod").setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanel("panelEditMethod"));
    }
    
    /**
     * Method responsible for returning the Panel Base Method UML.
     * @return Panel Base Method UML.
     */
    public PanelBaseMethodUML getPanelBaseMethodUML() {
        return ((PanelEditMethodUML) this.getPanel("panelEditMethod")).getPanelBaseMethodUML();
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return ((PanelEditMethodUML) this.getPanel("panelEditMethod")).getPanelStereotype();
    }
}