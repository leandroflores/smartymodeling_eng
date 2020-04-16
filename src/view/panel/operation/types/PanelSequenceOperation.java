package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelSequenceOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelSequenceDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelSequenceOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Sequence Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/04/2019
 * @see    controller.view.panel.operation.types.ControllerPanelSequenceOperation
 * @see    view.panel.operation.PanelOperation
 * @see    view.panel.diagram.types.PanelSequenceDiagram
 */
public final class PanelSequenceOperation extends PanelOperation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public PanelSequenceOperation(PanelSequenceDiagram panel) {
        super(panel);
        this.controller = new ControllerPanelSequenceOperation(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        this.add(this.createButton("lifelineButton", "", "New Actor Lifeline", "diagram/sequence/lifeline.png"));
        this.add(this.createButton("instanceButton", "", "New Class Instance", "diagram/sequence/instance.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {this.getAssociationImage("sequence/message-a"),
                          this.getAssociationImage("sequence/message-s"),
                          this.getAssociationImage("dependency"),
                          this.getAssociationImage("requires"),
                          this.getAssociationImage("mutex")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getLifelineButton().setBackground(this.getDefaultColor());
        this.getInstanceButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public PanelSequenceDiagram getPanelDiagram() {
        return (PanelSequenceDiagram) this.panelDiagram;
    }
    
    /**
     * Method responsible for returning the Lifeline Button.
     * @return Lifeline Button.
     */
    public JButton getLifelineButton() {
        return this.getButton("lifelineButton");
    }
    
    /**
     * Method responsible for returning the Instance Button.
     * @return Instance Button.
     */
    public JButton getInstanceButton() {
        return this.getButton("instanceButton");
    }
}