package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import model.structural.base.Element;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociationSequence</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Sequence Diagram Association</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.SequenceDiagram
 * @see    view.panel.diagram.types.PanelSequenceDiagram
 */
public class ControllerEventAssociationSequence extends ControllerEventAssociation {
    private final PanelSequenceDiagram panelDiagram;
    private final SequenceDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerEventAssociationSequence(PanelSequenceDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
        this.diagram      = this.panelDiagram.getDiagram();
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        if (this.check(source, target))
            this.createAssociation(association);
    } 
    
    @Override
    public void createAssociation(mxCell association) {
        switch (this.panelDiagram.getType()) {
            case 0:
            case 1:
                this.addMessageUML(association);
                break;
            case 2:
                this.addDependency(association);
                break;
            case 3:
                this.addRequires(association);
                break;
            case 4:
                this.addMutex(association);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for adding the Message UML.
     * @param association mxCell Association.
     */
    private void addMessageUML(mxCell association) {
        MessageUML messageUML = this.createMessageUML(association);
        if (messageUML != null)
            this.diagram.addMessage(messageUML);
    }
    
    /**
     * Method responsible for returning a new Message UML.
     * @param  association Association.
     * @return Message UML.
     */
    private MessageUML createMessageUML(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        try {
            return new MessageUML(source, target, this.getCategory());
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for returning the Message Category.
     * @return Message Category.
     */
    private String getCategory() {
        if (this.panelDiagram.getAssociationComboBox().getSelectedIndex() == 0)
            return "asynchronous";
        return "synchronous";
    }
}