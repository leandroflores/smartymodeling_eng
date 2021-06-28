package view.panel.edit.diagram.sequence.base;

import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import view.panel.base.diagram.sequence.base.PanelBaseInstanceUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditInstanceUML</b>.</p> 
 * <p>Class responsible for defining a <b>Instance UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-03
 * @see    model.structural.diagram.sequence.base.InstanceUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditInstanceUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     * @param instance Instance UML.
     */
    public PanelEditInstanceUML(ViewMenu view, SequenceDiagram diagram, InstanceUML instance) {
        super(view, diagram, instance);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseInstanceUML();
        addPanelDependency();
    }
    
    /**
     * Method responsible for adding the Panel Base Instance UML.
     */
    protected void addPanelBaseInstanceUML() {
        addPanel("base_instance", new PanelBaseInstanceUML(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_instance", getPanelBaseInstanceUML());
        getScrollPane("base_instance").setViewportView(getPanelBaseInstanceUML());
        tabbedPane.add("Instance", getScrollPane("base_instance"));
    }
    
    /**
     * Method responsible for returning the Panel Base Instance UML.
     * @return Panel Base Instance UML.
     */
    public PanelBaseInstanceUML getPanelBaseInstanceUML() {
        return (PanelBaseInstanceUML) getPanel("base_instance");
    }
    
    @Override
    public InstanceUML getElement() {
        return (InstanceUML) element;
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) diagram;
    }
}