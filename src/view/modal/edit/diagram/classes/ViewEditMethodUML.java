package view.modal.edit.diagram.classes;

import controller.view.modal.edit.diagram.classes.ControllerViewEditMethodUML;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.classes.base.PanelBaseMethodUML;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditMethodUML</b>.</p>
 * <p>Class responsible for defining the <b>Method UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-06
 * @see    controller.view.modal.edit.diagram.classes.ControllerViewEditMethodUML
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
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 325));
            this.addPanelBaseMethodUML();
            this.addPanelStereotype();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Method.
     */
    private void addPanelBaseMethodUML() {
        this.addPanel("panelBaseMethodUML", new PanelBaseMethodUML(this.getViewMenu(), this.diagram, this.method));
        this.createScrollPane("scrollPanelBaseMethodUML",  this.getPanelBaseMethodUML());
        this.getScrollPanelBaseMethodUML().setViewportView(this.getPanelBaseMethodUML());
        this.tabbedPane.add("Method", this.getScrollPanelBaseMethodUML());
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    protected void addPanelStereotype() {
        this.addPanel("panelStereotype", new PanelStereotype(this.getViewMenu(), this.method));
        this.createScrollPane("scrollPanelStereotype",  this.getPanelStereotype());
        this.getScrollPanelStereotype().setViewportView(this.getPanelStereotype());
        this.tabbedPane.add("Stereotype", this.getScrollPanelStereotype());
    }
    
    /**
     * Method responsible for returning the Panel Base Method UML.
     * @return Panel Base Method UML.
     */
    public PanelBaseMethodUML getPanelBaseMethodUML() {
        return (PanelBaseMethodUML) this.getPanel("panelBaseMethodUML");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Method UML.
     * @return Scroll Panel Base Method UML.
     */
    public JScrollPane getScrollPanelBaseMethodUML() {
        return this.getScrollPane("scrollPanelBaseMethodUML");
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