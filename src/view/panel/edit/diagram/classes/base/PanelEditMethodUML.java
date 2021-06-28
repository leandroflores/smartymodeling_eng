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
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseMethod();
        addPanelStereotype();
        addPanelDependency();
        addPanelParametersUML();
    }
    
    /**
     * Method responsible for adding the Panel Base Method.
     */
    private void addPanelBaseMethod() {
        if (getElement().getEntity().isClass())
            addPanelBaseMethodUML();
        else
            addPanelBaseMethodAbstractUML();
    }
    
    /**
     * Method responsible for adding the Panel Base Method UML.
     */
    protected void addPanelBaseMethodUML() {
        addPanel("base_method", new PanelBaseMethodUML(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_method", getPanelBaseMethodUML());
        getScrollPane("base_method").setViewportView(getPanelBaseMethodUML());
        tabbedPane.add("Method", getScrollPane("base_method"));
    }
    
    /**
     * Method responsible for adding the Panel Base Method Abstract UML.
     */
    protected void addPanelBaseMethodAbstractUML() {
        addPanel("base_method", new PanelBaseMethodAbsUML(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_method", getPanelBaseMethodAbsUML());
        getScrollPane("base_method").setViewportView(getPanelBaseMethodAbsUML());
        tabbedPane.add("Method", getScrollPane("base_method"));
    }
    
    /**
     * Method responsible for adding the Panel Parameters UML.
     */
    protected void addPanelParametersUML() {
        addPanel("parameters", new PanelParametersUML(viewMenu, getDiagram(), getElement()));
        createScrollPane("parameters", getPanelParametersUML());
        getScrollPane("parameters").setViewportView(getPanelParametersUML());
        tabbedPane.add("Parameters", getScrollPane("parameters"));
    }
    
    /**
     * Method responsible for returning the Panel Base Method UML.
     * @return Panel Base Method UML.
     */
    public PanelBaseMethodUML getPanelBaseMethodUML() {
        return (PanelBaseMethodUML) getPanel("base_method");
    }
    
    /**
     * Method responsible for returning the Panel Base Method Abs UML.
     * @return Panel Base Method Abs UML.
     */
    public PanelBaseMethodAbsUML getPanelBaseMethodAbsUML() {
        return (PanelBaseMethodAbsUML) getPanel("base_method");
    }
    
    /**
     * Method responsible for returning the Panel Parameters UML.
     * @return Panel Parameters UML.
     */
    public PanelParametersUML getPanelParametersUML() {
        return (PanelParametersUML) getPanel("parameters");
    }
    
    @Override
    public MethodUML getElement() {
        return (MethodUML) element;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
}