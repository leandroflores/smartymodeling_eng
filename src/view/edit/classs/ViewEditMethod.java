package view.edit.classs;

import controller.view.edit.classs.ControllerViewEditMethod;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.MethodUML;
import view.edit.ViewEdit;
import view.edit.panel.base.classs.PanelBaseMethod;
import view.edit.panel.stereotype.PanelStereotype;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditMethod</b>.</p>
 * <p>Class responsible for defining the <b>Method Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/06/2019
 * @see    controller.view.edit.classs.ControllerViewEditMethod
 * @see    model.structural.diagram.classs.base.MethodUML
 * @see    view.edit.ViewEdit
 */
public final class ViewEditMethod extends ViewEdit {
    private final ClassDiagram diagram;
    private final MethodUML method;
    private PanelBaseMethod panelBaseMethod;
    private PanelStereotype panelStereotype;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Class Diagram.
     * @param method Method UML.
     */
    public ViewEditMethod(PanelModeling panel, ClassDiagram diagram, MethodUML method) {
        super(panel);
        this.diagram    = diagram;
        this.method     = method;
        this.controller = new ControllerViewEditMethod(this);
        this.title      = "Edit Method";
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
        this.panelBaseMethod = new PanelBaseMethod(this.diagram, this.method);
        this.createScrollPane("scrollPanelBaseMethod", this.panelBaseMethod);
        this.getScrollPanelBaseMethod().setViewportView(this.panelBaseMethod);
        this.tabbedPane.add("Method", this.getScrollPanelBaseMethod());
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    private void addPanelStereotype() {
        this.panelStereotype  = new PanelStereotype(this.getViewMenu(), this.method);
        this.createScrollPane("scrollPanelStereotype",  this.panelStereotype);
        this.getScrollPanelStereotype().setViewportView(this.panelStereotype);
        this.tabbedPane.add("Stereotype", this.getScrollPanelStereotype());
    }
    
    @Override
    public void setValues() {
        this.panelBaseMethod.setValues();
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Method.
     * @return Scroll Panel Base Method.
     */
    public JScrollPane getScrollPanelBaseMethod() {
        return this.scrollPanes.get("scrollPanelBaseMethod");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Stereotype.
     * @return Scroll Panel Stereotype.
     */
    public JScrollPane getScrollPanelStereotype() {
        return this.scrollPanes.get("scrollPanelStereotype");
    }
}