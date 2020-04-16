package view.panel.edit.base.activity.association;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.association.FlowUML;
import view.edit.panel.base.activity.PanelBaseFlowUML;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditFlowUML</b>.</p>
 * <p>Class responsible for defining a <b>Flow UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-04
 * @see    model.structural.diagram.activity.base.association.FlowUML
 * @see    view.panel.edit.base.PanelEdit
 */
public final class PanelEditFlowUML extends PanelEdit {
    private final ActivityDiagram diagram;
    private final FlowUML flowUML;
    private PanelBaseFlowUML panelBaseFlowUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Activity Diagram.
     * @param flow Flow UML.
     */
    public PanelEditFlowUML(ViewMenu viewMenu, ActivityDiagram diagram, FlowUML flow) {
        super(viewMenu);
        this.diagram = diagram;
        this.flowUML = flow;
        this.setPreferredSize(new Dimension(200, 100));
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
    private void addPanelBaseFlowUML() {
        this.panelBaseFlowUML = new PanelBaseFlowUML(this.viewMenu, this.diagram, this.flowUML);
        this.createScrollPane("scrollPanelBaseFlowUML",  this.panelBaseFlowUML);
        this.getScrollPanelBaseFlowUML().setViewportView(this.panelBaseFlowUML);
        this.tabbedPane.add("Flow", this.getScrollPanelBaseFlowUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ActivityDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Flow UML.
     * @return Flow UML.
     */
    public FlowUML getFlowUML() {
        return this.flowUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Flow UML.
     * @return Scroll Panel Base Flow UML.
     */
    public JScrollPane getScrollPanelBaseFlowUML() {
        return this.getScrollPane("scrollPanelBaseFlowUML");
    }

    /**
     * Method responsible for returning the Panel Base Flow UML.
     * @return Panel Base Flow UML.
     */
    public PanelBaseFlowUML getPanelBaseFlowUML() {
        return this.panelBaseFlowUML;
    }
}