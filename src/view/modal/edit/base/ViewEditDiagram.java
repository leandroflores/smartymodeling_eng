package view.modal.edit.base;

import controller.view.modal.edit.base.ControllerViewEditDiagram;
import java.awt.Dimension;
import model.structural.base.Diagram;
import view.modal.edit.ViewEdit;
import view.panel.base.PanelBaseDiagram;
import view.panel.edit.base.PanelEditDiagram;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-29
 * @see    controller.view.modal.edit.base.ControllerViewEditDiagram
 * @see    model.structural.base.Diagram
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditDiagram extends ViewEdit {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     */
    public ViewEditDiagram(PanelModeling panel, Diagram diagram) {
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.controller = new ControllerViewEditDiagram(this);
        this.title      = "Edit Diagram Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 325);
    }
    
    @Override
    protected PanelEditDiagram createPanelEdit() {
        return new PanelEditDiagram(view, diagram);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 200);
    }
    
    @Override
    public PanelEditDiagram getPanelEdit() {
        return (PanelEditDiagram) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Diagram.
     * @return Panel Base Diagram.
     */
    public PanelBaseDiagram getPanelBaseDiagram() {
        return getPanelEdit().getPanelBaseDiagram();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
}