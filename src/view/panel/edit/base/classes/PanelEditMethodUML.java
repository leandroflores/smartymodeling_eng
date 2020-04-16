package view.panel.edit.base.classes;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;
import view.edit.panel.base.classes.PanelBaseMethodAbsUML;
import view.edit.panel.base.classes.PanelBaseMethodUML;
import view.edit.panel.base.classes.entity.PanelParametersUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditMethodUML</b>.</p> 
 * <p>Class responsible for defining a <b>Method UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-17
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditMethodUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param method_ Method UML.
     */
    public PanelEditMethodUML(ViewMenu view, ClassDiagram diagram, MethodUML method_) {
        super(view, diagram, method_);
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        
        this.addPanelBaseMethod();
        super.addPanelStereotype();
        super.addPanelDependency();
        this.addPanelParametersUML();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Method.
     */
    private void addPanelBaseMethod() {
        if (this.getElement().getEntity().isClass())
            this.addPanelBaseMethodUML();
        else
            this.addPanelBaseMethodAbstractUML();
    }
    
    /**
     * Method responsible for adding the Panel Base Method UML.
     */
    protected void addPanelBaseMethodUML() {
        this.addPanel("panelBaseMethodUML", new PanelBaseMethodUML(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelBaseElement",  this.getPanelBaseMethodUML());
        this.getScrollPanelBaseElement().setViewportView(this.getPanelBaseMethodUML());
        this.tabbedPane.add("Method", this.getScrollPanelBaseElement());
    }
    
    /**
     * Method responsible for adding the Panel Base Method Abstract UML.
     */
    protected void addPanelBaseMethodAbstractUML() {
        this.addPanel("panelBaseMethodUML", new PanelBaseMethodAbsUML(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelBaseElement",  this.getPanelBaseMethodAbsUML());
        this.getScrollPanelBaseElement().setViewportView(this.getPanelBaseMethodAbsUML());
        this.tabbedPane.add("Method", this.getScrollPanelBaseElement());
    }
    
    /**
     * Method responsible for adding the Panel Parameters UML.
     */
    protected void addPanelParametersUML() {
        this.addPanel("panelBaseParametersUML", new PanelParametersUML(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelParametersUML",  this.getPanelParametersUML());
        this.getScrollPanelParametersUML().setViewportView(this.getPanelParametersUML());
        this.tabbedPane.add("Parameters", this.getScrollPanelParametersUML());
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    @Override
    public MethodUML getElement() {
        return (MethodUML) this.element;
    }
    
    /**
     * Method responsible for returning the Panel Base Method UML.
     * @return Panel Base Method UML.
     */
    public PanelBaseMethodUML getPanelBaseMethodUML() {
        return (PanelBaseMethodUML) this.getPanel("panelBaseMethodUML");
    }
    
    /**
     * Method responsible for returning the Panel Base Method Abs UML.
     * @return Panel Base Method Abs UML.
     */
    public PanelBaseMethodAbsUML getPanelBaseMethodAbsUML() {
        return (PanelBaseMethodAbsUML) this.getPanel("panelBaseMethodUML");
    }
    
    /**
     * Method responsible for returning the Panel Parameters UML.
     * @return Panel Parameters UML.
     */
    public PanelParametersUML getPanelParametersUML() {
        return (PanelParametersUML) this.getPanel("panelParametersUML");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Parameters UML.
     * @return Scroll Panel Parameters UML.
     */
    public JScrollPane getScrollPanelParametersUML() {
        return this.getScrollPane("scrollPanelParametersUML");
    }
}