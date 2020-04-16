package view.panel.edit.base.activity;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.DecisionUML;
import view.edit.panel.base.PanelBaseElement;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditDecisionUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Decision UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  19/07/2019
 * @see    model.structural.diagram.activity.base.DecisionUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditDecisionUML extends PanelEditElement {
    private final ActivityDiagram diagram;
    private final DecisionUML decisionUML;
    private PanelBaseElement panelBaseElement;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Activity Diagram.
     * @param decisionUML Decision UML.
     */
    public PanelEditDecisionUML(ViewMenu viewMenu, ActivityDiagram diagram, DecisionUML decisionUML) {
        super(viewMenu, decisionUML);
        this.diagram     = diagram;
        this.decisionUML = decisionUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseElement();
        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Element.
     */
    private void addPanelBaseElement() {
        this.panelBaseElement = new PanelBaseElement(this.viewMenu, this.diagram, this.decisionUML);
        this.createScrollPane("scrollPanelBaseElement",   this.panelBaseElement);
        this.getScrollPanelBaseElement().setViewportView(this.panelBaseElement);
        this.tabbedPane.add("Decision", this.getScrollPanelBaseElement());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ActivityDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Decision UML.
     * @return Decision UML.
     */
    public DecisionUML getDecisionUML() {
        return this.decisionUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Element.
     * @return Scroll Panel Base Element.
     */
    public JScrollPane getScrollPanelBaseElement() {
        return this.getScrollPane("scrollPanelBaseElement");
    }

    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return this.panelBaseElement;
    }
}