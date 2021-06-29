package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelSequenceOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelSequenceDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelSequenceOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Sequence Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-04-09
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
        controller = new ControllerPanelSequenceOperation(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        add(createButton("lifeline", "", "New Actor Lifeline", "diagram/sequence/lifeline.png"));
        add(createButton("instance", "", "New Class Instance", "diagram/sequence/instance.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {getAssociationImage("sequence/message-a"),
                          getAssociationImage("sequence/message-s"),
                          getAssociationImage("dependency"),
                          getAssociationImage("requires"),
                          getAssociationImage("mutex")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        getClickButton().setBackground(getDefaultColor());
        getLifelineButton().setBackground(getDefaultColor());
        getInstanceButton().setBackground(getDefaultColor());
    }
    
    @Override
    public PanelSequenceDiagram getPanelDiagram() {
        return (PanelSequenceDiagram) panel;
    }
    
    /**
     * Method responsible for returning the Lifeline Button.
     * @return Lifeline Button.
     */
    public JButton getLifelineButton() {
        return getButton("lifeline");
    }
    
    /**
     * Method responsible for returning the Instance Button.
     * @return Instance Button.
     */
    public JButton getInstanceButton() {
        return getButton("instance");
    }
}