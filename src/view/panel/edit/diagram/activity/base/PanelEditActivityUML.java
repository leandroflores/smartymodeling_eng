package view.panel.edit.diagram.activity.base;

import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.ActivityUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditActivityUML</b>.</p> 
 * <p>Class responsible for defining a <b>Activity UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-19
 * @see    model.structural.diagram.activity.base.ActivityUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditActivityUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Activity Diagram.
     * @param activity Activity UML.
     */
    public PanelEditActivityUML(ViewMenu view, ActivityDiagram diagram, ActivityUML activity) {
        super(view, diagram, activity);
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        super.addPanels("Activity");
    }
    
    @Override
    public ActivityDiagram getDiagram() {
        return (ActivityDiagram) this.diagram;
    }
    
    @Override
    public ActivityUML getElement() {
        return (ActivityUML) this.element;
    }
}