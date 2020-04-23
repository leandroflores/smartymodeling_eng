package view.panel.edit.diagram.usecase.base;

import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditUseCaseUML</b>.</p> 
 * <p>Class responsible for defining a <b>Use Case UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-19
 * @see    model.structural.diagram.usecase.base.UseCaseUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditUseCaseUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Use Case Diagram.
     * @param useCase Use Case UML.
     */
    public PanelEditUseCaseUML(ViewMenu view, UseCaseDiagram diagram, UseCaseUML useCase) {
        super(view, diagram, useCase);
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addComponents("Use Case");
    }
    
    @Override
    public UseCaseDiagram getDiagram() {
        return (UseCaseDiagram) this.diagram;
    }
    
    @Override
    public UseCaseUML getElement() {
        return (UseCaseUML) this.element;
    }
}