package view.panel.edit.diagram.usecase.base;

import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.ActorUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditActorUML</b>.</p> 
 * <p>Class responsible for defining a <b>Actor UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-19
 * @see    model.structural.diagram.usecase.base.ActorUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditActorUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Use Case Diagram.
     * @param actor Actor UML.
     */
    public PanelEditActorUML(ViewMenu view, UseCaseDiagram diagram, ActorUML actor) {
        super(view, diagram, actor);
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addComponents("Actor");
    }
    
    @Override
    public UseCaseDiagram getDiagram() {
        return (UseCaseDiagram) this.diagram;
    }
    
    @Override
    public ActorUML getElement() {
        return (ActorUML) this.element;
    }
}