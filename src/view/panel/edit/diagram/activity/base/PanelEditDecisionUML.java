package view.panel.edit.diagram.activity.base;

import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.DecisionUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditDecisionUML</b>.</p> 
 * <p>Class responsible for defining a <b>Decision UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-19
 * @see    model.structural.diagram.activity.base.DecisionUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditDecisionUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Activity Diagram.
     * @param decision Decision UML.
     */
    public PanelEditDecisionUML(ViewMenu view, ActivityDiagram diagram, DecisionUML decision) {
        super(view, diagram, decision);
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        super.addPanels("Decision");
    }
    
    @Override
    public ActivityDiagram getDiagram() {
        return (ActivityDiagram) this.diagram;
    }
    
    @Override
    public DecisionUML getElement() {
        return (DecisionUML) this.element;
    }
}