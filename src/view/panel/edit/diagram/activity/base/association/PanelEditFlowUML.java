package view.panel.edit.diagram.activity.base.association;

import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.association.FlowUML;
import view.panel.base.diagram.activity.base.association.PanelBaseFlowUML;
import view.panel.edit.base.PanelEditAssociation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditFlowUML</b>.</p>
 * <p>Class responsible for defining a <b>Flow UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-04
 * @see    model.structural.diagram.activity.base.association.FlowUML
 * @see    view.panel.edit.base.PanelEditAssociation
 */
public final class PanelEditFlowUML extends PanelEditAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Activity Diagram.
     * @param flow Flow UML.
     */
    public PanelEditFlowUML(ViewMenu view, ActivityDiagram diagram, FlowUML flow) {
        super(view, diagram, flow);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseFlowUML();
    }
    
    /**
     * Method responsible for adding the Panel Base Flow UML.
     */
    protected void addPanelBaseFlowUML() {
        addPanel("base_flow", new PanelBaseFlowUML(viewMenu, getDiagram(), getAssociation()));
        createScrollPane("base_flow", getPanelBaseFlowUML());
        getScrollPane("base_flow").setViewportView(getPanelBaseFlowUML());
        tabbedPane.add("Flow", getScrollPane("base_flow"));
    }
    
    /**
     * Method responsible for returning the Panel Base Flow UML.
     * @return Panel Base Flow UML.
     */
    public PanelBaseFlowUML getPanelBaseFlowUML() {
        return (PanelBaseFlowUML) getPanel("base_flow");
    }
    
    @Override
    public FlowUML getAssociation() {
        return (FlowUML) association;
    }
    
    @Override
    public ActivityDiagram getDiagram() {
        return (ActivityDiagram) diagram;
    }
}