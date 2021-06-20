package view.modal.edit.diagram.sequence.base;

import controller.view.modal.edit.diagram.sequence.base.ControllerViewEditInstanceUML;
import java.awt.Dimension;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.sequence.base.PanelBaseInstanceUML;
import view.panel.edit.diagram.sequence.base.PanelEditInstanceUML;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditInstanceUML</b>.</p>
 * <p>Class responsible for defining the <b>Instance UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    controller.view.modal.edit.diagram.sequence.base.ControllerViewEditInstanceUML
 * @see    model.structural.diagram.sequence.base.InstanceUML
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditInstanceUML extends ViewEdit {
    private final SequenceDiagram diagram;
    private final InstanceUML instance;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Sequence Diagram.
     * @param instance Instance UML.
     */
    public ViewEditInstanceUML(PanelModeling panel, SequenceDiagram diagram, InstanceUML instance) {
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.instance   = instance;
        this.controller = new ControllerViewEditInstanceUML(this);
        this.title      = "Edit Instance Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 425);
    }
    
    @Override
    protected PanelEditInstanceUML createPanelEdit() {
        return new PanelEditInstanceUML(view, diagram, instance);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditInstanceUML getPanelEdit() {
        return (PanelEditInstanceUML) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Instance UML.
     * @return Panel Base Instance UML.
     */
    public PanelBaseInstanceUML getPanelBaseInstanceUML() {
        return getPanelEdit().getPanelBaseInstanceUML();
    }
    
    /**
     * Method responsible for returning the Instance UML.
     * @return Instance UML.
     */
    public InstanceUML getInstanceUML() {
        return instance;
    }
}