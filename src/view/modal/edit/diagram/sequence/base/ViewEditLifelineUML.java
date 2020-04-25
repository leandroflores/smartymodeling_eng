package view.modal.edit.diagram.sequence.base;

import controller.view.modal.edit.diagram.sequence.base.ControllerViewEditLifelineUML;
import java.awt.Dimension;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.LifelineUML;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.sequence.base.PanelBaseLifelineUML;
import view.panel.edit.diagram.sequence.base.PanelEditLifelineUML;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditLifelineUML</b>.</p>
 * <p>Class responsible for defining the <b>Lifeline UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    controller.view.modal.edit.diagram.sequence.base.ControllerViewEditLifelineUML
 * @see    model.structural.diagram.sequence.base.LifelineUML
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditLifelineUML extends ViewEdit {
    private final SequenceDiagram diagram;
    private final LifelineUML lifeline;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Sequence Diagram.
     * @param lifeline Lifeline UML.
     */
    public ViewEditLifelineUML(PanelModeling panel, SequenceDiagram diagram, LifelineUML lifeline) {
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.lifeline   = lifeline;
        this.controller = new ControllerViewEditLifelineUML(this);
        this.title      = "Edit Lifeline Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(600, 425));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditLifelineUML();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Lifeline UML.
     */
    private void addPanelEditLifelineUML() {
        this.addPanel("panelEditLifeline", new PanelEditLifelineUML(this.view, this.diagram, this.lifeline));
        this.getPanelEditLifeline().setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanelEditLifeline());
    }
    
    /**
     * Method responsible for returning the Panel Edit Lifeline.
     * @return Panel Edit Lifeline.
     */
    private PanelEditLifelineUML getPanelEditLifeline() {
        return (PanelEditLifelineUML) this.getPanel("panelEditLifeline");
    }
    
    /**
     * Method responsible for returning the Panel Base Lifeline UML.
     * @return Panel Base Lifeline UML.
     */
    public PanelBaseLifelineUML getPanelBaseLifelineUML() {
        return this.getPanelEditLifeline().getPanelBaseLifelineUML();
    }
    
    /**
     * Method responsible for returning the Lifeline UML.
     * @return Lifeline UML.
     */
    public LifelineUML getLifelineUML() {
        return this.lifeline;
    }
}