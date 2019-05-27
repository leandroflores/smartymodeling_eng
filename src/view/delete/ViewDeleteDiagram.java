package view.delete;

import controller.view.delete.ControllerViewDeleteDiagram;
import model.structural.base.Diagram;
import view.ViewStyle;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.delete.ControllerViewDeleteDiagram
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
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Delete Diagram");
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