package view.panel.edit.diagram.classes.base;

import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.base.diagram.classes.base.PanelBaseMethodAbsUML;
import view.panel.base.diagram.classes.base.PanelBaseMethodUML;
import view.panel.base.diagram.classes.entity.PanelParametersUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

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
    protected void addPanels() {
        this.addPanelBaseMethod();
        super.addPanelStereotype();
        super.addPanelDependency();
        this.addPanelParametersUML();
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
        this.createScrollPane("scrollPanelBaseMethodUML",  this.getPanelBaseMethodUML());
        this.getScrollPane("scrollPanelBaseMethodUML").setViewportView(this.getPanelBaseMethodUML());
        this.tabbedPane.add("Method", this.getScrollPane("scrollPanelBaseMethodUML"));
    }
    
    /**
     * Method responsible for adding the Panel Base Method Abstract UML.
     */
    protected void addPanelBaseMethodAbstractUML() {
        this.addPanel("panelBaseMethodUML", new PanelBaseMethodAbsUML(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelBaseMethodUML",  this.getPanelBaseMethodAbsUML());
        this.getScrollPane("scrollPanelBaseMethodUML").setViewportView(this.getPanelBaseMethodAbsUML());
        this.tabbedPane.add("Method", this.getScrollPane("scrollPanelBaseMethodUML"));
    }
    
    /**
     * Method responsible for adding the Panel Parameters UML.
     */
    protected void addPanelParametersUML() {
        this.addPanel("panelBaseParametersUML", new PanelParametersUML(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelParametersUML",  this.getPanelParametersUML());
        this.getScrollPane("scrollPanelParametersUML").setViewportView(this.getPanelParametersUML());
        this.tabbedPane.add("Parameters", this.getScrollPane("scrollPanelParametersUML"));
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
    
    @Override
    public MethodUML getElement() {
        return (MethodUML) this.element;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
}