package view.delete.base;

import controller.view.delete.base.ControllerViewDeleteDiagram;
import model.structural.base.Diagram;
import view.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.delete.base.ControllerViewDeleteDiagram
 * @see    model.structural.base.Diagram
 * @see    view.delete.ViewDelete
 */
public final class ViewDeleteDiagram extends ViewDelete {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     */
    public ViewDeleteDiagram(PanelModeling panel, Diagram diagram) {
        super(panel);
        this.diagram    = diagram;
        this.controller = new ControllerViewDeleteDiagram(this);
        this.title      = "Delete Diagram";
        this.initComponents();
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.diagram.getName());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
}