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
 * @since  2019-07-25
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.SequenceDiagram
 * @see    view.panel.diagram.types.PanelSequenceDiagram
 */
public class ControllerEventAssociationSequence extends ControllerEventAssociation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerEventAssociationSequence(PanelSequenceDiagram panel) {
        super(panel);
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        if (check(source, target))
            createAssociation(association);
    } 
    
    @Override
    public void createAssociation(mxCell association) {
        switch (getPanel().getType()) {
            case 0:
            case 1:
                addMessageUML(association);
                break;
            case 2:
                addDependency(association);
                break;
            case 3:
                addRequires(association);
                break;
            case 4:
                addMutex(association);
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
        MessageUML messageUML = createMessageUML(association);
        if (messageUML != null)
            getDiagram().addMessage(messageUML);
    }
    
    /**
     * Method responsible for returning a new Message UML.
     * @param  association Association.
     * @return Message UML.
     */
    private MessageUML createMessageUML(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        try {
            return new MessageUML(source, target, getCategory());
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for returning the Message Category.
     * @return Message Category.
     */
    private String getCategory() {
        if (getPanel().getPanelOperation().getAssociationComboBox().getSelectedIndex() == 0)
            return "asynchronous";
        return "synchronous";
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) diagram;
    }
    
    @Override
    public PanelSequenceDiagram getPanel() {
        return (PanelSequenceDiagram) panel;
    }
}