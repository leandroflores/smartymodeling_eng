package view.panel.edit.diagram.activity.base.association;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
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
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseFlowUML();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Flow UML.
     */
    protected void addPanelBaseFlowUML() {
        this.addPanel("panelBaseFlowUML", new PanelBaseFlowUML(this.viewMenu, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseAssociation",  this.getPanelBaseFlowUML());
        this.getScrollPanelBaseAssociation().setViewportView(this.getPanelBaseFlowUML());
        this.tabbedPane.add("Flow", this.getScrollPanelBaseAssociation());
    }
    
    @Override
    public ActivityDiagram getDiagram() {
        return (ActivityDiagram) this.diagram;
    }
    
    @Override
    public FlowUML getAssociation() {
        return (FlowUML) this.association;
    }

    /**
     * Method responsible for returning the Panel Base Flow UML.
     * @return Panel Base Flow UML.
     */
    public PanelBaseFlowUML getPanelBaseFlowUML() {
        return (PanelBaseFlowUML) this.getPanel("panelBaseFlowUML");
    }
}